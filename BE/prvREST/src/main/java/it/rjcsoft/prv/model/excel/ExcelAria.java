package it.rjcsoft.prv.model.excel;

import java.util.Date;

import it.rjcsoft.prv.annotation.ExcelPosition;
import it.rjcsoft.prv.enums.FormatType;

public class ExcelAria {

	@ExcelPosition(position = 1, formatType = FormatType.TEXT, name = "stazione")
	private String stazione;

	@ExcelPosition(position = 2, formatType = FormatType.TEXT, name = "parametro")
	private String parametro;

	@ExcelPosition(position = 3, formatType = FormatType.DATE, dateFormat = "dd-MMM-yyyy", name = "data")
	private Date data;

	@ExcelPosition(position = 4, formatType = FormatType.FLOAT, name = "mediaOraria")
	private Float mediaOraria;

	@ExcelPosition(position = 5, formatType = FormatType.BOOLEAN, name = "validita")
	private Boolean validita;
	
	@ExcelPosition(position = 6, formatType = FormatType.INTEGER, name = "numeroPagina")
	private Integer numeroPagina;
	

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getStazione() {
		return stazione;
	}

	public void setStazione(String stazione) {
		this.stazione = stazione;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Float getMediaOraria() {
		return mediaOraria;
	}

	public void setMediaOraria(Float mediaOraria) {
		this.mediaOraria = mediaOraria;
	}

	public Boolean getValidita() {
		return validita;
	}

	public void setValidita(Boolean validita) {
		this.validita = validita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcelAria [stazione=");
		builder.append(stazione);
		builder.append(", parametro=");
		builder.append(parametro);
		builder.append(", data=");
		builder.append(data);
		builder.append(", mediaOraria=");
		builder.append(mediaOraria);
		builder.append(", validita=");
		builder.append(validita);
		builder.append(", nomeFoglio=");
		builder.append(numeroPagina);
		builder.append("]");
		return builder.toString();
	}

	


}
