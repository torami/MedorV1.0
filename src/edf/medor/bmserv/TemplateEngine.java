package edf.medor.bmserv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import edf.medor.model.User;

public class TemplateEngine {
	private static HttpSession session;
	public static String build(final String pagebody){
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("./conf/server.properties"));
			String resdir = prop.getProperty("resources.repository.prefix");
			
			StringBuilder builder=new StringBuilder();
			builder.append(Utils.readFileAsString(resdir+"header.html"));
			builder.append(buildNavBar());
			builder.append(pagebody);
			builder.append(Utils.readFileAsString(resdir+"footer.html"));
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String buildFromFile(final String filepath){
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("./conf/server.properties"));
			String resdir = prop.getProperty("resources.repository.prefix");

			StringBuilder builder=new StringBuilder();			
			builder.append(Utils.readFileAsString(resdir+"header.html"));
			builder.append(buildNavBar());
			builder.append(Utils.readFileAsString(resdir+filepath));
			builder.append(Utils.readFileAsString(resdir+"footer.html"));
			return builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String goHome(){
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("./conf/server.properties"));
			String resdir = prop.getProperty("resources.repository.prefix");

			return Utils.readFileAsString(resdir+"redirect.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFile(final String filename){
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("./conf/server.properties"));
			String resdir = prop.getProperty("resources.repository.prefix");
			try {
				return Utils.readFileAsString(resdir+filename);
			} catch (Exception e) {
				return Utils.readFileAsString(resdir+"404.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String redirect(final String urldest, final int timeout, final String msg){
		StringBuilder builder = new StringBuilder();
		
		builder.append("<?xml version='1.0' encoding='UTF-8'?>");
		builder.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
		builder.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
		builder.append("<head><meta http-equiv='content-type' content='text/html; charset=UTF-8' />");
		builder.append("<meta http-equiv='refresh' content='").append(timeout).append("; url=").append(urldest).append("' />");
		builder.append("<title>Redirection</title>");
		builder.append("<meta name='robots' content='noindex,follow' />");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("<p>").append(StringEscapeUtils.escapeHtml(msg)).append("</p>");
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}	
	
	private static String buildNavBar(){
		StringBuilder builder=new StringBuilder();
		
		if(session.getAttribute("userid")!=null){
			String userid = (String) session.getAttribute("userid");
			User u = Server.uh.getUserFromId(userid);
			builder.append("<div id='navbar'>")
				.append("Vous &ecirc;tes connect&eacute; en tant que : ")
				.append(u.getFirstname())
				.append("&nbsp;")
				.append(u.getLastName())
				.append("&nbsp;&bull;&nbsp;")
				.append("<a href='/user/logout'>Logout</a>")
				.append("&nbsp;&bull;&nbsp;")
				.append("<a href='/'>Home</a>")
				.append("</div>");
		}
		return builder.toString();
	}
	
	public static void setSession(HttpSession currsession){
		session = currsession;
	}
	
	public static HttpSession getSession(){
		return session;
	}
}
