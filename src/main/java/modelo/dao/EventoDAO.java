package modelo.dao;

import java.util.List;
import modelo.entities.Evento;


public interface EventoDAO {
    
    // Obtener un Evento por su ID
    public Evento obtenerEvento(int id);
    
    // Obtener todos los Eventos
    public List<Evento> obtenerEventos();
    
    // Crear un nuevo Evento
    public boolean guardarEvento(Evento evento);
    
    // Actualizar un Evento existente
    public boolean actualizarEvento(Evento evento);
    
    // Eliminar un Evento por su ID
    public boolean eliminarEvento(int id);
    
}