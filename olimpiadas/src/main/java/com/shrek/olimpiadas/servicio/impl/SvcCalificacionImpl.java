package com.shrek.olimpiadas.servicio.impl;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Calificacion;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.repositorio.RepoCalificacion;
import com.shrek.olimpiadas.repositorio.RepoCompetidor;
import com.shrek.olimpiadas.servicio.SvcCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SvcCalificacionImpl implements SvcCalificacion {
    @Autowired
    private RepoCompetidor repoCompetidor;
    @Autowired
    private RepoCalificacion repoCalificacion;

    @Override
    public List<Competidor> traeCompetidores(Integer id) {
        List<Competidor> competidores = repoCompetidor.traeCompetidores(id);

        if(competidores != null){
            return competidores;
        }
        return null;
    }

    @Override
    public List<Calificacion> traeCalificaciones(Integer id) {
        List<Calificacion> calificaciones = repoCalificacion.traeCalificaciones(id);
        if(calificaciones != null){
            return calificaciones;
        }
        return null;
    }

    @Override
    public List<Calificacion> mostrarCalificacionPerso(String id) {
        List<Calificacion> calificaciones = repoCalificacion.findByIdcompetidor(id);
        if(calificaciones != null){
            return calificaciones;
        }
        return null;
    }

    @Override
    public List<Calificacion> traeComentarios(Integer id) {
        List<Calificacion> comentarios = repoCalificacion.traeComentarios(id);
        if(comentarios != null){
            return comentarios;
        }
        return null;
    }


    @Override
    public String agregarCalificacion(CalificacionDto calificacion) {
        if(calificacion.getComentario() == null){
            return "Favor de agregar un comentario";
        }
        repoCalificacion.crearCalificacion(calificacion.getComentario(), calificacion.getCalificacion(),
                calificacion.getIdcompetidor(), calificacion.getIdjuez());
        return null;
    }

}
