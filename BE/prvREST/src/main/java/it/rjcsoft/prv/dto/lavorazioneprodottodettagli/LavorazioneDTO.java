package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

public class LavorazioneDTO {
	
	private Integer id;
	
	private String lavorazione;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLavorazione() {
		return lavorazione;
	}

	public void setLavorazione(String lavorazione) {
		this.lavorazione = lavorazione;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LavorazioniDTO [id=");
		builder.append(id);
		builder.append(", lavorazione=");
		builder.append(lavorazione);
		builder.append("]");
		return builder.toString();
	}

}
