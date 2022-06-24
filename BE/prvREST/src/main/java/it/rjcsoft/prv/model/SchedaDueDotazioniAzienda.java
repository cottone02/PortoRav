package it.rjcsoft.prv.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "scheda_2_dotazioni_azienda")
@Table(name = "scheda_2_dotazioni_azienda")
public class SchedaDueDotazioniAzienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "scheda_due_id_seq", allocationSize = 1, name = "scheda_2_dotazioni_azienda_id_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "gru_fisse_con_benna")
	private Integer gruFisseBenna;

	@Column(name = "gru_fisse_con_benna_gomma")
	private Integer gruFisseBennaGomma;

	@Column(name = "gru_mobili_con_benna")
	private Integer gruMobiliBenna;

	@Column(name = "gru_mobili_con_benna_gomma")
	private Integer gruMobiliBennaGomma;

	@Column(name = "aspiratore")
	private Integer aspiratore;

	@Column(name = "trasporto_pneumatico")
	private Integer trasportoPneumatico;

	@Column(name = "tramoggia_scarico_semplice")
	private Integer tramoggiaSemplice;

	@Column(name = "tramoggia_scarico_aspirata")
	private Integer tramoggiaAspirata;

	@Column(name = "tramoggia_altro_tipo")
	private Integer tramoggiaAltro;

	@Column(name = "tramoggia_altro_tipo_desc", length = 100)
	private String tramoggiaAltroDesc;

	@Column(name = "pale_gommate")
	private Integer paleGommate;

	@Column(name = "spazzatrici")
	private Integer spazzatrici;

	@Column(name = "muletti")
	private Integer muletti;

	@Column(name = "autocisterne")
	private Integer autocisterne;

	@Column(name = "nastri_trasportatori")
	private Integer nastriTrasportatori;

	@Column(name = "reedler")
	private Integer reedler;

	@Column(name = "depositi_chiusi_confezionamento")
	private Integer depositiChiusiConfezionamento;

	@Column(name = "depositi_chiusi_macinazione")
	private Integer depositiChiusiMacinazione;

	@Column(name = "depositi_chiusi_altro_tipo")
	private Integer depositiChiusiAltro;

	@Column(name = "depositi_chiusi_altro_tipo_desc")
	private String depositiChiusiAltroDesc;

	@Column(name = "depositi_aperto_confezionamento")
	private Integer depositiApertoConfezionamento;

	@Column(name = "depositi_aperto_macinazione")
	private Integer depositiApertoMacinazione;

	@Column(name = "depositi_aperto_altro_tipo")
	private Integer depositiApertoAltro;

	@Column(name = "depositi_aperto_altro_tipo_desc")
	private String depositiApertoAltroDesc;

	@Column(name = "impianti_irrigazione_cumuli")
	private Boolean impiantiIrrigazioneCumuli;

	@Column(name = "impianti_irrigazione_strade")
	private Boolean impiantiIrrigazioneStrade;

	@Column(name = "utilizzo_prodotti_aggreganti")
	private Boolean utilizzoProdottiAggreganti;

	@Column(name = "autocisterne_strade_interne")
	private Boolean autocisterneStradeInterne;

	@Column(name = "barriere_fisse_contenimento")
	private Boolean barriereFisseContenimento;

	@Column(name = "lavaggio_ruote_camion")
	private Boolean lavaggioRuoteCamion;

	@Column(name = "pulizia_camion")
	private Boolean puliziaCamion;

	@Column(name = "coperture_camion_prodotti_sfusi")
	private Boolean copertureCamionProdottiSfusi;

	@Column(name = "percorsi_separati_camion_sistemi")
	private Boolean percorsiSeparatiCamionSistemi;

	@Column(name = "presenza_procedura_movimentazione")
	private Boolean presenzaProceduraMovimentazione;

	@Column(name = "presenza_operatore_verifica")
	private Boolean presenzaOperatoreVerifica;

	@Column(name = "created")
	private Timestamp created;

	@Column(name = "updated")
	private Timestamp updated;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "anno")
	private String anno;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "num_scheda")
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
		SchedaDueDotazioniAzienda other = (SchedaDueDotazioniAzienda) obj;
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
		builder.append("SchedaDueDotazioniAzienda [id=");
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
