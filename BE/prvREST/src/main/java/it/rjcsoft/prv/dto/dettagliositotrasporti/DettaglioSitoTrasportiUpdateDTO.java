package it.rjcsoft.prv.dto.dettagliositotrasporti;

import javax.validation.constraints.NotNull;

public class DettaglioSitoTrasportiUpdateDTO {

    @NotNull(message = "Id must be not null")
    private Integer id;

    private Integer trasportoInDepositoId;

    private Double quantita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrasportoInDepositoId() {
        return trasportoInDepositoId;
    }

    public void setTrasportoInDepositoId(Integer trasportoInDepositoId) {
        this.trasportoInDepositoId = trasportoInDepositoId;
    }

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DettaglioSitoTrasportiUpdateDTO [id=");
        builder.append(id);
        builder.append(", quantita=");
        builder.append(quantita);
        builder.append(", trasportoInDepositoId=");
        builder.append(trasportoInDepositoId);
        builder.append("]");
        return builder.toString();
    }

}
