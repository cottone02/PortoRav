package it.rjcsoft.prv.dto.banchina;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.LineString;

import it.rjcsoft.prv.utils.LineStringDeserializer;
import it.rjcsoft.prv.utils.LineStringSerializer;

public class BanchinaDTO {

    private Integer id;

    @JsonSerialize(using = LineStringSerializer.class)
    @JsonDeserialize(using = LineStringDeserializer.class)
    private LineString banchinaGeo;

    private String nome;

    private String colore;

    private Boolean isDeletable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LineString getBanchinaGeo() {
        return banchinaGeo;
    }

    public void setBanchinaGeo(LineString banchinaGeo) {
        this.banchinaGeo = banchinaGeo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Boolean getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BanchinaDTO{id=").append(id);
        sb.append(", banchinaGeo=").append(banchinaGeo);
        sb.append(", nome=").append(nome);
        sb.append(", colore=").append(colore);
        sb.append(", isDeletable=").append(isDeletable);
        sb.append('}');
        return sb.toString();
    }

}
