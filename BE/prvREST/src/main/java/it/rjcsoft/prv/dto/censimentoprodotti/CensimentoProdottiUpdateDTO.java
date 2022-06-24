package it.rjcsoft.prv.dto.censimentoprodotti;

import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CensimentoProdottiUpdateDTO {

	@NotNull(message = "il campo schedaId è obbligatorio")
	private Integer schedaId;

	@NotNull(message = "il campo censimentoAziendaId è obbligatorio")
	private Integer censimentoAziendaId;
	
	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	private Polygon mappaStabilimento;

	private Integer sfuso;

	private Integer inConfezioni;

	private Integer umidita;

	private Double densita;

	private Integer classeGranulometricaId;

	private Integer silt;

	public Integer getSchedaId() {
		return schedaId;
	}

	public void setSchedaId(Integer schedaId) {
		this.schedaId = schedaId;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public Polygon getMappaStabilimento() {
		return mappaStabilimento;
	}

	public void setMappaStabilimento(Polygon mappaStabilimento) {
		this.mappaStabilimento = mappaStabilimento;
	}

	public Integer getSfuso() {
		return sfuso;
	}

	public void setSfuso(Integer sfuso) {
		this.sfuso = sfuso;
	}

	public Integer getInConfezioni() {
		return inConfezioni;
	}

	public void setInConfezioni(Integer inConfezioni) {
		this.inConfezioni = inConfezioni;
	}

	public Integer getUmidita() {
		return umidita;
	}

	public void setUmidita(Integer umidita) {
		this.umidita = umidita;
	}

	public Double getDensita() {
		return densita;
	}

	public void setDensita(Double densita) {
		this.densita = densita;
	}

	public Integer getClasseGranulometricaId() {
		return classeGranulometricaId;
	}

	public void setClasseGranulometricaId(Integer classeGranulometricaId) {
		this.classeGranulometricaId = classeGranulometricaId;
	}

	public Integer getSilt() {
		return silt;
	}

	public void setSilt(Integer silt) {
		this.silt = silt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoProdottiUpdateDTO [schedaId=");
		builder.append(schedaId);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", mappaStabilimento=");
		builder.append(mappaStabilimento);
		builder.append(", sfuso=");
		builder.append(sfuso);
		builder.append(", inConfezioni=");
		builder.append(inConfezioni);
		builder.append(", umidita=");
		builder.append(umidita);
		builder.append(", densita=");
		builder.append(densita);
		builder.append(", granulometriaId=");
		builder.append(classeGranulometricaId);
		builder.append(", silt=");
		builder.append(silt);
		builder.append("]");
		return builder.toString();
	}

}
