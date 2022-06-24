package it.rjcsoft.prv.dto.movimentazione;

import java.sql.Timestamp;

public class DettaglioMovimentazioneUpdateDTO {

	private Integer id;

	private Timestamp dataDettaglio;

	private Double quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDataDettaglio() {
		return dataDettaglio;
	}

	public void setDataDettaglio(Timestamp dataDettaglio) {
		this.dataDettaglio = dataDettaglio;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DettaglioMovimentazioneUpdateDTO [dataDettaglio=");
		builder.append(dataDettaglio);
		builder.append(", id=");
		builder.append(id);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
