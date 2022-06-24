package it.rjcsoft.prv.dto.censimentodotazioniazienda;

public class CensimentoDotazioniDTO {

	private Integer id;

	private Integer censimentoAziendaId;

	private Integer dotazioneId;

	private Integer quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public Integer getDotazioneId() {
		return dotazioneId;
	}

	public void setDotazioneId(Integer dotazioneId) {
		this.dotazioneId = dotazioneId;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoDotazioneDTO [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", dotazioneId=");
		builder.append(dotazioneId);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
