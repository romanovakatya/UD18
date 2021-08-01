package main;

import clases.BDMySQL;

public class LosInvestigadoresApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla facultades,
		String QueryFacultades = "CREATE TABLE facultades(\r\n"
				+ "codigo int,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "PRIMARY KEY (codigo));";
		
		BDMySQL.crearTabla(db, "facultades", QueryFacultades);
		
		String QueryInsertFacultades = "INSERT INTO facultades VALUES "
				+ "(1, 'Agronomía'), (2, 'Filosofía'), (3, 'Arquitectura'), (4, 'Química'), \r\n"
				+ "(5, 'Derecho'), (6, 'Matemática '), (7, 'Psicología'), (8, 'Diseño'), \r\n"
				+ "(9, 'Medicina'), (10, 'Información');";
	
		BDMySQL.insertData(db, "facultades", QueryInsertFacultades);
		
		
		//tabla investigadores,
		String QueryInvestigadores = "CREATE TABLE investigadores(\r\n"
				+ "DNI varchar(8) not null,\r\n"
				+ "nomApels varchar(255) not null,\r\n"
				+ "codigoFacultad int,\r\n"
				+ "PRIMARY KEY (DNI),\r\n"
				+ "KEY (codigoFacultad),\r\n"
				+ "FOREIGN KEY (codigoFacultad)\r\n"
				+ "references facultades(codigo)\r\n"
				+ "on delete set null\r\n"
				+ "on update cascade);\r\n";
		
		BDMySQL.crearTabla(db, "investigadores", QueryInvestigadores);
		
		String QueryInsertInvestigadores = "INSERT INTO investigadores VALUES"
				+ "('12etgd', 'Inv1', 1), ('oyity8y', 'Inv2', 10), ('bfg3', 'Inv3', 3), \r\n"
				+ "('mhgy7', 'Inv4', 7), ('svg23g', 'Inv5', 5),('nmjlk86', 'Inv6', 8), \r\n"
				+ "('m9hkjj', 'Inv7',  4), ('nhhg5t', 'Inv8', 2), ('sdfdgi', 'Inv9', 9), \r\n"
				+ "('8u6hff', 'Inv10', 6);";
	
		BDMySQL.insertData(db, "investigadores", QueryInsertInvestigadores);
		
		
		//tabla equipos,
		String QueryEquipos = "CREATE TABLE equipos(\r\n"
				+ "numSerie char(4) not null,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "codigoFacultad int,\r\n"
				+ "PRIMARY KEY (numSerie),\r\n"
				+ "FOREIGN KEY (codigoFacultad)\r\n"
				+ "references facultades(codigo)\r\n"
				+ "on delete set null\r\n"
				+ "on update cascade);\r\n";
		
		BDMySQL.crearTabla(db, "equipos", QueryEquipos);
		
		String QueryInsertEquipos = "INSERT INTO equipos VALUES"
				+ "('a', 'Equipo1', 2), ('b', 'Equipo2', 1), ('c', 'Equipo3', 5), \r\n"
				+ "('d', 'Equipo4', 3), ('ed', 'Equipo5', 9), ('bb', 'Equipo6', 8), \r\n"
				+ "('12ef', 'Equipo7', 10), ('ng65', 'Equipo8', 4),\r\n"
				+ " ('ui77', 'Equipo9', 6), ('mjsd', 'Equipo10', 7);";
	
		BDMySQL.insertData(db, "equipos", QueryInsertEquipos);
		
		
		//tabla investigadores_reserva_equipos,
		String QueryInvestigadores_reserva_equipos = 
				"CREATE TABLE investigadores_reserva_equipos(\r\n"
				+ "DNIinvestigador varchar(8),\r\n"
				+ "numSerieEquipo char(4),\r\n"
				+ "comienzo datetime,\r\n"
				+ "fin datetime,\r\n"
				+ "PRIMARY KEY (DNIinvestigador, numSerieEquipo),\r\n"
				+ "FOREIGN KEY (DNIinvestigador)\r\n"
				+ "references investigadores(DNI)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade,\r\n"
				+ "FOREIGN KEY (numSerieEquipo)\r\n"
				+ "references equipos(numSerie)\r\n"
				+ "on delete restrict\r\n"
				+ "on update cascade);";
		
		BDMySQL.crearTabla(db, "investigadores_reserva_equipos", QueryInvestigadores_reserva_equipos);
		
		String QueryInsertInvestigadores_reserva_equipos = "INSERT INTO investigadores_reserva_equipos VALUES"
				+ "('12etgd', 'b', '1987-12-03', '1988-01-31'), \r\n"
				+ "('sdfdgi', 'a', '2001-06-05', '2002-01-01'), \r\n"
				+ "('bfg3', '12ef', '1966-09-28', '1966-10-02'), \r\n"
				+ "('oyity8y', 'c', '2021-07-01', '2021-07-31'), \r\n"
				+ "('mhgy7', 'ui77', '2020-03-01', '2020-03-28'), \r\n"
				+ "('bfg3', 'a', '2021-09-01', '2021-09-22'), \r\n"
				+ "('nmjlk86', 'ng65', '2019-02-10', '2019-03-01'), \r\n"
				+ "('m9hkjj', 'ui77', '2020-03-01', '2021-09-22'), \r\n"
				+ "('8u6hff', 'd', '2018-04-20', '2019-04-20'), \r\n"
				+ "('svg23g', 'ng65', '2000-05-05', '2021-05-05');";
		
		BDMySQL.insertData(db, "investigadores_reserva_equipos", QueryInsertInvestigadores_reserva_equipos);
		
		BDMySQL.cerrarConexion();

	}

}
