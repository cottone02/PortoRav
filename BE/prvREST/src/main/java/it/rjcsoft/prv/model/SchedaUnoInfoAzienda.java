package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "scheda_1_info_azienda")
@Table(name = "scheda_1_info_azienda")
public class SchedaUnoInfoAzienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "sheda_uno_id_seq", allocationSize = 1, name = "scheda_1_info_azienda_id_seq")
    @Column(name = "id", nullable = false)
	private Integer id;
	
	@SequenceGenerator(sequenceName = "num_scheda_seq", allocationSize = 1, name = "num_scheda_SEQ")
	@Column(name = "num_scheda")
	private Integer numScheda;

	@Column(name = "anno", length = 4)
	private String anno;

	@Column(name = "unita_produttiva", length = 100)
	private String unitaProduttiva;

	@Column(name = "indirizzo_impianto", length = 100)
	private Integer indirizzoImpianto;

	@Column(name = "codIstatAttivita")
	private Integer codIstatAttivita;

	@Column(name = "superficieTotaleOccupata")
	private Double supTotOccupata;

	@Column(name = "superficieTotaleCoperta")
	private Double supTotCoperta;

	@Column(name = "num_dipendenti")
	private Integer numDipendenti;

	@Column(name = "consumi_idrici_annui")
	private Double consumiIdriciAnnui;

	@Column(name = "scarichi_idrici_annui")
	private Double scarichiIdriciAnnui;

	@Column(name = "sist_trattamento_acque_meteoriche")
	private Boolean sistTrattAcqueMet;

	@Column(name = "sist_trattamento_acque_desc", length = 100)
	private String sistTrattAcqueDesc;

	@Column(name = "rifiuti_totali_prodotti")
	private Double rifiutiTotProdotti;

	@Column(name = "rifiuti_totali_prodotti_pericolosi")
	private Double rifiutiTotProdottiPericolosi;

	@Column(name = "autorizzazione_emissioni_convogliate")
	private Boolean autEmissioniConvogliate;

	@Column(name = "autorizzazione_emissioni_convogliate_desc", length = 100)
	private String autEmissioniConvogliateDesc;

	private java.sql.Timestamp created;

	@Column(name = "created_by")
	private Integer createdBy;

	private java.sql.Timestamp updated;

	@Column(name = "updated_by")
	private Integer updatedBy;

	public SchedaUnoInfoAzienda() {

	}

	public Integer getNumScheda() {
		return numScheda;
	}

	public void setNumScheda(Integer numScheda) {
		this.numScheda = numScheda;
	}

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

	public String getUnitaProduttiva() {
		return unitaProduttiva;
	}

	public void setUnitaProduttiva(String unitaProduttiva) {
		this.unitaProduttiva = unitaProduttiva;
	}

	public Integer getIndirizzoImpianto() {
		return indirizzoImpianto;
	}

	public void setIndirizzoImpianto(Integer indirizzoImpianto) {
		this.indirizzoImpianto = indirizzoImpianto;
	}

	public Integer getCodIstatAttivita() {
		return codIstatAttivita;
	}

	public void setCodIstatAttivita(Integer codIstatAttivita) {
		this.codIstatAttivita = codIstatAttivita;
	}

	public Double getSupTotOccupata() {
		return supTotOccupata;
	}

	public void setSupTotOccupata(Double supTotOccupata) {
		this.supTotOccupata = supTotOccupata;
	}

	public Double getSupTotCoperta() {
		return supTotCoperta;
	}

	public void setSupTotCoperta(Double supTotCoperta) {
		this.supTotCoperta = supTotCoperta;
	}

	public Integer getNumDipendenti() {
		return numDipendenti;
	}

	public void setNumDipendenti(Integer numDipendenti) {
		this.numDipendenti = numDipendenti;
	}

	public Double getConsumiIdriciAnnui() {
		return consumiIdriciAnnui;
	}

	public void setConsumiIdriciAnnui(Double consumiIdriciAnnui) {
		this.consumiIdriciAnnui = consumiIdriciAnnui;
	}

	public Double getScarichiIdriciAnnui() {
		return scarichiIdriciAnnui;
	}

	public void setScarichiIdriciAnnui(Double scarichiIdriciAnnui) {
		this.scarichiIdriciAnnui = scarichiIdriciAnnui;
	}

	public Boolean getSistTrattAcqueMet() {
		return sistTrattAcqueMet;
	}

	public void setSistTrattAcqueMet(Boolean sistTrattAcqueMet) {
		this.sistTrattAcqueMet = sistTrattAcqueMet;
	}

	public String getSistTrattAcqueDesc() {
		return sistTrattAcqueDesc;
	}

	public void setSistTrattAcqueDesc(String sistTrattAcqueDesc) {
		this.sistTrattAcqueDesc = sistTrattAcqueDesc;
	}

	public Double getRifiutiTotProdotti() {
		return rifiutiTotProdotti;
	}

	public void setRifiutiTotProdotti(Double rifiutiTotProdotti) {
		this.rifiutiTotProdotti = rifiutiTotProdotti;
	}

	public Double getRifiutiTotProdottiPericolosi() {
		return rifiutiTotProdottiPericolosi;
	}

	public void setRifiutiTotProdottiPericolosi(Double rifiutiTotProdottiPericolosi) {
		this.rifiutiTotProdottiPericolosi = rifiutiTotProdottiPericolosi;
	}

	public Boolean getAutEmissioniConvogliate() {
		return autEmissioniConvogliate;
	}

	public void setAutEmissioniConvogliate(Boolean autEmissioniConvogliate) {
		this.autEmissioniConvogliate = autEmissioniConvogliate;
	}

	public String getAutEmissioniConvogliateDesc() {
		return autEmissioniConvogliateDesc;
	}

	public void setAutEmissioniConvogliateDesc(String autEmissioniConvogliateDesc) {
		this.autEmissioniConvogliateDesc = autEmissioniConvogliateDesc;
	}

	public java.sql.Timestamp getCreated() {
		return created;
	}

	public void setCreated(java.sql.Timestamp created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public java.sql.Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(java.sql.Timestamp updated) {
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
		SchedaUnoInfoAzienda other = (SchedaUnoInfoAzienda) obj;
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
		builder.append("SchedaUnoInfoAzienda [id=").append(id).append(", numScheda=").append(numScheda)
				.append(", anno=").append(anno).append(", unitaProduttiva=").append(unitaProduttiva)
				.append(", indirizzoImpianto=").append(indirizzoImpianto).append(", codIstatAttivita=")
				.append(codIstatAttivita).append(", supTotOccupata=").append(supTotOccupata).append(", supTotCoperta=")
				.append(supTotCoperta).append(", numDipendenti=").append(numDipendenti).append(", consumiIdriciAnnui=")
				.append(consumiIdriciAnnui).append(", scarichiIdriciAnnui=").append(scarichiIdriciAnnui)
				.append(", sistTrattAcqueMet=").append(sistTrattAcqueMet).append(", sistTrattAcqueDesc=")
				.append(sistTrattAcqueDesc).append(", rifiutiTotProdotti=").append(rifiutiTotProdotti)
				.append(", rifiutiTotProdottiPericolosi=").append(rifiutiTotProdottiPericolosi)
				.append(", autEmissioniConvogliate=").append(autEmissioniConvogliate)
				.append(", autEmissioniConvogliateDesc=").append(autEmissioniConvogliateDesc).append(", created=")
				.append(created).append(", createdBy=").append(createdBy).append(", updated=").append(updated)
				.append(", updatedBy=").append(updatedBy).append("]");
		return builder.toString();
	}

}
