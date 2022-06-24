package it.rjcsoft.prv.dto.censimentoprodottiallegati;

public class ProdottiAllegatiDTO {

    private Integer id;

    private Integer schedaId;

    private String nomeFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchedaId() {
        return schedaId;
    }

    public void setSchedaId(Integer schedaId) {
        this.schedaId = schedaId;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProdottiAllegatiDTO [id=");
        builder.append(id);
        builder.append(", nomeFile=");
        builder.append(nomeFile);
        builder.append(", schedaId=");
        builder.append(schedaId);
        builder.append("]");
        return builder.toString();
    }
    
}
