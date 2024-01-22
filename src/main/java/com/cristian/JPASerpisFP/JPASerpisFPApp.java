package com.cristian.JPASerpisFP;

import com.cristian.JPASerpisFP.View.MainView;
import com.cristian.JPASerpisFP.model.PersistenceManager;

public class JPASerpisFPApp {
	public static void runApp(boolean defaultData) {
		System.out.println("Iniciando...");
		boolean  databaseConnected = PersistenceManager.setFactory();
		if(databaseConnected) {
			System.out.println("Bienvenido");
			MainView.showView();
		} else {
			System.out.println("No se ha podido conectar con la base de datos. Saliendo..");
		}
	}
}
