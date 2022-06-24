package it.rjcsoft.prv.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "prodotti")
@Table(name = "prodotti", uniqueConstraints = { @UniqueConstraint(columnNames = { "nome", "polverosita" }) })
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "prodotti_id_seq", allocationSize = 1, name = "prodotti_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@OneToOne(cascade = { CascadeType.REFRESH }, targetEntity = Polverosita.class)
	@JoinColumn(name = "polverosita", referencedColumnName = "tipo")
	private Polverosita polverosita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Polverosita getPolverosita() {
		return polverosita;
	}

	public void setPolverosita(Polverosita polverosita) {
		this.polverosita = polverosita;
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
		Prodotto other = (Prodotto) obj;
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
		builder.append("Prodotto [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", polverosita=");
		builder.append(polverosita);
		builder.append("]");
		return builder.toString();
	}

}
