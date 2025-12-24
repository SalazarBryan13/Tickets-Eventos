package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.TicketDAO;
import modelo.entities.Ticket;
import modelo.JPA.impl.JPATicketDAO;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/QrImageServlet")
public class QrImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ticketId = request.getParameter("ticketId");
        if (ticketId == null || ticketId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro ticketId");
            return;
        }

        try {
            // Obtener el ticket desde la base de datos
            TicketDAO ticketDAO = new JPATicketDAO();
            Ticket ticket = ticketDAO.obtenerTicketPorId(Integer.parseInt(ticketId));

            if (ticket != null && ticket.getQrImage() != null) {
                // Configurar la respuesta
                response.setContentType("image/png");
                OutputStream out = response.getOutputStream();
                out.write(ticket.getQrImage());
                out.flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen QR no encontrada");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener la imagen QR");
            e.printStackTrace();
        }
    }
}
