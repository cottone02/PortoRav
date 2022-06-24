package it.rjcsoft.prv.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "censimenti_protezioni_contenimento")
@Table(name = "censimenti_protezioni_contenimento")
public class CensimentoProtezioniContenimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "censimenti_contenimento_emissioni_id_seq", allocationSize = 1, name = "censimenti_contenimento_emissioni_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "censimento_azienda_id", nullable = false)
	private Integer censimentoAziendaId;
	
//	@Column(name = "contenimento_id", nullable = false)
//	private Integer contenimentoId;
//	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, targetEntity = ProtezioneContenimentoEmissioni.class)
	@JoinColumn(name="contenimento_id")
	private ProtezioneContenimentoEmissioni contenimento;
	
	@Column(name = "note")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

//	public Integer getContenimentoId() {
//		return contenimentoId;
//	}
//
//	public void setContenimentoId(Integer contenimentoId) {
//		this.contenimentoId = contenimentoId;
//	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ProtezioneContenimentoEmissioni getContenimento() {
		return contenimento;
	}
	
	public void setContenimento(ProtezioneContenimentoEmissioni contenimento) {
		this.contenimento = contenimento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoProtezioniContenimento [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
//		builder.append(", contenimentoId=");
//		builder.append(contenimentoId);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}

}
