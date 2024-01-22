package com.cristian.JPASerpisFP.View;

import static com.cristian.JPASerpisFP.View.Utils.Input.getInt;

import com.cristian.JPASerpisFP.Domain.Controller.FinalProjectController;
import com.cristian.JPASerpisFP.Domain.Controller.GroupController;
import com.cristian.JPASerpisFP.Domain.Controller.StudentController;
import com.cristian.JPASerpisFP.Domain.Controller.SubjectController;



public class MainView {
	private static GroupController groupController = new GroupController();
	private static StudentController studentController = new StudentController();
	private static FinalProjectController finalProjectController = new FinalProjectController();
	private static SubjectController subjectController = new SubjectController();
	public static int menu() {
		System.out.println("--------------------");
		System.out.println("SELECCIONA QUIE QUIERES CONSULTAR");
		System.out.println("1. Grupos");
		System.out.println("2. Alumnos");
		System.out.println("3. Modulos");
		System.out.println("4. Eliminar todos los datos");
		System.out.println("5. Salir de la aplicacion");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion del 1 al 3: ", false);
	}
	
	
	public static void showView() {
		int selectedOption = 0;
		do {
			selectedOption = MainView.menu();
			action(selectedOption);
		} while(selectedOption != 5);
	}
	
	
	public static void action(int selectedOption) {
		switch(selectedOption) {
			case 1:
				GroupView.showView();
				break;
			case 2:
				StudentView.showView();
				break;
			case 3:
				SubjectView.showView();
				break;
			case 4:
				System.out.println("Eliminando todos los datos que hay en la base de datos");
				studentController.deleteAll();
				groupController.deleteAll();
				finalProjectController.deleteAll();
				subjectController.deleteAll();
				break;
			case 5:
				System.out.println("Has seleccionado salir de la aplicacion");
				break;
			default:
				System.out.println("Debes de seleccionar una opcion del 1 al 3");
				break;
		}
	}
}
