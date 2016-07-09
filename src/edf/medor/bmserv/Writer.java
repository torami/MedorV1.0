package edf.medor.bmserv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.JAXB;

import edf.medor.bmserv.Server;





/**
 * Classe chargee de la serialisation des donnees.
 * @author ndelafor
 *
 */
public class Writer {
	private static String taggings_fname;
	private static String actions_fname;
	private static String rights_fname;

	private static String users_fname;
	private static String zone_fname;
	private static String issues_fname;
	private static String capteurs_fname;
	private static String backup_suffix;
	private static String datarep_prefix;
	private static String backuprep_prefix;
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./conf/server.properties"));
			zone_fname = prop.getProperty("zones.filename");
			rights_fname = prop.getProperty("rights.filename");

			users_fname = prop.getProperty("users.filename");
			actions_fname = prop.getProperty("actions.filename");
			taggings_fname = prop.getProperty("taggings.filename");
			issues_fname = prop.getProperty("issues.filename");
			capteurs_fname = prop.getProperty("capteurs.filename");
			backup_suffix = prop.getProperty("backup.suffix");
			datarep_prefix = prop.getProperty("data.repository.prefix");
			backuprep_prefix = prop.getProperty("backup.repository.prefix");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Serialisation de toute la hierarchie d'objets
	 * Les anciens fichiers sont préservés pour une restauration en cas de probleme voir {@link Loader}.
	 * @throws IOException
	 */
	public static void serialize() throws IOException {
		serializeZones();
		serializeRights();

		serializeActions();
		serializeTaggings();
		serializeIssues();
		serializeUsers();
		serializCapteurs();
	}
	public static void serializeActions() {
		File faction = new File(datarep_prefix + actions_fname);		
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		faction.renameTo(new File(backuprep_prefix + actions_fname+backup_suffix));
		try {
			faction.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.th, faction);
		System.out.print("Actions serialises");
		Server.pc.print();
	}
	public static void serializeIssues() {
		File fissues = new File(datarep_prefix + issues_fname);		
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fissues.renameTo(new File(backuprep_prefix + issues_fname+backup_suffix));
		try {
			fissues.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.th, fissues);
		System.out.print("Issues serialises");
		Server.th.print();
	}
	public static void serializeRights() {
		File fissues = new File(datarep_prefix + rights_fname);		
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fissues.renameTo(new File(backuprep_prefix + rights_fname+backup_suffix));
		try {
			fissues.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.rg, fissues);
		System.out.print("right serialises");
		Server.rg.print();
	}
	public static void serializeUsers() {
		File fusers = new File(datarep_prefix + users_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fusers.renameTo(new File(backuprep_prefix + users_fname+backup_suffix));
		try {
			fusers.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.uh, fusers);
		System.out.print("Utilisateurs serialis�s");
		Server.uh.print();		
	}
	public static void serializeZones() {
		File fbm = new File(datarep_prefix + zone_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fbm.renameTo(new File(backuprep_prefix + zone_fname+backup_suffix));
		try {
			fbm.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JAXB.marshal(Server.zh, fbm);
		System.out.print("Zone serialisees");
		Server.zh.print();
	}
	public static void serializeTaggings() {
		File ftg = new File(datarep_prefix + taggings_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		ftg.renameTo(new File(backuprep_prefix + taggings_fname+backup_suffix));
		try {
			ftg.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Serialisation
		JAXB.marshal(Server.tgh, ftg);
		System.out.print("Taggings sérialisés");
		Server.tgh.print();		
	}
	public static void serializCapteurs() {
		File fcp = new File(datarep_prefix + capteurs_fname);
		// On cree une copie de sauvegarde des fichiers precedents en cas de probleme 
		fcp.renameTo(new File(backuprep_prefix + capteurs_fname+backup_suffix));
		try {
			fcp.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JAXB.marshal(Server.cp, fcp);
		System.out.print("Capteurs serialis�");
		Server.cp.print();
	}

}
