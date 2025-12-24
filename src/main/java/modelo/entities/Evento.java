package modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventoId")
    private int eventoId;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha", length = 10)
    private String fecha;

    @Column(name = "lugar", length = 100)
    private String lugar;

    @Column(name = "cupoMaximo")
    private int cupoMaximo;
    
    @Column(name = "precio")
    private double precio;
    
    @Column(name = "bannerLink", length = 200)
    private String bannerLink;


	public Evento() {}

    public Evento(String nombre, String descripcion, String fecha, String lugar, int cupoMaximo, double precio, String bannerLink) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.lugar = lugar;
        this.cupoMaximo = cupoMaximo;
        this.precio = precio;
        this.bannerLink = bannerLink;
    }

    public double getPrecio() {
    	return precio;
    }
    
    public void setPrecio(double precio) {
    	this.precio = precio;
    }
    
    public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}

	// Getters y Setters
    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
}
