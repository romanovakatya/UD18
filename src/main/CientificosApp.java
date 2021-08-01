package main;

import clases.BDMySQL;

public class CientificosApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla cientificos,
		String QueryCientificos = "CREATE TABLE cientificos(\r\n"
				+ "DNI varchar(8),\r\n"
				+ "nomApels varchar(255) not null,\r\n"
				+ "PRIMARY KEY (DNI));";
		
		BDMySQL.crearTabla(db, "cientificos", QueryCientificos);
		
		String QueryInsertCientificos = "INSERT INTO cientificos VALUES "
				+ "('34rt5', 'Katia'),\r\n"
				+ "('5thygd', 'Lidia'),\r\n"
				+ "('kjhg6ygh', 'Cristina'),\r\n"
				+ "('999iikj', 'Josep'),\r\n"
				+ "('123hyubv', 'Albert'),\r\n"
				+ "('9i5tbhmj', 'Francesc'),\r\n"
				+ "('10eudj57', 'Mikel'),\r\n"
				+ "('4rgx9f', 'Luis'),\r\n"
				+ "('000ooii', 'Edit'),\r\n"
				+ "('4567yhn', 'Bogdan');";
	
		BDMySQL.insertData(db, "cientificos", QueryInsertCientificos);
		
		
		//tabla proyectos,
		String QueryProyectos = "CREATE TABLE proyectos(\r\n"
				+ "id char(4),\r\n"
				+ "nombre varchar(255) not null,\r\n"
				+ "horas int null,\r\n"
				+ "PRIMARY KEY (id));\r\n";
		
		BDMySQL.crearTabla(db, "proyectos", QueryProyectos);
		
		String QueryInsertProyectos = "INSERT INTO proyectos VALUES"
				+ "('1', 'Proyecto1', 12),\r\n"
				+ "('11', 'Proyecto2', 0),\r\n"
				+ "('111', 'Proyecto3', 234),\r\n"
				+ "('2', 'Proyecto4', 0),\r\n"
				+ "('22', 'Proyecto5', 36),\r\n"
				+ "('222', 'Proyecto6', 89),\r\n"
				+ "('3', 'Proyecto7', 93),\r\n"
				+ "('333', 'Proyecto8', 54),\r\n"
				+ "('3333', 'Proyecto9', 48),\r\n"
				+ "('5678', 'Proyecto11', 81);";
	
		BDMySQL.insertData(db, "proyectos", QueryInsertProyectos);
		
		//tabla cientificos_asignado_a_proyectos
		String QueryCientificos_asignado_a_proyectos = 
				"CREATE TABLE cientificos_asignado_a_proyectos(\r\n"
				+ "DNICientifico varchar(8),\r\n"
				+ "idProyecto char(4),\r\n"
				+ "KEY (DNICientifico, idProyecto),\r\n"
				+ "FOREIGN KEY (DNICientifico) \r\n"
				+ "references cientificos(DNI)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade,\r\n"
				+ "FOREIGN KEY (idProyecto) \r\n"
				+ "references proyectos(id)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade,\r\n"
				+ "PRIMARY KEY (DNICientifico, idProyecto)"
				+ ");";
		
		BDMySQL.crearTabla(db, "cientificos_asignado_a_proyectos", QueryCientificos_asignado_a_proyectos);
		
		String QueryInsertCientificos_asignado_a_proyectos = "INSERT INTO cientificos_asignado_a_proyectos VALUES"
				+ "('34rt5', '1'),\r\n"
				+ "('5thygd', '11'),\r\n"
				+ "('5thygd', '22'),\r\n"
				+ "('kjhg6ygh', '3'),\r\n"
				+ "('999iikj', '111'),\r\n"
				+ "('123hyubv', '333'),\r\n"
				+ "('10eudj57', '5678'),\r\n"
				+ "('9i5tbhmj', '222'),\r\n"
				+ "('4rgx9f', '3333'),\r\n"
				+ "('000ooii', '5678');";
		
		BDMySQL.insertData(db, "cientificos_asignado_a_proyectos", QueryInsertCientificos_asignado_a_proyectos);
		
		BDMySQL.cerrarConexion();

	}

}
