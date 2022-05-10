package com.shrek.olimpiadas.dto;

import lombok.Data;

@Data
public class CompetidorJuezDto {
    private String idcompetidor;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private Integer sexo;

    public String getIdcompetidor() {
        return idcompetidor;
    }

    public void setIdcompetidor(String idcompetidor) {
        this.idcompetidor = idcompetidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }
}
