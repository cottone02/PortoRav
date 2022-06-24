package it.rjcsoft.prv.dto;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

public class CensimentoDotazioniAziendaDTO {

	@NotNull
	private Integer censimentoAziendaId;

	private DotazioneDTO dotazione;
	
	@NotNull
	@Positive
	private Integer quantita;

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

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
		builder.append("CensimentoDotazioniAziendaDTO [censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", dotazione=");
		builder.append(dotazione);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
