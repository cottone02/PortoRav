package it.rjcsoft.prv.model;

public class MonitoringFiles {
	private Integer idMonitoring;
	private String fileName;
	private Boolean processed;
	private Integer id;
	private Integer companyId;

	public Integer getIdMonitoring() {
		return idMonitoring;
	}

	public void setIdMonitoring(Integer idMonitoring) {
		this.idMonitoring = idMonitoring;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitoringFiles [idMonitoring=");
		builder.append(idMonitoring);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", processed=");
		builder.append(processed);
		builder.append(", id=");
		builder.append(id);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append("]");
		return builder.toString();
	}

}
