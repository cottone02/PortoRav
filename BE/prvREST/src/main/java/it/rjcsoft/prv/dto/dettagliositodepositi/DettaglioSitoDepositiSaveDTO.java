package it.rjcsoft.prv.dto.dettagliositodepositi;

public class DettaglioSitoDepositiSaveDTO {

    private Integer prodottoDettaglioId;

    private Integer tipoDepositoStoccaggioId;

    private Integer numero;

    private Double capacita;

    public Integer getProdottoDettaglioId() {
        return prodottoDettaglioId;
    }

    public void setProdottoDettaglioId(Integer prodottoDettaglioId) {
        this.prodottoDettaglioId = prodottoDettaglioId;
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
        builder.append("DettaglioSitoDepositiSaveDTO [capacita=");
        builder.append(capacita);
        builder.append(", numero=");
        builder.append(numero);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", tipoDepositoStoccaggioId=");
        builder.append(tipoDepositoStoccaggioId);
        builder.append("]");
        return builder.toString();
    }

}
