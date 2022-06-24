package it.rjcsoft.prv.dto.stili;

import org.apache.commons.lang3.StringUtils;

public class StiliDTO {

	private Integer id;

	private String descrizione;

	private Boolean stato;

	private Integer idGeotiff;

	private String geotiffBase64;

	private String legendBase64;

	public StiliDTO() {}
	
	public StiliDTO(Integer idGeotiff, String geotiffBase64, String legendBase64) {
		super();
		this.idGeotiff = idGeotiff;
		this.geotiffBase64 = geotiffBase64;
		this.legendBase64 = legendBase64;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StiliDTO [id=");
		builder.append(id);
		builder.append(", descrizione=");
		builder.append(descrizione);
		builder.append(", stato=");
		builder.append(stato);
		builder.append(", idGeotiff=");
		builder.append(idGeotiff);
		builder.append(", geotiffBase64=");
		builder.append(StringUtils.substring(geotiffBase64, 0, 10) + "...");
		builder.append(", legendBase64=");
		builder.append(StringUtils.substring(legendBase64, 0, 10) + "...");
		builder.append("]");
		return builder.toString();
	}

}
