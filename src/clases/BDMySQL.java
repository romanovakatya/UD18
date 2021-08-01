package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BDMySQL {
	
	//atributos,
	protected static Connection conexion;
	
	//constructores,
	//por defecto,
	public BDMySQL() {
		super();
	}
	
	
	//método para conectarse al servidor MySQL de bases de datos,
	public static void crearConexion(String IP, String user, String password) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + IP +"?"
					+ "userTimezone=true&serverTimezone=UTC", user, password);
			
			System.out.println("Server Connected");
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con base de datos");
			System.out.println(ex);
		}
	}
	
	//método que acaba la conexión a MySQL,
	public static void cerrarConexion() {
		try {
			
			conexion.close();
			System.out.println("Se ha finalizado la conexión con el servidor");
			
		} catch (SQLException ex) {
			Logger.getLogger(BDMySQL.class.getName()).log(Level.SEVERE, null, ex);
		}	
	}
	
	//método para crear base de datos,
	public void crearBD(String nombre) {
		
		try {
			
			String Query = "CREATE DATABASE IF NOT EXISTS " + nombre;
			Statement statement = conexion.createStatement();
			statement.executeUpdate(Query);
						
			System.out.println("Se ha creado la base de datos " + nombre
					+ " de forma exitosa");
			
		} catch (SQLException ex) {
			
			Logger.getLogger(BDMySQL.class.getName()).log(Level.SEVERE, null, ex);
		} 
	}
	
	//método para crear tablas en MySQL,
	public static void crearTabla(String db, String nombre, String Query) {
		
		try {
			String Querydb = "USE " + db + ";";
			Statement statementdb = conexion.createStatement();
			statementdb.executeUpdate(Querydb);
			
			String Querydrop = "DROP TABLE IF EXISTS " + nombre + ";";
			Statement statementdrop = conexion.createStatement();
			statementdrop.executeUpdate(Querydrop);
			
			
			Statement statement = conexion.createStatement();
			statement.executeUpdate(Query);
			
			System.out.println("Tabla " + nombre + " creada con éxito");
			
		} catch (SQLException ex) {
			
			System.out.println(ex.getMessage());
			System.out.println("Error creando tabla.");
		}
	}
	
	//método que inserta datos a una tabla,
	
	public static void insertData(String db, String tablaName, String Query) {
		
		try {
			
			String Querydb = "USE " + db + ";";
			Statement statementdb = conexion.createStatement();
			statementdb.executeUpdate(Querydb);
			
			Statement statement = conexion.createStatement();
			statement.executeUpdate(Query);
			
			System.out.println("Datos almacenados correctamente en " + tablaName);
						
		} catch (SQLException ex) {

			//Logger.getLogger(BDMySQL.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
			System.out.println("Error en el almacenamiento");
						
		}
	}
	
	//método para hacer select de datos desde BD MySQL,
	public static void getValues(String db, String tablaName, String Query) {
		
		try {
			String Querydb = "USE " + db + ";";
			Statement statementdb = conexion.createStatement();
			statementdb.executeUpdate(Querydb);
			
			Statement statement = conexion.createStatement();
			java.sql.ResultSet resultado;
			resultado = statement.executeQuery(Query);
			
			while (resultado.next()) {
				
				System.out.println(resultado.getString("codigo")
						+ " " + resultado.getString("nombre"));
			}			
			
		} catch (SQLException ex) {
			
			System.out.println(ex.getMessage());
			System.out.println("Error en el SELECT de datos");
		}
		
	}
}
