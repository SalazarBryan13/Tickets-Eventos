package modelo.dao;

import modelo.entities.Ticket;

import java.util.List;

public interface TicketDAO {

    // Guardar un ticket
    boolean guardarTicket(Ticket ticket);

    // Extraer todos los tickets
    List<Ticket> extraerTickets();

    // Obtener un ticket por ID
    Ticket obtenerTicketPorId(int ticketId);
    List<Ticket> obtenerTicketsPorUsuario(int idUsuario);  // Nuevo método
}
