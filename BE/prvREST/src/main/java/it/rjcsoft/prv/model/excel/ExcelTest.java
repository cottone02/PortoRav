//package it.rjcsoft.prv.model.excel;
//
//import java.util.Date;
//
//import it.rjcsoft.prv.annotation.ExcelPosition;
//import it.rjcsoft.prv.enums.FormatType;
//
//public class ExcelTest {
//
//	@ExcelPosition(position = 1, formatType = FormatType.TEXT)
//	private String stazione;
//
//	@ExcelPosition(position = 2, formatType = FormatType.TEXT)
//	private String parametro;
//
//	@ExcelPosition(position = 3, formatType = FormatType.DATE, dateFormat = "dd/MM/yyyy")
//	private Date data;
//
//	@ExcelPosition(position = 4, formatType = FormatType.TEXT)
//	private String oraFineMisura;
//
//	@ExcelPosition(position = 5, formatType = FormatType.TEXT)
//	private String mediaOraria;
//
//	@ExcelPosition(position = 6, formatType = FormatType.BOOLEAN)
//	private Boolean validita;
//
//	public String getStazione() {
//		return stazione;
//	}
//
//	public void setStazione(String stazione) {
//		this.stazione = stazione;
//	}
//
//	public String getParametro() {
//		return parametro;
//	}
//
//	public void setParametro(String parametro) {
//		this.parametro = parametro;
//	}
//
//	public Date getData() {
//		return data;
//	}
//
//	public void setData(Date data) {
//		this.data = data;
//	}
//
//	public String getMediaOraria() {
//		return mediaOraria;
//	}
//
//	public void setMediaOraria(String mediaOraria) {
//		this.mediaOraria = mediaOraria;
//	}
//
//	public Boolean getValidita() {
//		return validita;
//	}
//
//	public void setValidita(Boolean validita) {
//		this.validita = validita;
//	}
//
//	public String getOraFineMisura() {
//		return oraFineMisura;
//	}
//
//	public void setOraFineMisura(String oraFineMisura) {
//		this.oraFineMisura = oraFineMisura;
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("ExcelTest [stazione=");
//		builder.append(stazione);
//		builder.append(", parametro=");
//		builder.append(parametro);
//		builder.append(", data=");
//		builder.append(data);
//		builder.append(", oraFineMisura=");
//		builder.append(oraFineMisura);
//		builder.append(", media=");
//		builder.append(mediaOraria);
//		builder.append(", validita=");
//		builder.append(validita);
//		builder.append("]");
//		return builder.toString();
//	}
//
//}
