package main;

import clases.BDMySQL;

public class LosGrandesAlmacenesApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla productos,
		String QueryProductos = "CREATE TABLE productos(\r\n"
				+ "codigo int auto_increment,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "precio int not null,\r\n"
				+ "PRIMARY KEY (codigo));";
		
		BDMySQL.crearTabla(db, "productos", QueryProductos);
		
		String QueryInsertProductos = "INSERT INTO productos (nombre, precio) VALUES "
				+ "('leche', 11), ('pan', 2), ('vino', 18), ('salsa', 3),\r\n"
				+ "('sandia', 90), ('jamon', 120), ('tomate', 8), ('agua', 1),\r\n"
				+ "('pizza', 3), ('almendra', 5);";
	
		BDMySQL.insertData(db, "productos", QueryInsertProductos);
		
		
		//tabla cajeros,
		String QueryCajeros = "CREATE TABLE cajeros(\r\n"
				+ "codigo int auto_increment,\r\n"
				+ "nomApels varchar(255) not null,\r\n"
				+ "PRIMARY KEY (codigo));\r\n";
		
		BDMySQL.crearTabla(db, "cajeros", QueryCajeros);
		
		String QueryInsertCajeros = "INSERT INTO cajeros (nomApels) VALUES"
				+ "('Laya'), ('Alba'), ('Jack'), ('Fred'), ('Isabel'), \r\n"
				+ "('María'), ('Lidia'), ('Andry'), ('Petr'), ('Sergiy');";
	
		BDMySQL.insertData(db, "cajeros", QueryInsertCajeros);
		
		
		//tabla maquinas_registradoras,
		String QueryMaquinas = "CREATE TABLE maquinas_registradoras(\r\n"
				+ "codigo int auto_increment,\r\n"
				+ "piso int null,\r\n"
				+ "PRIMARY KEY (codigo));\r\n";
		
		BDMySQL.crearTabla(db, "maquinas_registradoras", QueryMaquinas);
		
		String QueryInsertMaquinas = "INSERT INTO maquinas_registradoras (piso) VALUES"
				+ "(1),(2),(3),(4),(5),(6),(11),(9),(34),(0);";
	
		BDMySQL.insertData(db, "maquinas_registradoras", QueryInsertMaquinas);
		
		
		//tabla venta,
		String QueryVenta = 
				"CREATE TABLE venta(\r\n"
				+ "codigoProducto int,\r\n"
				+ "codigoCajero int,\r\n"
				+ "codigoMaquina_Registradora int,\r\n"
				+ "PRIMARY KEY (codigoProducto, codigoCajero, codigoMaquina_Registradora),\r\n"
				+ "FOREIGN KEY (codigoProducto)\r\n"
				+ "references productos(codigo)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade,\r\n"
				+ "FOREIGN KEY (codigoCajero)\r\n"
				+ "references cajeros(codigo)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade,\r\n"
				+ "FOREIGN KEY (codigoMaquina_Registradora)\r\n"
				+ "references maquinas_registradoras(codigo)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade"
				+ ");";
		
		BDMySQL.crearTabla(db, "venta", QueryVenta);
		
		String QueryInsertVenta = "INSERT INTO venta VALUES"
				+ "(1,1,1), (1,2,4), (10,2,5), (2,2,10), (5,5,9), \r\n"
				+ "(4,8,3), (6,1,7), (4,3,7), (10,10,2), (3,4,8);";
		
		BDMySQL.insertData(db, "venta", QueryInsertVenta);
		
		BDMySQL.cerrarConexion();

	}

}
