package com.shrek.olimpiadas.dto;

public class EntrenadorDTO {
    private String idcompetidor;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String correo;
    private String password;

    public String getIdcompetidor() {
        return idcompetidor;
    }

    public void setIdcompetidor(String idcompetidor) {
        this.idcompetidor = idcompetidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

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
        return "EntrenadorDTO{" +
                "idcompetidor='" + idcompetidor + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidop='" + apellidop + '\'' +
                ", apellidom='" + apellidom + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
