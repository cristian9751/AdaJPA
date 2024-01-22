package com.cristian.JPASerpisFP.View;
import com.cristian.JPASerpisFP.Domain.Controller.GroupController;
import com.cristian.JPASerpisFP.Domain.Controller.SubjectController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.Domain.Entity.Subject;
import com.cristian.JPASerpisFP.View.Utils.Input;
import com.cristian.JPASerpisFP.View.Utils.Util;

import static com.cristian.JPASerpisFP.View.Utils.Input.*;
import static com.cristian.JPASerpisFP.View.Utils.Util.*;

public class SubjectView {
	
	private static SubjectController subjectController = new SubjectController();
	

	private static Long subjectCounter;
	/**
	 * Menu de principal de grupo
	 * @return Opcion seleccionada en el menu
	 */
	private static int menu() {
		System.out.println("--------------------");
		System.out.println("Hay " + subjectCounter + " grupos");
		System.out.println("1. Mostrar modulos (todos  o filtrados)");
		System.out.println("2. Crear un nuevo modulo");
		System.out.println("3. Eliminar un modulo existente");
		System.out.println("4. Eliminar todos los modulos");
		System.out.println("5. Volver atras");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion del 1 al 5: ", false);
	}
	
	/**
	 * Menu de filtrado de grupos
	 * @return Opcion de filtrado seleccionada
	 */
	private static int filterMenu() {
		System.out.println("--------------------");
		System.out.println("1- Mostrar todos los modulos");
		System.out.println("2. Mostrar los grupos filtrados por numero de horas")	;
		System.out.println("3. Volver atras");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion de la 1 a la 3", false);
	}
	
	
	
	
	/**
	 * Metodo que mustra el menu principal de grupo
	 * 
	 * 
	 */
	
	public static void showView() {
		int selectedOption = 0;
		do {
			subjectCounter = subjectController.getTotalCount();
			selectedOption = SubjectView.menu();
			if(subjectCounter == 0 && (selectedOption == 1 || selectedOption == 3)) {
				System.out.println("No hay modulos");
				continue;
			}
			action(selectedOption);
		} while(selectedOption != 5);
	}
	
	/**
	 * Metodo que especifica las acciones que realiza el menu principal de grupos
	 * @param selectedOption Recibe la opcion seleccionada en el menu
	 */
	
	public static void action(int selectedOption) {
		switch(selectedOption) {
			//MOSTRAR TODOS LOS GRUPOS
			case 1: 
				int filterOption;
				do {
					filterOption = filterMenu();
					if(filterOption == 1) {
						showList(subjectController.getAll(), " modulos");
					} else if(filterOption == 2) {
						int hours = getInt("Indica las horas por las que quires filtrar", false);
						showList(subjectController.getByHours(hours), " modulos con " + hours + " horas");
					}
				} while(filterOption != 3);
				break;
			case 2: 
				createNewInstances( obtainedSubjectCode -> {
					Subject subject = new Subject(obtainedSubjectCode, getInt("Numero de horas que tiene el modulo", true), 
							getStringDatabase("Introduce una breve descripcion del modulo", false));
					manageResult(subjectController.save(subject), "Modulo creado correctamente", "Creando modulo...");
					return null; //FIN DE LA LAMBDA
				}, "Introduce la id del nuevo modulo", "Creacion de modulos finalizada.");
				break;
			case 3:
				showList(subjectController.getAll(), " modulos");
				int subjectCode = getInt("Introduce la id del modulo que quieres eliminar", false);
				OperationResult result = subjectController.delete(subjectCode, false);
				if(result == OperationResult.NOT_DELETE) {
					if(confirmationMessage("El modulo que intentas eliminar tiene alumnos matriculados. Si continuas se eliminaran todas las matriculas.")) {
						System.out.println("Eliminando el modulo y todas las matriculas asociadas...");
						subjectController.delete(subjectCode, true);
						System.out.println("Se ha eliminado el modulo y todas sus matriculas correctamente");
					}
						
				} else {
					manageResult(result, "Se ha eliminado el modulo correctamente", "Eliminando modulo....");
				}
				break;
			case 4:
				System.out.println("Se han eliminado " + subjectController.deleteAll() + " grupos");
				break;
			case 5:
				System.out.println("Has seleccionado la opcion para volver al menu principal");
				break;
			default:
				System.out.println("Debes seleccionar una opcion del 1 al 4");
				break;
		}
	}
	
	
	
	
	

	
	
	


	
	/**
	 * Maneja las respuestas que se devuelven al trabajar con la entidad Group de la base de datos
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
				System.out.println("El modulo con esa id ya existe");
				break;
			case NOT_EXISTS:
				System.out.println("El modulo no existe");
				break;
			case COMMON_ERROR: default:
			System.out.println("Se ha producido un error");
			break;
				
		}
	}
}

