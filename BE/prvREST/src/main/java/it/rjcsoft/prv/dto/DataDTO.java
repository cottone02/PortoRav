package it.rjcsoft.prv.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

import it.rjcsoft.prv.model.Data;

public class DataDTO extends BaseDTO {
	
	private Integer id;
	private Timestamp uploadData;
	private String fileName;
	private Integer typeFile;
	
	@CsvBindByName
	private String columnA;
	@CsvBindByName
	private Integer columnB;
	@CsvBindByName
	private Double columnC;
	@CsvBindByName
	private String columnD;
	@CsvBindByName
	private String columnE;
	@CsvBindByName
	private String columnF;
	@CsvBindByName
	private Date columnG;
	@CsvBindByName
	private Boolean columnH;
	@CsvBindByName
	private String columnI;

	private Data data;
	private List<Data> datas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getUploadData() {
		return uploadData;
	}
	public void setUploadData(Timestamp uploadData) {
		this.uploadData = uploadData;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getTypeFile() {
		return typeFile;
	}
	public void setTypeFile(Integer typeFile) {
		this.typeFile = typeFile;
	}
	public String getColumnA() {
		return columnA;
	}
	public void setColumnA(String columnA) {
		this.columnA = columnA;
	}
	public Integer getColumnB() {
		return columnB;
	}
	public void setColumnB(Integer columnB) {
		this.columnB = columnB;
	}
	public Double getColumnC() {
		return columnC;
	}
	public void setColumnC(Double columnC) {
		this.columnC = columnC;
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
	public Date getColumnG() {
		return columnG;
	}
	public void setColumnG(Date columnG) {
		this.columnG = columnG;
	}
	public Boolean getColumnH() {
		return columnH;
	}
	public void setColumnH(Boolean columnH) {
		this.columnH = columnH;
	}
	public String getColumnI() {
		return columnI;
	}
	public void setColumnI(String columnI) {
		this.columnI = columnI;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public List<Data> getDatas() {
		return datas;
	}
	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataDTO [id=");
		builder.append(id);
		builder.append(", uploadData=");
		builder.append(uploadData);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", typeFile=");
		builder.append(typeFile);
		builder.append(", columnA=");
		builder.append(columnA);
		builder.append(", columnB=");
		builder.append(columnB);
		builder.append(", columnC=");
		builder.append(columnC);
		builder.append(", columnD=");
		builder.append(columnD);
		builder.append(", columnE=");
		builder.append(columnE);
		builder.append(", columnF=");
		builder.append(columnF);
		builder.append(", columnG=");
		builder.append(columnG);
		builder.append(", columnH=");
		builder.append(columnH);
		builder.append(", columnI=");
		builder.append(columnI);
		builder.append(", data=");
		builder.append(data);
		builder.append(", datas=");
		builder.append(datas);
		builder.append("]");
		return builder.toString();
	}
	
	public String toString(int typeFile) {
		StringBuilder builder = new StringBuilder();
		builder.append("[fileName=");
		builder.append(fileName);
		builder.append(", typeFile=");
		builder.append(typeFile);
		if (typeFile == 0) {
			builder.append(", columnA=");
			builder.append(columnA);
			builder.append(", columnB=");
			builder.append(columnB);
			builder.append(", columnC=");
			builder.append(columnC);
		} else if (typeFile == 1) {
			builder.append(", columnD=");
			builder.append(columnD);
			builder.append(", columnE=");
			builder.append(columnE);
			builder.append(", columnF=");
			builder.append(columnF);
		} else if (typeFile == 2) {
			builder.append(", columnG=");
			builder.append(columnG);
			builder.append(", columnH=");
			builder.append(columnH);
			builder.append(", columnI=");
			builder.append(columnI);
		}
		builder.append("]");
		return builder.toString();
	}
}
