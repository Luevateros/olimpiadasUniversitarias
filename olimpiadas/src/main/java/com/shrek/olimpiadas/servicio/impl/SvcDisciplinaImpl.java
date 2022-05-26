package com.shrek.olimpiadas.servicio.impl;

import java.util.List;

import com.shrek.olimpiadas.dto.DisciplinaDTO;
import com.shrek.olimpiadas.servicio.SvcDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.repositorio.RepoDisciplina;

@Service
public class SvcDisciplinaImpl implements SvcDisciplina {
    @Autowired
    private RepoDisciplina repoDisciplina;

    @Override
    public List<Disciplina> mostrarDisciplinas() {
        return (List<Disciplina>) repoDisciplina.findAll();
    }

    @Override
    public DisciplinaDTO mostrarDisciplina(Integer iddisciplina) {
        Disciplina disciplina = repoDisciplina.findByIddisciplina(iddisciplina);
        if(disciplina == null)
           return null;
        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setNombre(disciplina.getNombre());
        dto.setResponsable(disciplina.getResponsable());
        dto.setDescripcion(disciplina.getDescripcion());
        dto.setImagen(disciplina.getImagen());
        return dto;
    }

    @Override
    public String agregarDisciplina(DisciplinaDTO disciplina) {
        repoDisciplina.agregarDisciplina(
                disciplina.getNombre(),
                disciplina.getResponsable(),
                disciplina.getImagen(),
                disciplina.getDescripcion());
        return null;
    }


    @Override
    public String actualizarDisciplina(DisciplinaDTO disciplina) {
        repoDisciplina.actualizarDisciplina(
                disciplina.getNombre(),
                disciplina.getResponsable(),
                disciplina.getImagen(),
                disciplina.getDescripcion(),
                disciplina.getIddisciplina());
        return null;
    }

    @Override
    public void eliminarDisciplina(Integer iddisciplina) {
        Disciplina disciplina = (Disciplina) repoDisciplina.findByIddisciplina(iddisciplina);
        repoDisciplina.eliminarDisciplina(iddisciplina);
    }
}
