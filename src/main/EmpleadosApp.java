package main;

import clases.BDMySQL;

public class EmpleadosApp {

	public static void main(String[] args) {
		String db = "UD18";		
		
		BDMySQL.crearConexion("192.168.1.139:3306", "remote", "katia");
		
		BDMySQL mysql = new BDMySQL();
		
		mysql.crearBD(db);
		
		//tabla departamentos,
		String QueryDepartamentos = "CREATE TABLE departamentos(\r\n"
				+ "codigo int not null,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "presupuesto int default 1000,\r\n"
				+ "primary key (codigo));";
		
		BDMySQL.crearTabla(db, "departamentos", QueryDepartamentos);
		
		String QueryInsertDepartamentos = "INSERT INTO departamentos VALUES"
				+ " (14,'IT',65000), (37,'Accounting',15000), (59,'Human Resources',240000), (77,'Research',55000);";
	
		BDMySQL.insertData(db, "departamentos", QueryInsertDepartamentos);
		
		//tabla empleados,
		String QueryEmpleados = "CREATE TABLE empleados(\r\n"
				+ "DNI int not null,\r\n"
				+ "nombre varchar(100) not null,\r\n"
				+ "apellidos varchar(255) not null,\r\n"
				+ "codigoDepartamento int,\r\n"
				+ "primary key (DNI)\r\n,"
				+ "KEY (codigoDepartamento)\r\n,"
				+ "FOREIGN KEY (codigoDepartamento)\r\n"
				+ "references departamentos(codigo)\r\n"
				+ "on delete set null\r\n"
				+ "on update cascade);";
		
		BDMySQL.crearTabla(db, "empleados", QueryEmpleados);
		
		String QueryInsertEmpleados = "INSERT INTO empleados VALUES "
				+ "(123234877,'Michael','Rogers',14), (152934485,'Anand','Manikutty',14), (222364883,'Carol','Smith',37), "
				+ "(326587417,'Joe','Stevens',37), (332154719,'Mary-Anne','Foster',14), (332569843,'George','O\\'Donnell',77),"
				+ "(546523478,'John','Doe',59), (631231482,'David','Smith',77), (654873219,'Zacary','Efron',59), "
				+ "(745685214,'Eric','Goldsmith',59);";
	
		BDMySQL.insertData(db, "empleados", QueryInsertEmpleados);
		
		
		BDMySQL.cerrarConexion();

	}

}
