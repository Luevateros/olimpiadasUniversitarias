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

	public void setIddisciplina(Integer iddisciplina) {
		this.iddisciplina = iddisciplina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}