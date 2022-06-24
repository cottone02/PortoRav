package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "prodotti_allegati")
@Table(name = "prodotti_allegati")
public class ProdottiAllegati {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "prodotti_allegati_id_seq", allocationSize = 1, name = "prodotti_allegati_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "scheda_id", nullable = false)
	private Integer schedaId;

	@Column(name = "nome_file", nullable = false, length = 50)
	private String nomeFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchedaId() {
		return schedaId;
	}

	public void setSchedaId(Integer schedaId) {
		this.schedaId = schedaId;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
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
		ProdottiAllegati other = (ProdottiAllegati) obj;
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
		builder.append("ProdottiAllegati [id=");
		builder.append(id);
		builder.append(", nomeFile=");
		builder.append(nomeFile);
		builder.append(", schedaId=");
		builder.append(schedaId);
		builder.append("]");
		return builder.toString();
	}

}
