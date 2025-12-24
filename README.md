# 🎫 Sistema de Venta de Entradas para Eventos

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10.0-blue.svg)](https://jakarta.ee/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Sistema web  para la gestión y venta de entradas para eventos, desarrollado con tecnologías Java Enterprise. Permite a los administradores gestionar eventos, clientes y comprobantes de pago, mientras que los usuarios pueden adquirir entradas y recibir tickets digitales con códigos QR únicos.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Uso](#-uso)
- [API](#-api)
- [Base de Datos](#-base-de-datos)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)

## ✨ Características

### Para Administradores
- 📊 **Dashboard Principal**: Vista general de eventos disponibles y estadísticas
- 🎪 **Gestión de Eventos**: Crear, listar, modificar y eliminar eventos
- 👥 **Gestión de Clientes**: Administración completa de usuarios del sistema
- 💰 **Gestión de Comprobantes**: Control y validación de comprobantes de pago
- 🖼️ **Gestión de Imágenes**: Subida de banners de eventos mediante Cloudinary

### Para Usuarios
- 🔐 **Autenticación**: Sistema de registro e inicio de sesión
- 🎟️ **Compra de Entradas**: Visualización y adquisición de entradas para eventos
- 📄 **Comprobantes de Pago**: Envío y gestión de comprobantes de pago
- 🎫 **Tickets Digitales**: Generación automática de tickets con códigos QR únicos
- 📱 **Visualización de Tickets**: Consulta de entradas adquiridas con códigos QR

## 🛠️ Tecnologías

### Backend
- **Java 21**: Lenguaje de programación principal
- **Jakarta EE 10**: Especificaciones de Java Enterprise
- **Jakarta Servlets 6.0**: Manejo de peticiones HTTP
- **Jakarta JSP 3.1**: Generación dinámica de vistas
- **Jakarta JPA 3.1**: API de persistencia Java
- **EclipseLink 4.0.2**: Proveedor de JPA
- **Jersey 3.1.2**: Framework REST API

### Base de Datos
- **MySQL 8.3+**: Sistema de gestión de bases de datos relacional

### Servicios Externos
- **Cloudinary**: Almacenamiento y gestión de imágenes en la nube
- **ZXing 3.5.3**: Generación de códigos QR

### Herramientas de Desarrollo
- **Maven 3.9+**: Gestión de dependencias y construcción del proyecto
- **Apache Tomcat**: Servidor de aplicaciones (recomendado)

## 📦 Requisitos Previos


- **Java Development Kit (JDK) 21** o superior
- **Apache Maven 3.9+**
- **MySQL Server 8.0+**
- **Apache Tomcat 10+** (o servidor de aplicaciones compatible con Jakarta EE 10)
- **Git** (para clonar el repositorio)

## 🚀 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/SalazarBryan13/Tickets-Eventos.git
cd Tickets-Eventos/App
```

### 2. Configurar la Base de Datos

Crea una base de datos MySQL:

```sql
CREATE DATABASE app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar las Propiedades de Persistencia

Edita el archivo `src/main/java/META-INF/persistence.xml` y actualiza las credenciales de conexión:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/app"/>
<property name="jakarta.persistence.jdbc.user" value="tu_usuario"/>
<property name="jakarta.persistence.jdbc.password" value="tu_contraseña"/>
```

### 4. Configurar Cloudinary

Copia el archivo de ejemplo y configura tus credenciales:

```bash
cp src/main/resources/cloudinary.properties.example src/main/resources/cloudinary.properties
```

Edita `src/main/resources/cloudinary.properties` con tus credenciales de Cloudinary:

```properties
cloudinary.cloud_name=tu_cloud_name
cloudinary.api_key=tu_api_key
cloudinary.api_secret=tu_api_secret
```


### 5. Compilar el Proyecto

```bash
mvn clean compile
```

### 6. Generar el WAR

```bash
mvn clean package
```

El archivo WAR se generará en `target/App-0.0.1-SNAPSHOT.war`

### 7. Desplegar en Tomcat

Copia el archivo WAR al directorio `webapps` de Tomcat:

```bash
cp target/App-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/
```

O renómbralo a `ROOT.war` para acceder directamente en la raíz:

```bash
cp target/App-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war
```

## ⚙️ Configuración

### Variables de Entorno

El proyecto utiliza archivos de propiedades para la configuración:

- **Base de Datos**: `src/main/java/META-INF/persistence.xml`
- **Cloudinary**: `src/main/resources/cloudinary.properties`

### Configuración de EclipseLink

El proyecto está configurado para crear automáticamente las tablas en la base de datos:

```xml
<property name="eclipselink.ddl-generation" value="create-tables" />
```

## 📁 Estructura del Proyecto

```
App/
├── src/
│   └── main/
│       ├── java/
│       │   ├── config/
│       │   │   └── CloudinaryConfig.java          # Configuración de Cloudinary
│       │   ├── controlador/
│       │   │   ├── CrearCuentaController.java     # Registro de usuarios
│       │   │   ├── DetalleEventoController.java   # Detalles de eventos
│       │   │   ├── EnviarComprobanteDePagoController.java
│       │   │   ├── GenerarTicketsController.java  # Generación de tickets QR
│       │   │   ├── GestionarClientesController.java
│       │   │   ├── GestionarComprobanteController.java
│       │   │   ├── GestionarEventosController.java
│       │   │   ├── LoginController.java           # Autenticación
│       │   │   ├── QrImageServlet.java            # Servicio de imágenes QR
│       │   │   └── VisualizarDashboardPrincipalController.java
│       │   ├── modelo/
│       │   │   ├── dao/                           # Interfaces DAO
│       │   │   ├── entities/                      # Entidades JPA
│       │   │   │   ├── Cliente.java
│       │   │   │   ├── ComprobanteDePago.java
│       │   │   │   ├── Evento.java
│       │   │   │   └── Ticket.java
│       │   │   └── JPA/impl/                      # Implementaciones JPA
│       │   └── META-INF/
│       │       └── persistence.xml                 # Configuración JPA
│       ├── resources/
│       │   ├── cloudinary.properties              # Credenciales Cloudinary
│       │   └── cloudinary.properties.example      # Plantilla de configuración
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml                        # Descriptor de despliegue
│           ├── vistas/                            # Vistas JSP
│           ├── styles/                            # Estilos CSS y fuentes
│           └── index.html
├── pom.xml                                        # Configuración Maven
├── .gitignore                                     # Archivos ignorados por Git
└── README.md                                      # Este archivo
```

## 💻 Uso

### Iniciar la Aplicación

1. Inicia el servidor MySQL
2. Inicia Apache Tomcat
3. Accede a la aplicación en: `http://localhost:8080/App` (o la URL configurada)

### Flujo de Usuario

1. **Registro/Login**: Los usuarios pueden crear una cuenta o iniciar sesión
2. **Explorar Eventos**: Visualizar eventos disponibles en el dashboard
3. **Seleccionar Evento**: Ver detalles y disponibilidad
4. **Comprar Entrada**: Realizar la compra y subir comprobante de pago
5. **Generar Tickets**: El sistema genera tickets con códigos QR únicos
6. **Visualizar Tickets**: Consultar entradas adquiridas

### Flujo de Administrador

1. **Login**: Acceso con credenciales de administrador
2. **Dashboard**: Vista general del sistema
3. **Gestionar Eventos**: Crear, editar y eliminar eventos
4. **Gestionar Clientes**: Administrar usuarios del sistema
5. **Validar Comprobantes**: Revisar y aprobar comprobantes de pago


## 🗄️ Base de Datos

### Entidades Principales

- **Evento**: Información de eventos (nombre, descripción, fecha, lugar, cupo, precio)
- **Cliente**: Datos de usuarios del sistema
- **ComprobanteDePago**: Comprobantes de pago enviados por usuarios
- **Ticket**: Tickets generados con códigos QR únicos

