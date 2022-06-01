package com.shrek.olimpiadas.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Competidor")
public class Competidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompetidor")
    private String idcompetidor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidop")
    private String apellidop;

    @Column(name = "apellidom")
    private String apellidom;

    @Column(name = "sexo")
    private Integer sexo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "nacimiento")
    private String nacimiento;

    @Column(name = "escuela")
    private String escuela;

    @ManyToOne()
    @JoinColumn(name = "iddisciplina")
    private Disciplina disciplina;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    @ManyToMany(mappedBy = "competidores")
    private List<Entrenador> entrenadores;

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
	
    public String getDisciplina() {
        if (this.disciplina == null) {
            return null;
        }
        return this.disciplina.getNombre();
    }

	public Integer getIddisciplina() {
		if (this.disciplina == null) {
			return null;
		}
		return this.disciplina.getIddisciplina();
	}
    
    public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

    public Usuario getUsuario() {
        return usuario;
	}
    
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
