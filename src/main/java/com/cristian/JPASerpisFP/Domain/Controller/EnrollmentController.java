package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cristian.JPASerpisFP.Domain.Entity.Enrollment;
import com.cristian.JPASerpisFP.model.dao.EnrollmentDAO;

public class EnrollmentController {
	
	private static EnrollmentDAO dao;

	public EnrollmentController() {
		 dao = new EnrollmentDAO();
	}
	
	
	public List<Enrollment> getAll() {
		List<Enrollment> list = new ArrayList<Enrollment>();
		list = dao.findAll();
		return list;
	}
	
	public void deleteAll() {
		try {
			dao.deleteAll();
		} catch(Exception e) {
			System.out.println("Se ha producido un error");
		}
	}
}
