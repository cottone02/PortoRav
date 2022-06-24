package it.rjcsoft.prv.dto.censimentointerventi;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CensimentoInterventiFullDTO {

	private Integer id;

	private Integer censimentoAziendaId;

	private InterventoDTO interventoDTO;

	private String note;

	private Boolean sbarcoMateriale;

	private Boolean trasportoAutocarri;

	private Boolean cumuliAperto;

	private Boolean emissioniConvogliate;

	@JsonInclude(Include.NON_NULL)
	private Timestamp dataAttivazione;

	private String presenzaProceduraFormalizzata;

	@Positive(message = "valori negativi non accettati")
	@Max(value = 100, message = "valore superiore al limite di 100")
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

	public InterventoDTO getInterventoDTO() {
		return interventoDTO;
	}

	public void setInterventoDTO(InterventoDTO intervento) {
		this.interventoDTO = intervento;
	}

	public String getNote() {
		return note;
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

	public void setSbarcoMateriale(Boolean sbarcoMateriale) {
		this.sbarcoMateriale = sbarcoMateriale;
	}

	public Boolean getSbarcoMateriale() {
		return sbarcoMateriale;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoInterventiFullDTO [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", intervento=");
		builder.append(interventoDTO);
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
