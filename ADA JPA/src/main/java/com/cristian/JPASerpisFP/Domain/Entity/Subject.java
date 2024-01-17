package com.cristian.JPASerpisFP.Domain.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "MODULO_CP19")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODMODULO")
	private int subjectCode;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "NUMHORAS")
	private int hours;
	
	@ManyToMany
	@JoinTable(
			name = "MATRICULA_CP19",
			joinColumns = @JoinColumn(name = "CODMODULO"),
			inverseJoinColumns = @JoinColumn(name = "NIA")
			)
	private Set<Student> students = new HashSet<>();
	
	
	public Subject() {}
	
	public Subject(int hours, String description) {
		this.hours = hours;
		this.description = description;
	}
	
	
	
	public int getSubjectCode() {
		return this.subjectCode;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
	public Set<Student> getStudents() {
		return this.students;
	}
	
	
	
}
