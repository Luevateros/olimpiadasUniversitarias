package com.shrek.olimpiadas.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CompetidorDTO {

	private String idcompetidor;
	private String nombre; 
	private String apellidop;
	private String apellidom;
	private Integer sexo; 
	private String nacimiento;
	private String escuela; 
	private String correoNuevo;
	private String correoViejo;
	private String password;
	private String disciplina;
	
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
	public Integer getSexo() {
		return sexo;
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	public String getNacimiento() {
		
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getEscuela() {
		return escuela;
	}
	public void setEscuela(String escuela) {
		this.escuela = escuela;
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
		return "CompetidorDTO [idcompetidor=" + idcompetidor
				+ ", nombre=" + nombre + ", apellidop=" + apellidop + ", apellidom=" + apellidom + ", sexo=" + sexo
				+ ", nacimiento=" + nacimiento + ", escuela=" + escuela + ", correoNuevo=" + correoNuevo
				+ ", correoViejo=" + correoViejo + ", password=" + password + ", disciplina=" + disciplina + "]";
	}
}
