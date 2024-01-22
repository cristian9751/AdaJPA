package com.cristian.JPASerpisFP.Domain.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "GRUPO_CP19")
@NamedQueries({
	@NamedQuery(name = "Group.selectByClassroom", query = "SELECT g FROM Group g WHERE g.classroom like :classroom"),
	@NamedQuery(name = "Group.selectAll", query = "SELECT g FROM Group g"),
	@NamedQuery(name = "Group.deleteAll", query = "DELETE FROM Group g"),
	@NamedQuery(name = "Group.countAll", query = "SELECT COUNT (g) FROM Group g"),
	@NamedQuery(name = "Group.slectByDescription", query = "SELECT g FROM Group g WHERE g.description = :description")
})

public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String SEARCH_CLASSROOM = "Group.selectByClassroom";
	public static final String SEARCH_ALL = "Group.selectAll";
	public static final String DELETE_ALL = "Group.deleteAll";
	public static final String COUNT_ALL = "Group.countAll";
	public static final String SEARCH_DESCRIPTION = "Group.selectByDescription";

	@Id
	@Column(name = "CODGRUPO")
	private int groupCode;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "AULA")
	private String classroom;
	
	@OneToMany(mappedBy = "group")
	private List<Student> students = new ArrayList<>();
	
	public Group() {
		
	}
	
	
	public Group(int groupCode) {
		this.groupCode = groupCode;
	}
	
	public Group(int groupCode, String classroom, String description) {
		this(groupCode);
		this.description = description == null ? "" : description;
		this.classroom = classroom == null ? "" : classroom ;
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
	
	public List<Student> getStudents() {
		return this.students;
	}
	
	
	protected boolean addStudent(Student student) {
		if(this.getStudents().contains(student)) {
			return false;
		} else {
			this.getStudents().add(student);
			return true;
		}
	
	}
	
	
	protected boolean removeStudent(Student student) {
		if(this.getStudents().contains(student)) {
			this.getStudents().remove(student);
			return true;
		} else {
			return false;
		}
	
	}


	
	@Override
	public String toString() {
	    return "-----INFORMACION DEL GRUPO-----\n" +
	           "Codigo del grupo: " + this.groupCode + "\n" +
	           "Descripcion del grupo: " + this.description + "\n" +
	           "Aula del grupo: " + this.classroom + "\n" +
	           "--------------------------------\n";
	}

	
	
	
	
}
