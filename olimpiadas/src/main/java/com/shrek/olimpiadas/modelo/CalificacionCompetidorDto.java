package com.shrek.olimpiadas.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionCompetidorDto {

    private String nombre;
    private String apellidop;
    private String apellidom;
    private Integer sexo;
    private String escuela;
    private Float calificacion;

    public CalificacionCompetidorDto(
    		String nombre,
    		String apellidop,
    		String apellidom,
    		Integer sexo,
    		String escuela,
    		Float calificacion) {
    	super();
    	this.nombre = nombre;
    	this.apellidop = apellidop;
    	this.apellidom = apellidom;
    	this.sexo = sexo;
    	this.escuela = escuela;
    	this.calificacion = calificacion;
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

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

}
