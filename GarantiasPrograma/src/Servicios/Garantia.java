package Servicios;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

import Data.Computadora;

import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Garantia {

    private Queue<Computadora> recepcion = new LinkedList<>();
    private Queue<Computadora> inspeccion = new LinkedList<>();
    private Queue<Computadora> reparacion = new LinkedList<>();
    private Queue<Computadora> controlCalidad = new LinkedList<>();
    private Queue<Computadora> entrega = new LinkedList<>();

    private HashMap<String, LinkedList<String>> historial = new HashMap<>();

    public void registrarComputadora(Computadora pc, String usuario) {
        recepcion.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Recepción", "Registro inicial");
    }

    public boolean moverAInspeccion(String usuario) {
        if (recepcion.isEmpty()) return false;

        Computadora pc = recepcion.poll();
        inspeccion.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Inspección", "Movimiento de Recepción a Inspección");
        return true;
    }

    public boolean hayComputadorasEnInspeccion() {
        return !inspeccion.isEmpty();
    }

    public boolean moverAReparacion(String usuario, String comentario, String tecnico) {
        if (inspeccion.isEmpty()) return false;

        Computadora pc = inspeccion.poll();
        reparacion.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Reparación", comentario + " | Técnico: " + tecnico);
        return true;
    }

    public boolean moverAEntregaDesdeInspeccion(String usuario, String comentario) {
        if (inspeccion.isEmpty()) return false;

        Computadora pc = inspeccion.poll();
        entrega.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Entrega", comentario + " | Sin reparación");
        return true;
    }

    public boolean moverAControlCalidad(String usuario) {
        if (reparacion.isEmpty()) return false;

        Computadora pc = reparacion.poll();
        controlCalidad.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Control de Calidad", "Movimiento de Reparación a Control de Calidad");
        return true;
    }

    public boolean hayComputadorasEnCalidad() {
        return !controlCalidad.isEmpty();
    }

    public boolean moverAEntregaDesdeCalidad(String usuario, String comentario) {
        if (controlCalidad.isEmpty()) return false;

        Computadora pc = controlCalidad.poll();
        entrega.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Entrega", comentario + " | Aprobado en Control de Calidad");
        return true;
    }

    public boolean moverAReparacionDesdeCalidad(String usuario, String comentario) {
        if (controlCalidad.isEmpty()) return false;

        Computadora pc = controlCalidad.poll();
        reparacion.add(pc);
        agregarHistorial(pc.getServiceTag(), usuario, "Reparación", comentario + " | Rechazado por Control de Calidad");
        return true;
    }

    // Obtener historial
    public String obtenerHistorialCompleto(String serviceTag) {
        LinkedList<String> registros = historial.get(serviceTag);
        if (registros == null) return null;

        StringBuilder sb = new StringBuilder();
        for (String linea : registros) {
            sb.append(linea).append("\n");
        }
        return sb.toString();
    }

    // Obtener historial desde el archivo de texto
    public String obtenerHistorialDesdeArchivo(String serviceTag) {
        String rutaArchivo = "historiales/" + serviceTag + ".txt";
        try {
            return new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            return null;
        }
    }

    private void agregarHistorial(String serviceTag, String usuario, String fase, String comentario) {
        String fecha = LocalDate.now().toString();
        String registro = usuario + "|" + fecha + "|" + fase + "|" + comentario;

        historial.putIfAbsent(serviceTag, new LinkedList<>());
        historial.get(serviceTag).add(registro);

        String rutaArchivo = "historiales/" + serviceTag + ".txt";

        try {
            Files.createDirectories(Paths.get("historiales"));
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true));
            bw.write(registro);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error guardando historial en archivo: " + e.getMessage());
        }
    }

    public void mostrarStatusActual() {
        System.out.println("Status actual de las colas:");
        System.out.println("Recepción: " + recepcion.size());
        System.out.println("Inspección: " + inspeccion.size());
        System.out.println("Reparación: " + reparacion.size());
        System.out.println("Control de Calidad: " + controlCalidad.size());
        System.out.println("Entrega: " + entrega.size());
    }
}