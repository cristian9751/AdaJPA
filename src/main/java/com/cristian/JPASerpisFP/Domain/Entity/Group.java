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
	private int groupCode;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "AULA")
	private String classroom;
	
	@OneToMany(mappedBy = "group")
	private Set<Student> students = new HashSet<>();
	
	public Group() {
		
	}
	
	
	public Group(int groupCode) {
		this.groupCode = groupCode;
	}
	
	public Group(int groupCode, String description, String classroom) {
		this(groupCode);
		this.description = description;
		this.classroom = classroom;
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
