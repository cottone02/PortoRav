package it.rjcsoft.prv.dto.censimentoazienda;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CensimentoUpdateDTO {
	
	@NotNull(message = "il campo id è obbligatorio")
	private Integer id;

	private String anno;

	private Integer companyId;

	private String unitaProduttiva;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	private Polygon indirizzoImpiantoGeo;

	private String indirizzoImpianto;

	private String codIstatAttivita;

	private Float superficieTotale;

	private Float superficieTotaleOccupata;

	private Integer numDipendenti;

	private Float consumiIdriciAnnui;

	private Float scarichiIdriciAnnui;

	private Boolean sistTrattamentoAcqueMeteoriche;

	private String sistTrattamentoAcqueDesc;

	private Float rifiutiTotaliProdotti;

	private Float rifiutiTotaliProdottiPericolosi;

	private Boolean autorizzazioneEmissioniConvogliate;

	private String autorizzazioneEmissioniConvogliateDesc;

	private Timestamp created;

	private Integer createdBy;

	private Timestamp updated;

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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoAziendaDTO [id=");
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
