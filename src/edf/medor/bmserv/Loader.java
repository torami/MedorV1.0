package edf.medor.bmserv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import edf.medor.model.handler.UserHandler;
import edf.medor.bmserv.Server;
import edf.medor.exceptions.LoadFileException;
import edf.medor.exceptions.NoBackupFileException;
import edf.medor.model.handler.ActionHandler;
import edf.medor.model.handler.CapteurHandler;
import edf.medor.model.handler.IssueHandler;
import edf.medor.model.handler.TaggingZoneHandler;
import edf.medor.model.handler.ZoneHandler;
import edf.rules.engine.RightHandler;

public class Loader {
	private static String users_fname;
	private static String rights_fname;
	private static String actions_fname;
	private static String zones_fname;
	private static String issues_fname;
	private static String taggings_fname;
	private static String capteurs_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.properties"));
			users_fname = prop.getProperty("users.filename");
			rights_fname = prop.getProperty("rights.filename");
			actions_fname = prop.getProperty("actions.filename");
			zones_fname = prop.getProperty("zones.filename");
			issues_fname = prop.getProperty("issues.filename");
			capteurs_fname = prop.getProperty("capteurs.filename");
			taggings_fname = prop.getProperty("taggings.filename");
			backup_suffix = prop.getProperty("backup.suffix");
			datarep_prefix = prop.getProperty("data.repository.prefix");
			backuprep_prefix = prop.getProperty("backup.repository.prefix");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void load() {
		try {
			loadUsers();
			loadZones();
			loadTaggings();
			loadIssues();
			loadCapteurs();
			loadActions();
			loadRights();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Un probleme est survenu pendant le chargement des donnees. \nLes fichiers de sauvegarde ont ete restaurees.");
//			restore();
		}}
		/**
		 * Restauration des fichiers de backup en cas de probleme (corruption de
		 * fichier etc)
		 */
		public static void restore() {
			try {
				restoreUsers();
				restoreRights();
				restoreZones();
				restoreTaggings();
				restoreIssues();
				restoreCapteurs();
				restoreActions();
				load();
			} catch (NoBackupFileException e) {
				e.printStackTrace();
			}
		}
		public static void loadUsers() throws LoadFileException {
			try {
				File fusers = new File(datarep_prefix + users_fname);
				Server.uh = JAXB.unmarshal(fusers, UserHandler.class);
				System.out.print(fusers.getAbsolutePath());
				Server.uh.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des utilisateurs");
			}
		}
		public static void loadRights() throws LoadFileException {
			try {
				File fusers = new File(datarep_prefix + rights_fname);
				Server.rg =  JAXB.unmarshal(fusers, RightHandler.class);
				System.out.print(fusers.getAbsolutePath());
				Server.rg.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des rights");
			}
		}
		public static void loadActions() throws LoadFileException {
			try {
				File factions = new File(datarep_prefix + actions_fname);
				Server.pc = JAXB.unmarshal(factions,ActionHandler.class);
				System.out.print(factions.getAbsolutePath());
				Server.pc.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des actions");
			}
		}
		public static void loadIssues() throws LoadFileException {
			try {
				File fissues = new File(datarep_prefix + issues_fname);
				Server.th = JAXB.unmarshal(fissues, IssueHandler.class);;
				System.out.print(fissues.getAbsolutePath());
				Server.th.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des tags");
			}
		}
		public static void loadCapteurs() throws LoadFileException {
			try {
				File fcp = new File(datarep_prefix + capteurs_fname);
				Server.cp =  JAXB.unmarshal(fcp, CapteurHandler.class);
				System.out.print(fcp.getAbsolutePath());
				Server.cp.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des capteurs");
			}
		}
		public static void loadZones() throws LoadFileException {
			try {
				File fzones = new File(datarep_prefix + zones_fname);
				Server.zh = JAXB.unmarshal(fzones, ZoneHandler.class);
				System.out.print(fzones.getAbsolutePath());
				Server.zh.print();
			} catch (DataBindingException e) {
				throw new LoadFileException("Erreur au chargement du fichier des zones");
			}
		}
		public static void loadTaggings() throws LoadFileException {
			try {
				File ftg = new File(datarep_prefix + taggings_fname);
				Server.tgh = JAXB.unmarshal(ftg, TaggingZoneHandler.class);
				System.out.print(ftg.getAbsolutePath());
				Server.tgh.print();
			} catch (DataBindingException e) {
				throw new LoadFileException(
						"Erreur au chargement du fichier des taggings");
			}
		}
		public static void restoreZones() throws NoBackupFileException {
			File fzones = new File(backuprep_prefix + zones_fname + backup_suffix);
			if (fzones.exists())
				fzones.renameTo(new File(datarep_prefix + zones_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a ete trouvé pour les zones");
		}
		public static void restoreTaggings() throws NoBackupFileException {
			File fusers = new File(backuprep_prefix + taggings_fname
					+ backup_suffix);
			if (fusers.exists())
				fusers.renameTo(new File(datarep_prefix + taggings_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a été trouvé pour les taggings");
		}
		public static void restoreRights() throws NoBackupFileException {
			File fusers = new File(backuprep_prefix + rights_fname
					+ backup_suffix);
			if (fusers.exists())
				fusers.renameTo(new File(datarep_prefix + rights_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a été trouvé pour les rights");
		}
		public static void restoreIssues() throws NoBackupFileException {
			File fissues = new File(backuprep_prefix + issues_fname + backup_suffix);
			if (fissues.exists())
				fissues.renameTo(new File(datarep_prefix + issues_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a été trouvé pour les issues");
		}
		public static void restoreCapteurs() throws NoBackupFileException {
			File fcapteur = new File(backuprep_prefix + capteurs_fname
					+ backup_suffix);
			if (fcapteur.exists())
				fcapteur.renameTo(new File(datarep_prefix + capteurs_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a ete trouvé pour les capteurs");
		}
		public static void restoreActions() throws NoBackupFileException {
			File faction = new File(backuprep_prefix + actions_fname
					+ backup_suffix);
			if (faction.exists())
				faction.renameTo(new File(datarep_prefix + actions_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a ete trouvé pour les actions");
		}
		public static void restoreUsers() throws NoBackupFileException {
			File fusers = new File(backuprep_prefix + users_fname + backup_suffix);
			if (fusers.exists())
				fusers.renameTo(new File(datarep_prefix + users_fname));
			else
				throw new NoBackupFileException(
						"Aucun fichier de sauvegarde pour n'a Ã©tÃ© trouvÃ© pour les utilisateurs");
		}


}
