package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import clases.Arbol;

/**
 * 
 * @author ${Arnold Bermell}
 *
 */
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
				
				
				//PREPARATE
				
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
	                
	                PreparedStatement ps = conexion.prepareStatement("INSERT INTO arbol (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES (?, ?, ? ,?, ?);");
	                ps.setString(1, nombreComun);
	                ps.setString(2, nombreCientifico);
	                ps.setString(3,  habitat);
	                ps.setInt(4, altura);
	                ps.setString(5, origen);
	                ps.execute();
//	                st.execute("INSERT INTO arbol (nombre_comun, nombre_cientifico, habitat, altura, origen) VALUES ('"+nombreComun+"', '"+nombreCientifico+"', '"+habitat+"', "+altura+", '"+origen+"');");
//	                conexion.close();
//	                st.close();
	                //INSERT INTO `arbol` (`id`, `nombre_comun`, `nombre_cientifico`, `habitat`, `altura`, `origen`) VALUES (NULL, 'roble', 'robledo', 'jungla', '2', 'Habitacion');
	                break;
	            case OPCION_DOS:
	                System.out.println("Introduzca la ID del arbol que desee eliminar: \n");
	                id = scan.nextInt();
	                ps = conexion.prepareStatement("DELETE FROM arbol WHERE `arbol`.`id` = ?");
	                ps.setInt(1, id);
	                ps.execute();
//	                String sentenciaDelete = "DELETE FROM arbol WHERE `arbol`.`id` = "+id;
//	                st.execute(sentenciaDelete);
	                
	                //"DELETE FROM arbol WHERE `arbol`.`id` = 2"
	                break;
	            case OPCION_TRES:
	            	
	            	System.out.println("Introduzca la ID del arbol que desee modificar: ");
	            	id = Integer.parseInt(scan.nextLine());
	            	
	            	
	            	System.out.println("Que desea modificar: (nombre comun, nombre cientifico, habitat, origen, altura)");
	            	String modificar = scan.nextLine().toLowerCase();
	            	if(modificar.equals("nombre comun")) {
	            		modificar = "nombre_comun";
	            	}
	            	else if(modificar.equals("nombre cientifico")) {
	            		modificar = "nombre_cientifico";
	            	}
	            	else if(modificar.equals("id")) {
	            		System.out.println("No se puede modificar el ID");
	            	}
	            	
	            	
	            	if(modificar.equals("nombre_comun") || modificar.equals("nombre_cientifico")||modificar.equals("habitat") || modificar.equals("origen") || modificar.equals("altura")) {
	            		System.out.println("Introduzca el nuevo valor: " );
			            String nuevoValor = scan.nextLine();
			            
//			            String sentenciaActualizar = "UPDATE arbol SET `"+modificar+"` = '"+nuevoValor+"' WHERE `arbol`.`id` = "+id;
			            //UPDATE arboles SET nombre_comun= ?, nombre_cientifico= ?, habitad= ?, altura= ?, origen= ? WHERE id = ?;
			            ps = conexion.prepareStatement("UPDATE arbol SET " + modificar + " = ? WHERE id = ?");
			            ps.setString(1, nuevoValor);
			            ps.setInt(2, id);
			            ps.execute();
//			            st.execute(sentenciaActualizar);
	            	}
	            	else {
	            		System.out.println("Introduzca un valor valido");
	            		
	            	}
	            		break;
	            	
	            	//UPDATE `arbol` SET `nombre_comun` = 'siso' WHERE `arbol`.`id` = 5
	            	

	            case OPCION_CUATRO:
//	            	String visualizarArboles = "SELECT * FROM arbol";
//	            	ResultSet resultado = st.executeQuery(visualizarArboles);
	            	ps = conexion.prepareStatement("SELECT * FROM arbol");
//	              	ResultSet resultado = ps.executeQuery(visualizarArboles);
	              	ResultSet resultado = ps.executeQuery();
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
	        scan.close();
	        
		}
		
	
}
