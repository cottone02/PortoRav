package it.rjcsoft.prv.dto.dettagliositotrasporti;

public class TipoTrasportoInDepositoDTO {
    
    private Integer id;

    private String proprietaDeposito;

    private Boolean tipoDepositoRequired;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProprietaDeposito() {
		return proprietaDeposito;
	}

	public void setProprietaDeposito(String proprietaDeposito) {
		this.proprietaDeposito = proprietaDeposito;
	}

	public Boolean getTipoDepositoRequired() {
		return tipoDepositoRequired;
	}

	public void setTipoDepositoRequired(Boolean tipoDepositoRequired) {
		this.tipoDepositoRequired = tipoDepositoRequired;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoTrasportoInDepositoDTO [id=");
		builder.append(id);
		builder.append(", proprietaDeposito=");
		builder.append(proprietaDeposito);
		builder.append(", tipoDepositoRequired=");
		builder.append(tipoDepositoRequired);
		builder.append("]");
		return builder.toString();
	}

    
}
