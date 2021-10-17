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

@WebServlet(description = "Controlador de la información de los empleados y sus sueldos.", urlPatterns = { "/nominas" })
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		else if(opcion.equals("mostarSalario")){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/mostarSalario.jsp");
			requestDispatcher.forward(request, response);
		}
		else if(opcion.equals("salarioEspecifico")) {
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
			}catch(SQLException | DatosNoCorrectosException e) {
				e.printStackTrace();
			}
		}
	}
}
