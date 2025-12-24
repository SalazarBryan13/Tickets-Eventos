package controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import jakarta.servlet.http.Part;
import modelo.entities.Evento;
import modelo.JPA.impl.JPAEventoDAO;
import modelo.dao.EventoDAO;

//3. Agregamos la anotación de web servlet para que el servidor Tomcat
//reconozca la clase como un servle
@MultipartConfig
@WebServlet("/GestionarEventosController")
public class GestionarEventosController extends HttpServlet {
	// 1. Insertamos el SerialID
	private static final long serialVersionUID = 1L;

	// 2. Sobreescribimos los metodos doGet y doPost de la clase madre
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ruta = (req.getParameter("ruta")==null)?"listarEventos":req.getParameter("ruta");
		switch(ruta) {
		case "listarEventos":
			this.listarEventos(req, resp);
			break;
		case "registrarEvento":
			this.registrarEvento(req, resp);
			break;
		case "modificarEvento":
			this.modificarEvento(req, resp);
			break;
		case "eliminarEvento":
			this.eliminarEvento(req, resp);
			break;
		case "guardarExistente":
			this.guardarExistente(req, resp);
			break;
		//case "validarEvento":
			//this.validarEvento(req, resp);
			//break;
		}
	}

	private void listarEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2.- Hablar con el Modelo
		
		EventoDAO modeloDAO = new JPAEventoDAO();

		List<Evento> eventos = modeloDAO.obtenerEventos();
		//3.- Llamar a la vista
		request.setAttribute("eventos",eventos);
		request.getRequestDispatcher("vistas/listarEventos.jsp").forward(request, response);
	}

	private void registrarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.- Obtener los parámetros
		String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("txtDescripcion");
	    String fecha = request.getParameter("txtFecha");
	    String lugar = request.getParameter("txtLugar");
	    Integer cupoMaximo = Integer.parseInt(request.getParameter("txtCuposMaximos"));
	    Double precio = Double.parseDouble(request.getParameter("txtPrecio"));
	    // Obtener el archivo del formulario
	    Part archivoBanner = request.getPart("banner");

	    EventoDAO modeloDAO = new JPAEventoDAO();
	    
        // Verifica si el archivo es nulo o tiene un tamaño mayor a 0
        if (archivoBanner != null && archivoBanner.getSize() > 0) {
            try {
                // Crear un archivo temporal para almacenar el contenido del Part
                String submittedFileName = archivoBanner.getSubmittedFileName();
                File tempFile = File.createTempFile("banner_", submittedFileName);

                // Escribir el contenido del Part al archivo temporal
                archivoBanner.write(tempFile.getAbsolutePath());

                // Subir el archivo a Cloudinary
                Cloudinary cloudinary = CloudinaryConfig.getCloudinary();
                Map<String, Object> uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap(
                    "resource_type", "auto" // Detecta automáticamente si es imagen o PDF
                ));
                String archivoUrl = (String) uploadResult.get("secure_url");
                Evento evento = new Evento(nombre, descripcion, fecha, lugar, cupoMaximo, precio, archivoUrl);

                // Eliminar el archivo temporal
                tempFile.delete();
                
                boolean resultado = modeloDAO.guardarEvento(evento);
                //3.- Llamar a la vista
                if(resultado) {
                	response.sendRedirect("GestionarEventosController?ruta=listarEventos");
                }else {
                	response.sendRedirect("GestionarEventosController?ruta=listarEventos");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al subir el archivo: " + e.getMessage());
                request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
            }}  

	}

	private void modificarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		Integer id = Integer.parseInt(request.getParameter("id"));
		//2.- Hablar con el Modelo
		EventoDAO modeloDAO = new JPAEventoDAO();
		Evento evento = modeloDAO.obtenerEvento(id);
		//3.- Llamar a la vista
		request.setAttribute("evento", evento);
		request.getRequestDispatcher("vistas/modificarEvento.jsp").forward(request, response);
	}
	
	private void guardarExistente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.- Obtener los parámetros
		int eventoId = Integer.parseInt(request.getParameter("eventoId"));
		String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("txtDescripcion");
	    String fecha = request.getParameter("txtFecha");
	    String lugar = request.getParameter("txtLugar");
	    Integer cupoMaximo = Integer.parseInt(request.getParameter("txtCuposMaximos"));
	    Double precio = Double.parseDouble(request.getParameter("txtPrecio"));
	    String bannerLink = request.getParameter("banner");
	    EventoDAO modeloDAO = new JPAEventoDAO();

	    Evento evento = new Evento(nombre, descripcion, fecha, lugar, cupoMaximo, precio, bannerLink);
	    evento.setEventoId(eventoId);
	    
	    System.out.println("DATOS RECIBIDOS:");
	    System.out.println("id: " + eventoId);
	    System.out.println("Correo: " + nombre);
	    System.out.println("Clave: " + descripcion);
	    System.out.println("Celular: " + fecha);
	    System.out.println("Direccion: " + lugar);
	    System.out.println("Cedula: " + cupoMaximo);
	    System.out.println("Cedula: " + bannerLink);
	    
		//2.- Hablar con el Modelo
	    boolean respuesta = modeloDAO.actualizarEvento(evento);;
		//3.- Llamar a la vista
	    if(respuesta) {
	    	response.sendRedirect("GestionarEventosController?ruta=listarEventos");
	    }else {
	    	response.sendRedirect("GestionarEventosController?ruta=listarEventos");
	    }
	}

	private void eliminarEvento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			EventoDAO modeloDAO = new JPAEventoDAO();
			boolean eliminado = modeloDAO.eliminarEvento(id);

			if (eliminado) {
				response.sendRedirect("GestionarEventosController?ruta=listarEventos");
			} else {
				// Podrías mostrar una página de error si falla
				response.sendRedirect("GestionarEventosController?ruta=listarEventos");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("GestionarEventosController?ruta=listarEventos");
		}
	}





}