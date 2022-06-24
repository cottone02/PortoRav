package it.rjcsoft.prv.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Table(name = "prodotti_dettagli")
@Entity(name = "lavorazione_prodotto")
@Where(clause = "tipo = 'LAVORAZIONE_PRODOTTO'")
// @Subselect("select * from prodotti_dettagli pd where tipo =
// 'LAVORAZIONE_PRODOTTO'")

public class LavorazioneProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "prodotti_allegati_id_seq", allocationSize = 1, name = "prodotti_allegati_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "scheda_id", nullable = false)
    private Integer schedaId;

    @Column(name = "mov_via_nave")
    private Double movViaNave;

    @Column(name = "mov_via_terra")
    private Double movViaTerra;

    @Column(name = "mov_via_ferrovia")
    private Double movViaFerrovia;

    @Column(name = "in_proprio")
    private Boolean inProprio;

    @Column(name = "conto_terzi")
    private Boolean contoTerzi;

    @Column(name = "azienda_terza")
    private String aziendaTerza;

    @Column(name = "banchina")
    private Integer banchina;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = LavorazioneProdottoDettagli.class)
    @JoinColumn(name = "prodotto_dettaglio_id", referencedColumnName = "id")
    @OrderBy("id ASC")
    private List<LavorazioneProdottoDettagli> listalavorazioneProdottoDettagli;

    public List<LavorazioneProdottoDettagli> getListalavorazioneProdottoDettagli() {
        return listalavorazioneProdottoDettagli;
    }

    public void setListalavorazioneProdottoDettagli(
            List<LavorazioneProdottoDettagli> listalavorazioneProdottoDettagli) {
        this.listalavorazioneProdottoDettagli = listalavorazioneProdottoDettagli;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchedaId() {
        return schedaId;
    }

    public void setSchedaId(Integer schedaId) {
        this.schedaId = schedaId;
    }

    public Double getMovViaNave() {
        return movViaNave;
    }

    public void setMovViaNave(Double movViaNave) {
        this.movViaNave = movViaNave;
    }

    public Double getMovViaTerra() {
        return movViaTerra;
    }

    public void setMovViaTerra(Double movViaTerra) {
        this.movViaTerra = movViaTerra;
    }

    public Double getMovViaFerrovia() {
        return movViaFerrovia;
    }

    public void setMovViaFerrovia(Double movViaFerrovia) {
        this.movViaFerrovia = movViaFerrovia;
    }

    public Boolean getInProprio() {
        return inProprio;
    }

    public void setInProprio(Boolean inProprio) {
        this.inProprio = inProprio;
    }

    public Boolean getContoTerzi() {
        return contoTerzi;
    }

    public void setContoTerzi(Boolean contoTerzi) {
        this.contoTerzi = contoTerzi;
    }

    public String getAziendaTerza() {
        return aziendaTerza;
    }

    public void setAziendaTerza(String aziendaTerza) {
        this.aziendaTerza = aziendaTerza;
    }

    public Integer getBanchina() {
        return banchina;
    }

    public void setBanchina(Integer banchina) {
        this.banchina = banchina;
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
        LavorazioneProdotto other = (LavorazioneProdotto) obj;
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
        builder.append("LavorazioneProdotto [id=");
        builder.append(id);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append(", movViaNave=");
        builder.append(movViaNave);
        builder.append(", movViaTerra=");
        builder.append(movViaTerra);
        builder.append(", movViaFerrovia=");
        builder.append(movViaFerrovia);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", listalavorazioneProdottoDettagli=");
        builder.append(listalavorazioneProdottoDettagli);
        builder.append("]");
        return builder.toString();
    }

}
