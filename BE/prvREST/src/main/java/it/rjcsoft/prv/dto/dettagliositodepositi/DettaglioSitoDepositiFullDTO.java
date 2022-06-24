package it.rjcsoft.prv.dto.dettagliositodepositi;

public class DettaglioSitoDepositiFullDTO {

    private Integer id;

    private Integer prodottoDettaglioId;

    private TipoDepositoStoccaggioDTO tipoDepositoStoccaggio;

    private Integer numero;

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

    public TipoDepositoStoccaggioDTO getTipoDepositoStoccaggio() {
        return tipoDepositoStoccaggio;
    }

    public void setTipoDepositoStoccaggio(TipoDepositoStoccaggioDTO tipoDepositoStoccaggio) {
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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DettaglioSitoDepositiFullDTO [id=");
        builder.append(id);
        builder.append(", numero=");
        builder.append(numero);
        builder.append(", prodottoDettaglioId=");
        builder.append(prodottoDettaglioId);
        builder.append(", capacita=");
        builder.append(capacita);
        builder.append(", tipoDepositoStoccaggio=");
        builder.append(tipoDepositoStoccaggio);
        builder.append("]");
        return builder.toString();
    }

}
