package it.rjcsoft.prv.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Data {

	private Integer id;
	private Integer typeFile;
	private Integer columnB;

	private String fileName;
	private String columnA;
	private String columnD;
	private String columnE;
	private String columnF;
	private String columnI;
	
	private Double columnC;
	
	private Date columnG;
	private Timestamp uploadData;
	
	private Boolean columnH;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(Integer typeFile) {
		this.typeFile = typeFile;
	}

	public Integer getColumnB() {
		return columnB;
	}

	public void setColumnB(Integer columnB) {
		this.columnB = columnB;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getColumnA() {
		return columnA;
	}

	public void setColumnA(String columnA) {
		this.columnA = columnA;
	}

	public String getColumnD() {
		return columnD;
	}

	public void setColumnD(String columnD) {
		this.columnD = columnD;
	}

	public String getColumnE() {
		return columnE;
	}

	public void setColumnE(String columnE) {
		this.columnE = columnE;
	}

	public String getColumnF() {
		return columnF;
	}

	public void setColumnF(String columnF) {
		this.columnF = columnF;
	}

	public String getColumnI() {
		return columnI;
	}

	public void setColumnI(String columnI) {
		this.columnI = columnI;
	}

	public Double getColumnC() {
		return columnC;
	}

	public void setColumnC(Double columnC) {
		this.columnC = columnC;
	}

	public Date getColumnG() {
		return columnG;
	}

	public void setColumnG(Date columnG) {
		this.columnG = columnG;
	}

	public Timestamp getUploadData() {
		return uploadData;
	}

	public void setUploadData(Timestamp uploadData) {
		this.uploadData = uploadData;
	}

	public Boolean getColumnH() {
		return columnH;
	}

	public void setColumnH(Boolean columnH) {
		this.columnH = columnH;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [id=");
		builder.append(id);
		builder.append(", typeFile=");
		builder.append(typeFile);
		builder.append(", columnB=");
		builder.append(columnB);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", columnA=");
		builder.append(columnA);
		builder.append(", columnD=");
		builder.append(columnD);
		builder.append(", columnE=");
		builder.append(columnE);
		builder.append(", columnF=");
		builder.append(columnF);
		builder.append(", columnI=");
		builder.append(columnI);
		builder.append(", columnC=");
		builder.append(columnC);
		builder.append(", columnG=");
		builder.append(columnG);
		builder.append(", uploadData=");
		builder.append(uploadData);
		builder.append(", columnH=");
		builder.append(columnH);
		builder.append("]");
		return builder.toString();
	}

}
