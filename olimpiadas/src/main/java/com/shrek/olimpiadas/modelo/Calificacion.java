package com.shrek.olimpiadas.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Calificacion")
@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcalificacion")
    private Integer idcalificacion;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "calificacion")
    private float calificacion;

    @ManyToOne()
    @JoinColumn(name = "idcompetidor")
    private Competidor competidor;

    @ManyToOne()
    @JoinColumn(name = "idjuez")
    private Juez juez;

    @ManyToOne()
    @JoinColumn(name = "iddisciplina")
    private Disciplina disciplina;

    public Integer getIdcalificacion() {
        return idcalificacion;
    }

    public void setIdcalificacion(Integer idcalificacion) {
        this.idcalificacion = idcalificacion;
    }

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

    public Competidor getCompetidor() {
        return competidor;
    }

    public void setCompetidor(Competidor competidor) {
        this.competidor = competidor;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setJuez(Juez juez) {
        this.juez = juez;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "idcalificacion=" + idcalificacion +
                ", comentario='" + comentario + '\'' +
                ", calificacion=" + calificacion +
                ", competidor=" + competidor +
                ", juez=" + juez +
                ", disciplina=" + disciplina +
                '}';
    }
}
