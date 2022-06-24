package it.rjcsoft.prv.dto.cumuliaperto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.Polygon;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CumuliApertoLocalizzazioneSaveDTO {

    private Integer prodottoDettaglioId;

    @JsonSerialize(using = PolygonSerializer.class)
    @JsonDeserialize(using = PolygonDeserializer.class)
    private Polygon localizzazione;

    private Double altezza;

    public Integer getProdottoDettaglioId() {
        return prodottoDettaglioId;
    }

    public void setProdottoDettaglioId(Integer prodottoDettaglioId) {
        this.prodottoDettaglioId = prodottoDettaglioId;
    }

    public Polygon getLocalizzazione() {
        return localizzazione;
    }

    public void setLocalizzazione(Polygon localizzazione) {
        this.localizzazione = localizzazione;
    }

    public Double getAltezza() {
        return altezza;
    }

    public void setAltezza(Double altezza) {
        this.altezza = altezza;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CumuliApertoLocalizzazioneSaveDTO [altezza=");
        builder.append(altezza);
        builder.append(", localizzazione=");
        builder.append(localizzazione);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append("]");
        return builder.toString();
    }

}
