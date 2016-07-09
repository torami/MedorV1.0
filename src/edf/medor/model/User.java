package edf.medor.model;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Bean JAXB this beans implement the model of user
 * @author J60277
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="user", propOrder={
		"id",
		"login",
		"password",
		"lastname",
		"firstname",
		"message",
		"priorite"
}
		)
@XmlRootElement(name = "user")
public class User {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "lastname")
	private String lastname;
	@XmlElement(name = "firstname")
	private String firstname;
	@XmlElement(name = "login")
	private String login;
	@XmlElement(name = "password")
	private String password;
	@XmlElement(name = "message")
	private String message;
	@XmlElement(name = "priorite")
	private String priorite;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public User() {
	}
	/**
	 * this is another constructor
	 * @param login	
	 * 			the user is login
	 * @param lastname
	 * 			the user is last name
	 * @param firstname
	 * 			the user is first name
	 * @param message
	 * 			the user is last received message
	 * @param priorite
	 * 			this attribute contribute in the designation of the super user
	 * @throws UnsupportedEncodingException
	 */
	public User(final String login,final String user, final String lastname, final String firstname,final String message,final String priorite) throws UnsupportedEncodingException {
		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.message = message;
		this.priorite = priorite;
		this.id = createUserId(login);
	}
	/**
	 * Some Getters & Setters Tools
	 * @author J60277
	 */
	public String getMessage() {
		return message;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPriorite() {
		return priorite;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login){
		this.login = login;
		this.id = createUserId(login);
	}
	public void setLastName(final String lastname){
		this.lastname = lastname;
	}
	public void setFirstName(final String firstname){
		this.firstname = firstname;
	}


	public void setMessage(final String message) {
		this.message = message;
	}

	public void setPriorite( final String priorite) {
		this.priorite = priorite;
	}

	public static String createUserId(final String login) {
		String str = "";
		try {
			str = URLEncoder.encode(login, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
