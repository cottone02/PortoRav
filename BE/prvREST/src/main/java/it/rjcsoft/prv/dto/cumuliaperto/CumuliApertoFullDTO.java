package it.rjcsoft.prv.dto.cumuliaperto;

import java.util.List;


public class CumuliApertoFullDTO {
	
	private Integer id;

    private Integer schedaId;

    private Double movViaNave;

    private Double movViaTerra;

    private Double movViaFerrovia;

    private Boolean inProprio;

    private Boolean contoTerzi;

    private String aziendaTerza;

    private Integer banchina;
    
    private List<CumuliApertoLocalizzazioneDTO> listaCumuliApertoLocalizzazione;
    
    private Integer nCumuliAperto;


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

    
    
    public List<CumuliApertoLocalizzazioneDTO> getListaCumuliApertoLocalizzazione() {
		return listaCumuliApertoLocalizzazione;
	}

	public void setListaCumuliApertoLocalizzazione(List<CumuliApertoLocalizzazioneDTO> listaCumuliApertoLocalizzazione) {
		this.listaCumuliApertoLocalizzazione = listaCumuliApertoLocalizzazione;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CumuliApertoFullDTO [id=");
		builder.append(id);
		builder.append(", schedaId=");
		builder.append(schedaId);
		builder.append(", movViaNave=");
		builder.append(movViaNave);
		builder.append(", movViaTerra=");
		builder.append(movViaTerra);
		builder.append(", movViaFerrovia=");
		builder.append(movViaFerrovia);
		builder.append(", inProprio=");
		builder.append(inProprio);
		builder.append(", contoTerzi=");
		builder.append(contoTerzi);
		builder.append(", aziendaTerza=");
		builder.append(aziendaTerza);
		builder.append(", banchina=");
		builder.append(banchina);
		builder.append(", listaCumuliApertoLocalizzazione=");
		builder.append(listaCumuliApertoLocalizzazione);
		builder.append(", nCumuliAperto=");
		builder.append(nCumuliAperto);
		builder.append("]");
		return builder.toString();
	}

	public Integer getnCumuliAperto() {
		return nCumuliAperto;
	}

	public void setnCumuliAperto(Integer nCumuliAperto) {
		this.nCumuliAperto = nCumuliAperto;
	}

}
