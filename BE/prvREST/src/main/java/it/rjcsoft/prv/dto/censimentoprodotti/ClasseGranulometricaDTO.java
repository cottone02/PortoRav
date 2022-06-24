package it.rjcsoft.prv.dto.censimentoprodotti;

public class ClasseGranulometricaDTO {
	
	private Integer id;
	
	private String scala;
	
	private String intervalloDimensionale;
	
	private String classe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getIntervalloDimensionale() {
		return intervalloDimensionale;
	}

	public void setIntervalloDimensionale(String intervalloDimensionale) {
		this.intervalloDimensionale = intervalloDimensionale;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClasseGranulometricaDTO [id=");
		builder.append(id);
		builder.append(", scala=");
		builder.append(scala);
		builder.append(", intervalloDimensionale=");
		builder.append(intervalloDimensionale);
		builder.append(", classe=");
		builder.append(classe);
		builder.append("]");
		return builder.toString();
	}
	
}
