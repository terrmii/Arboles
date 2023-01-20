package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	            conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bbdd, username, password);
				Statement st = conexion.createStatement();
				opcion_menu = scan.nextInt();
				scan.nextLine();
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
	                conexion.close();
	                st.close();
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
	            	id = Integer.parseInt(scan.nextLine());
	            	
	            	
	            	System.out.println("Que desea modificar: (nombreComun, nombreCientifico, habitat, origen, altura)");
	            	String modificar = scan.nextLine().toLowerCase();
	            	if(modificar.equals("nombre comun")) {
	            		modificar = "nombreComun";
	            	}
	            	
	            	System.out.println("Introduzca el nuevo valor: ");
		            String nuevoValor = scan.nextLine();
	            		
	            	String sentenciaActualizar = "UPDATE arbol SET `"+modificar+"` = '"+nuevoValor+"' WHERE `arbol`.`id` = "+id;
		            st.execute(sentenciaActualizar);
		            	
	            	
	            	
	            	//UPDATE `arbol` SET `nombre_comun` = 'siso' WHERE `arbol`.`id` = 5
	            	
	                break;
	            case OPCION_CUATRO:
	            	String visualizarArboles = "SELECT * FROM arbol";
	            	ResultSet resultado = st.executeQuery(visualizarArboles);
	            	System.out.println("ID | NOMBRE COMUN | NOMBRE CIENTIFICO | HABITAT | ALTURA | ORIGEN");
	            	while(resultado.next()) {
	            		System.out.println(resultado.getInt(1)+" | "+resultado.getString(2)+" | "+resultado.getString(3)+" | "+resultado.getString(4)+" | "+resultado.getString(5)+" | "+resultado.getString(6));
	            	}
	            	
	            	
	            	
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
