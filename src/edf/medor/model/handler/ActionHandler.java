package edf.medor.model.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edf.medor.bmserv.Writer;
import edf.medor.model.Action;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "actions")
public class ActionHandler {
	@XmlElement(name = "action", required=true)
	private static List<Action> actions = new ArrayList<Action>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public ActionHandler() {	}

	public void createAction(final String userid,final String issue,final String capteur,final String zone,final String nature){
		String Actionid = Action.createAction(userid, nature);
		if(getActionFromId(Actionid)==null) {
			actions.add(new Action(userid, issue, capteur, zone, nature));
	
			Writer.serializeActions();

		}
	}
	public List<Action> getActions() {
		return actions;
	}
	
	public Action getActionFromId(final String actionid) {
		for(Action cap : actions){
			if(cap.getId().equals(actionid)) return cap;
		}
		return null;
	}
	/**
	 * Methode utilitaire pour afficher le nombre d'&eacute;l&eacute;ments contenus dans la liste.
	 */
	public void print(){
		System.out.print("=> "+actions.size()+" Elements chargés \n");
	}
}
