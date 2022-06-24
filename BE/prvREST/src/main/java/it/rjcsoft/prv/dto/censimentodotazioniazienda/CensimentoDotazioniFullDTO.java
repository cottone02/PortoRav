package it.rjcsoft.prv.dto.censimentodotazioniazienda;

public class CensimentoDotazioniFullDTO {

	private Integer id;
	
	private Integer censimentoAziendaId;

	private DotazioneDTO dotazioneDTO;

	private Integer quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public DotazioneDTO getDotazioneDTO() {
		return dotazioneDTO;
	}

	public void setDotazioneDTO(DotazioneDTO dotazioneDTO) {
		this.dotazioneDTO = dotazioneDTO;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoDotazioniFullDTO [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", dotazione=");
		builder.append(dotazioneDTO);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
