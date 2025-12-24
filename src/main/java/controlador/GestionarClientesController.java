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
@WebServlet("/GestionarClientesController")
public class GestionarClientesController extends HttpServlet {
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
		String ruta = (req.getParameter("ruta")==null)?"listarClientes":req.getParameter("ruta");
		switch(ruta) {
		case "listarClientes":
			this.listarClientes(req, resp);
			break;
		case "crearCliente":
			this.crearCliente(req, resp);
			break;
		case "modificarCliente":
			this.modificarCliente(req, resp);
			break;
		case "eliminarCliente":
			this.eliminarCliente(req, resp);
			break;
		case "guardarNuevo":
			this.guardarNuevo(req, resp);
			break;
		case "guardarExistente":
			this.guardarExistente(req, resp);
			break;
		}
	}

	private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2.- Hablar con el Modelo
		
		ClienteDAO modeloDAO = new JPAClienteDAO();

		List<Cliente> clientes = modeloDAO.extraerClientes();
		//3.- Llamar a la vista
		request.setAttribute("clientes",clientes);
		request.getRequestDispatcher("vistas/listarClientes.jsp").forward(request, response);
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
	    
	    System.out.println("DATOS RECIBIDOS:");
	    System.out.println("Correo: " + correo);
	    System.out.println("Clave: " + clave);
	    System.out.println("Celular: " + celular);
	    System.out.println("Direccion: " + direccion);
	    System.out.println("Cedula: " + cedula);
	    System.out.println("Nombre: " + nombre);

	    Cliente cliente = new Cliente(correo, clave, celular, direccion, cedula, nombre);
	    
	    boolean resultado = modeloDAO.guardarCliente(cliente);
		//3.- Llamar a la vista
	    if(resultado) {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }else {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }


	}

	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		String cedula = request.getParameter("cedula");
		//2.- Hablar con el Modelo
		ClienteDAO modeloDAO = new JPAClienteDAO();
		Cliente cliente = modeloDAO.obtenerInformacionCliente(cedula);;
		//3.- Llamar a la vista
		request.setAttribute("cliente", cliente);
		request.getRequestDispatcher("vistas/modificarCliente.jsp").forward(request, response);
	}

	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cedula = request.getParameter("cedula");
			ClienteDAO modeloDAO = new JPAClienteDAO();
			boolean eliminado = modeloDAO.eliminarCliente(cedula);

			if (eliminado) {
				response.sendRedirect("GestionarClientesController?ruta=listarClientes");
			} else {
				// Podrías mostrar una página de error si falla
				response.sendRedirect("GestionarClientesController?ruta=listarClientes");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("GestionarClientesController?ruta=listarClientes");
		}
	}

	private void guardarNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.- Obtener los parámetros
		//2.- Hablar con el Modelo
		//3.- Llamar a la vista
		response.sendRedirect("vista/RegistrarCliente.jsp");

	}

	private void guardarExistente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		int usuarioId = Integer.parseInt(request.getParameter("UsuarioId"));
		String correo = request.getParameter("correo");
	    String clave = request.getParameter("txtClave");
	    String celular = request.getParameter("txtCelular");
	    String direccion = request.getParameter("txtDireccion");
	    String cedula = request.getParameter("txtCedula");
	    String nombre = request.getParameter("txtNombre");
	    ClienteDAO modeloDAO = new JPAClienteDAO();

	    Cliente cliente = new Cliente(correo, clave, celular, direccion, cedula, nombre);
	    cliente.setUsuarioId(usuarioId);
	    
	    System.out.println("DATOS RECIBIDOS:");
	    System.out.println("id: " + usuarioId);
	    System.out.println("Correo: " + correo);
	    System.out.println("Clave: " + clave);
	    System.out.println("Celular: " + celular);
	    System.out.println("Direccion: " + direccion);
	    System.out.println("Cedula: " + cedula);
	    System.out.println("Nombre: " + nombre);
	    
		//2.- Hablar con el Modelo
	    boolean respuesta = modeloDAO.actualizarCliente(cliente);
		//3.- Llamar a la vista
	    if(respuesta) {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }else {
	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");
	    }
	}

}