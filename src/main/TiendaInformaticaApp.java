package main;

import clases.BDMySQL;

public class TiendaInformaticaApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla fabricantes,
		String QueryFabricantes = "CREATE TABLE fabricantes(\r\n"
				+ "codigo int auto_increment,\r\n"
				+ "nombre varchar(100),\r\n"
				+ "primary key (codigo));";
		
		BDMySQL.crearTabla(db, "fabricantes", QueryFabricantes);
		
		String QueryInsertFabricantes = "INSERT INTO fabricantes (nombre)"
				+ "VALUES ('Sony'), ('Creative Labs'), ('Hewlett-Packard'), ('Iomega'), ('Fujitsu'), ('Winchester');";
	
		BDMySQL.insertData(db, "fabricantes", QueryInsertFabricantes);
		
		String QuerySelectFabricantes = "SELECT * FROM fabricantes";
		
		BDMySQL.getValues(db, "fabricantes", QuerySelectFabricantes);
		
		//tabla articulos,
		String QueryArticulos = "CREATE TABLE articulos(\r\n"
				+ "codigo int auto_increment not null,\r\n"
				+ "nombre varchar(100),\r\n"
				+ "precio int,\r\n"
				+ "codigoFabricante int,\r\n"
				+ "primary key (codigo)\r\n,"
				+ "KEY (codigoFabricante)\r\n,"
				+ "FOREIGN KEY (codigoFabricante)\r\n"
				+ "references fabricantes(codigo)\r\n"
				+ "on delete set null\r\n"
				+ "on update cascade);";
		
		BDMySQL.crearTabla(db, "articulos", QueryArticulos);
		
		String QueryInsertArticulos = "INSERT INTO articulos (nombre, precio, codigoFabricante)"
				+ "VALUES ('Hard drive',240,5), ('Memory',120,6), ('ZIP drive',150,4), ('Floppy disk',5,6), ('Monitor',240,1), ('DVD drive',180,2),"
				+ "('CD drive',90,2), ('Printer',270,3), ('Toner cartridge',66,3), ('DVD burner',180,2);";
	
		BDMySQL.insertData(db, "articulos", QueryInsertArticulos);
		
		BDMySQL.cerrarConexion();

	}

}
