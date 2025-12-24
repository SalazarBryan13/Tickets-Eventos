<c:if test="${not empty evento}">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Detalle del Evento - ${evento.nombre}</title>
    <script src="https://kit.fontawesome.com/8720798117.js" crossorigin="anonymous"></script>
    <style>
        /* Estilos generales */
        body {
            background-color: #1a1a1a;
            font-family: Arial, sans-serif;
            color: #f1f1f1;
            margin: 0;
            padding: 0;
        }

        .event-card {
            background: #333; /* Fondo oscuro para la tarjeta */
            max-width: 700px;
            margin: 30px auto;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
            overflow: hidden;
            transition: box-shadow 0.3s ease;
            color: white;
        }

        .banner-evento {
            width: 100%;
            height: auto;
            max-height: 400px; /* Altura máxima para que no se deforme */
            object-fit: cover; /* Cubrir la imagen sin recortar */
            background-color: #1a1a1a;
            display: block;
            margin: 0 auto;
        }

        .informacion {
            padding: 20px;
            background-color: #222; /* Fondo gris oscuro */
        }

        .resumen h1, .precios h2 {
            color: #f1f1f1; /* Blanco para los títulos */
        }

        .linea-hr {
            border: none;
            border-top: 3px solid #f1f1f1; /* Línea blanca */
            width: 70px;
            margin: 10px 0 25px 0;
            border-radius: 2px;
        }

        .ubicacion i {
            color: #f1f1f1;
            margin-right: 10px;
            font-size: 1.3rem;
        }

        .compra-link {
            background-color: #f1f1f1; /* Blanco para el botón */
            color: #333; /* Texto oscuro */
            font-weight: 700;
            border-radius: 30px;
            padding: 14px 30px;
            font-size: 1.1rem;
            text-decoration: none;
        }

        .compra-link:hover {
            background-color: #ddd; /* Gris claro al pasar el ratón */
            color: #333;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .event-card {
                margin: 20px 15px;
            }
            .informacion {
                padding: 15px;
            }
            .banner-evento {
                max-height: 250px;
            }
        }
    </style>
</head>
<body>
    <div class="event-card">
        <img src="${evento.bannerLink}" alt="Banner del evento ${evento.nombre}" class="banner-evento" />
        <div class="informacion">
            <div class="resumen">
                <h1>${evento.nombre}</h1>
                <hr class="linea-hr" />
                <p><strong>Fecha:</strong> ${evento.fecha}</p>
                <p>${evento.descripcion}</p>
            </div>
            <div class="ubicacion">
                <i class="fa-solid fa-location-dot"></i> <span>${evento.lugar}</span>
            </div>
            <div class="precios">
                <h2>Entradas</h2>
                <div class="opcionEntrada">
                    <p><strong>Precio:</strong> $${evento.precio}</p>
                    <p><strong>Aforo máximo:</strong> ${evento.cupoMaximo}</p>
                </div>
            </div>
        </div>
        <c:if test="${not empty sessionScope.usuarioLogueado}">
            <div style="text-align:center; margin: 30px 0 40px;">
                <a href="EnviarComprobanteDePagoController?ruta=mostrarFormularioEnvioComprobante&idEvento=${evento.eventoId}" class="compra-link">Comprar Entradas</a>
            </div>
        </c:if>
    </div>
</body>
</html>
</c:if>
