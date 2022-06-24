package it.rjcsoft.prv.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "censimenti_dotazioni_azienda")
@Table(name = "censimenti_dotazioni_azienda")
public class CensimentoDotazioniAzienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "censimenti_dotazioni_azienda_id_seq", allocationSize = 1, name = "censimenti_dotazioni_azienda_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "censimento_azienda_id")
	private Integer censimentoAziendaId;

//	@Column(name = "dotazione_id")
//	private Integer dotazioneId;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = Dotazione.class)
	@JoinColumn(name = "dotazione_id")
	private Dotazione dotazione;

	@Column(name = "quantita")
	private Integer quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public Dotazione getDotazione() {
		return dotazione;
	}

	public void setDotazione(Dotazione dotazione) {
		this.dotazione = dotazione;
	}

//	public Integer getDotazioneId() {
//		return dotazioneId;
//	}
//
//	public void setDotazioneId(Integer dotazioneId) {
//		this.dotazioneId = dotazioneId;
//	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
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
		CensimentoDotazioniAzienda other = (CensimentoDotazioniAzienda) obj;
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
		builder.append("CensimentoDotazioniAzienda [id=");
		builder.append(id);
		builder.append(", censimentoAziendaId=");
		builder.append(censimentoAziendaId);
//		builder.append(", dotazioneId=");
//		builder.append(dotazioneId);
		builder.append(", dotazione=");
		builder.append(dotazione);
		builder.append(", quantita=");
		builder.append(quantita);
		builder.append("]");
		return builder.toString();
	}

}
