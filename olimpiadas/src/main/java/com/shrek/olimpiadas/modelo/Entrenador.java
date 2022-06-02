package com.shrek.olimpiadas.modelo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identrenador")
    private Integer identrenador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidop")
    private String apellidop;

    @Column(name = "apellidom")
    private String apellidom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    @JoinTable(
            name = "Asesorar",
            joinColumns = @JoinColumn(name = "idcompetidor", nullable = false),
            inverseJoinColumns = @JoinColumn(name="identrenador", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Competidor> competidores;

    public void agregarCompetidor(Competidor competidor){
        if(this.competidores == null){
            this.competidores = new ArrayList<>();
        }

        this.competidores.add(competidor);
    }

	public Integer getIdentrenador() {
		return identrenador;
	}

	public void setIdentrenador(Integer identrenador) {
		this.identrenador = identrenador;
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

	public Integer getCompetidores() {
		return competidores.size();
	}    
    
}
