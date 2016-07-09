package edf.rules.engine;

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
		"userid",
		"zoneid",
		"priv"
}
		)
@XmlRootElement(name = "user")
public class Right {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "userid")
	private String userid;
	@XmlElement(name = "zoneid")
	private String zoneid;
	@XmlElement(name = "priv")
	private String priv;
	/**
	 * this 's a default empty constructor for JAXB
	 * @author J60277 
	 */
	public Right() {
	}
	public Right(final String userid,final String zoneid,final String priv) {
		super();
		this.id = createRightId(userid,zoneid);
		this.userid = userid;
		this.zoneid = zoneid;
		this.priv = priv;
	}
	
	public String getId() {
		return id;
	}
	public String getUserid() {
		return userid;
	}
	public String getZoneid() {
		return zoneid;
	}
	public String getPriv() {
		return priv;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}
	public void setPriv(String priv) {
		this.priv = priv;
	}
	public static String createRightId(final String login,final String zone) {
		String str = "";
		str= login+"@"+zone;
		return str;
	}
}
