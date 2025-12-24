package modelo.JPA.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;
import modelo.entities.Evento;


public class JPAClienteDAO implements ClienteDAO {

    private EntityManager em;

    public JPAClienteDAO() {
        em = Persistence.createEntityManagerFactory("evento").createEntityManager();
    }

	@Override
	public Cliente obtenerInformacionCliente(String cedula) {
	    String jpql = "SELECT c FROM Cliente c WHERE c.cedula = :cedula";
	    List<Cliente> resultados = em.createQuery(jpql, Cliente.class).setParameter("cedula", cedula).getResultList();
	    return resultados.isEmpty() ? null : resultados.get(0);
	}

	@Override
	public List<Cliente> extraerClientes() {
        String jpql = "SELECT c FROM Cliente c";
        jakarta.persistence.Query query = em.createQuery(jpql);
        return (List<Cliente>) query.getResultList();
	}

	@Override
	public boolean guardarCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean actualizarCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
	}
	
	@Override
	public boolean eliminarCliente(String cedula) {
	    try {
	        String jpql = "SELECT c FROM Cliente c WHERE c.cedula = :cedula";
	        List<Cliente> resultados = em.createQuery(jpql, Cliente.class).setParameter("cedula", cedula).getResultList();

	        if (!resultados.isEmpty()) {
	            Cliente cliente = resultados.get(0);
	            em.getTransaction().begin();
	            em.remove(em.contains(cliente) ? cliente : em.merge(cliente)); // asegura que sea una entidad gestionada
	            em.getTransaction().commit();
	            return true;
	        } else {
	            return false; 
	        }

	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) em.getTransaction().rollback();
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@Override
	public Cliente obtenerClientePorCredenciales(String correo, String contraseña) {
	    try {
	        String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo AND c.contraseña = :contraseña";
	        return em.createQuery(jpql, Cliente.class)
	                 .setParameter("correo", correo)
	                 .setParameter("contraseña", contraseña)
	                 .getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	
	public Integer obtenerIdPorCorreo(String correo) {
	    String jpql = "SELECT c.usuarioId FROM Cliente c WHERE c.correo = :correo";
	    try {
	        return (int) em.createQuery(jpql)
	                       .setParameter("correo", correo)
	                       .getSingleResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;  // Retorna un valor negativo en caso de error
	    }
	}


}
