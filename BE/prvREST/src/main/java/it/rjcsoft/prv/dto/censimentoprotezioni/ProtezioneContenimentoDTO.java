package it.rjcsoft.prv.dto.censimentoprotezioni;

public class ProtezioneContenimentoDTO {
	
	private Integer id;

	private String tipologia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProtezioneContenimentoDTO [id=");
		builder.append(id);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append("]");
		return builder.toString();
	}
	
	
}
