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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Table(name = "prodotti_dettagli")
@Entity(name = "prodotto_in_transito")
@Where(clause = "tipo = 'PRODOTTO_IN_TRANSITO'")
//@Subselect("select * from prodotti_dettagli pd where tipo = 'PRODOTTO_IN_TRANSITO'")
public class ProdottoInTransito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "prodotti_allegati_id_seq", allocationSize = 1, name = "prodotti_allegati_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "scheda_id", nullable = false)
    private Integer schedaId;

    @Column(name = "in_proprio")
    private Boolean inProprio;

    @Column(name = "conto_terzi")
    private Boolean contoTerzi;

    @Column(name = "azienda_terza")
    private String aziendaTerza;

    @Column(name = "banchina")
    private Integer banchina;
    
    @OneToMany(cascade = { CascadeType.REFRESH }, targetEntity = DettaglioSitoTrasporti.class)
	@JoinColumn(name = "prodotto_dettaglio_id", referencedColumnName = "id")
	private List<DettaglioSitoTrasporti> listaDettaglioSitoTrasporti;
    
    @OneToMany(cascade = { CascadeType.REFRESH }, targetEntity = DettaglioSitoDepositi.class)
	@JoinColumn(name = "prodotto_dettaglio_id", referencedColumnName = "id")
	private List<DettaglioSitoDepositi> listaDettaglioSitoDepositi;

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

    
    
    public List<DettaglioSitoTrasporti> getListaDettaglioSitoTrasporti() {
		return listaDettaglioSitoTrasporti;
	}

	public void setListaDettaglioSitoTrasporti(List<DettaglioSitoTrasporti> listaDettaglioSitoTrasporti) {
		this.listaDettaglioSitoTrasporti = listaDettaglioSitoTrasporti;
	}
	
	public List<DettaglioSitoDepositi> getListaDettaglioSitoDepositi() {
		return listaDettaglioSitoDepositi;
	}

	public void setDettaglioSitoDepositi(List<DettaglioSitoDepositi> listaDettaglioSitoDepositi) {
		this.listaDettaglioSitoDepositi = listaDettaglioSitoDepositi;
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
        ProdottoInTransito other = (ProdottoInTransito) obj;
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
        builder.append("ProdottoInTransito [aziendaTerza=");
        builder.append(aziendaTerza);
        builder.append(", banchina=");
        builder.append(banchina);
        builder.append(", contoTerzi=");
        builder.append(contoTerzi);
        builder.append(", id=");
        builder.append(id);
        builder.append(", inProprio=");
        builder.append(inProprio);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append(", listaDettaglioSitoTrasporti=");
		builder.append(listaDettaglioSitoTrasporti);
		builder.append(", listaDettaglioSitoDepositi=");
		builder.append(listaDettaglioSitoDepositi);
        builder.append("]");
        return builder.toString();
    }

}
