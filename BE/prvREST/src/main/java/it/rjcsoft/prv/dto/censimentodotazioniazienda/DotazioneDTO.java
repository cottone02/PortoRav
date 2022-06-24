package it.rjcsoft.prv.dto.censimentodotazioniazienda;

public class DotazioneDTO {

	private Integer id;

	private String attrezzatura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttrezzatura() {
		return attrezzatura;
	}

	public void setAttrezzatura(String attrezzatura) {
		this.attrezzatura = attrezzatura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DotazioneDTO [id=");
		builder.append(id);
		builder.append(", attrezzatura=");
		builder.append(attrezzatura);
		builder.append("]");
		return builder.toString();
	}

}
