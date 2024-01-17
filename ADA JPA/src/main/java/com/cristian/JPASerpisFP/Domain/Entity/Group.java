package com.cristian.JPASerpisFP.Domain.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "GRUPO_CP19")
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODGRUPO")
	@SequenceGenerator(name = "group_seq", sequenceName = "GROUP_SEQUENCE", allocationSize = 1, initialValue = 1)
	private int groupCode;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "AULA")
	private String classroom;
	
	@OneToMany(mappedBy = "group")
	private Set<Student> students = new HashSet<>();
	
	public Group() {
		
	}
	
	public Group(String description, String classroom) {
		this.description = description;
		this.classroom = classroom;
	}
	
	public Group(String description, String classroom, Set<Student> students) {
		this(description, classroom);
		this.students = students;
	}
	
	
	public int getGroupCode() {
		return this.groupCode;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	public String getClassroom() {
		return this.classroom;
	}
	
	public Set<Student> getStudents() {
		return this.students;
	}
	
	
	
}
