package modelo.JPA.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.dao.TicketDAO;
import modelo.entities.Ticket;

import java.util.Collections;
import java.util.List;

public class JPATicketDAO implements TicketDAO {

	private EntityManager em;

    public JPATicketDAO() {
        em = Persistence.createEntityManagerFactory("evento").createEntityManager();
    }
    @Override
    public boolean guardarTicket(Ticket ticket) {
        EntityManager em = Persistence.createEntityManagerFactory("evento").createEntityManager(); // Crear un nuevo EntityManager aquí
        try {
            // Comenzar la transacción
            em.getTransaction().begin();

            // Persistir el ticket
            em.persist(ticket);

            // Confirmar la transacción
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            // Si ocurre un error, realizar rollback
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar el EntityManager después de la operación
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    @Override
    public List<Ticket> extraerTickets() {
        try {
            return em.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Ticket obtenerTicketPorId(int ticketId) {
        try {
            return em.find(Ticket.class, ticketId);  // Buscar ticket por ID
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Ticket> obtenerTicketsPorUsuario(int idUsuario) {
        try {
            // Consulta JPQL actualizada para usar la relación directa con Cliente
            String jpql = "SELECT t FROM Ticket t WHERE t.usuario.usuarioId = :idUsuario";
            return em.createQuery(jpql, Ticket.class)
                     .setParameter("idUsuario", idUsuario)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Mejor práctica: devolver lista vacía en lugar de null
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Cierra solo si el EntityManager no es gestionado por un contenedor
            }
        }
    }
}
