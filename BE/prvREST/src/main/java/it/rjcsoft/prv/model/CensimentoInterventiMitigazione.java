package it.rjcsoft.prv.model;

import java.sql.Timestamp;

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

@Entity(name = "censimenti_interventi_mitigazione")
@Table(name = "censimenti_interventi_mitigazione")
public class CensimentoInterventiMitigazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "censimenti_interventi_mitigazione_id_seq", allocationSize = 1, name = "censimenti_interventi_mitigazione_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "censimento_azienda_id", nullable = false)
	private Integer censimentoAziendaId;

//	@Column(name = "intervento_id", nullable = false)
//	private Integer interventoId;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = InterventoMitigazionePolveri.class)
	@JoinColumn(name = "intervento_id")
	private InterventoMitigazionePolveri intervento;

	@Column(name = "note", length = 255)
	private String note;

	@Column(name = "data_attivazione")
	private Timestamp dataAttivazione;

	@Column(name = "presenza_procedura_formalizzata", length = 500)
	private String presenzaProceduraFormalizzata;

	@Column(name = "sbarco_materiale")
	private Boolean sbarcoMateriale;

	@Column(name = "trasporto_autocarri")
	private Boolean trasportoAutocarri;

	@Column(name = "cumuli_aperto")
	private Boolean cumuliAperto;

	@Column(name = "emissioni_convogliate")
	private Boolean emissioniConvogliate;

	@Column(name = "valutazione_qualitativa")
	private Integer valutazioneQualitativa;

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

//	public Integer getInterventoId() {
//		return interventoId;
//	}
//
//	public void setInterventoId(Integer interventoId) {
//		this.interventoId = interventoId;
//	}

	public String getNote() {
		return note;
	}

	public InterventoMitigazionePolveri getIntervento() {
		return intervento;
	}

	public void setIntervento(InterventoMitigazionePolveri intervento) {
		this.intervento = intervento;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getDataAttivazione() {
		return dataAttivazione;
	}

	public void setDataAttivazione(Timestamp dataAttivazione) {
		this.dataAttivazione = dataAttivazione;
	}

	public String getPresenzaProceduraFormalizzata() {
		return presenzaProceduraFormalizzata;
	}

	public void setPresenzaProceduraFormalizzata(String presenzaProceduraFormalizzata) {
		this.presenzaProceduraFormalizzata = presenzaProceduraFormalizzata;
	}

	public Boolean getSbarcoMateriale() {
		return sbarcoMateriale;
	}

	public void setSbarcoMateriale(Boolean sbarcoMateriale) {
		this.sbarcoMateriale = sbarcoMateriale;
	}

	public Boolean getTrasportoAutocarri() {
		return trasportoAutocarri;
	}

	public void setTrasportoAutocarri(Boolean trasportoAutocarri) {
		this.trasportoAutocarri = trasportoAutocarri;
	}

	public Boolean getCumuliAperto() {
		return cumuliAperto;
	}

	public void setCumuliAperto(Boolean cumuliAperto) {
		this.cumuliAperto = cumuliAperto;
	}

	public Boolean getEmissioniConvogliate() {
		return emissioniConvogliate;
	}

	public void setEmissioniConvogliate(Boolean emissioniConvogliate) {
		this.emissioniConvogliate = emissioniConvogliate;
	}

	public Integer getValutazioneQualitativa() {
		return valutazioneQualitativa;
	}

	public void setValutazioneQualitativa(Integer valutazioneQualitativa) {
		this.valutazioneQualitativa = valutazioneQualitativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CensimentoInterventiMitigazione other = (CensimentoInterventiMitigazione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoInterventiMitigazione [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
//		builder.append(", interventoId=");
//		builder.append(interventoId);
		builder.append(", note=");
		builder.append(note);
		builder.append(", dataAttivazione=");
		builder.append(dataAttivazione);
		builder.append(", presenzaProceduraFormalizzata=");
		builder.append(presenzaProceduraFormalizzata);
		builder.append(", sbarcoMateriale=");
		builder.append(sbarcoMateriale);
		builder.append(", trasportoAutocarri=");
		builder.append(trasportoAutocarri);
		builder.append(", cumuliAperto=");
		builder.append(cumuliAperto);
		builder.append(", emissioniConvogliate=");
		builder.append(emissioniConvogliate);
		builder.append(", valutazioneQualitativa=");
		builder.append(valutazioneQualitativa);
		builder.append("]");
		return builder.toString();
	}

}
