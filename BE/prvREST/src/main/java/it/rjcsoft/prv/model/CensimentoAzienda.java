package it.rjcsoft.prv.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Polygon;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

@Entity(name = "censimenti_azienda")
@Table(name = "censimenti_azienda")
public class CensimentoAzienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "censimenti_azienda_id_seq", allocationSize = 1, name = "censimenti_azienda_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "anno", nullable = false, length = 4)
	private String anno;

	@Column(name = "company_id", nullable = false)
	private Integer companyId;

	@Column(name = "unita_produttiva", length = 100)
	private String unitaProduttiva;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	@Column(name = "indirizzo_impianto_geo", nullable = true)
	private Polygon indirizzoImpiantoGeo;

	@Column(name = "indirizzo_impianto", length = 100)
	private String indirizzoImpianto;

	@Column(name = "cod_istat_attivita", length = 8)
	private String codIstatAttivita;

	@Column(name = "superficie_totale")
	private Float superficieTotale;

	@Column(name = "superficie_totale_occupata")
	private Float superficieTotaleOccupata;

	@Column(name = "num_dipendenti")
	private Integer numDipendenti;

	@Column(name = "consumi_idrici_annui")
	private Float consumiIdriciAnnui;

	@Column(name = "scarichi_idrici_annui")
	private Float scarichiIdriciAnnui;

	@Column(name = "sist_trattamento_acque_meteoriche")
	private Boolean sistTrattamentoAcqueMeteoriche;

	@Column(name = "sist_trattamento_acque_desc", length = 100)
	private String sistTrattamentoAcqueDesc;

	@Column(name = "rifiuti_totali_prodotti")
	private Float rifiutiTotaliProdotti;

	@Column(name = "rifiuti_totali_prodotti_pericolosi")
	private Float rifiutiTotaliProdottiPericolosi;

	@Column(name = "autorizzazione_emissioni_convogliate", length = 100)
	private Boolean autorizzazioneEmissioniConvogliate;

	@Column(name = "autorizzazione_emissioni_convogliate_desc")
	private String autorizzazioneEmissioniConvogliateDesc;

	@CreationTimestamp
	@Column(name = "created", nullable = false)
	private Timestamp created;

	@Column(name = "created_by", nullable = false)
	private Integer createdBy;

	@Column(name = "updated")
	private Timestamp updated;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getUnitaProduttiva() {
		return unitaProduttiva;
	}

	public void setUnitaProduttiva(String unitaProduttiva) {
		this.unitaProduttiva = unitaProduttiva;
	}

	public Polygon getIndirizzoImpiantoGeo() {
		return indirizzoImpiantoGeo;
	}

	public void setIndirizzoImpiantoGeo(Polygon indirizzoImpiantoGeo) {
		this.indirizzoImpiantoGeo = indirizzoImpiantoGeo;
	}

	public String getIndirizzoImpianto() {
		return indirizzoImpianto;
	}

	public void setIndirizzoImpianto(String indirizzoImpianto) {
		this.indirizzoImpianto = indirizzoImpianto;
	}

	public String getCodIstatAttivita() {
		return codIstatAttivita;
	}

	public void setCodIstatAttivita(String codIstatAttivita) {
		this.codIstatAttivita = codIstatAttivita;
	}

	public Float getSuperficieTotale() {
		return superficieTotale;
	}

	public void setSuperficieTotale(Float superficieTotale) {
		this.superficieTotale = superficieTotale;
	}

	public Float getSuperficieTotaleOccupata() {
		return superficieTotaleOccupata;
	}

	public void setSuperficieTotaleOccupata(Float superficieTotaleOccupata) {
		this.superficieTotaleOccupata = superficieTotaleOccupata;
	}

	public Integer getNumDipendenti() {
		return numDipendenti;
	}

	public void setNumDipendenti(Integer numDipendenti) {
		this.numDipendenti = numDipendenti;
	}

	public Float getConsumiIdriciAnnui() {
		return consumiIdriciAnnui;
	}

	public void setConsumiIdriciAnnui(Float consumiIdriciAnnui) {
		this.consumiIdriciAnnui = consumiIdriciAnnui;
	}

	public Float getScarichiIdriciAnnui() {
		return scarichiIdriciAnnui;
	}

	public void setScarichiIdriciAnnui(Float scarichiIdriciAnnui) {
		this.scarichiIdriciAnnui = scarichiIdriciAnnui;
	}

	public Boolean getSistTrattamentoAcqueMeteoriche() {
		return sistTrattamentoAcqueMeteoriche;
	}

	public void setSistTrattamentoAcqueMeteoriche(Boolean sistTrattamentoAcqueMeteoriche) {
		this.sistTrattamentoAcqueMeteoriche = sistTrattamentoAcqueMeteoriche;
	}

	public String getSistTrattamentoAcqueDesc() {
		return sistTrattamentoAcqueDesc;
	}

	public void setSistTrattamentoAcqueDesc(String sistTrattamentoAcqueDesc) {
		this.sistTrattamentoAcqueDesc = sistTrattamentoAcqueDesc;
	}

	public Float getRifiutiTotaliProdotti() {
		return rifiutiTotaliProdotti;
	}

	public void setRifiutiTotaliProdotti(Float rifiutiTotaliProdotti) {
		this.rifiutiTotaliProdotti = rifiutiTotaliProdotti;
	}

	public Float getRifiutiTotaliProdottiPericolosi() {
		return rifiutiTotaliProdottiPericolosi;
	}

	public void setRifiutiTotaliProdottiPericolosi(Float rifiutiTotaliProdottiPericolosi) {
		this.rifiutiTotaliProdottiPericolosi = rifiutiTotaliProdottiPericolosi;
	}

	public Boolean getAutorizzazioneEmissioniConvogliate() {
		return autorizzazioneEmissioniConvogliate;
	}

	public void setAutorizzazioneEmissioniConvogliate(Boolean autorizzazioneEmissioniConvogliate) {
		this.autorizzazioneEmissioniConvogliate = autorizzazioneEmissioniConvogliate;
	}

	public String getAutorizzazioneEmissioniConvogliateDesc() {
		return autorizzazioneEmissioniConvogliateDesc;
	}

	public void setAutorizzazioneEmissioniConvogliateDesc(String autorizzazioneEmissioniConvogliateDesc) {
		this.autorizzazioneEmissioniConvogliateDesc = autorizzazioneEmissioniConvogliateDesc;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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
		CensimentoAzienda other = (CensimentoAzienda) obj;
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
		builder.append("CensimentoAzienda [id=");
		builder.append(id);
		builder.append(", anno=");
		builder.append(anno);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", unitaProduttiva=");
		builder.append(unitaProduttiva);
		builder.append(", indirizzoImpiantoGeo=");
		builder.append(indirizzoImpiantoGeo);
		builder.append(", indirizzoImpianto=");
		builder.append(indirizzoImpianto);
		builder.append(", codIstatAttivita=");
		builder.append(codIstatAttivita);
		builder.append(", superficieTotale=");
		builder.append(superficieTotale);
		builder.append(", superficieTotaleOccupata=");
		builder.append(superficieTotaleOccupata);
		builder.append(", numDipendenti=");
		builder.append(numDipendenti);
		builder.append(", consumiIdriciAnnui=");
		builder.append(consumiIdriciAnnui);
		builder.append(", scarichiIdriciAnnui=");
		builder.append(scarichiIdriciAnnui);
		builder.append(", sistTrattamentoAcqueMeteoriche=");
		builder.append(sistTrattamentoAcqueMeteoriche);
		builder.append(", sistTrattamentoAcqueDesc=");
		builder.append(sistTrattamentoAcqueDesc);
		builder.append(", rifiutiTotaliProdotti=");
		builder.append(rifiutiTotaliProdotti);
		builder.append(", rifiutiTotaliProdottiPericolosi=");
		builder.append(rifiutiTotaliProdottiPericolosi);
		builder.append(", autorizzazioneEmissioniConvogliate=");
		builder.append(autorizzazioneEmissioniConvogliate);
		builder.append(", autorizzazioneEmissioniConvogliateDesc=");
		builder.append(autorizzazioneEmissioniConvogliateDesc);
		builder.append(", created=");
		builder.append(created);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updated=");
		builder.append(updated);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append("]");
		return builder.toString();
	}

}
