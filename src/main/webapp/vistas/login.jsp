<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="../styles/styles.css">
</head>
<body>
    <div class="contenedor-login">
        <h1>Iniciar Sesión</h1>
        <form action="${pageContext.request.contextPath}/LoginController" method="post" class="formulario-login">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Ingresa tu usuario" required />

            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" placeholder="Ingresa tu contraseña" required />

            <button type="submit">Entrar</button>
        </form>
        <form action="${pageContext.request.contextPath}/CrearCuentaController" method="get">
            <button type="submit">Registrarse</button>
        </form>
    </div>  
</body>
</html>