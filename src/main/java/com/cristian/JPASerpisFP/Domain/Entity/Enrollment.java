package com.cristian.JPASerpisFP.Domain.Entity;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Enrollment.selectAll", query = "SELECT e FROM Enrollment e"),
    @NamedQuery(name = "Enrollment.deleteAll", query = "DELETE FROM Enrollment e")
})
@Table(name = "MATRICULA_CP19")
public class Enrollment {

    public static String SELECT_ALL = "Enrollment.selectAll";
    public static String DELETE_ALL = "Enrollment.deleteAll";

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "IDENROLLMENT")
    Long idEnrollment;
    
    @ManyToOne()
    @JoinColumn(name = "NIA")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "CODMODULO")
    private Subject subject;

    public Enrollment() {

    }

    public Enrollment(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

	@Override
	public String toString() {
		return "Matricula [idMatricula=" + idEnrollment + ", Alumno=" + student + ",  Modulo=" + subject + "]";
	}
    
    
    
}
