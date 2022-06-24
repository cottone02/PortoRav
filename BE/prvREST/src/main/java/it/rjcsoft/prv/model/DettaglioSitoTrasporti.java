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

@Table(name = "dettagli_sito_trasporti")
@Entity(name = "dettagli_sito_trasporti")
public class DettaglioSitoTrasporti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "dettagli_sito_trasporti_id_seq", allocationSize = 1, name = "dettagli_sito_trasporti_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prodotto_dettaglio_id", nullable = false)
    private Integer prodottoDettaglioId;

    @ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = TipoTrasportoInDeposito.class)
    @JoinColumn(name = "trasporto_in_deposito_id")
    private TipoTrasportoInDeposito trasportoInDeposito;

    @Column(name = "quantita", nullable = false)
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

    public TipoTrasportoInDeposito getTrasportoInDeposito() {
        return trasportoInDeposito;
    }

    public void setTrasportoInDeposito(TipoTrasportoInDeposito trasportoInDeposito) {
        this.trasportoInDeposito = trasportoInDeposito;
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
        DettaglioSitoTrasporti other = (DettaglioSitoTrasporti) obj;
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
        builder.append("DettaglioSitoTrasporti [id=");
        builder.append(id);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", quantita=");
        builder.append(quantita);
        builder.append(", trasportoInDeposito=");
        builder.append(trasportoInDeposito);
        builder.append("]");
        return builder.toString();
    }

}
