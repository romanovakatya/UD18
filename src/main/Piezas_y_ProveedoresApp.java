package main;

import clases.BDMySQL;

public class Piezas_y_ProveedoresApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//Tabla piezas,
		String QueryPiezas = "CREATE TABLE piezas(\r\n"
				+ "codigo int auto_increment,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "primary key (codigo));";
		
		BDMySQL.crearTabla(db, "piezas", QueryPiezas);
		
		String QueryInsertPiezas = "INSERT INTO piezas (nombre)"
				+ "VALUES ('p1'),('p2'),('p3'),('p4'),('p5'),"
				+ "('p6'),('p7'),('p8'),('p9'),('p10');";
	
		BDMySQL.insertData(db, "piezas", QueryInsertPiezas);
		
		
		//tabla proveedores,
		String QueryProveedores = "CREATE TABLE proveedores(\r\n"
				+ "id char(4) not null,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "PRIMARY KEY (id));\r\n";
		
		BDMySQL.crearTabla(db, "proveedores", QueryProveedores);
		
		String QueryInsertProveedores = "INSERT INTO proveedores VALUES"
				+ " ('1', 'Proveedor1'), ('2', 'Proveedor2'), ('3', 'Proveedor3'),\r\n"
				+ "('4', 'Proveedor4'), ('5', 'Proveedor5'), ('6', 'Proveedor6'),\r\n"
				+ "('7', 'Proveedor7'), ('8', 'Proveedor8'), \r\n"
				+ "('9', 'Proveedor9'), ('10', 'Proveedor10');";
	
		BDMySQL.insertData(db, "proveedores", QueryInsertProveedores);
		
		//tabla proveedores_suministra_piezas
		String QueryProveedores_suministra_Piezas = "CREATE TABLE proveedores_suministra_piezas(\r\n"
				+ "codigoPieza int,\r\n"
				+ "idProveedor char(4),\r\n"
				+ "precio int not null,\r\n"
				+ "PRIMARY KEY (codigoPieza, idProveedor),\r\n"
				+ "FOREIGN KEY (codigoPieza)\r\n"
				+ "references piezas(codigo)\r\n"
				+ "on delete cascade\r\n"
				+ "on update cascade,\r\n"
				+ "FOREIGN KEY (idProveedor)\r\n"
				+ "references proveedores(id)\r\n"
				+ "on delete cascade\r\n"
				+ "on update cascade\r\n"
				+ ");";
		
		BDMySQL.crearTabla(db, "proveedores_suministra_piezas", QueryProveedores_suministra_Piezas);
		
		String QueryInsertProveedores_suministra_Piezas = "INSERT INTO proveedores_suministra_piezas VALUES"
				+ "(1, '1', 11), (2, '1', 19), (1, '10', 200), (3, '7', 9),\r\n"
				+ "(3, '4', 15), (9, '9', 600),(6, '6', 666), \r\n"
				+ "(6, '5', 855), (4, '3', 1290), (7, '2', 99);";
		
		BDMySQL.insertData(db, "proveedores_suministra_piezas", QueryInsertProveedores_suministra_Piezas);
		
		BDMySQL.cerrarConexion();

	}

}
