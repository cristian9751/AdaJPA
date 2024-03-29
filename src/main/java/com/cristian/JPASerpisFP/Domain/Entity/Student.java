package com.cristian.JPASerpisFP.Domain.Entity;
import java.io.Serializable;

import com.cristian.JPASerpisFP.Domain.Entity.Enrollment;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Student.selectBySurname", query = "SELECT s FROM Student s WHERE s.surname LIKE CONCAT('%', :surname, '%')"),
	@NamedQuery(name = "Student.selectByName", query = "SELECT s FROM Student s WHERE s.name LIKE CONCAT('%', :name, '%')"),
	@NamedQuery(name = "Student.selectAll", query = "SELECT s FROM  Student s"),
	@NamedQuery(name = "Student.deleteAll", query = "DELETE FROM Student s"),
	@NamedQuery(name = "Student.countAll", query = "SELECT COUNT(s) FROM Student s"),
	@NamedQuery(name = "Student.selectByGroup", query = "SELECT s FROM Student s WHERE s.group = :group")
})
@Table(name = "ALUMNO_CP19")
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String SEARCH_NAME = "Student.selectByName";
	public static final String SEARCH_SURNAME = "Student.selectBySurname";
	public static final String SEARCH_ALL = "Student.selectAll";
	public static final String DELETE_ALL = "Student.deleteAll";
	public static final String COUNT_ALL = "Student.countAll";
	public static final String SEARCH_GROUP = "Student.selectByGroup";
	

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
	
	@OneToMany(mappedBy = "student")
	private Set<Enrollment> enrollment = new HashSet<Enrollment>();
	
	
	public Student() {
		
	}
	
	public Student(String NIA, Group group) {
		this.NIA = NIA;
		this.group = group;
	}
	
	public Student(String NIA, String name, String surname, Group group,  FinalProject project ) {
		this(NIA, group);
		this.name = name;
		this.surname = surname;
	}
	
	public Student(String NIA, String name, String surname, Group group) {
		this(NIA, group);
		this.name = name == null ? "" : name;
		this.surname = surname == null ? "" : name;
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
	
	
	


	public Set<Enrollment> getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Set<Enrollment> enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public String toString() {
	    return "-----INFORMACION DEL ALUMNO-----\n" +
	           "NIA: " + this.NIA + "\n" +
	           "Nombre: " + this.name + "\n" +
	           "Apellidos: " + this.surname + "\n" +
	           "Grupo: " + this.group + "\n" +
	           "--------------------------------\n";
	}

}
