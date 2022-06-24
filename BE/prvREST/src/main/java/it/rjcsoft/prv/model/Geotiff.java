package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

@Entity(name = "geotiff")
@Table(name = "geotiff")
public class Geotiff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "geotiff_id_seq", allocationSize = 1, name = "geotiff_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "nome_file", nullable = false)
	private String nomeFile;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	@Column(name = "geometria", nullable = false)
	private Polygon geometria;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "processed", nullable = false)
	private Boolean processed;

	@Column(name = "uom")
	private String uom;

	@Column(name = "id_utente")
	private Integer idUtente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public Polygon getGeometria() {
		return geometria;
	}

	public void setGeometria(Polygon geometria) {
		this.geometria = geometria;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
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
		Geotiff other = (Geotiff) obj;
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
		builder.append("Geotiff [id=");
		builder.append(id);
		builder.append(", nomeFile=");
		builder.append(nomeFile);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", content=");
		builder.append(StringUtils.substring(content, 0, 10) + "...");
		builder.append(", processed=");
		builder.append(processed);
		builder.append(", uom=");
		builder.append(uom);
		builder.append(", idUtente=");
		builder.append(idUtente);
		builder.append("]");
		return builder.toString();
	}

}