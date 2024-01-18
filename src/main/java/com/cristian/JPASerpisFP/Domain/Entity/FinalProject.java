package com.cristian.JPASerpisFP.Domain.Entity;


import javax.persistence.*;

@Entity
@Table(name = "PROYECTO_CONVOCATORIA_CP19")
public class FinalProject {
	@Id
	@Column(name = "CODPROYECTO")
	private String proyectCode;
	
	@Column(name = "TITULO")
	private String title;
	
	@OneToOne
	@JoinColumn(name = "NIA")
	Student student;
	
	
	public FinalProject() {} 
	
	public FinalProject(String title, Student student) {
		this.title = title;
		this.student = student;
	}
	
	
	public FinalProject(String proyectCode, String title, Student student) {
		this(title, student);
		this.proyectCode = proyectCode;
	}
	
	
	public void setProyectCode(String proyectCode) {
		this.proyectCode = proyectCode;
	}
	
	public String getProyectCode() {
		return this.proyectCode;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Student getStudent() {
		return this.student;
	}
}
