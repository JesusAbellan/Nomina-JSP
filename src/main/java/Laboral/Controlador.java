package Laboral;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Controlador de la informaci�n de los empleados y sus sueldos.", urlPatterns = { "/empresa" })
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("mostrarEmpleados")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados = dm.selectEmpleados();
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}

		else if (opcion.equals("mostrarSalario")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarSalario.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (opcion.equals("salarioEspecifico")) {
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				String dni = request.getParameter("dni");
				Empleado emp = dm.selectEmpleado(dni);
				int salario = dm.selectSalario(dni);

				request.setAttribute("empleado", emp);
				request.setAttribute("salario", salario);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/salarioEspecifico.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		} else if (opcion.equals("meditar")) {
			String dni = request.getParameter("dni");
			DatabaseManager dm;
			Empleado emp;
			try {
				dm = new DatabaseManager();
				emp = dm.selectEmpleado(dni);
				request.setAttribute("empleado", emp);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
		else if(opcion.equals("buscarEmpleados")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscarEmpleados.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(opcion.equals("buscarNombre")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados = dm.selectEmpleadosNombre(request.getParameter("nombre"));
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
		else if(opcion.equals("buscarDni")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados.add(dm.selectEmpleado(request.getParameter("dni")));
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
		else if(opcion.equals("buscarSexo")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados = dm.selectEmpleadosSexo(request.getParameter("sexo"));
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
		else if(opcion.equals("buscarCategoria")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados = dm.selectEmpleadosCategoria(Integer.parseInt((String)request.getParameter("categoria")));
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
		else if(opcion.equals("buscarAnyos")) {
			List<Empleado> empleados = new ArrayList<>();
			DatabaseManager dm;
			try {
				dm = new DatabaseManager();
				empleados = dm.selectEmpleadosAnyos(Integer.parseInt((String)request.getParameter("anyos")));
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostrarEmpleados.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String opcion = request.getParameter("opcion");
		
		if(opcion.equals("editar")) {
			DatabaseManager dm;
			Nomina nom = new Nomina();
			//String nombre, String dni, String sexo, int categoria, int anyos
			String nombre = (String) request.getParameter("nombre");
			String dni = (String) request.getParameter("dni");
			String sexo = (String) request.getParameter("sexo");
			String categoriaStr =  (String)request.getParameter("categoria");
			int categoria = Integer.parseInt(categoriaStr);
			String anyosStr = (String)request.getParameter("anyos");
			int anyos =  Integer.parseInt(anyosStr);
			Empleado emp;
			try {
				dm = new DatabaseManager();
				emp = new Empleado(nombre,dni,sexo,categoria,anyos);
				dm.actualizarEmpleado(emp);
				dm.actualizarSalario(dni, String.valueOf(nom.sueldo(dm.selectEmpleado(dni))));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			}catch(SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
	}
}
