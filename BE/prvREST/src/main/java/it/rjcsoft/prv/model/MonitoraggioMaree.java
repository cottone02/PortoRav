package it.rjcsoft.prv.model;

 import java.sql.Date;

 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.SequenceGenerator;
 import javax.persistence.Table;

 import org.springframework.format.annotation.DateTimeFormat;

 @Entity(name = "monitoraggi_maree")
 @Table(name = "monitoraggi_maree")
 public class MonitoraggioMaree {

 	@Id
 	@Column(name = "id", nullable = false)
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@SequenceGenerator(sequenceName = "monitoraggi_maree_id_seq", allocationSize = 1, name = "monitoraggi_maree_id_seq")
 	private Integer id;
 	@Column(name = "nome_file", nullable = false)
 	private String nomeFile;

 	@Column(name = "numero_pagina", nullable = false)
 	private Integer numeroPagina;

 	@Column(name = "data_ora")
 	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
 	private Date dataOra;

 	@Column(name = "lvl_idrometrico", nullable = false)
 	private Double lvlIdrometrico;

 	 //TODO : --> Il model monitoring files non esiste e quello monitoring è jdbc
 	 //niente ManyToOne
 	@Column(name = "monitoring_files", nullable = false)
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

 	public Double getLivelloIdrometrico() {
 		return lvlIdrometrico;
 	}

 	public void setLivelloIdrometrico(Double lvlIdrometrico) {
 		this.lvlIdrometrico = lvlIdrometrico;
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
 		builder.append("MonitoraggioMaree [id=");
 		builder.append(id);
 		builder.append(", nomeFile=");
 		builder.append(nomeFile);
 		builder.append(", numeroPagina=");
 		builder.append(numeroPagina);
 		builder.append(", dataOra=");
 		builder.append(dataOra);
 		builder.append(", livelloIdrometrico=");
 		builder.append(lvlIdrometrico);
 		builder.append(", idMonitoraggio=");
 		builder.append(idMonitoraggio);
 		builder.append("]");
 		return builder.toString();
 	}

 }
