package it.rjcsoft.prv.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class SchedaUnoInfoAziendaDto {
	// @EnumNamePattern(regexp = "TEXT|JSON|CSV|MULTIMEDIA")
	// @JsonIgnore
	private Integer id;

	@NotNull
	private Integer numScheda;

	@NotNull
	@Size(min = 4, max = 4)
	private String anno;

	@Size(max = 100)
	private String unitaProduttiva;

	@Size(max = 100)
	private Integer indirizzoImpianto;

	private Integer codIstatAttivita;

	@Positive
	private Double supTotOccupata;

	@PositiveOrZero
	private Double supTotCoperta;

	@PositiveOrZero
	private Integer numDipendenti;

	@PositiveOrZero
	private Double consumiIdriciAnnui;

	@PositiveOrZero
	private Double scarichiIdriciAnnui;

	private Boolean sistTrattAcqueMet;

	@Size(max = 100)
	private String sistTrattAcqueDesc;

	@PositiveOrZero
	private Double rifiutiTotProdotti;

	@PositiveOrZero
	private Double rifiutiTotProdottiPericolosi;

	private Boolean autEmissioniConvogliate;

	@Size(max = 100)
	private String autEmissioniConvogliateDesc;

	private java.sql.Timestamp created;

	private Integer createdBy;

	private java.sql.Timestamp updated;

	private Integer updatedBy;

	public SchedaUnoInfoAziendaDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumScheda() {
		return numScheda;
	}

	public void setNumScheda(Integer numScheda) {
		this.numScheda = numScheda;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchedaUnoInfoAziendaDto [id=").append(id).append(", numScheda=").append(numScheda)
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
