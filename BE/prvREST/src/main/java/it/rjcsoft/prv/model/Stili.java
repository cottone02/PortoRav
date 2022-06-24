package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity(name = "stili")
@Table(name = "stili")
public class Stili {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "stili_id_seq", allocationSize = 1, name = "stili_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "id_geotiff", nullable = false)
	private Integer idGeotiff;

	@Column(name = "geotiff_base64", nullable = false)
	private String geotiffBase64;

	@Column(name = "legend_base64", nullable = false)
	private String legendBase64;

	@Column(name = "stato", nullable = false, columnDefinition = "boolean default false")
	private Boolean stato;

	@Column(name = "descrizione")
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdGeotiff() {
		return idGeotiff;
	}

	public void setIdGeotiff(Integer idGeotiff) {
		this.idGeotiff = idGeotiff;
	}

	public String getGeotiffBase64() {
		return geotiffBase64;
	}

	public void setGeotiffBase64(String geotiffBase64) {
		this.geotiffBase64 = geotiffBase64;
	}

	public String getLegendBase64() {
		return legendBase64;
	}

	public void setLegendBase64(String legendBase64) {
		this.legendBase64 = legendBase64;
	}

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		Stili other = (Stili) obj;
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
		builder.append("Stili [id=");
		builder.append(id);
		builder.append(", idGeotiff=");
		builder.append(idGeotiff);
		builder.append(", geotiffBase64=");
		builder.append(StringUtils.substring(geotiffBase64, 0, 10) + "...");
		builder.append(", legendBase64=");
		builder.append(StringUtils.substring(legendBase64, 0, 10) + "...");
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", descrizione=");
		builder.append(descrizione);
		builder.append("]");
		return builder.toString();
	}

}
