package com.shrek.olimpiadas.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String correo;
    private String password;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
