package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "interventi_mitigazione_polveri")
@Table(name = "interventi_mitigazione_polveri")
public class InterventoMitigazionePolveri {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "interventi_mitigazione_polveri_id_seq", allocationSize = 1, name = "interventi_mitigazione_polveri_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "tipologia", length = 255)
	private String tipologia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterventoMitigazionePolveri other = (InterventoMitigazionePolveri) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("interventoMitigazionePolveri [id=");
		builder.append(id);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append("]");
		return builder.toString();
	}

}
