package it.rjcsoft.prv.dto.email;

import java.util.List;

import it.rjcsoft.prv.dto.BaseDTO;
import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;

public class EmailDTO extends BaseDTO {

	private List<CompanyEntityDTO> aziende;
	private String object;
	private String body;

	public List<CompanyEntityDTO> getAziende() {
		return aziende;
	}

	public void setAziende(List<CompanyEntityDTO> aziende) {
		this.aziende = aziende;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailDTO [aziende=");
		builder.append(aziende);
		builder.append(", object=");
		builder.append(object);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}

}
