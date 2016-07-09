package edf.medor.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Beans Issue this object represent both of Window and Door
 *  @author J60277
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issue", propOrder={
		"id",
		"type",
		"etat",
		"emplacement"
	}
)
public class Issue {
	@XmlElement(name = "id", required=true)
	private String id;
	@XmlElement(name = "type")
	private String type;
	@XmlElement(name = "etat")
	private String etat;
	@XmlElement(name = "emplacement")
	private String emplacement;
	/**
	 * this 's a default constructor for JAXB
	 * @author J60277 
	 */
	public Issue() {	}
	/**
	 * This 's another constructor
	 * @param type
	 * 			Type of issue
	 * @param etat
	 * 			State of the door or the window
	 * @param emplacement
	 * 			the position of the door or window
	 * @author J60277
	 */
	public Issue(final String type,final String etat,final String emplacement) {
		this.id = createIssueId(type,emplacement);
		this.type = type;
		this.etat = etat;
		this.emplacement = emplacement;
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getEtat() {
		return etat;
	}
	public String getEmplacement() {
		return emplacement;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setType(final String type) {
		this.type = type;
		this.id = createIssueId(type,this.emplacement);
	}
	public void setEtat(final String etat) {
		this.etat = etat;
	}
	public void setEmplacement(final String emplacement) {
		this.emplacement = emplacement;
		this.id = createIssueId(this.type, emplacement);
	}
	public static String createIssueId(final String type,final String emplacement) {
		String str = "";
		str = type + "@" + emplacement;
		return str;}
}
