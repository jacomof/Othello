package com.subgrup13.Drivers;
import com.subgrup13.Dominio.Color;
import com.subgrup13.Dominio.Jugador;
import com.subgrup13.Dominio.Persona;

import java.util.Scanner;

public class DriverJugador {

    private static void testConstructor() {
        System.out.println("Iniciar test constructora");
        System.out.println("Color del jugador es blanco");
        Jugador j = new Persona("Jugador1", Color.WHITE);
        System.out.println("Terminado testConstructor");
    }

    private static void testGetPuntuacion() {
        System.out.println("Iniciar testGetPuntuacion");
        Jugador j = new Persona("Jugador1", Color.WHITE);
        System.out.println("Puntuación: " + j.getPuntuacion());
        System.out.println("Terminado testGetPuntuacion");
    }

    private static void testSetPuntuacion() {
        System.out.println("Iniciar testSetPuntuacion");
        Jugador j = new Persona("Jugador1", Color.WHITE);
        System.out.println("Puntuación inicial: " + j.getPuntuacion());
        j.setPuntuacion(10);
        System.out.println("Puntuación final: " + j.getPuntuacion());
        System.out.println("Terminado testSetPuntuacion");
    }

    private static void testGetMiColor() {
        System.out.println("Iniciar testgetMiColor");
        Jugador j = new Persona("Jugador1", Color.WHITE);
        System.out.println("Color: " + j.getColor());
        System.out.println("Terminado testgetMiColor");
    }

    public static void main(String[] args) {
        System.out.println("Driver de la clase Jugador:");

        boolean salir = false;
        try {
            int num;
            while (!salir) {
                System.out.println("Escoge la funcion a probar:");
                System.out.println("1: Test constructor");
                System.out.println("2: Test getter puntuación");
                System.out.println("3: Test set puntuación");
                System.out.println("4: Test getter color");
                System.out.println("5: Salir");

                Scanner in = new Scanner(System.in);
                num = in.nextInt();

                switch (num){
                    case 1:
                        testConstructor();
                        break;
                    case 2:
                        testGetPuntuacion();
                        break;
                    case 3:
                        testSetPuntuacion();
                        break;
                    case 4:
                        testGetMiColor();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("La opción no existe");
                        break;
                }

                if(!salir){
                    System.out.println("Presione enter para continuar...");
                    Scanner n = new Scanner(System.in);
                    n.hasNextLine();
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
