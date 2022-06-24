package it.rjcsoft.prv.dto.companyentity;

import java.util.List;

import it.rjcsoft.prv.model.UserEntity;

public class CompanyEntityCompleteDTO {
	
	private Integer id;
	private String name;
	private String vatNumber;
	private String address;
	private String businessName;
	private String city;
	private List<UserEntity> dipendenti;
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
	public List<UserEntity> getDipendenti() {
		return dipendenti;
	}
	public void setDipendenti(List<UserEntity> dipendenti) {
		this.dipendenti = dipendenti;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyEntityCompleteDTO [id=");
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
		builder.append(", dipendenti=");
		builder.append(dipendenti);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
