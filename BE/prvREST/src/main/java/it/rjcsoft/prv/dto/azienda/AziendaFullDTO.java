package it.rjcsoft.prv.dto.azienda;

import it.rjcsoft.prv.dto.BaseDTO;

public class AziendaFullDTO extends BaseDTO{
    
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
        StringBuilder sb = new StringBuilder();
        sb.append("AziendaFullDTO{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", vatNumber=").append(vatNumber);
        sb.append(", address=").append(address);
        sb.append(", businessName=").append(businessName);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
	
	
}
