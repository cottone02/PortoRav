package it.rjcsoft.prv.dto.dettagliositotrasporti;

public class DettaglioSitoTrasportiSaveDTO {

    private Integer prodottoDettaglioId;

    private Integer trasportoInDepositoId;

    private Double quantita;

    public Integer getProdottoDettaglioId() {
        return prodottoDettaglioId;
    }

    public void setProdottoDettaglioId(Integer prodottoDettaglioId) {
        this.prodottoDettaglioId = prodottoDettaglioId;
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
        builder.append("DettaglioSitoTrasportiSaveDTO [prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", quantita=");
        builder.append(quantita);
        builder.append(", trasportoInDepositoId=");
        builder.append(trasportoInDepositoId);
        builder.append("]");
        return builder.toString();
    }

}
