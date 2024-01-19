package com.cristian.JPASerpisFP.Domain.Entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ALUMNO_CP19")
public class Student {
	@Id
	private String NIA;
	
	@Column(name = "NOMBRE")
	private String name = "";
	
	@Column(name = "APELLIDOS")
	private String surname = "";
	
	@ManyToOne
	@JoinColumn(name = "CODGRUPO")
	private Group group;
	
	
	@OneToOne(mappedBy = "student")
	FinalProject project;
	
	@ManyToMany
	@JoinTable(
			name = "MATRICULA_CP19",
			joinColumns = @JoinColumn(name = "NIA"),
			inverseJoinColumns = @JoinColumn(name = "CODMODULO")
			)
	private Set<Subject> subjects = new HashSet<>();
	
	public Student() {
		
	}
	
	public Student(String NIA, Group group) {
		this.NIA = NIA;
		this.group = group;
	}
	
	
	public Student(String NIA, String name, String surname, Group group, Set<Subject> subjects, FinalProject project ) {
		this(NIA, group);
		this.name = name;
		this.surname = surname;
		this.subjects = subjects;
	}
	
	
	public void setNIA(String NIA) {
		this.NIA = NIA;
	}
	
	public String getNIA() {
		return this.NIA;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	public Group getGroup() {
		return this.group;
	}
	
	
	public void setProject(FinalProject project) {
		this.project = project;
	}
	
	public FinalProject getProject() {
		return this.project;
	}
}
