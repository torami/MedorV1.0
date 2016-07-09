
package edf.medor.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import edf.medor.bmserv.TemplateEngine;

@Path("/")
public class NavResource {
	@GET
	@Produces("text/html")
	public String getHome(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		TemplateEngine.setSession(req.getSession());
		
		if(session.getAttribute("userid")!=null ){
			String userid = (String) session.getAttribute("userid");
			System.out.println("Vous êtes identifier en tant que :"+userid);
			return TemplateEngine.buildFromFile("home.html");
		}else{
			return TemplateEngine.buildFromFile("login.html");
		}
	}
	@GET
	@Path("/{filename}")
	public String getFile(@PathParam("filename") String filename) {
		return TemplateEngine.getFile(filename);
	}
	
	@GET
	@Produces("text/html")
	@Path("/form/login")
	public String getLogin(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("login.html");
	}
	
	@GET
	@Produces("text/html")
	@Path("/form/user/add")
	public String getUserForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("adduser.html");
	}

	@GET
	@Produces("text/html")
	@Path("/form/issue/add")
	public String getActionForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addissue.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/capteur/add")
	public String getCapteurForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addcapteur.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/taggings/add")
	public String gettaggingcapteursForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addtaggingzone.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/invitation/add")
	public String getInvitationForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addinvitation.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/action/add")
	public String getActionsForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addaction.html");
	}
	@GET
	@Produces("text/html")
	@Path("/form/rights/add")
	public String getRightsForm(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		return TemplateEngine.buildFromFile("addrights.html");
	}

}
