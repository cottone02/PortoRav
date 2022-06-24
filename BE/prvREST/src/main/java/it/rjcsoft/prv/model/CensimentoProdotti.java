package it.rjcsoft.prv.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.locationtech.jts.geom.Polygon;

@Entity(name = "censimenti_prodotti")
@Table(name = "censimenti_prodotti")
public class CensimentoProdotti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "num_scheda_SEQ", allocationSize = 1, name = "num_scheda_SEQ")
	@Column(name = "scheda_id", nullable = false)
	private Integer schedaId;

	@Column(name = "censimento_azienda_id", nullable = false)
	private Integer censimentoAziendaId;

	@OneToOne(cascade = { CascadeType.REFRESH }, targetEntity = Prodotto.class)
	@JoinColumn(name = "prodotto_id", referencedColumnName = "id")
	private Prodotto prodotto;

	// @JsonSerialize(using = PolygonSerializer.class)
	// @JsonDeserialize(using = PolygonDeserializer.class)
	@Column(name = "mappa_stabilimento")
	private Polygon mappaStabilimento;

	@Column(name = "sfuso")
	private Integer sfuso;

	@Column(name = "in_confezioni")
	private Integer inConfezioni;

	@Column(name = "umidita")
	private Integer umidita;

	@Column(name = "densita")
	private Double densita;

	@ManyToOne(cascade = { CascadeType.REFRESH }, targetEntity = ClasseGranulometrica.class)
	@JoinColumn(name = "granulometria_id", referencedColumnName = "id")
	private ClasseGranulometrica classeGranulometrica;

	@Column(name = "silt")
	private Integer silt;

	@OneToMany(cascade = { CascadeType.REFRESH }, targetEntity = ProdottiAllegati.class)
	@JoinColumn(name = "scheda_id", referencedColumnName = "scheda_id")
	private List<ProdottiAllegati> prodottiAllegati;

	public Integer getSchedaId() {
		return schedaId;
	}

	public void setSchedaId(Integer schedaId) {
		this.schedaId = schedaId;
	}

	public Integer getCensimentoAziendaId() {
		return censimentoAziendaId;
	}

	public void setCensimentoAziendaId(Integer censimentoAziendaId) {
		this.censimentoAziendaId = censimentoAziendaId;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Polygon getMappaStabilimento() {
		return mappaStabilimento;
	}

	public void setMappaStabilimento(Polygon mappaStabilimento) {
		this.mappaStabilimento = mappaStabilimento;
	}

	public Integer getSfuso() {
		return sfuso;
	}

	public void setSfuso(Integer sfuso) {
		this.sfuso = sfuso;
	}

	public Integer getInConfezioni() {
		return inConfezioni;
	}

	public void setInConfezioni(Integer inConfezioni) {
		this.inConfezioni = inConfezioni;
	}

	public Integer getUmidita() {
		return umidita;
	}

	public void setUmidita(Integer umidita) {
		this.umidita = umidita;
	}

	public Double getDensita() {
		return densita;
	}

	public void setDensita(Double densita) {
		this.densita = densita;
	}

	public ClasseGranulometrica getClasseGranulometrica() {
		return classeGranulometrica;
	}

	public void setClasseGranulometrica(ClasseGranulometrica classeGranulometrica) {
		this.classeGranulometrica = classeGranulometrica;
	}

	public Integer getSilt() {
		return silt;
	}

	public void setSilt(Integer silt) {
		this.silt = silt;
	}

	public List<ProdottiAllegati> getProdottiAllegati() {
		return prodottiAllegati;
	}

	public void setProdottiAllegati(List<ProdottiAllegati> prodottiAllegati) {
		this.prodottiAllegati = prodottiAllegati;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schedaId == null) ? 0 : schedaId.hashCode());
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
		CensimentoProdotti other = (CensimentoProdotti) obj;
		if (schedaId == null) {
			if (other.schedaId != null)
				return false;
		} else if (!schedaId.equals(other.schedaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoProdotti [censimentoAziendaId=");
		builder.append(censimentoAziendaId);
		builder.append(", classeGranulometrica=");
		builder.append(classeGranulometrica);
		builder.append(", densita=");
		builder.append(densita);
		builder.append(", prodottiAllegati=");
		builder.append(prodottiAllegati);
		builder.append(", inConfezioni=");
		builder.append(inConfezioni);
		builder.append(", mappaStabilimento=");
		builder.append(mappaStabilimento);
		builder.append(", prodotto=");
		builder.append(prodotto);
		builder.append(", schedaId=");
		builder.append(schedaId);
		builder.append(", sfuso=");
		builder.append(sfuso);
		builder.append(", silt=");
		builder.append(silt);
		builder.append(", umidita=");
		builder.append(umidita);
		builder.append("]");
		return builder.toString();
	}

}
