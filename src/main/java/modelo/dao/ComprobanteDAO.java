package modelo.dao;

import modelo.entities.ComprobanteDePago;
import java.util.List;

public interface ComprobanteDAO {

    // Método para guardar un comprobante de pago
    public boolean guardarComprobante(ComprobanteDePago comprobante);
    
    // Método para extraer todos los comprobantes de pago
    public List<ComprobanteDePago> extraerComprobantes();
    
    // Método para actualizar el estado de un comprobante
    public boolean actualizarEstado(int comprobanteId, String estado);

    // Método para obtener un comprobante por ID
    public ComprobanteDePago obtenerComprobantePorId(int comprobanteId);
    public List<ComprobanteDePago> obtenerComprobantesPorUsuario(int usuarioId);
}
