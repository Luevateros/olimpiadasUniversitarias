package com.shrek.olimpiadas.modelo;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idusuario;

    @Column(name = "correo")
    private String correo;

    @Column(name = "password")
    private String password;

    @Column(name = "tipousuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipousuario;

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
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

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	
}
