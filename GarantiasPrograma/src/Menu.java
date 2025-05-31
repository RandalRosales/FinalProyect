import java.io.Console;
import java.time.LocalDate;
import java.util.Scanner;

import Data.Computadora;
import Servicios.Garantia;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Garantia servicio = new Garantia();
    private static String usuarioLogueado;

    public static void mostrarMenu() {
        if (!login()) {
            System.out.println("Acceso denegado. Saliendo del programa.");
            return;
        }

        int opcion;
        do {
            limpiarPantalla();
            System.out.println("========== MENÚ PRINCIPAL ==========");
            System.out.println("Usuario: " + usuarioLogueado);
            System.out.println("1. Registrar una nueva computadora");
            System.out.println("2. Seleccionar fase");
            System.out.println("3. Mostrar historial");
            System.out.println("4. Mostrar status general");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarComputadora();
                    break;
                case 2:
                    moverComputadora();
                    break;
                case 3:
                    mostrarHistorial();
                    break;
                case 4:
                    mostrarStatusActual();
                    break;
                case 5:
                    System.out.println("Saliendo... ¡Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor vuelva a intentarlo.");
                    pausa();
            }
        } while (opcion != 5);
    }

    private static boolean login() {
        limpiarPantalla();
        System.out.println("===== LOGIN =====");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        String password = leerPassword("Contraseña: ");

        if (usuario.equals("Randal") && password.equals("RandalUMG")) {
            usuarioLogueado = usuario;
            return true;
        } else {
            System.out.println("Error, usuario o contraseña incorrectos.");
            return false;
        }
    }

    private static String leerPassword(String contra) {
        Console console = System.console();
        if (console != null) {
            char[] passwordChars = console.readPassword(contra);
            return new String(passwordChars);
        } else {
            System.out.print(contra + " (La contraseña no esta oculta): ");
            return scanner.nextLine();
        }
    }

    private static int leerEntero() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                return num;
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Ingrese un número por favor: ");
            }
        }
    }

    private static void registrarComputadora() {
        limpiarPantalla();
        System.out.println("\n--- Registro de Computadora ---");

        System.out.print("Service Tag: ");
        String serviceTag = scanner.nextLine();

        System.out.print("Descripción del problema: ");
        String descripcion = scanner.nextLine();

        System.out.print("Fecha de recepción (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();

        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        String correo;
        do {
            System.out.print("Correo electrónico del cliente: ");
            correo = scanner.nextLine();
            if (!correo.contains("@")) {
                System.out.println("Error. Introduzca un correo válido.");
            }
        } while (!correo.contains("@"));

        System.out.print("Teléfono del cliente: ");
        String telefono = scanner.nextLine();

        try {
            LocalDate fechaRecepcion = LocalDate.parse(fechaStr);

            Computadora pc = new Computadora(
                serviceTag,
                descripcion,
                fechaRecepcion,
                nombreCliente,
                correo,
                telefono
            );

            servicio.registrarComputadora(pc, usuarioLogueado);
            System.out.println("La computadora se ha registrado correctamente.\n");
        } catch (Exception e) {
            System.out.println("Error en el registro. Verifique los datos nuevamente por favor.\n");
        }
        pausa();
    }

    private static void moverComputadora() {
        limpiarPantalla();
        System.out.println("=== Seleccionar la siguiente fase ===");
        System.out.println("1. Mover de Recepción a Inspección");
        System.out.println("2. Mover de Inspección a Reparación o Entrega");
        System.out.println("3. Mover de Reparación a Control de Calidad");
        System.out.println("4. Mover de Control de Calidad a Reparación o Entrega");
        System.out.print("Seleccione opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                moverRecepcionAInspeccion();
                break;
            case 2:
                moverInspeccionAReparacionOEntrega();
                break;
            case 3:
                moverReparacionAControlCalidad();
                break;
            case 4:
                moverCalidadAReparacionOEntrega();
                break;
            default:
                System.out.println("Opción inválida.");
        }
        pausa();
    }

    private static void moverRecepcionAInspeccion() {
        if (servicio.moverAInspeccion(usuarioLogueado)) {
            System.out.println("La computadora fue trasladada al área de Inspección.");
        } else {
            System.out.println("No hay computadoras en el área de Recepción.");
        }
    }

    private static void moverInspeccionAReparacionOEntrega() {
        if (!servicio.hayComputadorasEnInspeccion()) {
            System.out.println("No hay computadoras en el área de Inspección.");
            return;
        }
        System.out.print("¿Se puede reparar la computadora? (s/n): ");
        String resp = scanner.nextLine();

        System.out.print("Ingresar comentario: ");
        String comentario = scanner.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Nombre del técnico que repara: ");
            String tecnico = scanner.nextLine();
            if (servicio.moverAReparacion(usuarioLogueado, comentario, tecnico)) {
                System.out.println("La computadora fue trasladada al área de Reparación.");
            } else {
                System.out.println("Error al trasladar esta computadora.");
            }
        } else {
            if (servicio.moverAEntregaDesdeInspeccion(usuarioLogueado, comentario)) {
                System.out.println("La computadora fue solicitada ser trasladada al área de Entrega.");
            } else {
                System.out.println("Error al trasladar esta computadora.");
            }
        }
    }

    private static void moverReparacionAControlCalidad() {
        if (servicio.moverAControlCalidad(usuarioLogueado)) {
            System.out.println("La computadora fué trasladada al área de QA (Control de Calidad).");
        } else {
            System.out.println("No existe ninguna computadora en el área de Reparación.");
        }
    }

    private static void moverCalidadAReparacionOEntrega() {
        if (!servicio.hayComputadorasEnCalidad()) {
            System.out.println("No hay computadoras en proceso de Control de Calidad.");
            return;
        }
        System.out.print("¿Pasó el control de calidad? (s/n): ");
        String resp = scanner.nextLine();

        System.out.print("Ingresar comentario: ");
        String comentario = scanner.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            if (servicio.moverAEntregaDesdeCalidad(usuarioLogueado, comentario)) {
                System.out.println("Computadora aprobada y lista para ser Entregada.");
            } else {
                System.out.println("Error al mover la computadora.");
            }
        } else {
            if (servicio.moverAReparacionDesdeCalidad(usuarioLogueado, comentario)) {
                System.out.println("La computadora fue rechazada por control de calidad y esta siendo enviada a Reparación nuevamente.");
            } else {
                System.out.println("Error al mover la computadora.");
            }
        }
    }

    private static void mostrarHistorial() {
        limpiarPantalla();
        System.out.print("Ingrese el Service Tag de la computadora: ");
        String serviceTag = scanner.nextLine();

        String historial = servicio.obtenerHistorialCompleto(serviceTag);
        if (historial != null) {
            System.out.println("\n Historial de la computadora:");
            System.out.println(historial);
        } else {
            System.out.println("No se encontró nigún historial para esta computadora.");
        }
        pausa();
    }

    private static void mostrarStatusActual() {
        limpiarPantalla();
        servicio.mostrarStatusActual();
        pausa();
    }

    private static void limpiarPantalla() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Codigo para limpiar pantalla y que se vea más ordenado y amigable el programa
        }
    }

    private static void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}