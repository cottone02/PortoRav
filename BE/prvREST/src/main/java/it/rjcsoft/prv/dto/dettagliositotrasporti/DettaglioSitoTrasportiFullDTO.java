package it.rjcsoft.prv.dto.dettagliositotrasporti;

public class DettaglioSitoTrasportiFullDTO {

    private Integer id;

    private Integer prodottoDettaglioId;

    private TipoTrasportoInDepositoDTO trasportoInDeposito;

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

    public TipoTrasportoInDepositoDTO getTrasportoInDeposito() {
        return trasportoInDeposito;
    }

    public void setTrasportoInDeposito(TipoTrasportoInDepositoDTO trasportoInDeposito) {
        this.trasportoInDeposito = trasportoInDeposito;
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
        builder.append("DettaglioSitoTrasportiFullDTO [id=");
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
