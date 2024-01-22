package com.cristian.JPASerpisFP.View.Utils;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import static com.cristian.JPASerpisFP.View.Utils.Util.isNumeric;

public class Input {
	
	
	public static String getStringDatabase(String showText, String errorText, boolean allowBlank, int maxBytes) {
		System.out.println(showText);
        String string = null;
        do {
            try {
            	string = getString();
                if (!allowBlank && string.isEmpty()) {
                    throw new InputMismatchException("El valor no puede estar en blanco.");
                }
                if(string.getBytes(StandardCharsets.UTF_8).length > maxBytes) {
                	throw new InputMismatchException("Superada longitud maxima en bytes");
                }
            } catch (InputMismatchException e) {
               	string = null;
                System.out.println(errorText);
            }
        } while (string == null);
        
        return string;
    }
	
	
	public static String getStringDatabase(String showText, boolean allowBlank, int maxBytes) {
		return getStringDatabase(showText, "Debes indicar un texto alfanumerico valido que sea mas corto", allowBlank, maxBytes);
	}
	
	public static String getStringDatabase(String showText, boolean allowBlank) {
		return getStringDatabase(showText, allowBlank, 50);
	}
	
	
	
	public static Integer getInt(String showText, String errorText, boolean allowBlank) {
        System.out.println(showText);
        Integer integer = null;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();
                if (!allowBlank && input.isEmpty()) {
                    throw new InputMismatchException("Blank value is not valid");
                }
                integer = input.isEmpty() ? 0 : Integer.parseInt(input);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(errorText);
            }
            
        } while (integer == null || !isNumeric(integer.toString()));

        return integer;
    }
	
	
	public static Integer getInt(String showText, boolean allowBlank) {
		
		return getInt(showText, "Debes indicar un numero entero", allowBlank);
	}
	
	
	
	public static String getString() {
		String input =  new Scanner(System.in).nextLine();
		return input.trim();
	}
	
	public static boolean confirmationMessage(String showText) {
		System.out.println(showText);
		System.out.println("Introudce no para cancelar. Si deseas continuar, introduce si o cualquier otro caracter.");
		String input = getString();
		return !input.equalsIgnoreCase("no"); 
	}

	
	
	
	

	
}
