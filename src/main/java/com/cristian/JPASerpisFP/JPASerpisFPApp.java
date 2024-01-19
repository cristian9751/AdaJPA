package com.cristian.JPASerpisFP;

import com.cristian.JPASerpisFP.View.MainView;
import com.cristian.JPASerpisFP.model.PersistenceManager;

public class JPASerpisFPApp {
	public static void runApp() {
		try {
			PersistenceManager.setFactory();
			MainView.showView();
		} catch(Exception e) {
			System.out.println("Se ha producido un error: No se ha podido conectar a la base de datos");
		}
	}
}
