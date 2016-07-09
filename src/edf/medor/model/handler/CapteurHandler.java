package edf.medor.model.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import edf.medor.bmserv.Server;
import edf.medor.bmserv.Writer;
import edf.medor.model.Capteur;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "capteurs")
public class CapteurHandler {
	@XmlElement(name = "capteur", required=true)
	private static List<Capteur> capteur = new ArrayList<Capteur>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public CapteurHandler() {	}
	
	public void createCapteur(final String type,final String etat,final String emplacement){
		String capteurid = Capteur.createcapteurIdFormemplacement(emplacement,type);
		if(getCapteurFromId(capteurid)==null) {
			capteur.add(new Capteur(type,etat,emplacement));
			Writer.serializCapteurs();
		}
	}
	public Capteur getCapteurFromId(final String captid) {
		for(Capteur cap : capteur){
			if(cap.getId().equals(captid)) return cap;
		}
		return null;
	}

	public void updateCapteurFromId(final String oldcapteurid, final String newcapteurfinal, String type,final String etat,final String emplacement){
		Capteur t = getCapteurFromId(oldcapteurid);
		t.setEmplacement(emplacement);
		t.setEtat(etat);
		t.setType(type);
		Server.tgh.updateTaggingsWithIssueId(oldcapteurid, t.getId());
		Writer.serializeIssues();
	}
	/**
	 * Renvoie tous les {@link Capteur Capteur}
	 * @return une {@link List} de {@link capteur Capteurs}
	 */
	public List<Capteur> getCapteurs() {
		return capteur;
	}
	
	public void removeCapteur(String capteurid){
		Server.tgh.removeTaggingsZoneWithIssueId(capteurid);
		capteur.remove(getCapteurFromId(capteurid));
		Writer.serializeIssues();
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */
	public void print(){
		System.out.print("=> "+capteur.size()+" Elements chargés \n");
	}
}
