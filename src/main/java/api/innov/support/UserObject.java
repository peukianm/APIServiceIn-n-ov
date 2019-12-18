package api.innov.support;

public class UserObject {
	
	private String name;
	private String surname;
	private String address;
	private String id;
	private String apiKey;
	private CompanyObject company;
	
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
