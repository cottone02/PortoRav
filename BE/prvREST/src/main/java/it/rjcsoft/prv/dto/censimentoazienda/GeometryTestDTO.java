package it.rjcsoft.prv.dto.censimentoazienda;

import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.rjcsoft.prv.utils.PointDeserializer;

public class GeometryTestDTO extends CensimentoAziendaDTO {

	@JsonDeserialize(using = PointDeserializer.class)
	private Point wkt;

	public Point getWkt() {
		return wkt;
	}

	public void setWkt(Point wkt) {
		this.wkt = wkt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeometryTestDTO [wkt=");
		builder.append(wkt);
		builder.append("]");
		return builder.toString();
	}

}
