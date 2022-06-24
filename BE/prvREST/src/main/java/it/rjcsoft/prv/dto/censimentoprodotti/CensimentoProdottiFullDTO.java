package it.rjcsoft.prv.dto.censimentoprodotti;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.Polygon;

import it.rjcsoft.prv.dto.censimentoprodottiallegati.ProdottiAllegatiDTO;
import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CensimentoProdottiFullDTO {

	private Integer schedaId;

	private Integer censimentoAziendaId;

	private ProdottoDTO prodotto;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	private Polygon mappaStabilimento;

	private Integer sfuso;

	private Integer inConfezioni;

	private Integer umidita;

	private Double densita;

	private ClasseGranulometricaDTO granulometria;

	private Integer silt;

	private List<ProdottiAllegatiDTO> prodottiAllegati;

	private Boolean canDelete;

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

	public ProdottoDTO getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDTO prodotto) {
		this.prodotto = prodotto;
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

	public ClasseGranulometricaDTO getGranulometria() {
		return granulometria;
	}

	public void setGranulometria(ClasseGranulometricaDTO granulometria) {
		this.granulometria = granulometria;
	}

	public Integer getSilt() {
		return silt;
	}

	public void setSilt(Integer silt) {
		this.silt = silt;
	}

	public List<ProdottiAllegatiDTO> getProdottiAllegati() {
		return prodottiAllegati;
	}

	public void setProdottiAllegati(List<ProdottiAllegatiDTO> prodottiAllegati) {
		this.prodottiAllegati = prodottiAllegati;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoProdottiFullDTO [canDelete=");
		builder.append(canDelete);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", densita=");
		builder.append(densita);
		builder.append(", granulometria=");
		builder.append(granulometria);
		builder.append(", inConfezioni=");
		builder.append(inConfezioni);
		builder.append(", mappaStabilimento=");
		builder.append(mappaStabilimento);
		builder.append(", prodottiAllegati=");
		builder.append(prodottiAllegati);
		builder.append(", prodotto=");
		builder.append(prodotto);
		builder.append(", schedaId=");
		builder.append(schedaId);
		builder.append(", sfuso=");
		builder.append(sfuso);
		builder.append(", silt=");
		builder.append(silt);
		builder.append(", umidita=");
		builder.append(umidita);
		builder.append("]");
		return builder.toString();
	}



}
