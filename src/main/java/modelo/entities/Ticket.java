package modelo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private int ticketId;

    @Column(name = "codigoTicket", unique = true, nullable = false)
    private String codigoTicket;

    @ManyToOne
    @JoinColumn(name = "comprobanteId", referencedColumnName = "comprobanteId", nullable = false)
    private ComprobanteDePago comprobante;

    @ManyToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "usuarioId", nullable = false)
    private Cliente usuario; // Relación con Cliente

    @Lob
    @Column(name = "qrImage")
    private byte[] qrImage;

    // Getters y Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(String codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public ComprobanteDePago getComprobante() {
        return comprobante;
    }

    public void setComprobante(ComprobanteDePago comprobante) {
        this.comprobante = comprobante;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public byte[] getQrImage() {
        return qrImage;
    }

    public void setQrImage(byte[] qrImage) {
        this.qrImage = qrImage;
    }
}