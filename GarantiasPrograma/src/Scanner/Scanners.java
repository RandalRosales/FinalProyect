package Scanner;

import java.util.Scanner;

public class Scanners {

    public static int numeroentero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Asegurate de que tus valores sean numéricos.: ");
            }
        }
    }

    public static String numeros(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if (input.matches("\\d+")) return input;
                System.out.print("Asegurate de que tus valores sean numéricos. : ");
        }
    }

    public static String arrobacorreo(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            if (input.contains("@")) return input;
            System.out.print(" Correo inválido. Intente de nuevo: ");
        }
    }
}