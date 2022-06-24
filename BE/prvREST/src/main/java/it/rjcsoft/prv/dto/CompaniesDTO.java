package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.Company;

public class CompaniesDTO extends BaseDTO {


	private Integer rowCounter;
	
	private List<Company> companies;

	public Integer getRowCounter() {
		return rowCounter;
	}

	public void setRowCounter(Integer rowCounter) {
		this.rowCounter = rowCounter;
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
		builder.append("CompaniesDTO [rowCounter=");
		builder.append(rowCounter);
		builder.append(", companies=");
		builder.append(companies);
		builder.append("]");
		return builder.toString();
	}
	
}
