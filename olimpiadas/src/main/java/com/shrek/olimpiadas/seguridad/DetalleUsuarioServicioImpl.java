package com.shrek.olimpiadas.seguridad;

import com.shrek.olimpiadas.modelo.Usuario;
import com.shrek.olimpiadas.repositorio.RepoUsuario;
import com.shrek.olimpiadas.seguridad.DetalleUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DetalleUsuarioServicioImpl implements DetalleUsuarioServicio, UserDetailsService {

    @Autowired
    RepoUsuario repoUsuario;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuarioActivo = repoUsuario.findByCorreo(correo);
        if (usuarioActivo == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        UserDetails userDetails;
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioActivo.getTipousuario().toString());
        userDetails = new User(usuarioActivo.getCorreo(), usuarioActivo.getPassword(), Arrays.asList(grantedAuthority));
        return userDetails;
    }
}