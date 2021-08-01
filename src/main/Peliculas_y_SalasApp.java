package main;

import clases.BDMySQL;

public class Peliculas_y_SalasApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla peliculas,
		String QueryPeliculas = "CREATE TABLE peliculas(\r\n"
				+ "codigo int auto_increment NOT NULL,\r\n"
				+ "nombre varchar(255) NOT NULL,\r\n"
				+ "calificacionEdad varchar(255) DEFAULT NULL,"
				+ "primary key (codigo));";
		
		BDMySQL.crearTabla(db, "peliculas", QueryPeliculas);
		
		String QueryInsertPeliculas = "INSERT INTO peliculas (nombre, calificacionEdad) VALUES "
				+ "('Citizen Kane','PG'), ('Singin\\' in the Rain','G'), ('The Wizard of Oz','G'), "
				+ "('The Quiet Man',NULL), ('North by Northwest',NULL), ('The Last Tango in Paris','NC-17'),"
				+ "('Some Like it Hot','PG-13'), ('A Night at the Opera',NULL), ('Citizen King','G');";
	
		BDMySQL.insertData(db, "peliculas", QueryInsertPeliculas);
		
		
		//tabla salas,
		String QuerySalas = "CREATE TABLE salas(\r\n"
				+ "codigo int auto_increment not null,\r\n"
				+ "nombre varchar(255) not null,\r\n"
				+ "codigoPelicula int DEFAULT NULL,\r\n"
				+ "primary key (codigo)\r\n,"
				+ "KEY (codigoPelicula)\r\n,"
				+ "FOREIGN KEY (codigoPelicula)\r\n"
				+ "references peliculas(codigo)\r\n"
				+ "on delete set null\r\n"
				+ "on update cascade);";
		
		BDMySQL.crearTabla(db, "salas", QuerySalas);
		
		String QueryInsertSalas = "INSERT INTO salas (nombre, codigoPelicula)"
				+ "VALUES ('Odeon',5), ('Imperial',1), ('Majestic',NULL), ('Royale',6), ('Paraiso',3), "
				+ "('Nickelodeon',NULL);";
	
		BDMySQL.insertData(db, "salas", QueryInsertSalas);
		
		BDMySQL.cerrarConexion();

	}

}
