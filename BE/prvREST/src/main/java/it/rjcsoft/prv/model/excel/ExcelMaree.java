package it.rjcsoft.prv.model.excel;

import java.util.Date;

import it.rjcsoft.prv.annotation.ExcelPosition;
import it.rjcsoft.prv.enums.FormatType;

public class ExcelMaree {

	@ExcelPosition(position = 1, formatType = FormatType.DATE, dateFormat = "dd-MMM-yyyy", name = "dataOra")
	private Date dataOra;

	@ExcelPosition(position = 2, formatType = FormatType.DOUBLE, name = "lvlIdrometrico")
	private Double lvlIdrometrico;

	@ExcelPosition(position = 6, formatType = FormatType.INTEGER, name = "numeroPagina")
	private Integer numeroPagina;

	public Date getDataOra() {
		return dataOra;

	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	public Double getLvlIdrometrico() {
		return lvlIdrometrico;
	}

	public void setLvlIdrometrico(Double lvlIdrometrico) {
		this.lvlIdrometrico = lvlIdrometrico;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcelMaree [dataOra=");
		builder.append(dataOra);
		builder.append(", lvlIdrometrico=");
		builder.append(lvlIdrometrico);
		builder.append(", numeroPagina=");
		builder.append(numeroPagina);
		builder.append("]");
		return builder.toString();
	}

}
