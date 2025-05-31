package Servicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Data.FasesHistory;

public class ManejoArchivos {
    public static void guardarHistorial(String ruta, Map<String, FasesHistory> historiales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (FasesHistory h : historiales.values()) {
                writer.write(h.getHistorialCompleto());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}