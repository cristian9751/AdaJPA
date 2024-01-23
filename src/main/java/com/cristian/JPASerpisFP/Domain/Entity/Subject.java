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
	
	
	@OneToMany(mappedBy = "subject")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>(); 
	
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

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	@Override
	public String toString() {
	    return "Subject: [Code=" + subjectCode + "\nDescription=" + description +
	           "\nHours=" + hours + "\nEnrollments=" + enrollments + "]";
	}

	
	
	

	
	
	
}
