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
import edf.medor.model.TaggingZone;
import edf.medor.model.Zone;

@Path("/taggings")
/**
 * this class implement CRUD methods for the object TaggingZone
 * @author J60277
 */
public class TaggingResource {
	@GET
	@Path("/id/{znid}")
	@Produces("text/xml")
	public edf.medor.model.TaggingZone getTaggingZoneFromId(@PathParam("znid") String znid) {
		TaggingZone t = Server.tgh.getTaggingFromId(znid);
		return t;
	}

	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String capteurid = formParams.getFirst("capteur");
		String issueid = formParams.getFirst("issue");
		String zonename = formParams.getFirst("emplacement");
		Zone zn = Server.zh.getZoneFromNom(zonename);
		if( !(capteurid.isEmpty()) && !(issueid.isEmpty()))
		{Server.tgh.createTaggingZone(capteurid, issueid,zn.getId());}
		else if(capteurid.isEmpty()){
			Server.tgh.createTaggingZone(null, issueid, zn.getId());
		}
		else if(issueid.isEmpty()){
			Server.tgh.createTaggingZone(capteurid,null,zn.getId());
		}
		return TemplateEngine.goHome();
	}
	@GET
	@Path("/all")
	@Produces("text/xml")	
	public edf.medor.model.handler.TaggingZoneHandler getAllTaggings() {
		return Server.tgh;
	}
	@GET
	@Path("/id/{znid}/html")
	@Produces("text/html")
	public String getCapteurFromIdHtml(@Context HttpServletRequest req, @PathParam("znid") String taggingId) {
		TemplateEngine.setSession(req.getSession());
		TaggingZone t = Server.tgh.getTaggingFromId(taggingId);
		final StringBuilder sb = new StringBuilder();
		sb.append("\n<h1>Etat de l'espace</h1>\n<h2>")
		.append(t.getCapteurid()).append("</h2><ul><h2>")
		.append(t.getIssueid()).append("</h2><ul><h2>")
		.append(t.getZoneid()).append("</h2><ul><h2>");
		return TemplateEngine.build(sb.toString());
	}
	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllCapteursHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Zone</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>Capteur @</td><td>Issue @</td><td>Zone @</td><td>Date @</td></tr>");
		List<TaggingZone> blist = Server.tgh.getTaggings();
		for (TaggingZone b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getCapteurid())
			.append("</td><td>")
			.append(b.getIssueid())
			.append("</td><td>")
			.append(b.getZoneid())
			.append("</td><td>")
			.append(b.getDate())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}

}
