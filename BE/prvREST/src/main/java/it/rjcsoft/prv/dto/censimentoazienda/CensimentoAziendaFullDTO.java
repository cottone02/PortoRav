package it.rjcsoft.prv.dto.censimentoazienda;

import java.sql.Timestamp;

import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.dto.CodiceIstatAttivitaDTO;
import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class CensimentoAziendaFullDTO {

	protected Integer id;

	protected String anno;

	protected Integer companyId;

	protected String unitaProduttiva;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	protected Polygon indirizzoImpiantoGeo;

	protected String indirizzoImpianto;

	protected CodiceIstatAttivitaDTO codiceIstatAttivita;

	protected Float superficieTotale;

	protected Float superficieTotaleOccupata;

	protected Integer numDipendenti;

	protected Float consumiIdriciAnnui;

	protected Float scarichiIdriciAnnui;

	protected Boolean sistTrattamentoAcqueMeteoriche;

	protected String sistTrattamentoAcqueDesc;

	protected Float rifiutiTotaliProdotti;

	protected Float rifiutiTotaliProdottiPericolosi;

	protected Boolean autorizzazioneEmissioniConvogliate;

	protected String autorizzazioneEmissioniConvogliateDesc;

	protected Timestamp created;

	protected Integer createdBy;

	protected Timestamp updated;

	protected Integer updatedBy;

	protected Integer[] componentCounter;

	protected Boolean canDelete;

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

	public CodiceIstatAttivitaDTO getCodiceIstatAttivita() {
		return codiceIstatAttivita;
	}

	public void setCodiceIstatAttivita(CodiceIstatAttivitaDTO codiceIstatAttivita) {
		this.codiceIstatAttivita = codiceIstatAttivita;
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

	public Integer[] getComponentCounter() {
		return componentCounter;
	}

	public void setComponentCounter(Integer[] is) {
		this.componentCounter = is;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CensimentoAziendaFullDTO [id=");
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
		builder.append(", codiceIstatAttivita=");
		builder.append(codiceIstatAttivita);
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
		builder.append(", componentCounter=");
		builder.append(componentCounter);
		builder.append(", canDelete=");
		builder.append(canDelete);
		builder.append("]");
		return builder.toString();
	}

}
