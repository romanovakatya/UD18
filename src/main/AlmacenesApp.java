package main;

import clases.BDMySQL;

public class AlmacenesApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla almacenes,
		String QueryAlmacenes = "CREATE TABLE almacenes(\r\n"
				+ "codigo int auto_increment not null,\r\n"
				+ "lugar varchar(255) not null,\r\n"
				+ "capacidad int NOT NULL,\r\n"
				+ "primary key (codigo));";
		
		BDMySQL.crearTabla(db, "almacenes", QueryAlmacenes);
		
		String QueryInsertAlmacenes = "INSERT INTO almacenes (lugar, capacidad)"
				+ "VALUES ('Valencia',3), ('Barcelona',4), ('Bilbao',7), ('Los Angeles',2), "
				+ "('San Francisco',8);";
	
		BDMySQL.insertData(db, "almacenes", QueryInsertAlmacenes);
		
		
		//tabla cajas,
		String QueryCajas = "CREATE TABLE cajas(\r\n"
				+ "numreferencia varchar(255) not null,\r\n"
				+ "contenido varchar(255) not null,\r\n"
				+ "valor double not null,\r\n"
				+ "codigoAlmacen int not null,\r\n"
				+ "primary key (numreferencia)\r\n,"
				+ "KEY (codigoAlmacen)\r\n,"
				+ "FOREIGN KEY (codigoAlmacen)\r\n"
				+ "references almacenes(codigo)\r\n"
				+ "on delete cascade\r\n"
				+ "on update cascade);";
		
		BDMySQL.crearTabla(db, "cajas", QueryCajas);
		
		String QueryInsertCajas = "INSERT INTO cajas VALUES "
				+ "('0MN7','Rocks',180,3), ('4H8P','Rocks',250,1), ('4RT3','Scissors',190,4),"
				+ "('7G3H','Rocks',200,1), ('8JN6','Papers',75,1), ('8Y6U','Papers',50,3),"
				+ "('9J6F','Papers',175,2), ('LL08','Rocks',140,4), ('P0H6','Scissors',125,1),"
				+ "('P2T6','Scissors',150,2), ('TU55','Papers',90,5);";
	
		BDMySQL.insertData(db, "cajas", QueryInsertCajas);
		
		BDMySQL.cerrarConexion();

	}

}
