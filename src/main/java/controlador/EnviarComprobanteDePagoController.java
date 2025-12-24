package controlador;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import config.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.JPA.impl.JPAClienteDAO;
import modelo.JPA.impl.JPAComprobanteDePagoDAO;
import modelo.JPA.impl.JPAEventoDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.ComprobanteDAO;
import modelo.dao.EventoDAO;
import modelo.entities.Cliente;
import modelo.entities.ComprobanteDePago;
import modelo.entities.Evento;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/EnviarComprobanteDePagoController")
@MultipartConfig
public class EnviarComprobanteDePagoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String ruta = (request.getParameter("ruta") == null) ? "mostrarFormularioEnvioComprobante" : request.getParameter("ruta");

	    switch (ruta) {
	        case "mostrarFormularioEnvioComprobante":
	            this.mostrarFormularioEnvioComprobante(request, response);
	            break;
	        case "guardarComprobante":
	            this.guardarComprobante(request, response);
	            break;

	    }
	}
	private void mostrarFormularioEnvioComprobante(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

       
        String eventoIdStr = request.getParameter("idEvento");
        int eventoId = Integer.parseInt(eventoIdStr);
        ComprobanteDAO comprobanteDAO = new JPAComprobanteDePagoDAO();
		EventoDAO eventoDAO = new JPAEventoDAO();
		Evento evento =   eventoDAO.obtenerEvento(eventoId);
		 request.setAttribute("evento", evento);
		 request.getRequestDispatcher("vistas/SubirVoucher.jsp").forward(request, response);
	}
	
	private void guardarComprobante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Obtener la fecha actual
	    LocalDate fechaActual = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String fechaEnvio = fechaActual.format(formatter);

	    // Obtener el archivo del formulario
	    Part archivoVoucher = request.getPart("voucher");

	    // Verifica si el archivo es nulo o tiene un tamaño mayor a 0
	    if (archivoVoucher != null && archivoVoucher.getSize() > 0) {
	        try {
	            // Crear un archivo temporal para almacenar el contenido del Part
	            String submittedFileName = archivoVoucher.getSubmittedFileName();
	            File tempFile = File.createTempFile("voucher_", submittedFileName);

	            // Escribir el contenido del Part al archivo temporal
	            archivoVoucher.write(tempFile.getAbsolutePath());

	            // Subir el archivo a Cloudinary
	            Cloudinary cloudinary = CloudinaryConfig.getCloudinary();
	            Map<String, Object> uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap(
	                "resource_type", "auto" // Detecta automáticamente si es imagen o PDF
	            ));
	            String archivoUrl = (String) uploadResult.get("secure_url");

	            // Eliminar el archivo temporal
	            tempFile.delete();

	            // Obtener el ID del evento, monto y número de entradas desde la solicitud
	            Integer eventoId = Integer.parseInt(request.getParameter("id_evento"));
	            Double monto = Double.parseDouble(request.getParameter("monto").replace("$", "").trim());
	            Integer numeroEntradas = Integer.parseInt(request.getParameter("numEntradas")); // Obtener número de entradas

	            // Obtener el cliente desde la sesión
	            HttpSession sesion = request.getSession();
	            Cliente cliente = (Cliente) sesion.getAttribute("usuarioLogueado");

	            // Verificar si el cliente está logueado
	            if (cliente == null) {
	                request.setAttribute("error", "El usuario no está logueado.");
	                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	                return;
	            }

	            // Recuperar las entidades Evento y Cliente desde la base de datos
	            EventoDAO eventoDAO = new JPAEventoDAO();  // Asegúrate de tener un DAO para Evento
	            Evento evento = eventoDAO.obtenerEvento(eventoId); // Método que obtiene Evento por ID

	            // Crear la entidad ComprobanteDePago
	            ComprobanteDePago comprobanteDePago = new ComprobanteDePago();
	            comprobanteDePago.setFechaEnvio(fechaEnvio); // Aquí puedes ajustar la fecha según sea necesario
	            comprobanteDePago.setMonto(monto);
	            comprobanteDePago.setEstado("Pendiente");
	            comprobanteDePago.setArchivoComprobante(archivoUrl);
	            comprobanteDePago.setNumeroEntradas(numeroEntradas); // Establecer el número de entradas

	            // Establecer las relaciones con el evento y el usuario
	            comprobanteDePago.setEvento(evento);  // Establecer evento con la entidad completa
	            comprobanteDePago.setUsuario(cliente); // Establecer usuario con la entidad completa

	            // Guardar la información en la base de datos
	            ComprobanteDAO comprobanteDAO = new JPAComprobanteDePagoDAO();
	            boolean resultado = comprobanteDAO.guardarComprobante(comprobanteDePago);

	            // Enviar respuesta según el resultado
	            if (resultado) {
	                request.setAttribute("exitoso", "El archivo se ha enviado con éxito");
	                request.getRequestDispatcher("VisualizarDashboardPrincipalController?ruta=obtenerEventosDisponibles").forward(request, response);
	            } else {
	                request.setAttribute("error", "Ha ocurrido un error al guardar en la base de datos");
	                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error al subir el archivo: " + e.getMessage());
	            request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	        }
	    } else {
	        request.setAttribute("error", "No se ha seleccionado ningún archivo");
	        request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
	    }
	}


}

