package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.Company;

public class CompanyDTO extends BaseDTO{

	private Integer id;
	
	private String name;
	private String vatNumber;
	private String address;
	private String businessName;
	private String city;
	
	private Company company;
	private List<Company> companies;
	
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyDTO [id=");
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
		builder.append(", company=");
		builder.append(company);
		builder.append(", companies=");
		builder.append(companies);
		builder.append("]");
		return builder.toString();
	}
	
}
