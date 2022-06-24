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

@Entity(name = "lavorazioni_prodotto")
@Table(name = "lavorazioni_prodotto")
public class LavorazioneProdottoDettagli {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "lavorazioni_prodotto_id_seq", allocationSize = 1, name = "lavorazioni_prodotto_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	//@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = ProdottoDettagli.class)
	//@JoinColumn(name = "prodotto_dettaglio_id")
	@Column(name="prodotto_dettaglio_id")
    private Integer prodottoDettaglioId;
	
	public Integer getProdottoDettaglioId() {
		return prodottoDettaglioId;
	}

	public void setProdottoDettaglioId(Integer prodottoDettaglioId) {
		this.prodottoDettaglioId = prodottoDettaglioId;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = Lavorazione.class)
	@JoinColumn(name = "lavorazione_id")
	private Lavorazione lavorazione;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = TipoLavorazione.class)
	@JoinColumn(name = "tipo_lavorazione")
	private TipoLavorazione tipoLavorazione;
	
	@Column(name = "gg_anno")
	private Integer ggAnno;
	
	@Column(name = "ore_gg")
	private Integer oreGg;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public ProdottoDettagli getProdottoDettaglio() {
		return prodottoDettaglio;
	}

	public void setProdottoDettaglio(ProdottoDettagli prodottoDettaglio) {
		this.prodottoDettaglio = prodottoDettaglio;
	}*/

	public Lavorazione getLavorazione() {
		return lavorazione;
	}

	public void setLavorazione(Lavorazione lavorazione) {
		this.lavorazione = lavorazione;
	}

	public TipoLavorazione getTipoLavorazione() {
		return tipoLavorazione;
	}

	public void setTipoLavorazione(TipoLavorazione tipoLavorazione) {
		this.tipoLavorazione = tipoLavorazione;
	}

	public Integer getGgAnno() {
		return ggAnno;
	}

	public void setGgAnno(Integer ggAnno) {
		this.ggAnno = ggAnno;
	}

	public Integer getOreGg() {
		return oreGg;
	}

	public void setOreGg(Integer oreGg) {
		this.oreGg = oreGg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LavorazioneProdottoDettagli [id=");
		builder.append(id);
		builder.append(", prodottoDettaglioId=");
		builder.append(prodottoDettaglioId);
		builder.append(", lavorazione=");
		builder.append(lavorazione);
		builder.append(", tipoLavorazione=");
		builder.append(tipoLavorazione);
		builder.append(", ggAnno=");
		builder.append(ggAnno);
		builder.append(", oreGg=");
		builder.append(oreGg);
		builder.append("]");
		return builder.toString();
	}

}
