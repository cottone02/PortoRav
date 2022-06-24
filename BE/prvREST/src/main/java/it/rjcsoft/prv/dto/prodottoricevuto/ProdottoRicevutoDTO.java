package it.rjcsoft.prv.dto.prodottoricevuto;

public class ProdottoRicevutoDTO {

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
        StringBuilder sb = new StringBuilder();
        sb.append("ProdottoRicevutoDTO{id=").append(id);
        sb.append(", schedaId=").append(schedaId);
        sb.append(", movViaNave=").append(movViaNave);
        sb.append(", movViaTerra=").append(movViaTerra);
        sb.append(", movViaFerrovia=").append(movViaFerrovia);
        sb.append(", inProprio=").append(inProprio);
        sb.append(", contoTerzi=").append(contoTerzi);
        sb.append(", aziendaTerza=").append(aziendaTerza);
        sb.append(", banchina=").append(banchina);
        sb.append('}');
        return sb.toString();
    }

    

    

}
