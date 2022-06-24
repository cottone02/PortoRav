package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "dotazioni")
@Table(name = "dotazioni")
public class Dotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "dotazioni_id_seq", allocationSize = 1, name = "dotazioni_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "attrezzatura", length = 255)
	private String attrezzatura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttrezzatura() {
		return attrezzatura;
	}

	public void setAttrezzatura(String attrezzatura) {
		this.attrezzatura = attrezzatura;
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
		Dotazione other = (Dotazione) obj;
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
		builder.append("Dotazione [id=");
		builder.append(id);
		builder.append(", attrezzatura=");
		builder.append(attrezzatura);
		builder.append("]");
		return builder.toString();
	}

}
