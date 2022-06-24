package it.rjcsoft.prv.dto.stili;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import it.rjcsoft.prv.annotation.ValueOfEnum;
import it.rjcsoft.prv.enums.SCALE;

public class CustomStiliDTO {

	@NotNull(message= "Il campo name non può essere null")
	private String name;
	
	@NotNull(message= "Il campo mainColor_RED non può essere null")
	@Min(value=0,message="Il valore minimo è 0")
	@Max(value=255,message="Il valore massimo è 255")
	private Integer mainColor_RED;
	
	@NotNull(message= "Il campo mainColor_GREEN non può essere null")
	@Min(value=0,message="Il valore minimo è 0")
	@Max(value=255,message="Il valore massimo è 255")
	private Integer mainColor_GREEN;
	
	@NotNull(message= "Il campo mainColor_BLUE non può essere null")
	@Min(value=0,message="Il valore minimo è 0")
	@Max(value=255,message="Il valore massimo è 255")
	private Integer mainColor_BLUE;

	private Double minValue;
	private Double maxValue;
	
	@NotNull(message= "Il campo useOpacity non può essere null")
	private Boolean useOpacity;
	
	@NotNull(message= "Il campo fixedOpacity non può essere null")
	private Double fixedOpacity;
	
	@NotNull(message= "Il campo useSpectrum non può essere null")
	private Boolean useSpectrum;
	
	@NotNull(message= "Il campo useReverseGradient non può essere null")
	private Boolean useReverseGradient;
	
	@NotNull(message= "Il campo scaleValue non può essere null")
	@ValueOfEnum(enumClass = SCALE.class)
	private String scaleValue;
	private List<Double> userLevels;

	public CustomStiliDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMainColor_RED() {
		return mainColor_RED;
	}

	public void setMainColor_RED(Integer mainColor_RED) {
		this.mainColor_RED = mainColor_RED;
	}

	public Integer getMainColor_GREEN() {
		return mainColor_GREEN;
	}

	public void setMainColor_GREEN(Integer mainColor_GREEN) {
		this.mainColor_GREEN = mainColor_GREEN;
	}

	public Integer getMainColor_BLUE() {
		return mainColor_BLUE;
	}

	public void setMainColor_BLUE(Integer mainColor_BLUE) {
		this.mainColor_BLUE = mainColor_BLUE;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Boolean getUseOpacity() {
		return useOpacity;
	}

	public void setUseOpacity(Boolean useOpacity) {
		this.useOpacity = useOpacity;
	}

	public Double getFixedOpacity() {
		return fixedOpacity;
	}

	public void setFixedOpacity(Double fixedOpacity) {
		this.fixedOpacity = fixedOpacity;
	}

	public Boolean getUseSpectrum() {
		return useSpectrum;
	}

	public void setUseSpectrum(Boolean useSpectrum) {
		this.useSpectrum = useSpectrum;
	}

	public Boolean getUseReverseGradient() {
		return useReverseGradient;
	}

	public void setUseReverseGradient(Boolean useReverseGradient) {
		this.useReverseGradient = useReverseGradient;
	}

	public String getScaleValue() {
		return scaleValue;
	}

	public void setScaleValue(String scaleValue) {
		this.scaleValue = scaleValue.toUpperCase();
	}

	public List<Double> getUserLevels() {
		return userLevels;
	}

	public void setUserLevels(List<Double> userLevels) {
		this.userLevels = userLevels;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomStiliDTO [name=").append(name).append(", mainColor_RED=").append(mainColor_RED)
				.append(", mainColor_GREEN=").append(mainColor_GREEN).append(", mainColor_BLUE=").append(mainColor_BLUE)
				.append(", minValue=").append(minValue).append(", maxValue=").append(maxValue).append(", useOpacity=")
				.append(useOpacity).append(", fixedOpacity=").append(fixedOpacity).append(", useSpectrum=")
				.append(useSpectrum).append(", useReverseGradient=").append(useReverseGradient).append(", scaleValue=")
				.append(scaleValue).append(", userLevels=").append(userLevels).append("]");
		return builder.toString();
	}

}
