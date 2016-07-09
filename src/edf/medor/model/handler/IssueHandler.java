package edf.medor.model.handler;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edf.medor.bmserv.Server;
import edf.medor.bmserv.Writer;
import edf.medor.model.Issue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "issues")
public class IssueHandler {
	@XmlElement(name = "issue", required=true)
	private static List<Issue> issues = new ArrayList<Issue>();
	public IssueHandler(){	}
	
	public void createIssue(final String type,final String etat,final String emplacement) {
		if(getIssueFromId(Issue.createIssueId(type,emplacement)) == null) {
			issues.add(new Issue(type,etat,emplacement));
			Writer.serializeIssues();
		}
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public Issue getIssueFromId(String issueid) {
		for(Issue t : issues){
			if(t.getId().equals(issueid)) return t;
		}
		return null;
	}
	public Issue getIssueFromNom(String issuenom) {
		for(Issue t : issues){
			if(t.getEmplacement().equals(issuenom)) return t;
		}
		return null;
	}
	public void updateIssueFromId(final String oldissueid, final String newissuefinal, String type,final String etat,final String emplacement){
		Issue t = getIssueFromId(oldissueid);
		t.setEmplacement(emplacement);
		t.setEtat(etat);
		t.setType(type);
		Server.tgh.updateTaggingsWithIssueId(oldissueid, t.getId());
		Writer.serializeIssues();
	}
	
	public void removeIssue(String issueid){
		Server.tgh.removeTaggingsZoneWithIssueId(issueid);
		issues.remove(getIssueFromId(issueid));
		Writer.serializeIssues();
	}
	public void print(){
		System.out.print("=> "+issues.size()+" elements chargés \n");
	}

}
