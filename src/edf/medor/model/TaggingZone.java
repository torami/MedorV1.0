package edf.medor.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Beans TaggingZone this object allow the application to attach the sensor to the appropriate zone
 *  @author J60277
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="taggingzone", propOrder={
		"id",
		"capteurid",
		"issueid",
		"zoneid",
		"date"
}
		)
public class TaggingZone {

	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "capteurid")
	private String capteurid;
	@XmlElement(name = "issueid")
	private String issueid;
	@XmlElement(name = "zoneid")
	private String zoneid;
	@XmlElement(name = "date")
	private String date;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public TaggingZone() {}
	/**
	 * this is another constructor
	 * @param capteurid
	 * 					the id of the sensor
	 * @param issueid
	 * 					the id of the issue (window/door)
	 * @param zoneid
	 * 					the id of the container 
	 * @param date
	 * 					the affectation date 
	 */
	public TaggingZone(final String capteurid,final String issueid,final String zoneid,final String date) {
		this.capteurid = capteurid;
		this.issueid = issueid ;
		this.zoneid = zoneid;
		this.date = date;
		this.id = createTaggingZoneId(zoneid,capteurid,issueid);
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	public String getId() {
		return id;
	}
	public String getCapteurid() {
		return capteurid;
	}
	public String getIssueid() {
		return issueid;
	}
	public String getZoneid() {
		return zoneid;
	}
	public String getDate() {
		return date;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setCapteurid(String capteurid) {
		this.capteurid = capteurid;
		this.id = createTaggingZoneId(this.capteurid, this.capteurid,this.issueid);
	}
	public void setIssueid(String issueid) {
		this.issueid = issueid;
		this.id = createTaggingZoneId(this.zoneid,this.capteurid,this.issueid);
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static String createTaggingZoneId(final String zoneid, final String capteurid,final String issueid) {
		return zoneid + "@" + capteurid+"@"+issueid;
	}
}
