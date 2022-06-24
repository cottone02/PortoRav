package it.rjcsoft.prv.dto.prodottoinuscita;

public class ProdottoInUscitaDTO {

    private Integer id;

    private Integer schedaId;

    private Double movViaNave;

    private Double movViaTerra;

    private Double movViaFerrovia;

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

    public Integer getSchedaId() {
        return schedaId;
    }

    public void setSchedaId(Integer schedaId) {
        this.schedaId = schedaId;
    }

    public Double getMovViaNave() {
        return movViaNave;
    }

    public void setMovViaNave(Double movViaNave) {
        this.movViaNave = movViaNave;
    }

    public Double getMovViaTerra() {
        return movViaTerra;
    }

    public void setMovViaTerra(Double movViaTerra) {
        this.movViaTerra = movViaTerra;
    }

    public Double getMovViaFerrovia() {
        return movViaFerrovia;
    }

    public void setMovViaFerrovia(Double movViaFerrovia) {
        this.movViaFerrovia = movViaFerrovia;
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
        builder.append("ProdottoInUscitaDTO [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", movViaFerrovia=");
        builder.append(movViaFerrovia);
        builder.append(", movViaNave=");
        builder.append(movViaNave);
        builder.append(", movViaTerra=");
        builder.append(movViaTerra);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        return builder.toString();
    }

}
