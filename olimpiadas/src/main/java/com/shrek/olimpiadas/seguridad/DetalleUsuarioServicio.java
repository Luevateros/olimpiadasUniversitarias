package com.shrek.olimpiadas.seguridad;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface DetalleUsuarioServicio {
    UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException;
}
