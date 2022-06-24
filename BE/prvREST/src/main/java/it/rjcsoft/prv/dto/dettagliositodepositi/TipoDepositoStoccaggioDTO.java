package it.rjcsoft.prv.dto.dettagliositodepositi;

public class TipoDepositoStoccaggioDTO {

    private Integer id;

    private String depositoStoccaggio;

    private Boolean isDeletable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepositoStoccaggio() {
        return depositoStoccaggio;
    }

    public void setDepositoStoccaggio(String depositoStoccaggio) {
        this.depositoStoccaggio = depositoStoccaggio;
    }

    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TipoDepositoStoccaggioDTO{id=").append(id);
        sb.append(", depositoStoccaggio=").append(depositoStoccaggio);
        sb.append(", isDeletable=").append(isDeletable);
        sb.append('}');
        return sb.toString();
    }
    

}
