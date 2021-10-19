package Laboral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseManager {

	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	private static BasicDataSource dataSource = null;

	public DatabaseManager() throws SQLException {
		con = getDataSource().getConnection();
		st = con.createStatement();
	}

	private static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mysql://localhost:3306/Nominas?useTimezone=true&serverTimezone=UTC");
			dataSource.setInitialSize(20);
			dataSource.setMaxIdle(15);
			dataSource.setMaxTotal(20);
			dataSource.setMaxWaitMillis(5000);
		}
		return dataSource;
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public void altaEmpleado(Empleado emp, Nomina nom) throws SQLException {
		st.executeUpdate("INSERT INTO Nominas.empleados VALUES ('" + emp.nombre + "','" + emp.dni + "','" + emp.sexo
				+ "'," + emp.getCategoria() + "," + emp.anyos + ");");
		st.execute("INSERT INTO Nominas.nominas (DNI,Sueldo) VALUES('" + emp.dni + "'," + nom.sueldo(emp) + ");");
	}

	public void altaEmpleado(List<Empleado> emps, Nomina nom) throws SQLException {
		for (Empleado empleado : emps) {
			altaEmpleado(empleado, nom);
		}
	}

	public void actualizarEmpleado(String dni, String valor, int aux) throws SQLException {
		switch (aux) {
		case 1:
			ps = con.prepareStatement(
					"UPDATE Nominas.empleados SET Nombre_Completo = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 2:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET DNI = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 3:
			ps = con.prepareStatement("UPDATE Nominas.empleados SET Sexo = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 4:
			ps = con.prepareStatement(
					"UPDATE Nominas.empleados SET Categoria = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		case 5:
			ps = con.prepareStatement(
					"UPDATE Nominas.empleados SET Anyos = '" + valor + "' WHERE DNI = '" + dni + "';");
			break;
		}
		ps.executeUpdate();
	}

	public void actualizarEmpleado(Empleado emp) throws SQLException {
		ps = con.prepareStatement("UPDATE Nominas.empleados SET Nombre_Completo = '" + emp.nombre + "', Sexo = '"
				+ emp.sexo + "', Categoria = " + emp.getCategoria() + ", Anyos = " + emp.anyos + " "
				+ " WHERE DNI = '" + emp.dni + "';");
		ps.executeUpdate();
	}

	public void actualizarSalario(String dni, String valor) throws SQLException {
		st.executeUpdate("UPDATE Nominas.nominas SET Sueldo = '" + valor + "' WHERE DNI = '" + dni + "';");
	}

	public List<Empleado> selectEmpleados() throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("select * from Nominas.empleados;");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Empleado> empleados = new ArrayList();
		while (rs.next()) {
			Empleado emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			empleados.add(emp);
		}
		return empleados;
	}
	

	public Empleado selectEmpleado(String dni) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("SELECT * FROM Nominas.empleados WHERE DNI = '" + dni + "';");
		rs = ps.executeQuery();
		rs.next();
		return new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
	}
	
	public List<Empleado> selectEmpleadosNombre(String nombre) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("select * from Nominas.empleados WHERE Nombre_Completo = '" +nombre +"';");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Empleado> empleados = new ArrayList();
		while (rs.next()) {
			Empleado emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			empleados.add(emp);
		}
		return empleados;
	}
	public List<Empleado> selectEmpleadosSexo(String sexo) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("select * from Nominas.empleados WHERE Sexo = '" + sexo +"';");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Empleado> empleados = new ArrayList();
		while (rs.next()) {
			Empleado emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			empleados.add(emp);
		}
		return empleados;
	}
	public List<Empleado> selectEmpleadosCategoria(int categoria) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("select * from Nominas.empleados WHERE Categoria = " + categoria +";");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Empleado> empleados = new ArrayList();
		while (rs.next()) {
			Empleado emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			empleados.add(emp);
		}
		return empleados;
	}

	public List<Empleado> selectEmpleadosAnyos(int anyos) throws SQLException, DatosNoCorrectosException {
		ps = con.prepareStatement("select * from Nominas.empleados WHERE Anyos = " + anyos +";");
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Empleado> empleados = new ArrayList();
		while (rs.next()) {
			Empleado emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			empleados.add(emp);
		}
		return empleados;
	}
	public List<String> selectDNIs() throws SQLException {
		List<String> dnis = new ArrayList();
		ps = con.prepareStatement("SELECT DNI FROM Nominas.empleados");
		rs = ps.executeQuery();
		while (rs.next()) {
			dnis.add(rs.getString("DNI"));
		}
		return dnis;
	}

	public int selectSalario(String dni) throws SQLException {
		ps = con.prepareStatement("select sueldo from Nominas.nominas WHERE DNI = '" + dni + "';");
		rs = ps.executeQuery();
		int salario = 0;
		while (rs.next()) {
			salario = rs.getInt(1);
		}
		return salario;
	}
}
