package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

public class LavorazioneProdottoDettagliInsertFullDTO {
	
	private Integer id;
	
	private Integer prodottoDettagliId;
	
	private LavorazioneDTO lavorazioneDTO;
	
	private TipoLavorazioneDTO tipoLavorazioneDTO;
	
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

	public LavorazioneDTO getLavorazioneDTO() {
		return lavorazioneDTO;
	}

	public void setLavorazioneDTO(LavorazioneDTO lavorazioneDTO) {
		this.lavorazioneDTO = lavorazioneDTO;
	}

	public TipoLavorazioneDTO getTipoLavorazioneDTO() {
		return tipoLavorazioneDTO;
	}

	public void setTipoLavorazioneDTO(TipoLavorazioneDTO tipoLavorazioneDTO) {
		this.tipoLavorazioneDTO = tipoLavorazioneDTO;
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
		builder.append("LavorazioneProdottoDettagliInsertFullDTO [id=");
		builder.append(id);
		builder.append(", prodottoDettagliId=");
		builder.append(prodottoDettagliId);
		builder.append(", lavorazioneDTO=");
		builder.append(lavorazioneDTO);
		builder.append(", tipoLavorazioneDTO=");
		builder.append(tipoLavorazioneDTO);
		builder.append(", ggAnno=");
		builder.append(ggAnno);
		builder.append(", oreGg=");
		builder.append(oreGg);
		builder.append("]");
		return builder.toString();
	}

	
}
