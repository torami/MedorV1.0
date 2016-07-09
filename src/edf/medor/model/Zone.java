package edf.medor.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




/**
 * Bean JAXB this beans implement the model of zone
 * @author J60277
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zone", propOrder = { "id", "nom"})
@XmlRootElement(name = "zone")
public class Zone {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "nom")
	private String nom;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Zone() {	}
	/**
	 * this is another constructor
	 * @param nom
	 * @author J60277
	 */
	public Zone(final String nom) {
		this.id = createZoneFormName(nom);
		this.nom = nom;
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	public String getId() {
		return id;
	}
	public String getNom() {
		return nom;
	}

	public void setId(final String id) {
		this.id = id;
	}
	public void setNom(final String nom) {
		this.nom = nom;
	}
	public static String createZoneFormName(final String nom) {
		String str = "";
		str = nom + "@" + nom;
		return str;
	}

}
