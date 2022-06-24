package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "lavorazioni")
@Table(name = "lavorazioni")
public class Lavorazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "lavorazioni_id_seq", allocationSize = 1, name = "lavorazioni_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "lavorazione", length = 255)
	private String lavorazione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLavorazione() {
		return lavorazione;
	}

	public void setLavorazione(String lavorazione) {
		this.lavorazione = lavorazione;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lavorazione [id=");
		builder.append(id);
		builder.append(", lavorazione=");
		builder.append(lavorazione);
		builder.append("]");
		return builder.toString();
	}

}
