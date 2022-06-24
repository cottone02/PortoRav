package it.rjcsoft.prv.dto.censimentoprodotti;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CensimentoProdottiDTO {

	private Integer schedaId;

	private Integer censimentoAziendaId;

	private Integer prodottoId;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	private Polygon mappaStabilimento;

	private Integer sfuso;

	private Integer inConfezioni;

	private Integer umidita;

	private Float densita;

	private Integer granulometriaId;

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

	public Integer getProdottoId() {
		return prodottoId;
	}

	public void setProdottoId(Integer prodottoId) {
		this.prodottoId = prodottoId;
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

	public Float getDensita() {
		return densita;
	}

	public void setDensita(Float densita) {
		this.densita = densita;
	}

	public Integer getGranulometriaId() {
		return granulometriaId;
	}

	public void setGranulometriaId(Integer granulometriaId) {
		this.granulometriaId = granulometriaId;
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
		builder.append("CensimentoProdottiDTO [schedaId=");
		builder.append(schedaId);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", prodottoId=");
		builder.append(prodottoId);
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
		builder.append(granulometriaId);
		builder.append(", silt=");
		builder.append(silt);
		builder.append("]");
		return builder.toString();
	}

}
