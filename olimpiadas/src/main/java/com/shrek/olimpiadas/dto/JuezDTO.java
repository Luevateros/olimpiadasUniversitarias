package com.shrek.olimpiadas.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JuezDTO {
	
	private Integer idjuez;
	private String nombre; 
	private String apellidop;
	private String apellidom;
	private String correoNuevo;
	private String correoViejo;
	private String password;
	private String disciplina;
	
	public Integer getIdjuez() {
		return idjuez;
	}
	public void setIdjuez(Integer idjuez) {
		this.idjuez = idjuez;
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
	public String getCorreoNuevo() {
		return correoNuevo;
	}
	public void setCorreoNuevo(String correoNuevo) {
		this.correoNuevo = correoNuevo;
	}
	public String getCorreoViejo() {
		return correoViejo;
	}
	public void setCorreoViejo(String correoViejo) {
		this.correoViejo = correoViejo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	@Override
	public String toString() {
		return "JuezDTO [idjuez=" + idjuez + ", nombre=" + nombre + ", apellidop=" + apellidop + ", apellidom="
				+ apellidom + ", correoNuevo=" + correoNuevo + ", correoViejo=" + correoViejo + ", password=" + password
				+ ", disciplina=" + disciplina + "]";
	}
	
}
