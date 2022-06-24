package it.rjcsoft.prv.dto.lavorazioneprodottodettagli;

public class TipoLavorazioneDTO {
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoLavorazioneDTO [tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}


}
