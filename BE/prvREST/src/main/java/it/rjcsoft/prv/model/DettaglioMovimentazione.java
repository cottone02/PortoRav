package it.rjcsoft.prv.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "dettagli_movimentazione")
@Table(name = "dettagli_movimentazione")
public class DettaglioMovimentazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "dettagli_movimentazione_id_seq", allocationSize = 1, name = "dettagli_movimentazione_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prodotto_dettaglio_id")
    private Integer prodottoDettaglioId;

    @Column(name = "data_dettaglio")
    private Timestamp dataDettaglio;

    @Column(name = "quantita")
    private Double quantita;

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

    public Timestamp getDataDettaglio() {
        return dataDettaglio;
    }

    public void setDataDettaglio(Timestamp dataDettaglio) {
        this.dataDettaglio = dataDettaglio;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
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
        DettaglioMovimentazione other = (DettaglioMovimentazione) obj;
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
        builder.append("DettaglioMovimentazione [dataDettaglio=");
        builder.append(dataDettaglio);
        builder.append(", id=");
        builder.append(id);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", quantita=");
        builder.append(quantita);
        builder.append("]");
        return builder.toString();
    }

}
