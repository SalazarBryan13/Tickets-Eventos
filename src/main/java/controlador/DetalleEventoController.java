package controlador;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import modelo.entities.Evento;
import modelo.JPA.impl.JPAEventoDAO;

@WebServlet("/DetalleEventoController")
public class DetalleEventoController extends HttpServlet {

    private JPAEventoDAO eventoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        eventoDAO = new JPAEventoDAO();  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Evento evento = eventoDAO.obtenerEvento(id); 
            if (evento == null) {
                response.sendRedirect("dashboardPrincipal.jsp");
                return;
            }
            request.setAttribute("evento", evento);
            request.getRequestDispatcher("detalleEvento.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("dashboardPrincipal.jsp");
        }
    }
}

