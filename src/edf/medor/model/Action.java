package edf.medor.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Beans Action this object represent a User Action like {ENTER/LEFT}
 *  @author J60277
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action", propOrder={
		"id",
		"userid",
		"issueid",
		"capteurid",
		"zoneid",
		"nature"		
	}
)
@XmlRootElement(name = "action")
public class Action {
	
	@XmlElement(name = "id", required=true)
	private String id;
	@XmlElement(name = "userid")
	private String userid;
	@XmlElement(name = "issueid")
	private String issueid;
	@XmlElement(name = "capteurid")
	private String capteurid;
	@XmlElement(name = "zoneid")
	private String zoneid;
	@XmlElement(name = "nature")
	private String nature;
	/**
	 * this 's a default constructor for JAXB
	 * @author J60277 
	 */
	public Action() {	}
	
	/**
	 * This 's another constructor
	 * @param userid
	 * 		habitant id
	 * @param issue
	 * 		this parameter represent the id of a window or a door    
	 * @param capteur
	 * 		this parameter represent a sensor id 
	 * @param zone
	 * 		this attribute represent the area of the action
	 * @param nature
	 * 		represent the kind of the operation E/S
	 * 
	 * @author J60277
	 */
	public Action( final String userid,final String issue,final String capteur,final String zone,final String nature) {
		this.id = createAction(userid,nature);
		this.userid= userid;
		this.issueid = issue;
		this.capteurid=capteur;
		this.zoneid=zone;
		this.nature=nature;
	}
	
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	public String getId() {
		return id;
	}
	public String getUser() {
		return userid;
	}
	public String getIssue() {
		return issueid;
	}
	public String getCapteurid() {
		return capteurid;
	}
	public String getZone() {
		return zoneid;
	}
	public String getNature() {
		return nature;
	}
	
	public void setId(final String id) {
		this.id = id;
	}
	public void setUser(final String user) {
		this.userid = user;
	}
	public void setIssue(final String issue) {
		this.issueid = issue;
	}
	public void setCapteurid(final String capteurid) {
		this.capteurid = capteurid;
	}
	public void setZone(final String zone) {
		this.zoneid = zone;
	}
	public void setNature(final String nature) {
		this.nature = nature;
	}
	public static String createAction(final String acteur,final String nature) {
		String str = "";
		str = acteur + "@" + nature;
		return str;
	}
	
}
