package modelo.dao;

import java.util.List;

import modelo.entities.Cliente;

public interface ClienteDAO {
	
    // Obtener un Cliente por su ID
    public Cliente obtenerInformacionCliente(String cedula);
    public Integer obtenerIdPorCorreo(String correo);  // Solo la firma del método
    // Obtener todos los Cliente
    public List<Cliente> extraerClientes();
    
    // Crear un nuevo Cliente
    public boolean guardarCliente(Cliente cliente);
    
    // Actualizar un Cliente existente
    public boolean actualizarCliente(Cliente cliente);
    
    // Eliminar un Cliente por su cedula
    public boolean eliminarCliente(String cedula);
    
    public Cliente obtenerClientePorCredenciales(String correo, String clave);


}
