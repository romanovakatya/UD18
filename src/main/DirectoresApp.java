package main;

import clases.BDMySQL;

public class DirectoresApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla despachos,
		String QueryDespachos = "CREATE TABLE despachos(\r\n"
				+ "numero int auto_increment not null,\r\n"
				+ "capacidad int not null,\r\n"
				+ "primary key (numero));";
		
		BDMySQL.crearTabla(db, "despachos", QueryDespachos);
		
		String QueryInsertDespachos = "INSERT INTO despachos (capacidad)"
				+ "VALUES (20), (12), (5), (9), (11), (1);";
	
		BDMySQL.insertData(db, "despachos", QueryInsertDespachos);
		
		
		//tabla directores,
		String QueryDirectores = "CREATE TABLE directores(\r\n"
				+ "DNI varchar(8) not null,\r\n"
				+ "nomApels varchar(255) not null,\r\n"
				+ "DNIJefe varchar(8) null,\r\n"
				+ "numeroDespacho int null,\r\n"
				+ "PRIMARY KEY (DNI),\r\n"
				+ "KEY (numeroDespacho),\r\n"
				+ "FOREIGN KEY (numeroDespacho)\r\n"
				+ "references despachos(numero)\r\n"
				+ "on delete set null \r\n"
				+ "on update cascade,\r\n"
				+ "KEY (DNIJefe),\r\n"
				+ "FOREIGN KEY (DNIJefe)\r\n"
				+ "references directores(DNI)\r\n"
				+ "on delete set null \r\n"
				+ "on update cascade\r\n"
				+ ");\r\n";
		
		BDMySQL.crearTabla(db, "directores", QueryDirectores);
		
		String QueryInsertDirectores = "INSERT INTO directores VALUES"
				+ " ('yrt64783', 'Katia Romanova', 'yrt64783', 5),\r\n"
				+ "('erf1urj9', 'Lila Jose', 'yrt64783', 1),\r\n"
				+ "('fgt64343', 'Fred Aster', 'erf1urj9', 2),\r\n"
				+ "('12345678', 'Oleg True', 'fgt64343', 3),\r\n"
				+ "('i678', 'Mila', 'fgt64343', 4),\r\n"
				+ "('56oid', 'Juan', '12345678', 3),\r\n"
				+ "('iu784n', 'Leila', 'erf1urj9', 4),\r\n"
				+ "('0olk8', 'Monica', 'fgt64343', 2),\r\n"
				+ "('nh756', 'Mark', 'erf1urj9', 1),\r\n"
				+ "('p05tdg', 'John', 'fgt64343', 1);";
	
		BDMySQL.insertData(db, "directores", QueryInsertDirectores);
		
		BDMySQL.cerrarConexion();

	}

}
