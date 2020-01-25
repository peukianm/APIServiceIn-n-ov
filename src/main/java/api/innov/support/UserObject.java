package api.innov.support;

import java.io.Serializable;

/**
 * @author Michalis Pefkianakis
 *
 */

public class UserObject implements Serializable {
	
	
	private static final long serialVersionUID = -2490510490445782679L;
	private String name;
	private String surname;
	private String username;
	private String address;
	private String id;
	private String role;
	private String apiKey;
	private CompanyObject company;
	private String password;
	
	public UserObject(String name, String surname, String username, String id, String address, String password, String apikey, CompanyObject company, String role) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.id = id;
		this.address = address;
		this.password = password;
		this.apiKey = apikey;
		this.role = role;
		this.company = company;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public CompanyObject getCompany() {
		return company;
	}
	public void setCompany(CompanyObject company) {
		this.company = company;
	}
	

}
