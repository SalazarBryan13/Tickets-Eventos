<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Comprobantes de Pago</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar izquierdo de administrador -->
            <div class="col-md-3 col-lg-2 px-0 sidebar">
                <div class="p-3">
                    <div class="d-flex align-items-center mb-4">
                        <i class="bi bi-person-fill" style="font-size: 1.5rem; width: 40px; height: 40px;"></i>
                        <small class="text-muted ms-2">Administrador</small>
                    </div>
                    <ul class="nav flex-column">
                        <li><a class="nav-link active" href="./GestionarEventosController?ruta=listarEventos">
                            <i class="bi bi-calendar-event"></i> Eventos
                        </a></li>
                        <li><a class="nav-link active" href="./GestionarClientesController?ruta=listarClientes">
                            <i class="bi bi-person-circle"></i> Clientes
                        </a></li>
                        <li><a class="nav-link active" href="./GestionarComprobantesController?ruta=listarComprobantes">
                            <i class="bi bi-file-earmark-earphones"></i> Comprobantes
                        </a></li>
                        <li><a class="nav-link" href="configuracion_cuenta.html">
                            <i class="bi bi-gear"></i> Configuración Cuenta
                        </a></li>
                        <li class="mt-4">
                            <a class="nav-link text-danger" href="./LoginController">
                                <i class="bi bi-box-arrow-right"></i> Cerrar Sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Contenido principal de la pagina -->
            <div class="col-md-9 col-lg-10 contenido-principal">
                <h2 class="my-4">Listado de Comprobantes de Pago</h2>

                <!-- Tabla de Comprobantes -->
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre Cliente</th>
                                <th>Fecha</th>
                                <th>Monto</th>
                                <th>Número de Entradas</th>
                                <th>Estado</th>
                                <th>Comprobante</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="comprobante" items="${comprobantes}">
                                <tr>
                                    <td>${comprobante.comprobanteId}</td>
                                    <td>${comprobante.usuario.nombre}</td>
                                    <td>${comprobante.fechaEnvio}</td>
                                    <td>$${comprobante.monto}</td>
                                    <td>${comprobante.numeroEntradas}</td>
                                    <td>
                                        <form action="GestionarComprobantesController?ruta=actualizarEstado" method="post">
                                            <input type="hidden" name="comprobanteId" value="${comprobante.comprobanteId}">
                                            <select name="estado" class="form-select">
                                                <option value="pendiente" ${comprobante.estado == 'pendiente' ? 'selected' : ''}>Pendiente</option>
                                                <option value="aprobado" ${comprobante.estado == 'aprobado' ? 'selected' : ''}>Aprobado</option>
                                                <option value="rechazado" ${comprobante.estado == 'rechazado' ? 'selected' : ''}>Rechazado</option>
                                            </select>
                                            <button type="submit" class="btn btn-sm btn-outline-primary mt-2">Actualizar Estado</button>
                                        </form>
                                    </td>
                                    <td><a href="${comprobante.archivoComprobante}" target="_blank">Ver Comprobante</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>