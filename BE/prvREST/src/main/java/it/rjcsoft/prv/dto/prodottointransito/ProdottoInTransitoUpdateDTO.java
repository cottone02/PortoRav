package it.rjcsoft.prv.dto.prodottointransito;

import javax.validation.constraints.NotNull;

public class ProdottoInTransitoUpdateDTO {

    @NotNull(message = "Id must be not null")
    private Integer id;

    private Boolean inProprio;

    private Boolean contoTerzi;

    private String aziendaTerza;

    private Integer banchina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInProprio() {
        return inProprio;
    }

    public void setInProprio(Boolean inProprio) {
        this.inProprio = inProprio;
    }

    public Boolean getContoTerzi() {
        return contoTerzi;
    }

    public void setContoTerzi(Boolean contoTerzi) {
        this.contoTerzi = contoTerzi;
    }

    public String getAziendaTerza() {
        return aziendaTerza;
    }

    public void setAziendaTerza(String aziendaTerza) {
        this.aziendaTerza = aziendaTerza;
    }

    public Integer getBanchina() {
        return banchina;
    }

    public void setBanchina(Integer banchina) {
        this.banchina = banchina;
    }

    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProdottoRicevutoUpdateDTO [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append("]");
        return builder.toString();
    }

}
