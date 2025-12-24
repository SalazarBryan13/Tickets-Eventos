package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.entities.Cliente;
import modelo.JPA.impl.JPAClienteDAO;
import modelo.dao.ClienteDAO;

//3. Agregamos la anotación de web servlet para que el servidor Tomcat
//reconozca la clase como un servle
@WebServlet("/CrearCuentaController")
public class CrearCuentaController extends HttpServlet {
	// 1. Insertamos el SerialID
	private static final long serialVersionUID = 1L;

	// 2. Sobreescribimos los metodos doGet y doPost de la clase madre
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ruta = (req.getParameter("ruta")==null)?"forms":req.getParameter("ruta");
		switch(ruta) {
		case "forms":
			this.forms(req, resp);
			break;
		case "crearCliente":
			this.crearCliente(req, resp);
			break;
			

		}
	}

	private void forms(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("vistas/register.jsp").forward(request, response);

	}
	
	private void crearCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.- Obtener los parámetros
		String correo = request.getParameter("correo");
	    String clave = request.getParameter("txtClave");
	    String celular = request.getParameter("txtCelular");
	    String direccion = request.getParameter("txtDireccion");
	    String cedula = request.getParameter("txtCedula");
	    String nombre = request.getParameter("txtNombre");
	    ClienteDAO modeloDAO = new JPAClienteDAO();
	    

	    Cliente cliente = new Cliente(correo, clave, celular, direccion, cedula, nombre);
	    
	    boolean resultado = modeloDAO.guardarCliente(cliente);
		//3.- Llamar a la vista
	    
	    if(resultado) {
	        response.sendRedirect("vistas/login.jsp");
	    }else {
	    	response.sendRedirect(".vistas/error.jsp");
	    }
	}

}