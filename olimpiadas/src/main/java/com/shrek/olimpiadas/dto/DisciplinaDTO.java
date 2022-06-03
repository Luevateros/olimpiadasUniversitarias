package com.shrek.olimpiadas.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaDTO {
    private Integer iddisciplina;
    private String nombre;
    private String responsable;
    private String descripcion;
    private String imagen;
    /**
     * Método que regresa el id de la discilina.
     * @return Integer - Identificador disciplina
     * */
    public Integer getIddisciplina() {
        return iddisciplina;
    }

    public void setIddisciplina(Integer iddisciplina) {
        this.iddisciplina = iddisciplina;
    }
    /**
     * Método que regresa el nombre de la discilina.
     * @return String - Nombre disciplina
     * */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre de la discilina.
     * @param nombre - Nombre para la disciplina
     * */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que regresa el representante de la discilina.
     * @return String - Nombre del representante de la disciplina
     * */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Método que establece el representatnte de la discilina.
     * @param responsable - Nombre del responsable
     * */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * Método que regresa la descripción de la discilina.
     * @return String - Descripción de la disciplina
     * */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que establece la descripción de la discilina.
     * @param descripcion- Descripción de la disciplina
     * */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método que regresa el nombre de la imagen de la discilina.
     * @return String - Nombre de la imagen de la disciplina
     * */
    public String getImagen() {
        return imagen;
    }

    @Transient
    public String imagen(){
        return "/disciplinas-imagenes/" + imagen;
    }
    /**
     * Método que establece la descripción de la discilina.
     * @param imagen - Nombre de la imagen de la disciplina
     * */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public  String toString(){
        return getIddisciplina() + " " + getNombre();
    }
}
