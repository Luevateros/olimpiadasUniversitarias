package com.shrek.olimpiadas.servicio.impl;

import com.shrek.olimpiadas.dto.EntrenadorDTO;
import com.shrek.olimpiadas.modelo.Disciplina;
import com.shrek.olimpiadas.modelo.TipoUsuario;
import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoDisciplina;
import com.shrek.olimpiadas.repositorio.RepoEntrenador;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.servicio.SvcEntrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SvcEntrenadorImpl implements SvcEntrenador {
    @Autowired
    private RepoDisciplina repoDisciplina;
    @Autowired
    private RepoEntrenador repoEntrenador;
    @Autowired
    private RepoUsuario repoUsuario;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Disciplina> mostrarDisciplina(){
        return (List<Disciplina>) repoDisciplina.findAll();
    }

    @Override
    public String agregarEntrenador(EntrenadorDTO entrenador) {
        Usuario usuarioNuevo = (Usuario) repoUsuario.findByCorreo(entrenador.getCorreo());
        if(usuarioNuevo != null)
            return "El correo está en uso.";

        usuarioNuevo = new Usuario();
        usuarioNuevo.setCorreo(entrenador.getCorreo());
        usuarioNuevo.setPassword(passwordEncoder.encode(entrenador.getPassword()));
        usuarioNuevo.setTipousuario(TipoUsuario.ENTRENADOR);

        Usuario usuarioGuardado = repoUsuario.save(usuarioNuevo);
        repoEntrenador.agregarEntrenador(
                entrenador.getNombre(),
                entrenador.getApellidop(),
                entrenador.getApellidom(),
                usuarioGuardado.getIdusuario()
        );
        return null;
    }
}
