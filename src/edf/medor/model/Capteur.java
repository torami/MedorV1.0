package edf.medor.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Beans Capteur this object represent a sensor like {TERMOSTAT}
 *  @author J60277
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capteur", propOrder = { "id","type","etat","emplacement"})
@XmlRootElement(name = "capteur")
public class Capteur {


	@XmlElement(name = "id")
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
	public Capteur() {	}
	/**
	 * This 's another constructor
	 * @param type
	 * 			Type of sensor
	 * @param etat
	 * 			State or value returned by the sensor
	 * @param emplacement
	 * 			the position of the sensor 
	 * @author J60277
	 */
	public Capteur( final String type,final String etat,final String emplacement) {
		this.id = createcapteurIdFormemplacement(emplacement,type);
		this.emplacement = emplacement;
		this.etat = etat;
		this.type=type;
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
	public void setType(String type) {
		this.type = type;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
		this.id = createcapteurIdFormemplacement(emplacement,this.type);
	}
	public static String createcapteurIdFormemplacement(final String emplacement,String type) {
		String str = "";
		str = emplacement + "@" + type;
		return str;
	}

}
