package it.rjcsoft.prv.dto.censimentoprotezioni;

public class CensimentoProtezioniFullDTO {

	private Integer id;

	private Integer censimentoAziendaId;

	private ProtezioneContenimentoDTO protezione;

	private String note;

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

	public ProtezioneContenimentoDTO getProtezione() {
		return protezione;
	}

	public void setProtezione(ProtezioneContenimentoDTO protezione) {
		this.protezione = protezione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoProtezioniFullDTO [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", protezione=");
		builder.append(protezione);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
