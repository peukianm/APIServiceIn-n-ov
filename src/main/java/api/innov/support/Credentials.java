package api.innov.support;

import java.io.Serializable;

/**
 * @author Michalis Pefkianakis
 *
 */

public class Credentials implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
    private String APIKEY;
    private String role;
    
    
    
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAPIKEY() {
		return APIKEY;
	}
	public void setAPIKEY(String aPIKEY) {
		APIKEY = aPIKEY;
	}

	@Override
	public String toString() {
		return "Credentials [username=" + username + ", APIKEY=" + APIKEY + "]";
	}
}
