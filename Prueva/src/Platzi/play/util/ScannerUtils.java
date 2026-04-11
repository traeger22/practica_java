package Platzi.play.util;

import Platzi.play.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);

    public static String capturarTexto (String mensaje){
        System.out.println(mensaje + ": ");
        return scanner.nextLine();
    }

    public static int capturarNumero(String mensaje){
        System.out.println(mensaje + ": ");

        while (!scanner.hasNextInt()){
            System.out.println("el dato es invalido " + mensaje + ": ");
            scanner.nextLine();
        }
        int dato = scanner.nextInt();
        scanner.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");
        while (!scanner.hasNextDouble()){
            System.out.println("el dato es invalido " + mensaje + ": ");
            scanner.next();
        }

        double dato = scanner.nextDouble();
        scanner.nextLine();
        return dato;
    }

    public static Genero capturarGenero(String mensaje) {
        while (true) {
            System.out.println(mensaje + "Opciones");
            for (Genero genero : Genero.values()){
                System.out.println(" _ " + genero.name());
            }
            System.out.println("cual quieres: ");
            String entrada = scanner.nextLine().toUpperCase();

            try {
                return Genero.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Genero no aceptado");
            }
        }
    }
}
