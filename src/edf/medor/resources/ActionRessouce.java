package edf.medor.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import edf.medor.model.Action;
import edf.rules.engine.Right;


@Path("/actions")
public class ActionRessouce {
	@GET
	@Path("/id/{acid}")
	@Produces("text/xml")
	public edf.medor.model.Action getActionFromId(@PathParam("acid") String acid) {
		Action t = Server.pc.getActionFromId(acid);
		return t;
	}
	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams) {
		TemplateEngine.setSession(req.getSession());
		String user = formParams.getFirst("user");
		String zone = formParams.getFirst("zone");
		String capteur = formParams.getFirst("capteur");
		String issue = formParams.getFirst("issue");
		String nature = formParams.getFirst("nature");
		List<Right> r = Server.rg.getListofRightUser(user);
		boolean boucle = false;
		if( nature.equals("O")){
			for(Right t : r){
			String right = t.getPriv();
			if(right.equals("ES") || right.equals("E")){
				
				Server.cp.getCapteurFromId(capteur).setEtat("1");
				Server.th.getIssueFromId(issue).setEtat("O");
				Server.pc.createAction(user,zone, capteur, issue,nature);
				break;
			}else {
				final StringBuilder sb = new StringBuilder();
				sb.append("<script>alert(\"you dont have enough right\")");
			}}}
			else {if( nature.equals("F")){
				for(Right t : r && boucle ){
					String right = t.getPriv();

				if(right.equals("ES") || right.equals("S")){
				Server.th.getIssueFromId(issue).setEtat("F");
				Server.cp.getCapteurFromId(capteur).setEtat("0");}
				Server.pc.createAction(user,zone, capteur, issue,nature);
				break;

			}}}
			return TemplateEngine.goHome();
		}

	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllActionHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Action</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>User</td><td>Issue</td><td>Zone</td><td>nature</td></tr>");
		List<Action> blist = Server.pc.getActions();
				for (Action b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getUser())
			.append("</td><td>")
			.append(b.getIssue())
			.append("</td><td>")
			.append(b.getZone())
			.append("</td><td>")
			.append(b.getNature())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}
}
