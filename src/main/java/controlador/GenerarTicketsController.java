package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.JPA.impl.JPAComprobanteDePagoDAO;
import modelo.JPA.impl.JPATicketDAO;
import modelo.dao.ComprobanteDAO;
import modelo.dao.TicketDAO;
import modelo.entities.ComprobanteDePago;
import modelo.entities.Ticket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@WebServlet("/GenerarTicketsController")
public class GenerarTicketsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.generarTickets(request, response);
        
    }

    private void generarTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el comprobanteId desde el request
        int comprobanteId = Integer.parseInt(request.getParameter("comprobanteId"));

        // Lógica para generar los tickets
        ComprobanteDAO comprobanteDAO = new JPAComprobanteDePagoDAO();
        ComprobanteDePago comprobante = comprobanteDAO.obtenerComprobantePorId(comprobanteId);

        if (comprobante != null) {
            // Crear los tickets y guardarlos en la base de datos
            TicketDAO ticketDAO = new JPATicketDAO();
            List<Ticket> tickets = generarTickets(comprobante);

            // Guardar los tickets en la base de datos
            for (Ticket ticket : tickets) {
                ticketDAO.guardarTicket(ticket);
            }

            // Redirigir a la lista de comprobantes después de generar los tickets
            response.sendRedirect("GestionarComprobantesController?ruta=listarComprobantes");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Comprobante no encontrado.");
        }
    }

    private List<Ticket> generarTickets(ComprobanteDePago comprobante) throws IOException {
        if (comprobante == null || comprobante.getUsuario() == null) {
            throw new IllegalArgumentException("El comprobante o su usuario asociado no pueden ser nulos");
        }

        List<Ticket> tickets = new ArrayList<>();
        int numeroEntradas = comprobante.getNumeroEntradas();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 200;
        int height = 200;

        for (int i = 0; i < numeroEntradas; i++) {
            Ticket ticket = new Ticket();
            ticket.setComprobante(comprobante);
            String codigoUnico = "TICKET-" + UUID.randomUUID().toString();
            ticket.setCodigoTicket(codigoUnico);
            ticket.setUsuario(comprobante.getUsuario());

            // Generar imagen QR en memoria y asignarla al ticket
            try {
                BitMatrix bitMatrix = qrCodeWriter.encode(codigoUnico, BarcodeFormat.QR_CODE, width, height);
                BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(qrImage, "png", baos);
                byte[] qrBytes = baos.toByteArray();
                ticket.setQrImage(qrBytes); // Asigna la imagen QR generada al ticket
            } catch (WriterException e) {
                e.printStackTrace();
                // Puedes manejar el error según tu lógica (por ejemplo, generar sin QR, lanzar excepción, etc.)
            }

            tickets.add(ticket);
        }
        return tickets;
    }
}