<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Modificar Evento</title>
    <link href="https://bootswatch.com/5/lux/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Modificar Evento</h2>
    <form method="post" action="./GestionarEventosController?ruta=registrarEvento">
        <!-- Campo oculto para indicar la ruta -->
        <input type="hidden" name="ruta" value="guardarExistente">
        <!-- Campo oculto para el ID del cliente -->
        <input type="hidden" name="eventoId" value="${evento.eventoId}">

        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="nombre" class="form-control" value="${evento.nombre}" required>
        </div>
        <div class="mb-3">
            <label>Descripcion</label>
            <input type="text" name="txtDescripcion" class="form-control" value="${evento.descripcion}" required>
        </div>
        <div class="mb-3">
            <label>Fecha</label>
            <input type="text" name="txtFecha" class="form-control" value="${evento.fecha}" required>
        </div>
        <div class="mb-3">
            <label>Lugar</label>
            <input type="text" name="txtLugar" class="form-control" value="${evento.lugar}" required>
        </div>
        <div class="mb-3">
            <label>Cupo Máximo</label>
            <input type="text" name="txtCuposMaximos" class="form-control" value="${evento.cupoMaximo}" required>
        </div>

        <div class="d-flex justify-content-between">
            <a href="./GestionarEventosController?ruta=listarEventos" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </div>
    </form>
</div>
</body>
</html>
