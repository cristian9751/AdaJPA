package com.cristian.JPASerpisFP.View;
import com.cristian.JPASerpisFP.Domain.Controller.GroupController;
import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;

import static com.cristian.JPASerpisFP.View.Utils.Input.*;
import static com.cristian.JPASerpisFP.View.Utils.Util.*;

public class GroupView {
	
	private static GroupController controller = new GroupController();
	
	
	public static int menu() {
		System.out.println("--------------------");
		System.out.println("Contador de grupos: " + controller.getTotal());
		System.out.println("1. Mostrar todos los grupos");
		System.out.println("2. Insertar un nuevo grupo");
		System.out.println("3. Eliminar un grupo existente");
		System.out.println("4. Volver atras");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion del 1 al 3: ");
	}
	
	
	public static void showView() {
		int selectedOption = 0;
		do {
			selectedOption = GroupView.menu();
			action(selectedOption);
		} while(selectedOption != 4);
	}
	
	
	public static void action(int selectedOption) {
		switch(selectedOption) {
			case 1: 
				showList(controller.getAll(), "grupos");
				break;
			case 2:
				Group group = new Group(getInt("Introduce la id del nuevo grupo"), 
						getString("Escribe la descripcion del nuevo grupo"), getString("Introduce el aula del "
								+ "nuevo grupo"));
				manageResult(controller.save(group), "Grupo añadido correctamente");
				break;
			case 3:
				if(showList(controller.getAll(), "grupos")) {
					int groupCode = getInt("Introduce la id del grupo que quieres eliminar");
					manageResult(controller.delete(groupCode), "Grupo eliminado correctamente");
				}
				break;
			default:
				System.out.println("Debes  seleccionar una opcion valida");
				break;
		}
	}
	
	private static void manageResult(OperationResult result, String ok) {
		switch(result) {
			case OK:
				System.out.println(ok);
				break;
			case ALREADY_EXISTS:
				System.out.println("El grupo ya existe");
				break;
			case NOT_EXISTS:
				System.out.println("El grupo no existe");
				break;
			case COMMON_ERROR:
				System.out.println("Ha ocurrido un error");
				break;
				
		}
	}
}
