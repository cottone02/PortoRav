package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "prodotti_dettagli")
@Table(name = "prodotti_dettagli")
public class ProdottoDettagli {

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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProdottoDettagli [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", movViaFerrovia=");
        builder.append(movViaFerrovia);
        builder.append(", movViaNave=");
        builder.append(movViaNave);
        builder.append(", movViaTerra=");
        builder.append(movViaTerra);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        return builder.toString();
    }

        

}
