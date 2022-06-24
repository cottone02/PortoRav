package it.rjcsoft.prv.config;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@ConfigurationProperties("app")
public class PrvRestConfig {

	@NotNull
	private String fileBasePath;

	@NotNull
	@Positive
	private Integer numFileType;

	@NotNull
	private String emailFrom;

	@NotNull
	private String[] emailDevelopers;

	@NotNull
	private String baseUrl;

	@NotNull
	private String monitoringBasePath;

	@NotNull
	private String geotiffBasePath;

	@NotNull
	private String errorBasePath;

	@NotNull
	private String documentsBasePath;

	@NotNull
	private String userManualPath;

	@NotNull
	private String pngBasePath;

	@NotNull
	private String logoPath;

	@NotNull
	private Boolean simulationEmailFlag;

	private Boolean sendStartupMail = false;

	@NotNull
	private String prodottiAllegatiBasePath;

	public Boolean getSimulationEmailFlag() {
		return simulationEmailFlag;
	}

	public void setSimulationEmailFlag(Boolean simulationEmailFlag) {
		this.simulationEmailFlag = simulationEmailFlag;
	}

	public Boolean getSendStartupMail() {
		return sendStartupMail;
	}

	public void setSendStartupMail(Boolean sendStartupMail) {
		this.sendStartupMail = sendStartupMail;
	}

	public String getUserManualPath() {
		return userManualPath;
	}

	public void setUserManualPath(String userManualPath) {
		this.userManualPath = userManualPath;
	}

	public String getFileBasePath() {
		return fileBasePath;
	}

	public void setFileBasePath(String fileBasePath) {
		this.fileBasePath = fileBasePath;
	}

	public Integer getNumFileType() {
		return numFileType;
	}

	public void setNumFileType(Integer numFileType) {
		this.numFileType = numFileType;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String[] getEmailDevelopers() {
		return emailDevelopers;
	}

	public void setEmailDevelopers(String[] emailDevelopers) {
		this.emailDevelopers = emailDevelopers;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getMonitoringBasePath() {
		return monitoringBasePath;
	}

	public void setMonitoringBasePath(String monitoringBasePath) {
		this.monitoringBasePath = monitoringBasePath;
	}

	public String getDocumentsBasePath() {
		return documentsBasePath;
	}

	public void setDocumentsBasePath(String documentsBasePath) {
		this.documentsBasePath = documentsBasePath;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getGeotiffBasePath() {
		return geotiffBasePath;
	}

	public void setGeotiffBasePath(String geotiffBasePath) {
		this.geotiffBasePath = geotiffBasePath;
	}

	public String getPngBasePath() {
		return pngBasePath;
	}

	public void setPngBasePath(String pngBasePath) {
		this.pngBasePath = pngBasePath;
	}

	public String getErrorBasePath() {
		return errorBasePath;
	}

	public void setErrorBasePath(String errorBasePath) {
		this.errorBasePath = errorBasePath;
	}

	public String getProdottiAllegatiBasePath() {
		return prodottiAllegatiBasePath;
	}

	public void setProdottiAllegatiBasePath(String prodottiAllegatiBasePath) {
		this.prodottiAllegatiBasePath = prodottiAllegatiBasePath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrvRestConfig [baseUrl=");
		builder.append(baseUrl);
		builder.append(", documentsBasePath=");
		builder.append(documentsBasePath);
		builder.append(", emailDevelopers=");
		builder.append(Arrays.toString(emailDevelopers));
		builder.append(", emailFrom=");
		builder.append(emailFrom);
		builder.append(", errorBasePath=");
		builder.append(errorBasePath);
		builder.append(", fileBasePath=");
		builder.append(fileBasePath);
		builder.append(", geotiffBasePath=");
		builder.append(geotiffBasePath);
		builder.append(", logoPath=");
		builder.append(logoPath);
		builder.append(", monitoringBasePath=");
		builder.append(monitoringBasePath);
		builder.append(", numFileType=");
		builder.append(numFileType);
		builder.append(", pngBasePath=");
		builder.append(pngBasePath);
		builder.append(", prodottiAllegatiBasePath=");
		builder.append(prodottiAllegatiBasePath);
		builder.append(", sendStartupMail=");
		builder.append(sendStartupMail);
		builder.append(", simulationEmailFlag=");
		builder.append(simulationEmailFlag);
		builder.append(", userManualPath=");
		builder.append(userManualPath);
		builder.append("]");
		return builder.toString();
	}

}
