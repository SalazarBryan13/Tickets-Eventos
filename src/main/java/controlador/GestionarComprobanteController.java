package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.entities.ComprobanteDePago;
import modelo.JPA.impl.JPAComprobanteDePagoDAO;
import modelo.dao.ComprobanteDAO;

@WebServlet("/GestionarComprobantesController")
public class GestionarComprobanteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ruta = (req.getParameter("ruta") == null) ? "listarComprobantes" : req.getParameter("ruta");
        switch (ruta) {
            case "listarComprobantes":
                this.listarComprobantes(req, resp);
                break;
            case "actualizarEstado":
                this.actualizarEstado(req, resp);
                break;
        }
    }

    private void listarComprobantes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los comprobantes
        ComprobanteDAO modeloDAO = new JPAComprobanteDePagoDAO();
        List<ComprobanteDePago> comprobantes = modeloDAO.extraerComprobantes();

        // Pasar a la vista
        request.setAttribute("comprobantes", comprobantes);
        request.getRequestDispatcher("vistas/listarComprobantes.jsp").forward(request, response);
    }

    private void actualizarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int comprobanteId = Integer.parseInt(request.getParameter("comprobanteId"));
        String estado = request.getParameter("estado");

        // Actualizar estado
        ComprobanteDAO modeloDAO = new JPAComprobanteDePagoDAO();
        boolean resultado = modeloDAO.actualizarEstado(comprobanteId, estado);

        if (resultado) {
            // Si el estado es aprobado, redirigir a generar los tickets
            if ("aprobado".equals(estado)) {
                response.sendRedirect("GenerarTicketsController?comprobanteId=" + comprobanteId);
                
            } else {
                response.sendRedirect("GestionarComprobantesController?ruta=listarComprobantes");
            }
        } else {
            response.sendRedirect("GestionarComprobantesController?ruta=listarComprobantes");
        }
        System.out.println("Comprobante ID: " + comprobanteId + ", Nuevo estado: " + estado);
    }
}
