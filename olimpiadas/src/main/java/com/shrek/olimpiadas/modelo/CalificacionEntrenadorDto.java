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

    @Column(name = "calificacion")
    private Integer calificacion;

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
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
