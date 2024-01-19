package com.cristian.JPASerpisFP.View.Utils;

import java.util.Scanner;

public class Input {

	
	public static int getInt(String text) {
		Scanner in = new Scanner(System.in);
		int result = 0;
		boolean isValid = true;
		System.out.println(text);
		do {
			try {
				result = in.nextInt();
				isValid = true;
			} catch(Exception e) {
				isValid = false;
				System.out.println("Debes introducir un numero entero. Intentalo de nuevo:");
				in.nextLine();
			}
		} while(!isValid);
		
		return result;
	}
	
	
	public static String getString(String text) {
		Scanner in = new Scanner(System.in);
		System.out.println(text);
		String result = new String();
		boolean isValid = true;
		do {
			try {
				result = in.nextLine();
			} catch(Exception e) {
				isValid = false;
				System.out.println("Debes introducir caracteres alfanumericos. Intentalo de nuevo");
			}
		} while(!isValid);
		
		return result;
		
		
	}
	
	
	public static char getChar(String text) {
		Scanner in = new Scanner(System.in);
		System.out.println(text);
		boolean isValid = true;
		char result = '-';
		do {
			try {
				result = in.nextLine().charAt(0);
			} catch(Exception e) {
				isValid = false;
			}
		} while(!isValid);
		
		return result;
	}
}
