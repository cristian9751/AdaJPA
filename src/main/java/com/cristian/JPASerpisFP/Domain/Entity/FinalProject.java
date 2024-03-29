package com.cristian.JPASerpisFP.Domain.Entity;


import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "PROYECTO_CONVOCATORIA_CP19")
@NamedQueries({
	@NamedQuery(name = "Project.selectByTitle", query = "SELECT project FROM FinalProject project WHERE project.title = :title"),
	@NamedQuery(name = "Project.selectAll", query = "SELECT project FROM FinalProject project"),
	@NamedQuery(name = "Project.deleteAll", query = "DELETE FROM FinalProject project"),
	@NamedQuery(name = "Project.countAll", query = "SELECT COUNT (project) FROM FinalProject project")
})
public class FinalProject implements Serializable {
	
	public static String DELETE_ALL = "Project.deleteAll";
	public  static String SEARCH_ALL = "Project.selectAll";
	public static String COUNT_ALL = "Project.countAll";
	
	@Id
	@Column(name = "CODPROYECTO")
	private String projectCode;
	
	@Column(name = "TITULO")
	private String title;
	
	@OneToOne
	@JoinColumn(name = "NIA")
	private Student student;
	
	
	public FinalProject() {} 
	
	public FinalProject(String projectCode, Student student) {
		this.projectCode = projectCode;
		this.student = student;
	}

	
	public FinalProject(String projectCode, Student student, String title) {
		this(projectCode, student);
		this.title = title == null ? "" : title;
	}
	
	
	public void setProyectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	public String getProyectCode() {
		return this.projectCode;
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

	@Override
	public String toString() {
	    return "Project: [Code=" + projectCode + "\nTitle=" + title + "\nStudent=" + student + "]";
	}

	
	
}
