package it.rjcsoft.prv.model;

public class Company {

	private Integer id;
	
	private String name;
	private String vatNumber;
	private String address;
	private String businessName;
	private String city;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVatNumber() {
		return vatNumber;
	}
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", vatNumber=");
		builder.append(vatNumber);
		builder.append(", address=");
		builder.append(address);
		builder.append(", businessName=");
		builder.append(businessName);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

	
}