package it.rjcsoft.prv.dto.cumuliaperto;

public class CumuliApertoLocalizzazioneUpdateDTO {
	
	private Integer id;
	
	private Double altezza;

	private Double area;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CumuliApertoLocalizzazioneUpdateDTO [id=");
		builder.append(id);
		builder.append(", altezza=");
		builder.append(altezza);
		builder.append(", area=");
		builder.append(area);
		builder.append("]");
		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAltezza() {
		return altezza;
	}

	public void setAltezza(Double altezza) {
		this.altezza = altezza;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

}
