package com.shrek.olimpiadas.dto;

import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.modelo.Juez;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDto {
    private String comentario;
    private float calificacion;
    private String idcompetidor;
    private Integer idjuez;
    private Integer iddisciplina;

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

    public String getIdcompetidor() {
        return idcompetidor;
    }

    public void setIdcompetidor(String idcompetidor) {
        this.idcompetidor = idcompetidor;
    }

    public Integer getIdjuez() {
        return idjuez;
    }

    public void setIdjuez(Integer idjuez) {
        this.idjuez = idjuez;
    }

    public Integer getIddisciplina() {
        return iddisciplina;
    }

    public void setIddisciplina(Integer iddisciplina) {
        this.iddisciplina = iddisciplina;
    }

    @Override
    public String toString() {
        return "CalificacionDto{" +
                "comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                ", id" +
                "competidor=" + idcompetidor +
                ", idjuez=" + idjuez +
                ", iddisciplina=" + iddisciplina +
                '}';
    }
}
