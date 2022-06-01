package com.shrek.olimpiadas.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDto {
    private String comentario;
    private float calificacion;
    private Integer idcompetidor;
    private Integer idjuez;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getIdcompetidor() {
        return idcompetidor;
    }

    public void setIdcompetidor(Integer idcompetidor) {
        this.idcompetidor = idcompetidor;
    }

    public Integer getIdjuez() {
        return idjuez;
    }

    public void setIdjuez(Integer idjuez) {
        this.idjuez = idjuez;
    }

    @Override
    public String toString() {
        return "CalificacionDto{" +
                "comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                ", id" +
                "competidor=" + idcompetidor +
                ", idjuez=" + idjuez +
                '}';
    }
}
