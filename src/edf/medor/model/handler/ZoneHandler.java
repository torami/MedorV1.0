package edf.medor.model.handler;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import edf.medor.bmserv.Server;
import edf.medor.bmserv.Writer;
import edf.medor.model.Zone;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zones")
public class ZoneHandler {
	@XmlElement(name = "zone", required=true)
	private static List<Zone> zones = new ArrayList<Zone>();
	/**
	 * Constructeur vide pour JAXB
	 */
	public ZoneHandler() {	}
	public void createZone(String nom){
		String zoneid = Zone.createZoneFormName(nom);
		if(getZoneFromId(zoneid)==null) {
			zones.add(new Zone(nom));
			Writer.serializeZones();
		}
	}
	public List<Zone> getZone() {
		return zones;
	}
	public Zone getZoneFromId(final String zoneid) {
		for(Zone zn : zones){
			if(zn.getId().equals(zoneid)) return zn;
		}
		return null;
	}
	public Zone getZoneFromNom(final String zonename) {
		for(Zone zn : zones){
			if(zn.getNom().equals(zonename)) return zn;
		}
		return null;
	}
	public void updateZone(final String nom){
		Zone cpmk = getZoneFromId(nom);
		Server.tgh.updateTaggingsZoneWithCapteurId(nom, cpmk.getId());
		Writer.serializeZones();
	}
	public void removezone(String znid){
		Server.tgh.removeTaggingsZoneWithCapteurId(znid);
		zones.remove(getZoneFromId(znid));
		Writer.serializeZones();
	}
	public void print(){
		System.out.print("=> "+zones.size()+" element chargés \n");
	}
}
