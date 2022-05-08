package com.shrek.olimpiadas.modelo;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddisciplina")
    private Integer iddisciplina;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "descripcion")
    private String descripcion;

    public Integer getIddisciplina() {
        return iddisciplina;
    }

    public String getNombre() {
        return nombre;
    }
}
