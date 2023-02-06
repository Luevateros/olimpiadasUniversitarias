package com.shrek.olimpiadas.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "CalificacionEntrenador")
@Entity
public class CalificacionEntrenadorDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcalificacion")
    private Integer idcalificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidop")
    private String apellidop;

    @Column(name = "apellidom")
    private String apellidom;

    @Column(name = "juez")
    private String juez;
    
    @Column(name = "juezp")
    private String juezp;
    
    @Column(name = "juezm")
    private String juezm;

    @Column(name = "calificacion")
    private Double calificacion;

    @Column(name = "disciplina")
    private String disciplina;


    public Integer getIdcalificacion() {
        return idcalificacion;
    }

    public void setIdcalificacion(Integer idcalificacion) {
        this.idcalificacion = idcalificacion;
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

    public String getJuez() {
        return juez + " " + juezp + " " + juezm;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}