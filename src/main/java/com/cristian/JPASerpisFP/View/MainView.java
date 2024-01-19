package com.cristian.JPASerpisFP.View;

import static com.cristian.JPASerpisFP.View.Utils.Input.getInt;

public class MainView {
	public static int menu() {
		System.out.println("--------------------");
		System.out.println("SELECCIONA QUIE QUIERES CONSULTAR");
		System.out.println("1. Grupos");
		System.out.println("2. Alumnos");
		System.out.println("3. Modulos");
		System.out.println("4. Salir de la aplicacion");
		System.out.println("--------------------");
		return getInt("Selecciona una opcion del 1 al 3: ");
	}
	
	
	public static void showView() {
		int selectedOption = 0;
		do {
			selectedOption = MainView.menu();
			action(selectedOption);
		} while(selectedOption != 4);
	}
	
	
	public static void action(int selectedOption) {
		switch(selectedOption) {
			case 1:
				GroupView.showView();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				System.out.println("Has seleccionado salir de la aplicacion");
				break;
			default:
				System.out.println("Debes de seleccionar una opcion del 1 al 3");
				break;
		}
	}
}
