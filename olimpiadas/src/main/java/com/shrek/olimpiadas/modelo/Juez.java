package com.shrek.olimpiadas.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Juez")
@Entity
public class Juez {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuez")
    private Integer idjuez;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidop")
    private String apellidop;

    @Column(name = "apellidom")
    private String apellidom;

    @ManyToOne()
    @JoinColumn(name = "iddisciplina")
    private Disciplina disciplina;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    public Integer getIdjuez() {
        return idjuez;
    }

    public void setIdjuez(Integer idjuez) {
        this.idjuez = idjuez;
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
