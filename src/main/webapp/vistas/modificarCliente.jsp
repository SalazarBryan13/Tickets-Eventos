<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Modificar Cliente</title>
    <link href="https://bootswatch.com/5/lux/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Modificar Cliente</h2>
    <form method="post" action="./GestionarClientesController?ruta=guardarExistente">
        <!-- Campo oculto para indicar la ruta -->
        <input type="hidden" name="ruta" value="guardarExistente">
        <!-- Campo oculto para el ID del cliente -->
        <input type="hidden" name="UsuarioId" value="${cliente.usuarioId}">

        <div class="mb-3">
            <label>Correo</label>
            <input type="email" name="correo" class="form-control" value="${cliente.correo}" required>
        </div>
        <div class="mb-3">
            <label>Contraseña</label>
            <input type="text" name="txtClave" class="form-control" value="${cliente.contraseña}" required>
        </div>
        <div class="mb-3">
            <label>Celular</label>
            <input type="text" name="txtCelular" class="form-control" value="${cliente.celular}" required>
        </div>
        <div class="mb-3">
            <label>Dirección</label>
            <input type="text" name="txtDireccion" class="form-control" value="${cliente.direccion}" required>
        </div>
        <div class="mb-3">
            <label>Cédula</label>
            <input type="text" name="txtCedula" class="form-control" value="${cliente.cedula}" required>
        </div>
        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="txtNombre" class="form-control" value="${cliente.nombre}" required>
        </div>

        <div class="d-flex justify-content-between">
            <a href="./GestionarClientesController?ruta=listarClientes" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </div>
    </form>
</div>
</body>
</html>
