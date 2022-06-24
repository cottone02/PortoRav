package it.rjcsoft.prv.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SchedaDueDotazioniAziendaDto {
	
	private Integer id;

	private Integer gruFisseBenna;

	private Integer gruFisseBennaGomma;

	private Integer gruMobiliBenna;

	private Integer gruMobiliBennaGomma;

	private Integer aspiratore;

	private Integer trasportoPneumatico;

	private Integer tramoggiaSemplice;

	private Integer tramoggiaAspirata;

	private Integer tramoggiaAltro;
	
	@Size(max = 100)
	private String tramoggiaAltroDesc;

	private Integer paleGommate;

	private Integer spazzatrici;

	private Integer muletti;

	private Integer autocisterne;

	private Integer nastriTrasportatori;

	private Integer reedler;

	private Integer depositiChiusiConfezionamento;

	private Integer depositiChiusiMacinazione;

	private Integer depositiChiusiAltro;
	
	@Size(max = 100)
	private String depositiChiusiAltroDesc;

	private Integer depositiApertoConfezionamento;

	private Integer depositiApertoMacinazione;

	private Integer depositiApertoAltro;

	@Size(max = 100)
	private String depositiApertoAltroDesc;

	private Boolean impiantiIrrigazioneCumuli;

	private Boolean impiantiIrrigazioneStrade;

	private Boolean utilizzoProdottiAggreganti;

	private Boolean autocisterneStradeInterne;

	private Boolean barriereFisseContenimento;

	private Boolean lavaggioRuoteCamion;

	private Boolean puliziaCamion;

	private Boolean copertureCamionProdottiSfusi;

	private Boolean percorsiSeparatiCamionSistemi;

	private Boolean presenzaProceduraMovimentazione;

	private Boolean presenzaOperatoreVerifica;

	private Timestamp created;

	private Timestamp updated;

	private Integer createdBy;
	
	@NotNull
	@Size(min = 4, max = 4)
	private String anno;

	private Integer updatedBy;

	private Integer numScheda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGruFisseBenna() {
		return gruFisseBenna;
	}

	public void setGruFisseBenna(Integer gruFisseBenna) {
		this.gruFisseBenna = gruFisseBenna;
	}

	public Integer getGruFisseBennaGomma() {
		return gruFisseBennaGomma;
	}

	public void setGruFisseBennaGomma(Integer gruFisseBennaGomma) {
		this.gruFisseBennaGomma = gruFisseBennaGomma;
	}

	public Integer getGruMobiliBenna() {
		return gruMobiliBenna;
	}

	public void setGruMobiliBenna(Integer gruMobiliBenna) {
		this.gruMobiliBenna = gruMobiliBenna;
	}

	public Integer getGruMobiliBennaGomma() {
		return gruMobiliBennaGomma;
	}

	public void setGruMobiliBennaGomma(Integer gruMobiliBennaGomma) {
		this.gruMobiliBennaGomma = gruMobiliBennaGomma;
	}

	public Integer getAspiratore() {
		return aspiratore;
	}

	public void setAspiratore(Integer aspiratore) {
		this.aspiratore = aspiratore;
	}

	public Integer getTrasportoPneumatico() {
		return trasportoPneumatico;
	}

	public void setTrasportoPneumatico(Integer trasportoPneumatico) {
		this.trasportoPneumatico = trasportoPneumatico;
	}

	public Integer getTramoggiaSemplice() {
		return tramoggiaSemplice;
	}

	public void setTramoggiaSemplice(Integer tramoggiaSemplice) {
		this.tramoggiaSemplice = tramoggiaSemplice;
	}

	public Integer getTramoggiaAspirata() {
		return tramoggiaAspirata;
	}

	public void setTramoggiaAspirata(Integer tramoggiaAspirata) {
		this.tramoggiaAspirata = tramoggiaAspirata;
	}

	public Integer getTramoggiaAltro() {
		return tramoggiaAltro;
	}

	public void setTramoggiaAltro(Integer tramoggiaAltro) {
		this.tramoggiaAltro = tramoggiaAltro;
	}

	public String getTramoggiaAltroDesc() {
		return tramoggiaAltroDesc;
	}

	public void setTramoggiaAltroDesc(String tramoggiaAltroDesc) {
		this.tramoggiaAltroDesc = tramoggiaAltroDesc;
	}

	public Integer getPaleGommate() {
		return paleGommate;
	}

	public void setPaleGommate(Integer paleGommate) {
		this.paleGommate = paleGommate;
	}

	public Integer getSpazzatrici() {
		return spazzatrici;
	}

	public void setSpazzatrici(Integer spazzatrici) {
		this.spazzatrici = spazzatrici;
	}

	public Integer getMuletti() {
		return muletti;
	}

	public void setMuletti(Integer muletti) {
		this.muletti = muletti;
	}

	public Integer getAutocisterne() {
		return autocisterne;
	}

	public void setAutocisterne(Integer autocisterne) {
		this.autocisterne = autocisterne;
	}

	public Integer getNastriTrasportatori() {
		return nastriTrasportatori;
	}

	public void setNastriTrasportatori(Integer nastriTrasportatori) {
		this.nastriTrasportatori = nastriTrasportatori;
	}

	public Integer getReedler() {
		return reedler;
	}

	public void setReedler(Integer reedler) {
		this.reedler = reedler;
	}

	public Integer getDepositiChiusiConfezionamento() {
		return depositiChiusiConfezionamento;
	}

	public void setDepositiChiusiConfezionamento(Integer depositiChiusiConfezionamento) {
		this.depositiChiusiConfezionamento = depositiChiusiConfezionamento;
	}

	public Integer getDepositiChiusiMacinazione() {
		return depositiChiusiMacinazione;
	}

	public void setDepositiChiusiMacinazione(Integer depositiChiusiMacinazione) {
		this.depositiChiusiMacinazione = depositiChiusiMacinazione;
	}

	public Integer getDepositiChiusiAltro() {
		return depositiChiusiAltro;
	}

	public void setDepositiChiusiAltro(Integer depositiChiusiAltro) {
		this.depositiChiusiAltro = depositiChiusiAltro;
	}

	public String getDepositiChiusiAltroDesc() {
		return depositiChiusiAltroDesc;
	}

	public void setDepositiChiusiAltroDesc(String depositiChiusiAltroDesc) {
		this.depositiChiusiAltroDesc = depositiChiusiAltroDesc;
	}

	public Integer getDepositiApertoConfezionamento() {
		return depositiApertoConfezionamento;
	}

	public void setDepositiApertoConfezionamento(Integer depositiApertoConfezionamento) {
		this.depositiApertoConfezionamento = depositiApertoConfezionamento;
	}

	public Integer getDepositiApertoMacinazione() {
		return depositiApertoMacinazione;
	}

	public void setDepositiApertoMacinazione(Integer depositiApertoMacinazione) {
		this.depositiApertoMacinazione = depositiApertoMacinazione;
	}

	public Integer getDepositiApertoAltro() {
		return depositiApertoAltro;
	}

	public void setDepositiApertoAltro(Integer depositiApertoAltro) {
		this.depositiApertoAltro = depositiApertoAltro;
	}

	public String getDepositiApertoAltroDesc() {
		return depositiApertoAltroDesc;
	}

	public void setDepositiApertoAltroDesc(String depositiApertoAltroDesc) {
		this.depositiApertoAltroDesc = depositiApertoAltroDesc;
	}

	public Boolean getImpiantiIrrigazioneCumuli() {
		return impiantiIrrigazioneCumuli;
	}

	public void setImpiantiIrrigazioneCumuli(Boolean impiantiIrrigazioneCumuli) {
		this.impiantiIrrigazioneCumuli = impiantiIrrigazioneCumuli;
	}

	public Boolean getImpiantiIrrigazioneStrade() {
		return impiantiIrrigazioneStrade;
	}

	public void setImpiantiIrrigazioneStrade(Boolean impiantiIrrigazioneStrade) {
		this.impiantiIrrigazioneStrade = impiantiIrrigazioneStrade;
	}

	public Boolean getUtilizzoProdottiAggreganti() {
		return utilizzoProdottiAggreganti;
	}

	public void setUtilizzoProdottiAggreganti(Boolean utilizzoProdottiAggreganti) {
		this.utilizzoProdottiAggreganti = utilizzoProdottiAggreganti;
	}

	public Boolean getAutocisterneStradeInterne() {
		return autocisterneStradeInterne;
	}

	public void setAutocisterneStradeInterne(Boolean autocisterneStradeInterne) {
		this.autocisterneStradeInterne = autocisterneStradeInterne;
	}

	public Boolean getBarriereFisseContenimento() {
		return barriereFisseContenimento;
	}

	public void setBarriereFisseContenimento(Boolean barriereFisseContenimento) {
		this.barriereFisseContenimento = barriereFisseContenimento;
	}

	public Boolean getLavaggioRuoteCamion() {
		return lavaggioRuoteCamion;
	}

	public void setLavaggioRuoteCamion(Boolean lavaggioRuoteCamion) {
		this.lavaggioRuoteCamion = lavaggioRuoteCamion;
	}

	public Boolean getPuliziaCamion() {
		return puliziaCamion;
	}

	public void setPuliziaCamion(Boolean puliziaCamion) {
		this.puliziaCamion = puliziaCamion;
	}

	public Boolean getCopertureCamionProdottiSfusi() {
		return copertureCamionProdottiSfusi;
	}

	public void setCopertureCamionProdottiSfusi(Boolean copertureCamionProdottiSfusi) {
		this.copertureCamionProdottiSfusi = copertureCamionProdottiSfusi;
	}

	public Boolean getPercorsiSeparatiCamionSistemi() {
		return percorsiSeparatiCamionSistemi;
	}

	public void setPercorsiSeparatiCamionSistemi(Boolean percorsiSeparatiCamionSistemi) {
		this.percorsiSeparatiCamionSistemi = percorsiSeparatiCamionSistemi;
	}

	public Boolean getPresenzaProceduraMovimentazione() {
		return presenzaProceduraMovimentazione;
	}

	public void setPresenzaProceduraMovimentazione(Boolean presenzaProceduraMovimentazione) {
		this.presenzaProceduraMovimentazione = presenzaProceduraMovimentazione;
	}

	public Boolean getPresenzaOperatoreVerifica() {
		return presenzaOperatoreVerifica;
	}

	public void setPresenzaOperatoreVerifica(Boolean presenzaOperatoreVerifica) {
		this.presenzaOperatoreVerifica = presenzaOperatoreVerifica;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getNumScheda() {
		return numScheda;
	}

	public void setNumScheda(Integer numScheda) {
		this.numScheda = numScheda;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchedaDueDotazioniAziendaDto [id=");
		builder.append(id);
		builder.append(", gruFisseBenna=");
		builder.append(gruFisseBenna);
		builder.append(", gruFisseBennaGomma=");
		builder.append(gruFisseBennaGomma);
		builder.append(", gruMobiliBenna=");
		builder.append(gruMobiliBenna);
		builder.append(", gruMobiliBennaGomma=");
		builder.append(gruMobiliBennaGomma);
		builder.append(", aspiratore=");
		builder.append(aspiratore);
		builder.append(", trasportoPneumatico=");
		builder.append(trasportoPneumatico);
		builder.append(", tramoggiaSemplice=");
		builder.append(tramoggiaSemplice);
		builder.append(", tramoggiaAspirata=");
		builder.append(tramoggiaAspirata);
		builder.append(", tramoggiaAltro=");
		builder.append(tramoggiaAltro);
		builder.append(", tramoggiaAltroDesc=");
		builder.append(tramoggiaAltroDesc);
		builder.append(", paleGommate=");
		builder.append(paleGommate);
		builder.append(", spazzatrici=");
		builder.append(spazzatrici);
		builder.append(", muletti=");
		builder.append(muletti);
		builder.append(", autocisterne=");
		builder.append(autocisterne);
		builder.append(", nastriTrasportatori=");
		builder.append(nastriTrasportatori);
		builder.append(", reedler=");
		builder.append(reedler);
		builder.append(", depositiChiusiConfezionamento=");
		builder.append(depositiChiusiConfezionamento);
		builder.append(", depositiChiusiMacinazione=");
		builder.append(depositiChiusiMacinazione);
		builder.append(", depositiChiusiAltro=");
		builder.append(depositiChiusiAltro);
		builder.append(", depositiChiusiAltroDesc=");
		builder.append(depositiChiusiAltroDesc);
		builder.append(", depositiApertoConfezionamento=");
		builder.append(depositiApertoConfezionamento);
		builder.append(", depositiApertoMacinazione=");
		builder.append(depositiApertoMacinazione);
		builder.append(", depositiApertoAltro=");
		builder.append(depositiApertoAltro);
		builder.append(", depositiApertoAltroDesc=");
		builder.append(depositiApertoAltroDesc);
		builder.append(", impiantiIrrigazioneCumuli=");
		builder.append(impiantiIrrigazioneCumuli);
		builder.append(", impiantiIrrigazioneStrade=");
		builder.append(impiantiIrrigazioneStrade);
		builder.append(", utilizzoProdottiAggreganti=");
		builder.append(utilizzoProdottiAggreganti);
		builder.append(", autocisterneStradeInterne=");
		builder.append(autocisterneStradeInterne);
		builder.append(", barriereFisseContenimento=");
		builder.append(barriereFisseContenimento);
		builder.append(", lavaggioRuoteCamion=");
		builder.append(lavaggioRuoteCamion);
		builder.append(", puliziaCamion=");
		builder.append(puliziaCamion);
		builder.append(", copertureCamionProdottiSfusi=");
		builder.append(copertureCamionProdottiSfusi);
		builder.append(", percorsiSeparatiCamionSistemi=");
		builder.append(percorsiSeparatiCamionSistemi);
		builder.append(", presenzaProceduraMovimentazione=");
		builder.append(presenzaProceduraMovimentazione);
		builder.append(", presenzaOperatoreVerifica=");
		builder.append(presenzaOperatoreVerifica);
		builder.append(", created=");
		builder.append(created);
		builder.append(", updated=");
		builder.append(updated);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", anno=");
		builder.append(anno);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", numScheda=");
		builder.append(numScheda);
		builder.append("]");
		return builder.toString();
	}

}
