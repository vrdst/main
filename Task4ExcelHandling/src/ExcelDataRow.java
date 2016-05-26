import java.io.Serializable;

public class ExcelDataRow implements Serializable{
	/**
	 * serialization
	 */
	private static final long serialVersionUID = -2922912110751323393L;
	
	private String name = null;
	private String surName = null;
	private String Address = null;
	private String city = null;
	private String country = null;
	
	/////////////////////
	//getters + setters//
	/////////////////////
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	
}
