package modelo.JPA.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import modelo.dao.EventoDAO;
import modelo.entities.Evento;

public class JPAEventoDAO implements EventoDAO {

    private EntityManager em;

    public JPAEventoDAO() {
        em = Persistence.createEntityManagerFactory("evento").createEntityManager();
    }

    @Override
    public Evento obtenerEvento(int id) {
        return em.find(Evento.class, id);
    }

    @Override
    public List<Evento> obtenerEventos() {
        String jpql = "SELECT e FROM Evento e";
        jakarta.persistence.Query query = em.createQuery(jpql);
        return (List<Evento>) query.getResultList();
    }

    @Override
    public boolean guardarEvento(Evento evento) {
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEvento(Evento evento) {
        try {
            em.getTransaction().begin();
            em.merge(evento);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEvento(int id)  {
        try {
            Evento evento = em.find(Evento.class, id);
            em.getTransaction().begin();
            if (evento != null) {
                em.remove(evento);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
