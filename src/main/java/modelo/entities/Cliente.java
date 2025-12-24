package modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;
	@Column(name = "correo", nullable = false, unique = true)
	private String correo;
	@Column(name = "contraseña", nullable=false)
	private String contraseña;
	@Column(name = "celular", length = 15)
	private String celular;
	@Column(name = "direccion", length = 100)
	private String direccion;
	@Column(name = "cedula", length = 10, unique = true)
	private String cedula;
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	public Cliente() {}

	public Cliente(String correo, String contraseña, String celular, String direccion, String cedula, String nombre) {
		super();
		this.correo = correo;
		this.contraseña = contraseña;
		this.celular = celular;
		this.direccion = direccion;
		this.cedula = cedula;
		this.nombre = nombre;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
