package com.shrek.olimpiadas.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shrek.olimpiadas.dto.CalificacionDto;
import com.shrek.olimpiadas.modelo.Calificacion;
import com.shrek.olimpiadas.modelo.CalificacionCompetidorDto;
import com.shrek.olimpiadas.modelo.CalificacionEntrenadorDto;
import com.shrek.olimpiadas.modelo.Competidor;
import com.shrek.olimpiadas.repositorio.RepoCalificacion;
import com.shrek.olimpiadas.repositorio.RepoCalificacionEntrenadorDto;
import com.shrek.olimpiadas.repositorio.RepoCompetidor;
import com.shrek.olimpiadas.servicio.SvcCalificacion;

@Service
public class SvcCalificacionImpl implements SvcCalificacion {
    @Autowired
    private RepoCompetidor repoCompetidor;
    @Autowired
    private RepoCalificacion repoCalificacion;
    @Autowired
    private RepoCalificacionEntrenadorDto repoCalificacionEntrenadorDto;

    @Override
    public List<Competidor> traeCompetidores(Integer id) {
        List<Competidor> competidores = repoCompetidor.traeCompetidores(id);

        if(competidores != null){
            return competidores;
        }
        return null;
    }


    @Override
    public List<CalificacionEntrenadorDto> traeCalificacionesEntrenador(Integer id) {
        List<CalificacionEntrenadorDto> calificaciones = repoCalificacionEntrenadorDto.mostrarCalificaciones(id);

        if(calificaciones != null){
            return calificaciones;
        }
        return null;
    }

    @Override
    public List<CalificacionCompetidorDto> traeCalificaciones(Integer id, Integer sexo) {
        List<CalificacionCompetidorDto> posiciones = repoCalificacion.traeCalificaciones(id,sexo);
        if(posiciones != null){
            return posiciones;
        }
        return null;
    }

    @Override
    public List<Calificacion> competidorCalificacion(String id) {
        List<Calificacion> comentarios = repoCalificacion.findByIdcompetidor(id);
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
        repoCalificacion.crearCalificacion(
        								calificacion.getComentario(), 
        								calificacion.getCalificacion(),
        								calificacion.getIdcompetidor(), 
        								calificacion.getIdjuez(), 
        								calificacion.getIddisciplina());
        return null;
    }
}
