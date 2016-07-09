package edf.medor.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import edf.medor.model.User;

@Path("/user")
public class UserResource {
	@GET
	@Path("/id/{userid}")
	@Produces("text/xml")
	public edf.medor.model.User getUserFromId(@PathParam("userid") String userid) {
		User u = Server.uh.getUserFromId(userid);
		return u;
	}

	@POST
	@Path("/create")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String create(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String login = formParams.getFirst("login");
		String firstname = formParams.getFirst("firstname");
		String password = formParams.getFirst("password");
		String lastname = formParams.getFirst("lastname");
		String message = formParams.getFirst("message");
		String priorite = formParams.getFirst("priorite");
		Server.uh.createUser(login,password, lastname, firstname,message,priorite);
		return TemplateEngine.goHome();
	}	
	@POST
	@Path("/invite")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String invitation(@Context HttpServletRequest req, MultivaluedMap<String, String> formParams){
		TemplateEngine.setSession(req.getSession());
		String Nom = formParams.getFirst("nom");
		String login = formParams.getFirst("login");
		String firstname = "Monsieur";
		String lastname = Nom;
		String message = "";
		String priorite = "-1";
		User uRef = Server.uh.GetRefUser();
		User uSender = Server.uh.getUserFromLogin(login);
		Server.uh.sendMessageToUser(uRef,uSender, Nom);
		Server.uh.createUser(Nom,"invité" ,lastname, firstname,message,priorite);
		Server.uh.sendMessageToUser(uSender,uRef,"Votre demande est accépté");
		
		return TemplateEngine.goHome();
	}

	@POST
	@Path("/login")
	@Produces("text/html")
	@Consumes("application/x-www-form-urlencoded")
	public String login(@Context HttpServletRequest req,MultivaluedMap<String, String> formParams) {
		TemplateEngine.setSession(req.getSession());
		String login = formParams.getFirst("login");
		String password = formParams.getFirst("password");
		User usr = Server.uh.getUserFromLogin(login);
		if(Server.uh.getUserFromLogin(login)!=null && password != null ){
			HttpSession session = req.getSession();
			session.setAttribute("userid", User.createUserId(login));
			session.setAttribute("password", password);

			System.out.println("USER LOGIN : "+login);
		}
		return TemplateEngine.goHome();
	}	


	@GET
	@Path("/logout")
	@Produces("text/html")
	public String logout(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("userid");
		System.out.println("USER LOGOUT : "+userid);
		session.removeAttribute("userid");
		return TemplateEngine.redirect("/", 2, "Vous avez été déconnécté. Vous allez être rediriger dans 2 secondes.");
	}	

	@GET
	@Path("/all/")
	@Produces("text/xml")
	public edf.medor.model.handler.UserHandler getAllUsers() {
		return Server.uh;
	}

	@GET
	@Path("/all/html")
	@Produces("text/html")
	public String getAllUsersHtml(@Context HttpServletRequest req) {
		TemplateEngine.setSession(req.getSession());
		final StringBuilder sb = new StringBuilder();
		User uRef = Server.uh.GetRefUser();
		sb.append("<h2>L'utilisateur Référent est M / Mme "+uRef.getFirstname()+" "+uRef.getLastName()+"</h2>");
		sb.append("<h1>Utilisateurs</h1>\n<table border='1' cellpadding='2' cellspacing='0' style='margin-top:10px'>");
		sb.append("\n<tr style='font-weight:bold; background-color:gray;'><td>Login</td><td>ID</td><td>Firstname Lastname</td><td>Message</td></tr>");			
		List<User> ulist = Server.uh.getUsers();

		for (User u : ulist) {
			
			if (u.getMessage().equals("")){
				sb.append("\n<tr style='background-color=#1560BD;'><td>")
				.append(u.getLogin())
				.append("</td><td>")
				.append(u.getId())
				.append("</td><td>")
				.append(u.getFirstname()).append(" ").append(u.getLastName())
				.append("</td><td>")
				.append(u.getMessage())	
				.append("</td></tr>");
			}	
			else{
			sb.append("\n<tr style='background-color:green;'><td>")
			.append(u.getLogin())
			.append("</td><td>")
			.append(u.getId())
			.append("</td><td>")
			.append(u.getFirstname()).append(" ").append(u.getLastName())
			.append("</td><td>")
			.append(u.getMessage())	
			.append("</td></tr>");}
		}
		sb.append("\n</table>");
		return TemplateEngine.build(sb.toString());
	}

}
