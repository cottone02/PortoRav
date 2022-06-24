package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

public class LavorazioneProdottoDettagliDTO {
	
	private Integer id;
	
	private Integer prodottoDettagliId;
	
	private Integer lavorazioneId;
	
	private Integer tipoLavorazione;
	
	private Integer ggAnno;
	
	private Integer oreGg;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdottoDettagliId() {
		return prodottoDettagliId;
	}

	public void setProdottoDettagliId(Integer prodottoDettagliId) {
		this.prodottoDettagliId = prodottoDettagliId;
	}

	public Integer getLavorazioneId() {
		return lavorazioneId;
	}

	public void setLavorazioneId(Integer lavorazioneId) {
		this.lavorazioneId = lavorazioneId;
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
		builder.append("LavorazioneProdottoDettagliDTO [id=");
		builder.append(id);
		builder.append(", prodottoDettagliId=");
		builder.append(prodottoDettagliId);
		builder.append(", lavorazioneId=");
		builder.append(lavorazioneId);
		builder.append(", ggAnno=");
		builder.append(ggAnno);
		builder.append(", oreGg=");
		builder.append(oreGg);
		builder.append("]");
		return builder.toString();
	}

	public Integer getTipoLavorazione() {
		return tipoLavorazione;
	}

	public void setTipoLavorazione(Integer tipoLavorazione) {
		this.tipoLavorazione = tipoLavorazione;
	}

}
