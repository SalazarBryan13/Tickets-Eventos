<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema Gestor de Usuarios</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="./styles/styles.css">
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
					<h2>Gestión de Clientes</h2>
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#eventoModal">
						<i class="bi bi-plus-lg me-2"></i>Nuevo Cliente
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
								<th>Correo</th>
								<th>Contraseña</th>
								<th>Celular</th>
								<th>Dirección</th>
								<th>Cedula</th>
								<th>Nombre</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cliente" items="${clientes}">
								<tr>
									<td>${cliente.usuarioId}</td>
									<td>${cliente.correo}</td>
									<td>${cliente.contraseña}</td>
									<td>${cliente.celular}</td>
									<td>${cliente.direccion}</td>
									<td>${cliente.cedula}</td>
									<td>${cliente.nombre}</td>
									<td>
										<div class="grupo-botones">
											<a
												href="GestionarClientesController?ruta=modificarCliente&cedula=${cliente.cedula}"
												class="btn btn-sm btn-outline-primary"> <i
												class="bi bi-pencil"></i>
											</a> <a
												href="GestionarClientesController?ruta=eliminarCliente&cedula=${cliente.cedula}"
												class="btn btn-sm btn-outline-danger"
												onclick="return confirm('¿Seguro que deseas eliminar este cliente?');">
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
      <form id="clienteForm" method="post" action="CrearCuentaController">
        <input type="hidden" name="ruta" id="formRuta" value="crearCliente">
        <input type="hidden" name="UsuarioId" id="usuarioIdInput">
        <div class="modal-header">
          <h5 class="modal-title" id="eventoModalLabel">Nuevo Cliente</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
        </div>
        <div class="modal-body">
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Correo</label>
              <input type="email" name="correo" class="form-control" id="correoInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Contraseña</label>
              <input type="text" name="txtClave" class="form-control" id="claveInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Celular</label>
              <input type="text" name="txtCelular" class="form-control" id="celularInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Dirección</label>
              <input type="text" name="txtDireccion" class="form-control" id="direccionInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Cédula</label>
              <input type="text" name="txtCedula" class="form-control" id="cedulaInput" required>
            </div>
            <div class="col-md-6">
              <label class="form-label">Nombre</label>
              <input type="text" name="txtNombre" class="form-control" id="nombreInput" required>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
          <button type="submit" class="btn btn-primary">Guardar Cliente</button>
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