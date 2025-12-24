package controlador;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ClienteDAO;
import modelo.dao.ComprobanteDAO;
import modelo.dao.EventoDAO;
import modelo.dao.TicketDAO;
import modelo.JPA.impl.JPAClienteDAO;
import modelo.JPA.impl.JPAComprobanteDePagoDAO;
import modelo.JPA.impl.JPAEventoDAO;
import modelo.JPA.impl.JPATicketDAO;
import modelo.entities.Cliente;
import modelo.entities.ComprobanteDePago;
import modelo.entities.Evento;
import modelo.entities.Ticket;

@WebServlet("/VisualizarDashboardPrincipalController")
public class VisualizarDashboardPrincipalController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.ruteador(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ruta = (request.getParameter("ruta") == null) ? "obtenerEventosDisponibles" : request.getParameter("ruta");

        switch (ruta) {
            case "obtenerEventosDisponibles":
                this.obtenerEventosDisponibles(request, response);
                break;
            case "mostrarDetalleEvento":
                this.mostrarDetalleEvento(request, response);
                break;
            case "iniciarCompraEntradas":
                this.iniciarCompraEntradas(request, response);
                break;
            case "cerrarSesion":
                this.cerrarSesion(request, response);
                break;
            case "verEntradas":
                this.verEntradas(request, response);  // Nuevo caso para ver las entradas
                break;
        }
    }
    
    private void obtenerEventosDisponibles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Hablar con el Modelo
        EventoDAO modeloDAO = new JPAEventoDAO();
        List<Evento> eventos = modeloDAO.obtenerEventos(); 

        // 2.- Llamar a la vista
        request.setAttribute("eventos", eventos);
        request.getRequestDispatcher("vistas/dashboardPrincipal.jsp").forward(request, response);
    }
    
    private void mostrarDetalleEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Obtener el parámetro del ID del evento
        Integer id = Integer.parseInt(request.getParameter("id"));

        // 2.- Hablar con el Modelo
        EventoDAO modeloDAO = new JPAEventoDAO();
        Evento evento = modeloDAO.obtenerEvento(id);

        // 3.- Llamar a la vista
        request.setAttribute("evento", evento);
        request.getRequestDispatcher("vistas/detalleEvento.jsp").forward(request, response);
    }
    
    private void iniciarCompraEntradas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Obtener el parámetro del ID del evento
        Integer idEvento = Integer.parseInt(request.getParameter("idEvento"));

        // 2.- Hablar con el Modelo
        EventoDAO modeloDAO = new JPAEventoDAO();
        Evento evento = modeloDAO.obtenerEvento(idEvento);

        // 3.- Llamar a la vista
        request.setAttribute("evento", evento);
        request.getRequestDispatcher("vistas/comprarEntrada.jsp").forward(request, response);
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Invalidar la sesión del usuario
        request.getSession().invalidate();

        // 2.- Redirigir al usuario al login
        response.sendRedirect("LoginController");  // Cambia la ruta al controlador de login según tu configuración
    }
    private void verEntradas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.- Obtener el usuario de la sesión
        Cliente cliente = (Cliente) request.getSession().getAttribute("usuarioLogueado");
        if (cliente == null) {
            response.sendRedirect("login.jsp"); // Redirigir a la página de login si no hay sesión
            return;
        }
        
        String usuario = cliente.getCorreo();  // Suponiendo que "usuario" es el nombre o correo

        // 2.- Hablar con el modelo para obtener el ID del usuario con el correo
        ClienteDAO clienteDAO = new JPAClienteDAO();
        int idUsuario = clienteDAO.obtenerIdPorCorreo(usuario);

        // 3.- Obtener los tickets asociados a los comprobantes del usuario
        TicketDAO ticketDAO = new JPATicketDAO();  // Nueva implementación para trabajar con tickets
        List<Ticket> tickets = ticketDAO.obtenerTicketsPorUsuario(idUsuario);  // Método para obtener los tickets

        // 4.- Pasar los tickets a la vista
        request.setAttribute("tickets", tickets);  // Cambiar 'comprobantes' por 'tickets'
        request.getRequestDispatcher("vistas/verEntradas.jsp").forward(request, response);
    }
}

