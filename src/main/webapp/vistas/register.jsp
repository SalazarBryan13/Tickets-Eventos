<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Registro</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">

    <style>
        .fila-doble {
            display: flex;
            justify-content: center;
            gap: 25%;
            margin-bottom: 20px;
        }

        .campo {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .campo input {
            padding: 8px;
            font-size: 20px;
        }

        button {
            margin-top: 15px;
            width: 100%;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="contenedor-login">
        <h1>Registrarse</h1>
        <form id="clienteForm" method="post" action="${pageContext.request.contextPath}/CrearCuentaController" class="formulario-login">
            <input type="hidden" name="ruta" id="formRuta" value="crearCliente">
            <input type="hidden" name="UsuarioId" id="usuarioIdInput">

            <div class="fila-doble">
                <div class="campo">
                    <label for="correo">Correo</label>
                    <input type="email" id="correo" name="correo" placeholder="ejemplo@correo.com" required />
                </div>
                <div class="campo">
                    <label for="contrasena">Contraseña</label>
                    <input type="password" id="contrasena" name="txtClave" placeholder="Crea una contraseña" required />
                </div>
            </div>

            <div class="fila-doble">
                <div class="campo">
                    <label for="celular">Celular</label>
                    <input type="tel" id="celular" name="txtCelular" placeholder="0999999999" required />
                </div>
                <div class="campo">
                    <label for="direccion">Dirección</label>
                    <input type="text" id="direccion" name="txtDireccion" placeholder="Tu dirección" required />
                </div>
            </div>

            <div class="fila-doble">
                <div class="campo">
                    <label for="cedula">Cédula</label>
                    <input type="text" id="cedula" name="txtCedula" placeholder="Número de cédula" required />
                </div>
                <div class="campo">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="txtNombre" placeholder="Tu nombre completo" required />
                </div>
            </div>

            <button type="submit">Registrarse</button>
        </form>
    </div>  
</body>
</html>
