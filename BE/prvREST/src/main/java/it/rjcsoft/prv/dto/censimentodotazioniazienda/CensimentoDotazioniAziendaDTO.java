package it.rjcsoft.prv.dto.censimentodotazioniazienda;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class CensimentoDotazioniAziendaDTO {

	private DotazioneDTO dotazione;
	
	@NotNull
	@Positive
	private Integer quantita;

	public DotazioneDTO getDotazione() {
		return dotazione;
	}

	public void setDotazione(DotazioneDTO dotazione) {
		this.dotazione = dotazione;
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
		builder.append("CensimentoDotazioniAziendaDTO [dotazione=");
		builder.append(dotazione);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
