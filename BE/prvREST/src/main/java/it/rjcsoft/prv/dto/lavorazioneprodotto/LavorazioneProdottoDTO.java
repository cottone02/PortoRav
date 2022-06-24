package it.rjcsoft.prv.dto.lavorazioneprodotto;

import java.util.List;

import it.rjcsoft.prv.dto.lavorazioneprodottodettagli.LavorazioneProdottoDettagliFullDTO;

public class LavorazioneProdottoDTO {
	
	private Integer id;

    private Integer schedaId;

    private Double movViaNave;

    private Double movViaTerra;

    private Double movViaFerrovia;

    private Boolean inProprio;

    private Boolean contoTerzi;

    private String aziendaTerza;

    private Integer banchina;
    
    private List<LavorazioneProdottoDettagliFullDTO> lavorazioneProdottoDettagliFullDTO;

    private Integer nLavorazioni;

	public List<LavorazioneProdottoDettagliFullDTO> getLavorazioneProdottoDettagliFullDTO() {
		return lavorazioneProdottoDettagliFullDTO;
	}

	public void setLavorazioneProdottoDettagliFullDTO(List<LavorazioneProdottoDettagliFullDTO> lavorazioneProdottoDettagliFullDTO) {
		this.lavorazioneProdottoDettagliFullDTO = lavorazioneProdottoDettagliFullDTO;
	}

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

    

	public Integer getnLavorazioni() {
        return nLavorazioni;
    }

    public void setnLavorazioni(Integer nLavorazioni) {
        this.nLavorazioni = nLavorazioni;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LavorazioneProdottoDTO [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", lavorazioneProdottoDettagliFullDTO=");
        builder.append(lavorazioneProdottoDettagliFullDTO);
        builder.append(", movViaFerrovia=");
        builder.append(movViaFerrovia);
        builder.append(", movViaNave=");
        builder.append(movViaNave);
        builder.append(", movViaTerra=");
        builder.append(movViaTerra);
        builder.append(", nLavorazioni=");
        builder.append(nLavorazioni);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        return builder.toString();
    } 

}
