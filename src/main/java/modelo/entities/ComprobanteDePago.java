package modelo.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ComprobanteDePago")
public class ComprobanteDePago implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comprobanteId")
    private int comprobanteId;

    @Column(name = "fechaEnvio")
    private String fechaEnvio;

    @Column(name = "monto", nullable = false)
    private double monto;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "archivoComprobante")
    private String archivoComprobante;

    // Relación con la entidad Evento
    @ManyToOne
    @JoinColumn(name = "eventoId", referencedColumnName = "eventoId")
    private Evento evento;

    // Relación con la entidad Usuario
    @ManyToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "usuarioId")
    private Cliente usuario;

    // Nuevo atributo: número de entradas
    @Column(name = "numeroEntradas")
    private int numeroEntradas;

    // Constructores
    public ComprobanteDePago() {}

    // Getters y Setters
    public int getComprobanteId() {
        return comprobanteId;
    }

    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArchivoComprobante() {
        return archivoComprobante;
    }

    public void setArchivoComprobante(String archivoComprobante) {
        this.archivoComprobante = archivoComprobante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public int getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(int numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }
}

