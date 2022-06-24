package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "classi_granulometriche")
@Table(name = "classi_granulometriche", uniqueConstraints = {@UniqueConstraint(columnNames = {"scala", "intervallo_dimensionale", "classe"})})
public class ClasseGranulometrica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "classi_granulometriche_id_seq", allocationSize = 1, name = "classi_granulometriche_id_seq")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "scala", nullable = false)
	private String scala;
	
	@Column(name = "intervallo_dimensionale", nullable = false)
	private String intervalloDimensionale;
	
	@Column(name = "classe", nullable = false)
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClasseGranulometrica other = (ClasseGranulometrica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassiGranulometriche [id=");
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
