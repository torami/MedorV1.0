package edf.medor.resources;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import edf.medor.bmserv.Server;
import edf.medor.bmserv.TemplateEngine;
import edf.medor.model.Issue;
import edf.medor.model.TaggingZone;
@Path("/issues")
public class IssueResource {

	@GET
	@Path("/id/{cpid}")
	@Produces("text/xml")
	public edf.medor.model.Issue getIssueFromId(@PathParam("issueid") String issueid) {
		Issue t = Server.th.getIssueFromId(issueid);
		return t;
	}

	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String type = formParams.getFirst("type");
		String etat = formParams.getFirst("etat");
		String emplacement = formParams.getFirst("emplacement");
		Server.th.createIssue(type, etat, emplacement);
		return TemplateEngine.goHome();
	}

	@GET
	@Path("/all")
	@Produces("text/xml")	
	public edf.medor.model.handler.IssueHandler getAllIssue() {
		return Server.th;
	}

	@GET
	@Path("/id/{cpid}/html")
	@Produces("text/html")
	public String getIssueFromIdHtml(@Context HttpServletRequest req, @PathParam("cpid") String issueid) {
		TemplateEngine.setSession(req.getSession());
		Issue t = Server.th.getIssueFromId(issueid);
		final StringBuilder sb = new StringBuilder();
		sb.append("\n<h1>Liste des ouvrants</h1>\n<h2>")
		.append(t.getEmplacement()).append("</h2><ul><h2>")
		.append(t.getEtat()).append("</h2><ul><h2>")
		.append(t.getType()).append("</h2><ul><h2>");
		List<TaggingZone> tglist = Server.tgh.getTaggingsFromIssueId(issueid);
		for(TaggingZone tg : tglist){
			sb.append("<li>").append(tg.getIssueid()).append("</li>");
		}
		sb.append("</ul>");
		return TemplateEngine.build(sb.toString());
	}

	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllIssuesHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Issues</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Emplacement</td><td>Type</td><td>Etat</td></tr>");
		List<Issue> blist = Server.th.getIssues();
		for (Issue b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getEmplacement())
			.append("</td><td>")
			.append(b.getType())
			.append("</td><td>")
			.append(b.getEtat())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}

}
