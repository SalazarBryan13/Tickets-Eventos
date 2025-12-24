<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Principal</title>
    <link rel="stylesheet" href="styles/styles.css">
    <script src="https://kit.fontawesome.com/8720798117.js" crossorigin="anonymous"></script>
    <style>
        /* Estilos para los enlaces de login y logout */
        .login-link, .entradas-link, .logout-link {
            color: white;
            text-decoration: none;
            padding: 10px;
            background-color: #007BFF;
            border-radius: 5px;
        }

        .login-link:hover, .entradas-link:hover, .logout-link:hover {
            background-color: #0056b3;
        }

        /* Estilo para los botones */
        .login-button, .entradas-button, .logout-button {
            display: inline-block;
            margin: 10px;
        }
    </style>
</head>
<body>

    <div class="encabezado">
        <div class="logo">
            <a href="VisualizarDashboardPrincipalController?ruta=obtenerEventosDisponibles" class="logo-link"> 
                <i class="fa-solid fa-ticket"></i>
                <div> TicketPlan</div>
            </a>
        </div>

        <!-- Mostrar el botón de login si no hay sesión activa -->
        <c:if test="${empty sessionScope.usuarioLogueado}">
            <div class="login-button">
                <a href="LoginController" class="login-link">Iniciar sesión</a>
            </div>
        </c:if>

        <!-- Mostrar el botón de Ver entradas solo si el usuario está logueado -->
        <c:if test="${not empty sessionScope.usuarioLogueado}">
            <div class="entradas-button">
                <a href="VisualizarDashboardPrincipalController?ruta=verEntradas" class="entradas-link">Ver entradas</a>
            </div>

            <!-- Mostrar el botón de cerrar sesión solo si hay sesión activa -->
            <div class="logout-button">
                <a href="VisualizarDashboardPrincipalController?ruta=cerrarSesion" class="logout-link">Cerrar sesión</a>
            </div>
        </c:if>

    </div>

    <div class="banner-evento-transicion">
        <div class="animacion">
            <img src="https://picsum.photos/id/33/1200/400" alt="">
            <img src="https://picsum.photos/id/66/1200/400" alt="">
            <img src="https://picsum.photos/id/72/1200/400" alt="">
        </div>
    </div>

    <div class="cartelera"> 
        <div class="titulo"> Cartelera </div>

        

        <div class="contenedor-tarjetas">
            <c:if test="${not empty eventos}">
                <c:forEach var="e" items="${eventos}">
                    <div class="card categoria-generica">
                        <div class="nombre-evento"> ${e.nombre} </div>
                        <div class="banner-evento">
                            <a href="VisualizarDashboardPrincipalController?ruta=mostrarDetalleEvento&id=${e.eventoId}">
                                <img src="${e.bannerLink}" alt="Banner del evento">
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty eventos}">
                <p>No hay eventos disponibles por el momento.</p>
            </c:if>

        </div>
    </div>

</body>
</html>


