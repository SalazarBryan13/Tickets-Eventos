<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Entradas</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <style>
        .ticket-card {
            max-width: 350px;
            margin-bottom: 20px;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .ticket-card img {
            border: 2px solid #007bff;
            border-radius: 5px;
        }
        .ticket-header {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border-radius: 10px 10px 0 0;
            text-align: center;
        }
        .ticket-body {
            padding: 15px;
        }
        .ticket-footer {
            text-align: center;
            padding: 10px;
            background-color: #f8f9fa;
            border-radius: 0 0 10px 10px;
        }
        .qr-code {
            display: block;
            margin: 0 auto;
        }
        @media (max-width: 576px) {
            .ticket-card {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center mb-4">Mis Entradas Compradas</h2>
        <c:if test="${empty tickets}">
            <div class="alert alert-info text-center" role="alert">
                No se encontraron entradas para este usuario.
            </div>
        </c:if>
        <c:if test="${not empty tickets}">
            <div class="row">
                <c:forEach var="ticket" items="${tickets}">
                    <div class="col-12 col-sm-6 col-md-4 d-flex justify-content-center">
                        <div class="ticket-card">
                            <div class="ticket-header">
                                <h4>Entrada #${ticket.ticketId}</h4>
                            </div>
                            <div class="ticket-body">
                                <img class="qr-code" alt="Código QR" width="150" height="150"
                                     src="${pageContext.request.contextPath}/QrImageServlet?ticketId=${ticket.ticketId}" />
                                <p class="text-center mt-2"><small>Código: ${ticket.codigoTicket}</small></p>
                                <hr>
                                <p><strong>Evento:</strong> ${ticket.comprobante.evento.nombre}</p>
                                <p><strong>Fecha de Compra:</strong> ${ticket.comprobante.fechaEnvio}</p>
                                <p><strong>Monto:</strong> $${ticket.comprobante.monto}</p>
                                <!-- Opcional: Agregar más datos del evento si están disponibles -->
                                <c:if test="${not empty ticket.comprobante.evento.fecha}">
                                    <p><strong>Fecha del Evento:</strong> ${ticket.comprobante.evento.fecha}</p>
                                </c:if>
                                <c:if test="${not empty ticket.comprobante.evento.lugar}">
                                    <p><strong>Lugar:</strong> ${ticket.comprobante.evento.lugar}</p>
                                </c:if>
                                <c:if test="${not empty ticket.usuario.nombre}">
                                    <p><strong>Usuario:</strong> ${ticket.usuario.nombre}</p>
                                </c:if>
                            </div>
                            <div class="ticket-footer">
                                <a href="${pageContext.request.contextPath}/QrImageServlet?ticketId=${ticket.ticketId}"
                                   download="ticket_${ticket.ticketId}.png" class="btn btn-primary btn-sm">
                                    Descargar QR
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>