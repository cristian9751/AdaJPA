package com.cristian.JPASerpisFP.Domain.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Subject.selectByHours", query = "SELECT subject FROM Subject subject WHERE subject.hours = :hours"),
	@NamedQuery(name = "Subject.selectAll", query = "SELECT subject FROM Subject subject"),
	@NamedQuery(name = "Subject.countAll", query = "SELECT COUNT (subject) FROM Subject subject"),
	@NamedQuery(name = "Subject.deleteAll", query = "DELETE FROM Subject subject")
})


	


@Table(name = "MODULO_CP19")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SEARCH_HOURS = "Subject.selectByHours";
	public static final String SEARCH_ALL = "Subject.selectAll";
	public static final String DELETE_ALL = "Subject.deleteAll";
	public static final String COUNT_ALL = "Subject.countAll";
	@Id
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
	
	public Subject(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public Subject(int subjectCode, int hours, String description) {
		this(subjectCode);
		this.hours = hours;
		this.description = description == null ? "" : description;
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
