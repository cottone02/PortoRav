package it.rjcsoft.prv.model;

 import java.sql.Date;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;

 @Entity(name = "monitoraggi_aria")
 @Table(name = "monitoraggi_aria")
 public class MonitoraggioAria {

 	@Id
 	@Column(name = "id", nullable = false)
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@SequenceGenerator(sequenceName = "monitoraggi_aria_id_seq", allocationSize = 1, name = "monitoraggi_aria_id_seq")
 	private Integer id;
 	@Column(name = "nome_file", nullable = false)
 	private String nomeFile;

 	@Column(name = "numero_pagina", nullable = false)
 	private Integer numeroPagina;
	
 	@Column(name = "stazione", nullable = false)
 	private String stazione;

 	@Column(name = "data")
 	private Date data;

 	@Column(name = "parametro", nullable = false)
 	private String parametro;

 	@Column(name = "media_oraria", nullable = false)
 	private Float mediaOraria;

 	@Column(name = "validita", nullable = false)
 	private Boolean validita;

 	@Column(name = "monitoring_files", nullable = false)
 	private Integer idMonitoraggio;

 	public Integer getId() {
 		return id;
 	}

 	public String getStazione() {
 		return stazione;
 	}

 	public void setStazione(String stazione) {
 		this.stazione = stazione;
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

 	public Date getData() {
 		return data;
 	}

 	public void setData(Date dataOra) {
 		this.data = dataOra;
 	}

 	public String getParametro() {
 		return parametro;
 	}

 	public void setParametro(String parametro) {
 		this.parametro = parametro;
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

 	public Integer getIdMonitoraggio() {
 		return idMonitoraggio;
 	}

 	public void setIdMonitoraggio(Integer idMonitoraggio) {
 		this.idMonitoraggio = idMonitoraggio;
 	}

 	@Override
 	public String toString() {
 		StringBuilder builder = new StringBuilder();
 		builder.append("MonitoraggioAria [id=");
 		builder.append(id);
 		builder.append(", nomeFile=");
 		builder.append(nomeFile);
 		builder.append(", numeroPagina=");
 		builder.append(numeroPagina);
 		builder.append(", dataOra=");
 		builder.append(data);
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
