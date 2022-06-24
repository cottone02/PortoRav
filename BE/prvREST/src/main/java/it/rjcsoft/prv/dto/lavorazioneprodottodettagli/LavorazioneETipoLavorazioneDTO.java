package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

import java.util.List;

public class LavorazioneETipoLavorazioneDTO {
	
	private List<LavorazioneDTO> listaLavorazioni;
	
	private List<TipoLavorazioneDTO> ListaTipoLavorazioni;
	
	

	public List<LavorazioneDTO> getListaLavorazioni() {
		return listaLavorazioni;
	}



	public void setListaLavorazioni(List<LavorazioneDTO> listaLavorazioni) {
		this.listaLavorazioni = listaLavorazioni;
	}



	public List<TipoLavorazioneDTO> getListaTipoLavorazioni() {
		return ListaTipoLavorazioni;
	}



	public void setListaTipoLavorazioni(List<TipoLavorazioneDTO> listaTipoLavorazioni) {
		ListaTipoLavorazioni = listaTipoLavorazioni;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LavorazioneETipoLavorazioneDTO [listaLavorazioni=");
		builder.append(listaLavorazioni);
		builder.append(", ListaTipoLavorazioni=");
		builder.append(ListaTipoLavorazioni);
		builder.append("]");
		return builder.toString();
	}
	
	

}
