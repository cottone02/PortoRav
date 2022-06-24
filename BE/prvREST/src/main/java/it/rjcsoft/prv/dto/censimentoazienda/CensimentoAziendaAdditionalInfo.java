package it.rjcsoft.prv.dto.censimentoazienda;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

import it.rjcsoft.prv.utils.PrvIntegerConverterUtils;


@Entity
@Immutable
public class CensimentoAziendaAdditionalInfo {

	@Id
	private Integer censimentoAziendaId;

	@Convert(converter = PrvIntegerConverterUtils.class)
	private Integer[] componentCounter;

	private Boolean canDelete;

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public Integer[] getComponentCounter() {
		return componentCounter;
	}

	public void setComponentCounter(Integer[] componentCounter) {
		this.componentCounter = componentCounter;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoAziendaAdditionalInfo [censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", componentCounter=");
		builder.append(componentCounter);
		builder.append(", canDelete=");
		builder.append(canDelete);
		builder.append("]");
		return builder.toString();
	}

	

}
