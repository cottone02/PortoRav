package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "tipo_trasporto_in_deposito")
@Table(name = "tipo_trasporto_in_deposito")
public class TipoTrasportoInDeposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "tipo_trasporto_in_deposito_id_seq", allocationSize = 1, name = "tipo_trasporto_in_deposito_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "proprieta_deposito", nullable = false)
    private String proprietaDeposito;

    @Column(name = "tipo_deposito_required", nullable = false)
    private Boolean tipoDepositoRequired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProprietaDeposito() {
        return proprietaDeposito;
    }

    public void setProprietaDeposito(String proprietaDeposito) {
        this.proprietaDeposito = proprietaDeposito;
    }

    public Boolean getTipoDepositoRequired() {
        return tipoDepositoRequired;
    }

    public void setTipoDepositoRequired(Boolean tipoDepositoRequired) {
        this.tipoDepositoRequired = tipoDepositoRequired;
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
        TipoTrasportoInDeposito other = (TipoTrasportoInDeposito) obj;
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
        builder.append("TipoTrasportoInDeposito [id=");
        builder.append(id);
        builder.append(", proprietaDeposito=");
        builder.append(proprietaDeposito);
        builder.append(", tipoDepositoRequired=");
        builder.append(tipoDepositoRequired);
        builder.append("]");
        return builder.toString();
    }

}
