package it.rjcsoft.prv.dto.monitoraggio;

import java.sql.Date;

import it.rjcsoft.prv.dto.BaseDTO;

public class MonitoraggioAriaDTO extends BaseDTO {

	private Integer id;
	private String nomeFile;
	private Integer numeroPagina;
	private Date dataOra;
	private String parametro;
	private Integer mediaOraria;
	private Boolean validita;
	private Integer idMonitoraggio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public Integer getMediaOraria() {
		return mediaOraria;
	}

	public void setMediaOraria(Integer mediaOraria) {
		this.mediaOraria = mediaOraria;
	}

	public Boolean getValidita() {
		return validita;
	}

	public void setValidita(Boolean validita) {
		this.validita = validita;
	}

	public Integer getIdMonitoraggio() {
		return idMonitoraggio;
	}

	public void setIdMonitoraggio(Integer idMonitoraggio) {
		this.idMonitoraggio = idMonitoraggio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitoraggioAriaDTO [id=");
		builder.append(id);
		builder.append(", nomeFile=");
		builder.append(nomeFile);
		builder.append(", numeroPagina=");
		builder.append(numeroPagina);
		builder.append(", dataOra=");
		builder.append(dataOra);
		builder.append(", parametro=");
		builder.append(parametro);
		builder.append(", mediaOraria=");
		builder.append(mediaOraria);
		builder.append(", validita=");
		builder.append(validita);
		builder.append(", idMonitoraggio=");
		builder.append(idMonitoraggio);
		builder.append("]");
		return builder.toString();
	}

}
