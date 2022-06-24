package it.rjcsoft.prv.dto.censimentoprodotti;

public class ProdottoDTO {

	private Integer id;

	private String nome;

	private String polverosita;

	private Boolean isDeletable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPolverosita() {
		return polverosita;
	}

	public void setPolverosita(String polverosita) {
		this.polverosita = polverosita;
	}

	public Boolean getIsDeletable() {
		return isDeletable;
	}

	public void setIsDeletable(Boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProdottoDTO [id=");
		builder.append(id);
		builder.append(", isDeletable=");
		builder.append(isDeletable);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", polverosita=");
		builder.append(polverosita);
		builder.append("]");
		return builder.toString();
	}
}