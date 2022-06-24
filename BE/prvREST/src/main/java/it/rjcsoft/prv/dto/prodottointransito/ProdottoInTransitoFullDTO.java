package it.rjcsoft.prv.dto.prodottointransito;

import java.util.List;

import it.rjcsoft.prv.dto.dettagliositodepositi.DettaglioSitoDepositiFullDTO;
import it.rjcsoft.prv.dto.dettagliositotrasporti.DettaglioSitoTrasportiFullDTO;

public class ProdottoInTransitoFullDTO {
	
	private Integer id;

    private Integer schedaId;

    private Boolean inProprio;

    private Boolean contoTerzi;

    private String aziendaTerza;

    private Integer banchina;
    
    private List<DettaglioSitoTrasportiFullDTO> listaDettaglioSitoTrasporti;
    
    private List<DettaglioSitoDepositiFullDTO> listaDettaglioSitoDepositi;
    

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

    
    
    public List<DettaglioSitoTrasportiFullDTO> getListaDettaglioSitoTrasporti() {
		return listaDettaglioSitoTrasporti;
	}

	public void setListaDettaglioSitoTrasporti(List<DettaglioSitoTrasportiFullDTO> listaDettaglioSitoTrasporti) {
		this.listaDettaglioSitoTrasporti = listaDettaglioSitoTrasporti;
	}
	
	public List<DettaglioSitoDepositiFullDTO> getListaDettaglioSitoDepositi() {
		return listaDettaglioSitoDepositi;
	}

	public void setListaDettaglioSitoDepositi(List<DettaglioSitoDepositiFullDTO> listaDettaglioSitoDeposito) {
		this.listaDettaglioSitoDepositi = listaDettaglioSitoDeposito;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProdottoInTransitoFullDTO [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        builder.append(listaDettaglioSitoDepositi);
        builder.append(listaDettaglioSitoTrasporti);
        return builder.toString();
    }


}
