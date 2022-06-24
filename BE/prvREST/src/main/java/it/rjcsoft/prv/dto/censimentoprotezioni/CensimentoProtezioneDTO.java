package it.rjcsoft.prv.dto.censimentoprotezioni;

public class CensimentoProtezioneDTO {

	private ProtezioneContenimentoDTO protezione;

	private String note;

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
		builder.append("CensimentoProtezioneDTO [protezione=");
		builder.append(protezione);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
