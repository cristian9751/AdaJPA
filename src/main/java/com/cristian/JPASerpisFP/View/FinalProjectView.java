/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cristian.JPASerpisFP.View;
import com.cristian.JPASerpisFP.Domain.Controller.FinalProjectController;
import com.cristian.JPASerpisFP.Domain.Controller.StudentController;
import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.FinalProject;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import static com.cristian.JPASerpisFP.View.Utils.Input.*;
import static com.cristian.JPASerpisFP.View.Utils.Util.*;
/**
 *
 * @author cripoponc
 */
public class FinalProjectView {
    public static int menu() {
        System.out.println("1. Mostrar todos los proyectos");
        System.out.println("2. Crear un nuevo proyecto");
        System.out.println("3. Eliminar un proyecto");
        System.out.println("4. Volver atras");
        return getInt("Selecciona una opcion del 1 al 3", false);
    }
        
    private static Long FinalProjecCounter;
    
    private static FinalProjectController finalProjectController = new FinalProjectController();
    private static StudentController studentController = new StudentController();
    
      public static void showView() {
		int selectedOption = 0;
		do {
			FinalProjecCounter = finalProjectController.getTotalCount();
			selectedOption = FinalProjectView.menu();
			if(FinalProjecCounter == 0 && (selectedOption == 1 || selectedOption == 3)) {
				System.out.println("No hay proyectos");
				continue;
			}
			action(selectedOption);
		} while(selectedOption != 4);
	}
      
      
      private static void action(int selectedOption) {
          switch(selectedOption) {
              case 1:
                  showList(finalProjectController.getAll(), " proyectos");
              break;
              
              case 2:
                  if(studentController.getTotalCount() <= 0) {
                      System.out.println("No se pueden crear proyectos porque no hay alumnos");
                      break;
                  }
                  showList(studentController.getAll(), "alumnos");
                  Student student = StudentView.selectStudent();
                  
                  createNewInstances(obtainedId -> {
                      String projectCode = Integer.toString(obtainedId);
                      FinalProject project = new FinalProject(projectCode,  student, getStringDatabase("Introduce el titulo del proyecto", true));
                      manageResult(finalProjectController.save(project), "Proyecto creado correctamente", "Creando proyecto...");
                      return null;
                  }, "Introduuce el codigo del nuevo proyecto", "Creacion de proyectos finalizada");
              break; 
              
              case 3:
                  String finalProjectCode = getStringDatabase("Introduce el codigo de proyecto que quieres eliminar", false);
				manageResult(finalProjectController.delete(finalProjectCode), "Se ha eliminado el proyecto", "Eliminando proyecto..");
				
                  break;
              case 4:
                  System.out.println("Has seleccionado la opcion de volver al menu principal");
                  break;
                  
                  
          }
          
          
          
    }
      
      private static void manageResult(OperationResult result, String ok, String processing) {
		System.out.println(processing);
		switch(result) {
			case OK:
				System.out.println(ok);
				break;
			case ALREADY_EXISTS:
				System.out.println("El proyecto con ese codigo ya existe");
				break;
			case NOT_EXISTS:
				System.out.println("El proyecto no existe");
				break;
			case COMMON_ERROR: default:
			System.out.println("Se ha producido un error");
			break;
				
		}
	}
     
    
}
