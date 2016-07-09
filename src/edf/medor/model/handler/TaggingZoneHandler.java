package edf.medor.model.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edf.medor.bmserv.Server;
import edf.medor.bmserv.Writer;
import edf.medor.model.Capteur;
import edf.medor.model.Issue;
import edf.medor.model.TaggingZone;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "taggingzones")
public class TaggingZoneHandler {
	@XmlElement(name = "taggingzone", required=true)
	private static List<TaggingZone> taggings = new ArrayList<TaggingZone>();
	//Constructeur non param JAXB
	public TaggingZoneHandler() {}
	public List<TaggingZone> getTaggings() {
		return taggings;
	}
	public final TaggingZone getTaggingFromId(final String taggingId) {
		for(TaggingZone t : taggings){
			if(t.getId().equals(taggingId)) return t;
		}
		return null;
	}
	public final List<Issue> getGetIssueByZoneid(final String zoneid) {
		List<Issue> tlist = new ArrayList<Issue>();

		for(TaggingZone t : taggings){
			if(t.getZoneid().equals(zoneid) && !(t.getIssueid() == null)) {
				tlist.add(Server.th.getIssueFromId(t.getIssueid()));
			}
		}
		return tlist;
	}
	public final List<Capteur> getGetCapteurByZoneid(final String zoneid) {
		List<Capteur> tlist = new ArrayList<Capteur>();

		for(TaggingZone t : taggings){
			if(t.getZoneid().equals(zoneid) &&  !(t.getCapteurid() == null ) ) {
				tlist.add(Server.cp.getCapteurFromId(zoneid));
			}
		}
		return tlist;
	}
	public final List<TaggingZone> getTaggingsFromCapteurId(final String capteurid) {
		List<TaggingZone> tlist = new ArrayList<TaggingZone>();
		for(TaggingZone t : taggings){
			if(t.getCapteurid().equals(capteurid)) tlist.add(t);
		}		
		return tlist;
	}
	public final List<TaggingZone> getTaggingsFromIssueId(final String issueid) {
		List<TaggingZone> tlist = new ArrayList<TaggingZone>();
		for(TaggingZone t : taggings){
			if(t.getId().equals(issueid)) tlist.add(t);
		}		
		return tlist;
	}
	public final List<TaggingZone> getTaggingsFromZoneId(final String zoneid) {
		List<TaggingZone> tlist = new ArrayList<TaggingZone>();
		for(TaggingZone t : taggings){
			if(t.getZoneid().equals(zoneid)) tlist.add(t);
		}		
		return tlist;
	}
	public void createTaggingZone( final String capteurid,final String issueid, final String zonneid){
		GregorianCalendar dateGreg = new GregorianCalendar();
		java.util.Date dateDate = dateGreg.getTime();		 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String datestr = dateFormat.format(dateDate);
		taggings.add(new TaggingZone(capteurid,issueid,zonneid,datestr));
		Writer.serializeTaggings();
	}
	public void removeTaggingZone(final TaggingZone t){
		taggings.remove(t);
	}
	public final void removeTaggingsZoneWithCapteurId(final String capteurid){
		List<TaggingZone> glist = getTaggingsFromCapteurId(capteurid);
		for(TaggingZone t : glist){
			taggings.remove(t);
		}
		Writer.serializeTaggings();
	}
	public final void removeTaggingsZoneWithIssueId(final String issueid){
		List<TaggingZone> glist = getTaggingsFromIssueId(issueid);
		for(TaggingZone t : glist){
			taggings.remove(t);
		}
		Writer.serializeTaggings();
	}
	public final void removeTaggingsCapteurWithUserId(final String znid){
		List<TaggingZone> glist = getTaggingsFromZoneId(znid);
		for(TaggingZone t : glist){
			taggings.remove(t);
		}
		Writer.serializeTaggings();
	}
	public final void updateTaggingsZoneWithCapteurId(final String oldcapid, final String newcapid){
		List<TaggingZone> glist = getTaggingsFromCapteurId(oldcapid);
		for(TaggingZone t : glist){
			t.setCapteurid(newcapid);
		}
		Writer.serializeTaggings();
	}
	public final void updateTaggingsWithIssueId(final String oldissueid, final String newissueid){
		List<TaggingZone> glist = getTaggingsFromIssueId(oldissueid);
		for(TaggingZone t : glist){
			t.setIssueid(newissueid);
		}
		Writer.serializeTaggings();
	}
	public void print(){
		System.out.print("=> "+taggings.size()+" Elements chargés \n");
	}	
}
