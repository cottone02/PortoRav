
package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.LineString;

import it.rjcsoft.prv.utils.LineStringDeserializer;
import it.rjcsoft.prv.utils.LineStringSerializer;

@Entity(name="banchine")
@Table(name="banchine")
public class Banchina {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="banchina_id")
    private Integer id;
    
    @JsonSerialize(using = LineStringSerializer.class)
    @JsonDeserialize(using = LineStringDeserializer.class)
    @Column(name="banchina_geo")
    private LineString banchinaGeo;
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="colore")
    private String colore;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Banchina{id=").append(id);
        sb.append(", banchinaGeo=").append(banchinaGeo);
        sb.append(", nome=").append(nome);
        sb.append(", colore=").append(colore);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
