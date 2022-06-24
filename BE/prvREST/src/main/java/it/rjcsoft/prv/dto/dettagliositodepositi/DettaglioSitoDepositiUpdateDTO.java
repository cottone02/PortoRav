package it.rjcsoft.prv.dto.dettagliositodepositi;

public class DettaglioSitoDepositiUpdateDTO {

    private Integer id;

    private Integer tipoDepositoStoccaggioId;

    private Integer numero;

    private Double capacita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoDepositoStoccaggioId() {
        return tipoDepositoStoccaggioId;
    }

    public void setTipoDepositoStoccaggioId(Integer tipoDepositoStoccaggioId) {
        this.tipoDepositoStoccaggioId = tipoDepositoStoccaggioId;
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DettaglioSitoDepositiUpdateDTO [capacita=");
        builder.append(capacita);
        builder.append(", id=");
        builder.append(id);
        builder.append(", numero=");
        builder.append(numero);
        builder.append(", tipoDepositoStoccaggioId=");
        builder.append(tipoDepositoStoccaggioId);
        builder.append("]");
        return builder.toString();
    }

}
