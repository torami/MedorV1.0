package edf.rules.engine;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bmserv.model.medor.Actions;
import bmserv.model.medor.TaggingCapteur;
import edf.medor.bmserv.Server;
import edf.medor.bmserv.Writer;
import edf.medor.model.Capteur;
import edf.medor.model.User;
import edf.medor.model.Zone;
import edf.medor.model.handler.UserHandler;

/**
 * Gestion des instances de {@link User}.
 * {@link UserHandler} implemente les methodes CRUD sur les {@link User Users}.
 * @author TORKHANI Rami
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rights")
public class RightHandler {
	@XmlElement(name = "right", required=true)
	private static List<Right> rights = new ArrayList<Right>();
	public RightHandler() {
	}
	public void createRight(final String user,final String zone,final String priv){
		if (isUserExist(user).equals("1") && isZoneExist(zone).equals("1")){
		rights.add(new Right(user,zone,priv));}
		else if(isUserExist(user).equals("0")){
			System.out.println("user dosen't exist");
		}
		Writer.serializeRights();
	}
	public Right getRightFromId(final String rgid) {
		for(Right right : rights){
			if(right.getId().equals(rgid)) return right;
		}
		return null;
	}
	public String isZoneExist(final String Zone){
		List<Zone> zn = Server.zh.getZone();
		for(Zone z : zn){
			if(z.getNom().equals(Zone)) return "1";
		}
		return "0";
	}
	public String isUserExist(final String user){
		List<User> us = Server.uh.getUsers();
		for(User u : us){
			if(u.getId().equals(user)) return "1";
		}
		return "0";
	}
	public List<Right> getListofRightUser(final String user){
		List<Right> glist = null;
		for(Right t : rights){
			if(t.getUserid().equals(user)) 
			glist.add(t);
		}
		return glist;
	}
	public Right getRightbyUserid(final String user) {
		for(Right right : rights){
			if(right.getUserid().equals(user)) return right;
		}
		return null;
	}
	/**
	 * Renvoie tous les {@link Capteur Capteur}
	 * @return une {@link List} de {@link capteur Capteurs}
	 */
	public List<Right> getRights() {
		return rights;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */	
	public void print(){
		System.out.print("=> "+rights.size()+" element chargés \n");
	}
}
