package it.rjcsoft.prv.dto.geotiff;

import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Polygon;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PolygonDeserializer;
import it.rjcsoft.prv.utils.PolygonSerializer;

public class GeotiffAllDTO {

	private Integer id;

	private String nomeFile;

	@JsonSerialize(using = PolygonSerializer.class)
	@JsonDeserialize(using = PolygonDeserializer.class)
	private Polygon geometria;

	private String url;

	private String content;

	private String uom;

	private Boolean processed;

	private Integer idUtente;

	private Boolean isOwner;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeotiffAllDTO [content=");
		builder.append(StringUtils.substring(content, 0, 10) + "...");
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", id=");
		builder.append(id);
		builder.append(", idUtente=");
		builder.append(idUtente);
		builder.append(", isOwner=");
		builder.append(isOwner);
		builder.append(", nomeFile=");
		builder.append(nomeFile);
		builder.append(", processed=");
		builder.append(processed);
		builder.append(", uom=");
		builder.append(uom);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}


}
