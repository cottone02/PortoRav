package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.Polygon;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

@Entity(name = "cumuli_aperto_localizzazioni")
@Table(name = "cumuli_aperto_localizzazioni")
public class CumuliApertoLocalizzazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "cumuli_aperto_localizzazioni_id_seq", allocationSize = 1, name = "cumuli_aperto_localizzazioni_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prodotto_dettaglio_id")
    private Integer prodottoDettaglioId;

    @JsonSerialize(using = PolygonSerializer.class)
    @JsonDeserialize(using = PolygonDeserializer.class)
    @Column(name = "localizzazione", nullable = true)
    private Polygon localizzazione;

    @Column(name = "altezza")
    private Double altezza;
    
    @Column(name = "area")
    private Double area;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
    
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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
        CumuliApertoLocalizzazione other = (CumuliApertoLocalizzazione) obj;
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
        builder.append("CumuliApertoLocalizzazione [altezza=");
        builder.append(altezza);
        builder.append(", area=");
        builder.append(area);
        builder.append(", id=");
        builder.append(id);
        builder.append(", localizzazione=");
        builder.append(localizzazione);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append("]");
        return builder.toString();
    }

   
}
