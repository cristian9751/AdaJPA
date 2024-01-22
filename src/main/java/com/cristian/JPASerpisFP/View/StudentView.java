package com.cristian.JPASerpisFP.View;

import com.cristian.JPASerpisFP.Domain.Controller.GroupController;
import com.cristian.JPASerpisFP.Domain.Controller.StudentController;
import com.cristian.JPASerpisFP.Domain.Controller.SubjectController;
import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import com.cristian.JPASerpisFP.Domain.Entity.Subject;

import static com.cristian.JPASerpisFP.View.Utils.Util.*;
import static com.cristian.JPASerpisFP.View.Utils.Input.*;

public class StudentView {
	
	
	private static StudentController studentController = new StudentController();
	private static GroupController groupController = new GroupController();
	private static SubjectController subjectController = new SubjectController();
	
	private static Long studentCounter;
	
	private static int menu() {
		System.out.println("--------------------");
		System.out.println("Hay " + studentCounter + " alumnos");
		System.out.println("1. Mostrar alumnos (todos  o filtrados)");
		System.out.println("2. Crear nuevos alumnos");
		System.out.println("3. Eliminar un alumno existente");
		System.out.println("4. Eliminar todos los alumnos");
		System.out.println("5. Matricular alumno");
		System.out.println("6. Volver atras");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion del 1 al 5: ", false);
		
	}
	
	private static int filterMenu() {
		System.out.println("--------------------");
		System.out.println("1- Mostrar todos los estudiantes");
		System.out.println("2. Mostrar los estudiantes filtrados por grupo");
		System.out.println("3. Mostrar los estudiantes filtrados por nombre");
		System.out.println("4. Mostrar los estudiantes filtrados por apellidos");
		System.out.println("5. Volver atras");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion de la 1 a la 5", false);
	}
	
	
	public static void showView() {
		int selectedOption = 0;
		do {
			studentCounter = studentController.getTotalCount();
			selectedOption = StudentView.menu();
			if(studentCounter == 0 && (selectedOption == 1 || selectedOption == 3 || selectedOption == 5)) {
				System.out.println("No hay estudiantes");
				continue;
			}
			action(selectedOption);
		} while(selectedOption != 6);
	}
	
	
	public static void action(int selectedOption) {
		switch(selectedOption) {
			case 1:
				int filterOption;
				do {
					filterOption = filterMenu();
					switch(filterOption) {
						case 1:
							showList(studentController.getAll(), " estudiantes.");
							break;
						case 2:
							System.out.println("Selecciona la id de un grupo ");
							showList(studentController.getByGroup(selectGroup()), " estudiantes del grupo");
							break;
						case 3:
							String name = getStringDatabase("Introduce el nombre de un alumno para realizar la busqueda", false, 50);
							showList(studentController.getByName(name), "estudiantes con el nombre " + name);
							break;
						case 4:
							String surname = getStringDatabase("Introduce appelidos de un alumno para realizar la busquea", false, 50);
							showList(studentController.getBySurname(surname), " estudiantes con el apellido " + surname);
						break;	
						
						case 5:
							System.out.println("Has seleccionado la opcion de volver al menu de alumnos");
							break;
					} 
				}while(filterOption != 5);
				case 2:
					if(groupController.getTotalCount() <= 0) {
						System.out.println("No se puedeen crear nuevos alumnos porque no hay grupos creados");
						break;
					}
					
					
					createNewInstances( obtainedNIA -> {
						String NIA = Integer.toString(obtainedNIA);
						Group group = selectGroup();
						Student student = new Student(NIA, getStringDatabase("Introduce el nombre del alumno", true, 50),
								getStringDatabase("Introduce el apellido del alumno", true, 50), group);
						manageResult(studentController.save(student), "Se ha creado el estudiante correctamente", "Creando alumno...");
						return null; //FIN DE LA LAMBDA
					}, "Introduce el NIA del nuevo alumno", "Creacion de alumnos finalizada.");
					
				break;	
				
				case 3:
					showList(studentController.getAll(), " estudiantes");
					String NIA = getStringDatabase("Selecciona la id del alumno que quires eliminar ", false);
					OperationResult result = studentController.delete(NIA, false);
					if(result == OperationResult.NOT_DELETE) {
						if(confirmationMessage("El alumno esta matriculado de modulos presenciales o tiene pendiente un TFG. Si lo eliminas se  eliminara toda la informacion")) {
							System.out.println("Eliminando el alumno junto con todos sus datos asociados...");
							studentController.delete(NIA, true);
							System.out.println("Se han eliminado el alumno y todos sus datos correctamente");
						}
					} else {
						manageResult(result , "Se ha eliminado el estudiante correctamente", "Eliminando estudiante...");
					}
					
				break;
				
				case 4:
					System.out.println("Se han eliminado " + groupController.deleteAll() + " alumnos ");
					break;
				case 5:
					showList(studentController.getAll(), "alumnos");
					
					Subject subject = null;
					while(subject == null) {
						showList(subjectController.getAll(), " modulos existentes");
						subject = subjectController.getById(getInt("Selecciona la id  a la cual quiers matricular el alumno", false));
						if(subject == null)
							System.out.println("El modulo seleccionado no es valido. Intentalo de nuevo");
						
					}
					
					OperationResult enrollment = studentController.enrollStudent(getStringDatabase("Indica el nia del alumno que quires matricular", false), subject);
					if(enrollment == OperationResult.ALREADY_EXISTS) {
						System.out.println("El alumno ya esta matriculado en la asignatura indicada");
					} else {
						manageResult(enrollment , "Se ha matriculado al alumno correctamente", "Matriculando al alumno....");
					}
					
					break;
				case 6:
					System.out.println("Has seleccionado la opcion de volver al menu principal");
					break;
				default:
					System.out.println("Debes de seleccionar una ocion del 1 al 6");
					break;
		}
	}
	
	private static Group selectGroup() {
		Group group = null;
		while(group == null) {
			showList(groupController.getAll(), " grupos existentes");
			group = groupController.getById(getInt("Selecciona la id del grupo que quieres asignar al alumno", false));
			if(group == null) {
				System.out.println("El grupo seleccionado no es valido. Intentalo de nuevo");
			}
		}
		return group;
	}
	
	/**
	 * Maneja las respuestas que se devuelven al trabajar con la entidad Student de la base de datos
	 * @param result Es el tipo de resultado que puedo devolver una operacion
	 * @param ok Es el texto que se mostrara si la operacion se ha realizado con exito
	 * @param processing Es el texto que se muestra antes de comenzar a realizar una operacion
	 */
	private static void manageResult(OperationResult result, String ok, String processing) {
		System.out.println(processing);
		switch(result) {
			case OK:
				System.out.println(ok);
				break;
			case ALREADY_EXISTS:
				System.out.println("El estduiante con ese NIA ya existe");
				break;
			case NOT_EXISTS:
				System.out.println("El estudiante no existe");
				break;
			case COMMON_ERROR: default:
			System.out.println("Se ha producido un error");
			break;
				
		}
	}

}
