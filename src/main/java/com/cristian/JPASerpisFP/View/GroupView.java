package com.cristian.JPASerpisFP.View;
import com.cristian.JPASerpisFP.Domain.Controller.GroupController;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.View.Utils.Input;
import com.cristian.JPASerpisFP.View.Utils.Util;

import static com.cristian.JPASerpisFP.View.Utils.Input.*;
import static com.cristian.JPASerpisFP.View.Utils.Util.*;

public class GroupView {
	
	private static GroupController groupController = new GroupController();
	

	private static Long groupCounter;
	/**
	 * Menu de principal de grupo
	 * @return Opcion seleccionada en el menu
	 */
	private static int menu() {
		System.out.println("--------------------");
		System.out.println("Hay " + groupCounter + " grupos");
		System.out.println("1. Mostrar grupos (todos  o filtrados)");
		System.out.println("2. Crear un nuevo grupo");
		System.out.println("3. Eliminar un grupo existente");
		System.out.println("4. Eliminar todos los grupos");
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
		System.out.println("1- Mostrar todos los grupos");
		System.out.println("2. Mostrar los grupos filtrados por clase o aula");
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
			groupCounter = groupController.getTotalCount();
			selectedOption = GroupView.menu();
			if(groupCounter == 0 && (selectedOption == 1 || selectedOption == 3)) {
				System.out.println("No hay grupos");
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
						showAll();
					} else if(filterOption == 2) {
						String classroom = getStringDatabase("Indica la clase o aula por la cual quiers filtrar", false, 10);
						showList(groupController.getByClassroom(classroom), "grupos que tengan esa clase");
					}
				} while(filterOption != 3);
				break;
			case 2: 
				createGroups();
				break;
			case 3:
				//ELIMINAR GRUPO SELECCIONADO CON CONFIRMACION PARA ELIMINAR LOS ALUMNOS PARTICIPANTES
				showAll();
				int groupCode = getInt("Introduce la id del grupo que quieres eliminar", false);
				OperationResult result = groupController.delete(groupCode, false);
				if(result == OperationResult.NOT_DELETE) {
					showList(groupController.getById(groupCode).getStudents(), " estudiantes pertenecientes al grupo ");
					if(confirmationMessage("El grupo que intentas eliminar tiene alumnos asignados. Si continuas se eliminaran todos los alumnos.")) {
						System.out.println("Eliminando el grupo y todos los alumnos asociados...");
						groupController.delete(groupCode, true);
						System.out.println("Se ha eliminado el grupo y todos sus alumnos correctamente");
					}
						
				} else {
					manageResult(result, "Se ha eliminado el grupo correctamente", "Eliminando grupo....");
				}
				break;
			case 4:
				System.out.println("Se han eliminado " + groupController.deleteAll() + " grupos");
				break;
			case 5:
				System.out.println("Has seleccionado la opcion para volver al menu principal");
				break;
			default:
				System.out.println("Debes seleccionar una opcion del 1 al 4");
				break;
		}
	}
	
	
	
	public static Group selectGroup() {
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
	
	public static void showAll() {
		showList(groupController.getAll(), "grupos");
	}
	
	
	
	/**
	 * Crear grupos hasta que el usuario introduzca la id 0
	 * podra crear grupso basicos solo con id  o grupos completos con todos los parametros
	 */
	public static void createGroups() {
		createNewInstances( obtainedId -> {
			Group group = new Group(obtainedId, getStringDatabase("Introduce la clase a la cual quieres asignar el grupo", true, 10),
					getStringDatabase("Introduce una breve descripcion del grupo", true, 50)
					);
			manageResult(groupController.save(group), "Grupo creado con exito", "Creando grupo...");
			return null; //FIN DE LA LAMBDA
		}, "Introduce la id del nuevo grupo", "Creacion de grupos finalizada.");
		
		
		System.out.println("Se han creado " + (groupController.getTotalCount() - groupCounter) + " grupos.");
		
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
				System.out.println("El grupo con esa id ya existe");
				break;
			case NOT_EXISTS:
				System.out.println("El grupo no existe");
				break;
			case COMMON_ERROR: default:
			System.out.println("Se ha producido un error");
			break;
				
		}
	}
}
