package it.rjcsoft.prv.dto.monitoraggio;

import java.sql.Date;

import it.rjcsoft.prv.dto.BaseDTO;

public class MonitoraggioMareeDTO extends BaseDTO {
	private Integer id;
	private String nomeFile;
	private Integer numeroPagina;
	private Date dataOra;
	private Float livelloIdrometrico;
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

	public Float getLivelloIdrometrico() {
		return livelloIdrometrico;
	}

	public void setLivelloIdrometrico(Float livelloIdrometrico) {
		this.livelloIdrometrico = livelloIdrometrico;
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
		builder.append("MonitoraggioMareeDTO [id=");
		builder.append(id);
		builder.append(", nomeFile=");
		builder.append(nomeFile);
		builder.append(", numeroPagina=");
		builder.append(numeroPagina);
		builder.append(", dataOra=");
		builder.append(dataOra);
		builder.append(", livelloIdrometrico=");
		builder.append(livelloIdrometrico);
		builder.append(", idMonitoraggio=");
		builder.append(idMonitoraggio);
		builder.append("]");
		return builder.toString();
	}

}
