package modelo.JPA.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.dao.ComprobanteDAO;
import modelo.entities.ComprobanteDePago;
import java.util.List;

public class JPAComprobanteDePagoDAO implements ComprobanteDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("evento");

    @Override
    public boolean guardarComprobante(ComprobanteDePago comprobante) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comprobante);  // Guarda el comprobante
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // Si algo falla, rollback
            }
            e.printStackTrace();  
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<ComprobanteDePago> extraerComprobantes() {
        EntityManager em = emf.createEntityManager();
        try {
            // Se hace una consulta para extraer todos los comprobantes
            return em.createQuery("SELECT c FROM ComprobanteDePago c", ComprobanteDePago.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean actualizarEstado(int comprobanteId, String estado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Se busca el comprobante por ID
            ComprobanteDePago comprobante = em.find(ComprobanteDePago.class, comprobanteId);
            if (comprobante != null) {
                comprobante.setEstado(estado);  // Actualiza el estado
                em.merge(comprobante);  // Guarda los cambios
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // Si algo falla, rollback
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public ComprobanteDePago obtenerComprobantePorId(int comprobanteId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Se busca el comprobante por ID
            return em.find(ComprobanteDePago.class, comprobanteId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    @Override
    public List<ComprobanteDePago> obtenerComprobantesPorUsuario(int usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta para obtener todos los comprobantes de un usuario
            return em.createQuery("SELECT c FROM ComprobanteDePago c WHERE c.usuario.usuarioId = :usuarioId", ComprobanteDePago.class)
                     .setParameter("usuarioId", usuarioId)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
