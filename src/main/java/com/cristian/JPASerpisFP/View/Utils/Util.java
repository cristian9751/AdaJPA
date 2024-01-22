package com.cristian.JPASerpisFP.View.Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.cristian.JPASerpisFP.View.Utils.Input.*;

public class Util {
	public static <T> void showList(List<T> list, String text) {
		if(list.isEmpty()) {
			System.out.println("No hay " + text);
			return;
		}
		Iterator<T> it = list.iterator();
		System.out.println("Lista de " + text);
		System.out.println("-------------------");
		while(it.hasNext()) {
			System.out.println(it.next());
			if(it.hasNext())
				pressAnyKeyToContinue();
		}
		System.out.println("----------FIN DE LA LISTA----------");
	}
	
	public static void pressAnyKeyToContinue() {
		System.out.println(""); 
		System.out.println("Presiona la tecla enter para continuar...");
		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
		}
	}
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}
	
	
	public static void createNewInstances(Function<Integer, Void>  operation, String requestId, String creationDone) {
		int id = -1;
		while(id != 0) {
			id = Input.getInt(requestId, false);
			if(id == 0) break;
			operation.apply( id);
		}
		
		System.out.println(creationDone);
		
		
	}
	
}
