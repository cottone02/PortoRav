package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "tipo_lavorazioni")
@Table(name = "tipo_lavorazioni")
public class TipoLavorazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "tipo_lavorazioni_id_seq", allocationSize = 1, name = "tipo_lavorazioni_id_seq")
	@Column(name = "tipo", nullable = false)
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
		builder.append("TipoLavorazione [tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}

}
