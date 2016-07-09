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

import edf.medor.bmserv.TemplateEngine;
import edf.medor.bmserv.Server;
import edf.medor.model.Capteur;
import edf.medor.model.Issue;
import edf.medor.model.Zone;



@Path("/zone")
public class ZoneResource {
	@GET
	@Path("/id/{zoneid}")
	@Produces("text/xml")
	public edf.medor.model.Zone getCapteurFromId(@PathParam("zoneid") String znid) {
		Zone zn = Server.zh.getZoneFromId(znid);
		return zn;
	}


	@GET
	@Path("/all")
	@Produces("text/xml")
	public edf.medor.model.handler.ZoneHandler getAllCapteur() {
		return Server.zh;
	}
	@GET
	@Path("/id/{zoneid}/html")
	@Produces("text/html")
	public String getCapteurFromIdHtml(@Context HttpServletRequest req, @PathParam("zoneid") String znid) {
		TemplateEngine.setSession(req.getSession());
		Zone zn = Server.zh.getZoneFromId(znid);
		final StringBuilder sb = new StringBuilder();
		sb.append("\n<h1>Informations Sur l'espace </h1>\n<h2>");
		sb.append(zn.getNom())
		.append("</a></p>");
		return TemplateEngine.build(sb.toString());
	}	
	
	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String Nom = formParams.getFirst("nom");
		Server.zh.createZone(Nom);
		return TemplateEngine.goHome();
	}

	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllCapteursHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Zone</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Nom</td><td>Capteurs</td><td>Issues</td></tr>");
		List<Zone> blist = Server.zh.getZone();
		
		for (Zone b : blist) {
			List<Issue> i = Server.tgh.getGetIssueByZoneid(b.getId());
			List<Capteur> o = Server.tgh.getGetCapteurByZoneid(b.getId());

			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getNom())
			.append("</td><td>")
			.append(o.size())
			.append("</td><td>")
			.append(i.size())
			.append("</td></tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}		
}
