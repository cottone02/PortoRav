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

@Table(name = "dettagli_sito_depositi")
@Entity(name = "dettagli_sito_depositi")
public class DettaglioSitoDepositi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "dettagli_sito_depositi_id_seq", allocationSize = 1, name = "dettagli_sito_depositi_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prodotto_dettaglio_id", nullable = false)
    private Integer prodottoDettaglioId;

    @ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = TipoDepositoStoccaggio.class)
    @JoinColumn(name = "deposito_stoccaggio_id")
    private TipoDepositoStoccaggio tipoDepositoStoccaggio;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "capacita")
    private Double capacita;

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

    public TipoDepositoStoccaggio getTipoDepositoStoccaggio() {
        return tipoDepositoStoccaggio;
    }

    public void setTipoDepositoStoccaggio(TipoDepositoStoccaggio tipoDepositoStoccaggio) {
        this.tipoDepositoStoccaggio = tipoDepositoStoccaggio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getCapacita() {
        return capacita;
    }

    public void setCapacita(Double capacita) {
        this.capacita = capacita;
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
        DettaglioSitoDepositi other = (DettaglioSitoDepositi) obj;
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
        builder.append("DettaglioSitoDepositi [capacita=");
        builder.append(capacita);
        builder.append(", id=");
        builder.append(id);
        builder.append(", numero=");
        builder.append(numero);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", tipoDepositoStoccaggio=");
        builder.append(tipoDepositoStoccaggio);
        builder.append("]");
        return builder.toString();
    }

}
