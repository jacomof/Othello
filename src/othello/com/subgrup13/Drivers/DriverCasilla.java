package com.subgrup13.Drivers;
import com.subgrup13.Dominio.Casilla;
import com.subgrup13.Dominio.Color;
import java.util.Scanner;

public class DriverCasilla {

    private static void testConstructorParam() {
        System.out.println("Iniciar test constructora parametro");
        System.out.println("Los parametros son 1, 2 y blanco");
        Casilla c = new Casilla(1, 2, Color.WHITE);
        System.out.println("testConstructor resultado: " + c.fila + " " + c.columna);
    }

    private static void testCopiar() {
        System.out.println("Iniciar testCopiar");
        Casilla c = new Casilla(1, 2, Color.BLACK);
        Casilla p = c.copy();
        System.out.println("Casilla original: " + c.color );
        System.out.println("Casilla copiada: " + p.color );
        System.out.println("Terminado testCopiar");
    }

    private  static  void testCambiarColor(){
        System.out.println("Iniciar testCambiarColor");
        Casilla c = new Casilla(1, 2, Color.BLACK);
        System.out.println("Color inicial: " + c.getColor());
        c.cambiarColorCasilla(Color.WHITE);
        System.out.println("Color final: " + c.getColor());
        System.out.println("Terminado testCambiarColor");
    }

    private  static  void testImprimirColor(){
        System.out.println("Iniciar testImprimirColor");
        Casilla c = new Casilla(1, 2, Color.BLACK);
        c.imprimir_color();

        System.out.println("\nTerminado testImprimirColor");
    }

    private  static  void testGetColor(){
        System.out.println("Iniciar testGetColor");
        Casilla c = new Casilla(1, 2, Color.BLACK);
        Color color = c.getColor();
        System.out.println("Color: " + color);
        System.out.println("Terminado testGetColor");
    }

    public static void main(String[] args) {
        System.out.println("Driver de la clase Casilla:");

        Boolean salir = false;
        try {
            int num;
            while (!salir) {
                System.out.println("Escoge la funcion a probar:");
                System.out.println("1: Test constructor");
                System.out.println("2: Test cambiar color");
                System.out.println("3: Test getter color");
                System.out.println("4: Test imprimir color");
                System.out.println("5: Test copiar casilla");
                System.out.println("6: Salir");

                Scanner in = new Scanner(System.in);
                num = in.nextInt();

                switch (num){
                    case 1:
                        testConstructorParam();
                        break;
                    case 2:
                        testCambiarColor();
                        break;
                    case 3:
                        testGetColor();
                        break;
                    case 4:
                        testImprimirColor();
                        break;
                    case 5:
                        testCopiar();
                        break;
                    case 6:
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
