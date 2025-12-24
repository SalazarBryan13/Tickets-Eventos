<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema Gestor de Eventos</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="./styles/styles.css">
    <style>
    .input-archivo {
        width: 100%;
        padding: 14px;
        font-size: 16px;
        border-radius: 8px;
        border: 2px solid black;
        background-color: white;
        color: black;
    }
    </style>
</head>
<body class="admin-page">
	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar izquierdo de administrador -->
			<div class="col-md-3 col-lg-2 px-0 sidebar">
				<div class="p-3">
					<div class="d-flex align-items-center mb-4">
						<i class="bi bi-person-fill"
							style="font-size: 1.5rem; width: 40px; height: 40px;"></i> <small
							class="text-muted ms-2">Administrador</small>
					</div>
					<ul class="nav flex-column">
						<li><a class="nav-link active" href="./GestionarEventosController?ruta=listarEventos">
								<i class="bi bi-calendar-event"></i>Eventos
						</a></li>
						<li><a class="nav-link active"
							href="./GestionarClientesController?ruta=listarClientes"> <i
								class="bi bi-calendar-event"></i>Clientes
						</a></li>
						<li><a class="nav-link active"
							href="./GestionarComprobantesController?ruta=listarComprobantes"> <i
								class="bi bi-calendar-event"></i>Comprobantes
						</a></li>
						<li class="mt-4"><a class="nav-link text-danger"
							href="./LoginController"> <i class="bi bi-box-arrow-right"></i>Cerrar
								Sesión
						</a></li>
					</ul>
				</div>
			</div>

			<!-- Contenido principal de la pagina -->
			<div class="col-md-9 col-lg-10 contenido-principal">
				<div class="d-flex justify-content-between align-items-center mb-4">
					<h2>Gestión de Eventos</h2>
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#eventoModal">
						<i class="bi bi-plus-lg me-2"></i>Nuevo Evento
					</button>
				</div>

				<!-- Filtro de busqueda de clientes -->
				<div class="filtros mb-4">
					<div class="input-group">
						<span class="input-group-text"> <i class="bi bi-search"></i>
						</span> <input type="text" class="form-control"
							placeholder="Buscar eventos..." id="filtroBusqueda">
					</div>
				</div>

				<!-- Tabla de Clientes -->
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nombre</th>
								<th>Descripcion</th>
								<th>Fecha</th>
								<th>Lugar</th>
								<th>Cupo Máximo</th>
								<th>Precio</th>
								<th>Banner</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="evento" items="${eventos}">
								<tr>
									<td>${evento.eventoId}</td>
									<td>${evento.nombre}</td>
									<td>${evento.descripcion}</td>
									<td>${evento.fecha}</td>
									<td>${evento.lugar}</td>
									<td>${evento.cupoMaximo}</td>
									<td>${evento.precio}</td>
									<td>${evento.bannerLink}</td>
									<td>
										<div class="grupo-botones">
										<a
												href="GestionarEventosController?ruta=eliminarEvento&id=${evento.eventoId}"
												class="btn btn-sm btn-outline-danger"
												onclick="return confirm('¿Seguro que deseas eliminar este evento?');">
												<i class="bi bi-trash"></i>
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal para crear o editar Cliente -->
<div class="modal fade" id="eventoModal" tabindex="-1" aria-labelledby="eventoModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <form id="eventoForm" method="post" action="GestionarEventosController" enctype="multipart/form-data">
        <input type="hidden" name="ruta" id="formRuta" value="registrarEvento">
        <input type="hidden" name="eventooId" id="eventoIdInput">
        <div class="modal-header">
          <h5 class="modal-title" id="eventoModalLabel">Nuevo Evento</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
        </div>
        <div class="modal-body">
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Nombre</label>
              <input type="text" name="nombre" class="form-control" id="nombreInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Descripcion</label>
              <input type="text" name="txtDescripcion" class="form-control" id="descripcionInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Fecha</label>
              <input type="text" name="txtFecha" class="form-control" id="fechaInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Lugar</label>
              <input type="text" name="txtLugar" class="form-control" id="lugarInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Cupo Máximo</label>
              <input type="text" name="txtCuposMaximos" class="form-control" id="cuposMaximosInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Precio</label>
              <input type="text" name="txtPrecio" class="form-control" id="precioInput" required>
            </div>
            <div class="col-md-6">
	          <label for="voucher" class="form-label">Selecciona tu banner</label>
              <input type="file" id="bannerInput" name="banner" class="form-control" accept=".pdf, .jpg, .png, .jpeg" required />
            </div>
            
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="submit" class="btn btn-primary">Guardar Evento</button>
        </div>
      </form>
    </div>
  </div>
</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>