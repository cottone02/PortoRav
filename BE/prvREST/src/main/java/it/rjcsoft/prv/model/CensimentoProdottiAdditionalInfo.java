package it.rjcsoft.prv.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;


@Entity
@Immutable
public class CensimentoProdottiAdditionalInfo {

	@Id
	private Integer schedaId;

	private Boolean canDelete;

    public Integer getSchedaId() {
        return schedaId;
    }

    public void setSchedaId(Integer schedaId) {
        this.schedaId = schedaId;
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
        builder.append("CensimentoProdottiAdditionalInfo [canDelete=");
        builder.append(canDelete);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        return builder.toString();
    }

}

