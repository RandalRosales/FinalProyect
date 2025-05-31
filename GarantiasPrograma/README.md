# Sistema de Gestión de Garantías

Este programa en Java permite gestionar de forma ordenada y eficiente los equipos en proceso de garantía mediante el uso de estructuras de datos como colas (FIFO), registro de historial con trazabilidad por usuario, fases del proceso y manejo de archivos de texto.

---

##  Descripción General

El sistema está diseñado para que una empresa lleve un control detallado del estado de los equipos ingresados para garantía. Cada equipo pasa por distintas fases (Recepción, Diagnóstico, Reparación, Calidad, Entregado) y los movimientos son gestionados por usuarios autenticados. Todos los cambios quedan registrados en un historial con formato claro.

---

##  Características Principales

- Login seguro con usuario y contraseña (con contraseña oculta).
- Manejo de colas para representar las fases del proceso de garantía.
- Solicitud de comentario obligatorio en cada fase para mantener trazabilidad.
- Registro detallado en historial con formato separado por pipes.
- Validación de entradas, incluyendo correos electrónicos y datos numéricos.
- Control de excepciones y validación para evitar errores durante la ejecución.
- Limpieza de pantalla tras cada operación para mejor presentación.
- Historial persistente guardado en archivo `historial.txt`.

---

## ⚙️ Requisitos del Sistema

- **JDK:** Java Development Kit 17 o superior
- **IDE recomendado:** IntelliJ IDEA, Eclipse, NetBeans o Visual Studio Code
- **Sistema operativo:** Windows, Linux o macOS (con soporte Java)
- **Memoria RAM:** Mínimo 512 MB
- **Codificación de archivos:** UTF-8

 ## Estructura del Proyecto

SistemaGarantias/
│
├── Main.java                 # Clase principal con menú y flujo general
├── Login.java                # Clase para autenticación de usuario
├── Computadora.java          # Clase que representa un equipo en garantía
├── ColaGarantia.java         # Clase que implementa la lógica de las colas por fase
├── Historial.java            # Maneja el historial de movimientos y escritura en archivo
├── Scanner.java              # Métodos de validación, entrada segura y limpieza de consola
├── historial.txt             # Archivo que almacena el historial en texto plano
└── /data/ 

----

## Ejecución del Programa

1. Abre el proyecto en tu IDE favorito.
2. Asegúrate de tener creada la carpeta `/data/` en el directorio raíz.
3. Compila y ejecuta `Main.java`.
4. Inicia sesión con el usuario:

   - Usuario: Randal
   - Contraseña: RandalUMG

5. Usa el menú para registrar equipos, moverlos entre fases o consultar el historial.

---

## Historial

Los cambios quedan registrados en el archivo `historial.txt` bajo este formato:

**** Usuario | Fecha y Hora | Fase | Acción | Problema Reportado | Solución | Comentario ****


--------- Autor ---------------
- Desarrollado por: **[Randal Jazuary Cordon Rosales | | Carné: 0900-24-322]**
- Universidad: Universidad Mariano Gálvez de Guatemala
- Proyecto final de programación I