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
import edf.rules.engine.Right;

@Path("/rights")

public class RightRessource {
	@GET
	@Path("/id/{rgid}")
	@Produces("text/xml")
	public edf.rules.engine.Right getRightbyUserId(@PathParam("rgid") String userid) {
		Right r = Server.rg.getRightbyUserid(userid);
		return r;
	}
	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String user = formParams.getFirst("user");
		String zone = formParams.getFirst("zone");
		String priv = formParams.getFirst("priv");
		Server.rg.createRight(user, zone, priv);
		return TemplateEngine.goHome();
	}
	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllActionHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		sb.append("<h1>Right</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold;'><td>ID</td><td>User</td><td>Zone</td><td>Priv</td></tr>");
		List<Right> blist = Server.rg.getRights();
				for (Right b : blist) {
			sb.append("\n<tr><td>")
			.append(b.getId())
			.append("</td><td>")
			.append(b.getUserid())
			.append("</td><td>")
			.append(b.getZoneid())
			.append("</td><td>")
			.append(b.getPriv())
			.append("</td></<tr>");
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}
}
