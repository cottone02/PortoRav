package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.Monitoring;

public class MonitoringListDTO extends BaseDTO {

	private List<Monitoring> monitorings;
	
	private Integer rowCounter;

	public List<Monitoring> getMonitorings() {
		return monitorings;
	}

	public void setMonitorings(List<Monitoring> monitorings) {
		this.monitorings = monitorings;
	}

	public Integer getRowCounter() {
		return rowCounter;
	}

	public void setRowCounter(Integer rowCounter) {
		this.rowCounter = rowCounter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitoringListDTO [monitorings=");
		builder.append(monitorings);
		builder.append(", rowCounter=");
		builder.append(rowCounter);
		builder.append("]");
		return builder.toString();
	}
	
}
