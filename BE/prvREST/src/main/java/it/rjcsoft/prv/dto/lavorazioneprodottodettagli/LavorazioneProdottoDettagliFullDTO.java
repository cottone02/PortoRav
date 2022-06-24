package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

public class LavorazioneProdottoDettagliFullDTO {
	
	private Integer id;
	
	private Integer prodottoDettaglioId;
	
	private LavorazioneDTO lavorazioneDTO;
	
	private String tipoLavorazione;
	
	private Integer ggAnno;
	
	private Integer oreGg;
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getProdottoDettaglioId() {
		return prodottoDettaglioId;
	}



	public void setProdottoDettagliId(Integer prodottoDettaglioId) {
		this.prodottoDettaglioId = prodottoDettaglioId;
	}



	public LavorazioneDTO getLavorazioneDTO() {
		return lavorazioneDTO;
	}



	public void setLavorazioneDTO(LavorazioneDTO lavorazioneDTO) {
		this.lavorazioneDTO = lavorazioneDTO;
	}



	public String getTipoLavorazione() {
		return tipoLavorazione;
	}



	public void setTipoLavorazione(String tipoLavorazione) {
		this.tipoLavorazione = tipoLavorazione;
	}



	public Integer getGgAnno() {
		return ggAnno;
	}



	public void setGgAnno(Integer ggAnno) {
		this.ggAnno = ggAnno;
	}



	public Integer getOreGg() {
		return oreGg;
	}



	public void setOreGg(Integer oreGg) {
		this.oreGg = oreGg;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LavorazioneProdottoDettagliFullDTO [id=");
		builder.append(id);
		builder.append(", prodottoDettaglioId=");
		builder.append(prodottoDettaglioId);
		builder.append(", lavorazioneDTO=");
		builder.append(lavorazioneDTO);
		builder.append(", tipoLavorazione=");
		builder.append(tipoLavorazione);
		builder.append(", ggAnno=");
		builder.append(ggAnno);
		builder.append(", oreGg=");
		builder.append(oreGg);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
