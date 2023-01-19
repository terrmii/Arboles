package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import clases.Arbol;

public class GestorArboles {

	private static final String host = "localhost";
	private static final String bbdd = "eh_garden";
	private static final String username = "root";
	private static final String password = "";
	
		public void run() throws SQLException, ClassNotFoundException {
			
			Connection conexion;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bbdd, username, password);
			
			Statement st = conexion.createStatement();
			
			Scanner scan = new Scanner(System.in);
			Arbol arbol = new Arbol();
			
			//De momento usare estos atributos
			
			int altura, id;
			String nombreComun, nombreCientifico, habitat, origen;
			
			final int OPCION_UNO = 1;
	        final int OPCION_DOS = 2;
	        final int OPCION_TRES = 3;
	        final int OPCION_CUATRO = 4;
	        final int SALIR = 0;
	        int opcion_menu;
	        
	        do {
	            System.out.println(" ------MENU------");
	            System.out.println("Elije una de las opciones");
	            System.out.println(OPCION_UNO + ". Insertar arbol");
	            System.out.println(OPCION_DOS + ". Eliminar arbol");
	            System.out.println(OPCION_TRES + ". Modificar informacion del arbol");
	            System.out.println(OPCION_CUATRO +". Visualizar arboles");
	            System.out.println(SALIR + ". Salir");

	            opcion_menu = Integer.parseInt(scan.nextLine());
	            switch (opcion_menu) {
	            case OPCION_UNO:
	                System.out.println("Introduzca el nombre comun: ");
	                nombreComun = scan.nextLine();
	                System.out.println("Introduzca el nombre cientifico: ");
	                nombreCientifico = scan.nextLine();
	                System.out.println("Introduzca el habitat: ");
	                habitat = scan.nextLine();
	                System.out.println("Introduzca el origen:");
	                origen = scan.nextLine();
	                System.out.println("Introduzca la altura: " );
	                altura = scan.nextInt();
	                st.execute("INSERT INTO arbol (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES ('"+nombreComun+"', '"+nombreCientifico+"', '"+habitat+"', "+altura+", '"+origen+"');");
	                
	                //INSERT INTO `arbol` (`id`, `nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES (NULL, 'roble', 'robledo', 'jungla', '2', 'Habitacion');
	                break;
	            case OPCION_DOS:
	                System.out.println("Introduzca la ID del arbol que desee eliminar: \n");
	                id = scan.nextInt();
	                String sentenciaDelete = "DELETE FROM arbol WHERE `arbol`.`id` = "+id;
	                st.execute(sentenciaDelete);
	                
	                //"DELETE FROM arbol WHERE `arbol`.`id` = 2"
	                break;
	            case OPCION_TRES:
	            	
	            	System.out.println("Introduzca la ID del arbol que desee modificar: ");
	            	id = scan.nextInt();
	            	System.out.println("Que desea modificar: (nombreComun, nombreCientifico, habitat, origen, altura)");
	            	String modificar = scan.nextLine();
	         
	            	if(modificar.equals("nombreComun") || modificar.equals("nombreCientifico")||modificar.equals("habitat") || modificar.equals("origen") || modificar.equals("altura")) {
	            	  	System.out.println("Introduzca el nuevo valor: ");
		            	String nuevoValor = scan.nextLine();
	            		
	            		String sentenciaActualizar = "UPDATE `arbol` SET `"+modificar+"` = '"+nuevoValor+"' WHERE `arbol`.`id` = "+id;
		            	st.execute(sentenciaActualizar);
		            	
		             
		            
		           
	            	}
	            	//ERROR MODIFICAR
	            	else {
	            		System.out.println("Introduzca un campo correcto");
	            	}
	           
	            	
	            	//UPDATE `arbol` SET `nombre_comun` = 'siso' WHERE `arbol`.`id` = 5
	            	
	            	//Hacer si existe
//	            	if(null) {
//	            		
//	            	}
//	            	else
//	            		System.out.println("No existen arboles");
//	                System.out.println("tercera opcion seleccionada\n");
	                break;
	            case OPCION_CUATRO:
	            	
	            	break;
	            case SALIR:
	                System.out.println("ADIOS");
	                break;
	            default:
	                System.out.println("Opcion incorrecta!");
	            }
	        } while (opcion_menu != SALIR);
	        
	        conexion.close();
		
	       
		}
		
	
}
