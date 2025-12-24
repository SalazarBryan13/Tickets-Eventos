package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import modelo.dao.ClienteDAO;
import modelo.JPA.impl.JPAClienteDAO;
import modelo.entities.Cliente;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("vistas/login.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 1. Obtener parámetros
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // 2. Verificar usuario en la base de datos
        ClienteDAO clienteDAO = new JPAClienteDAO();
        Cliente cliente = clienteDAO.obtenerClientePorCredenciales(usuario, contrasena);

        if (cliente != null) {
            // 3. Usuario válido -> crear sesión
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioLogueado", cliente);

            
            if (cliente.getUsuarioId() == 1) {            	
    	    	response.sendRedirect("GestionarClientesController?ruta=listarClientes");           	
            }else {
            	// 4. Redirigir al dashboard
            	response.sendRedirect("VisualizarDashboardPrincipalController");           	
            }

        } else {
            // 5. Usuario no válido -> redirigir al login
            response.sendRedirect("vistas/login.jsp?error=1");
        }
    }
}
