package com.subgrup13.Drivers;

import com.subgrup13.Dominio.Casilla;
import com.subgrup13.Dominio.Color;
import com.subgrup13.Drivers.Stubs.StubCasilla;
import com.subgrup13.Drivers.Stubs.StubJugador;
import com.subgrup13.Dominio.Tablero;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverTablero {

    private static StubJugador stubJugador =  new StubJugador();
    private static StubCasilla stubCasilla =  new StubCasilla();

    private  static  Tablero InicializarTableroStub(){
        Boolean[] normas = new Boolean[]{true, false, false};
        Tablero t = new Tablero(normas);
        //t.setJugadores(stubJugador.GetJugador(Color.WHITE), stubJugador.GetJugador(Color.BLACK));

        return t;
    }

    private  static  void testConstructor1(){
        System.out.println("Iniciar testConstructor1");
        Tablero t = new Tablero();
        System.out.println("Terminado testConstructor1");
    }

    private  static  void testConstructor2(){
        System.out.println("Iniciar testConstructor2");
        Boolean[] normas = new Boolean[]{true, false, false};
        Tablero t = new Tablero(normas);
        System.out.println("Terminado testConstructor2");
    }

    private  static  void testSetJugadores(){
        System.out.println("Iniciar testSetJugadores");
        Boolean[] normas = new Boolean[]{true, false, false};
        Tablero t = new Tablero(normas);
        //t.setJugadores(stubJugador.GetJugador(Color.WHITE), stubJugador.GetJugador(Color.BLACK));
        System.out.println("Terminado testSetJugadores");
    }

    private  static  void testUpdateRonda(){
        System.out.println("Iniciar testUpdateRonda");
        Tablero t = InicializarTableroStub();
        t.updateRonda();
        System.out.println("Terminado testUpdateRonda");
    }

    private  static  void testInicializarDirecciones(){
        System.out.println("Iniciar testInicializarDirecciones");
        Tablero t = InicializarTableroStub();
        t.inicializar_direcciones(Color.BLACK);
        System.out.println("Terminado testInicializarDirecciones");
    }

    private  static  void testInicializarDireccionesMaquina(){
        System.out.println("Iniciar testInicializarDireccionesMaquina");
        Tablero t = InicializarTableroStub();
        ArrayList<Casilla> d = t.inicializar_direcciones_maquina(Color.WHITE);
        System.out.println("Tamaño direcciones: " + d.size());
        System.out.println("Terminado testInicializarDireccionesMaquina");
    }

    private  static  void testGetDirecciones(){
        System.out.println("Iniciar testGetDirecciones");
        Tablero t = InicializarTableroStub();
        ArrayList<Casilla> d = t.getDirecciones();
        System.out.println("Tamaño direcciones: " + d.size());
        System.out.println("Terminado testGetDirecciones");
    }

    private  static  void testMovimientoValido(){
        System.out.println("Iniciar testMovimientoValido");
        Tablero t = InicializarTableroStub();
        //Boolean m = t.movimientoValido(stubCasilla.getCasilla(), Color.WHITE);
        //System.out.println("Resultado: " + m);
        System.out.println("Terminado testMovimientoValido");
    }

    private  static  void testDireccionValida(){
        System.out.println("Iniciar testDireccionValida");
        Tablero t = InicializarTableroStub();
        //Boolean m = t.direccionValida(stubCasilla.getCasilla(), Color.WHITE ,0, 0);
        //System.out.println("Resultado: " + m);
        System.out.println("Terminado testDireccionValida");
    }

    private  static  void testLimpiarMovimientosValidos(){
        System.out.println("Iniciar testDireccionValida");
        Tablero t = InicializarTableroStub();
        t.inicializar_direcciones(Color.WHITE);
        System.out.println("Direcciones antes de limpiar: " + t.getDirecciones().size());
        //t.limpiarMovimientosValidos();
        System.out.println("Direcciones después de limpiar: " + t.getDirecciones().size());
        System.out.println("Terminado testDireccionValida");
    }

    private  static  void testEsValido(){
        System.out.println("Iniciar testEsValido");
        Tablero t = InicializarTableroStub();
        Boolean b = t.esValido("L3");
        System.out.println("Resultado: " + b);
        System.out.println("Terminado testEsValido");
    }

    private  static  void testColocarFicha(){
        System.out.println("Iniciar testColocarFicha");
        Tablero t = InicializarTableroStub();
        //t.colocarFicha("L3", Color.WHITE);
        System.out.println("Terminado testColocarFicha");
    }

    private  static  void testColocarFichaMaquina(){
        System.out.println("Iniciar testColocarFichaMaquina");
        Tablero t = InicializarTableroStub();
        t.colocarFicha_maquina(stubCasilla.getCasilla(), Color.WHITE);
        System.out.println("Terminado testColocarFichaMaquina");
    }

    private  static  void testGirarFichas(){
        System.out.println("Iniciar testGirarFicha");
        Tablero t = InicializarTableroStub();
        t.colocarFicha_maquina(stubCasilla.getCasilla(), Color.WHITE);
        //t.girarFichas(stubCasilla.getCasilla());
        System.out.println("Terminado testGirarFicha");
    }

    private  static  void testGiroDir(){
        System.out.println("Iniciar testGiroDir");
        Tablero t = InicializarTableroStub();
        //t.giroDir(stubCasilla.getCasilla(), 0,0);
        System.out.println("Terminado testGiroDir");
    }

    private  static  void testGirarFicha(){
        System.out.println("Iniciar testGirarFicha");
        Tablero t = InicializarTableroStub();
        t.colocarFicha_maquina(stubCasilla.getCasilla(), Color.WHITE);
        t.girarFicha(0,0, Color.WHITE);
        System.out.println("Terminado testGirarFicha");
    }

    private  static  void testPrintTablero(){
        System.out.println("Iniciar testPrintTablero");
        Tablero t = InicializarTableroStub();
        //t.printTablero();
        System.out.println("Terminado testPrintTablero");
    }

    private  static  void testTableroLleno(){
        System.out.println("Iniciar testTableroLleno");
        Tablero t = InicializarTableroStub();
        Boolean b = t.tableroLleno();
        System.out.println("Resultado: " + b);
        System.out.println("Terminado testTableroLleno");
    }

    private  static  void testSaltaTurno(){
        System.out.println("Iniciar testSaltaTurno");
        Tablero t = InicializarTableroStub();
        Boolean b = t.saltaTurno(Color.WHITE);
        System.out.println("Resultado: " + b);
        System.out.println("Terminado testSaltaTurno");
    }

    private  static  void testGetNumNegras(){
        System.out.println("Iniciar testGetNumNegras");
        Tablero t = InicializarTableroStub();
        double b = t.getNumNegras();
        System.out.println("Resultado: " + b);
        System.out.println("Terminado testGetNumNegras");
    }

    private  static  void testGetNumBlancas(){
        System.out.println("Iniciar testGetNumBlancas");
        Tablero t = InicializarTableroStub();
        double b = t.getNumBlancas();
        System.out.println("Resultado: " + b);
        System.out.println("Terminado testGetNumBlancas");
    }

    public static void main(String[] args) {
        System.out.println("Driver de la clase Casilla:");

        Boolean salir = false;
        try {
            int num;
            while (!salir) {
                System.out.println("Escoge la funcion a probar:");
                System.out.println("1: Test constructor1");
                System.out.println("2: Test constructor2");
                System.out.println("3: Test set jugadores");
                System.out.println("4: Test update ronda");
                System.out.println("5: Test inicializar direcciones");
                System.out.println("6: Test inicializar direcciones máquina");
                System.out.println("7: Test get direcciones");
                System.out.println("8: Test movimiento válido");
                System.out.println("9: Test direcciones válidas");
                System.out.println("10: Test limpiar movimientos válidos");
                System.out.println("11: Test es válido");
                System.out.println("12: Test colocar ficha");
                System.out.println("13: Test colocar ficha máquina");
                System.out.println("14: Test girar fichas");
                System.out.println("15: Test giro dir");
                System.out.println("16: Test girar ficha");
                System.out.println("17: Test print tablero");
                System.out.println("18: Test tablero lleno");
                System.out.println("19: Test salta turno");
                System.out.println("20: Test get num negras");
                System.out.println("21: Test get num blancas");
                System.out.println("0: Salir");

                Scanner in = new Scanner(System.in);
                num = in.nextInt();

                switch (num){
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        testConstructor1();
                        break;
                    case 2:
                        testConstructor2();
                        break;
                    case 3:
                        testSetJugadores();
                        break;
                    case 4:
                        testUpdateRonda();
                        break;
                    case 5:
                        testInicializarDirecciones();
                        break;
                    case 6:
                        testInicializarDireccionesMaquina();
                        break;
                    case 7:
                        testGetDirecciones();
                        break;
                    case 8:
                        testMovimientoValido();
                        break;
                    case 9:
                        testDireccionValida();
                        break;
                    case 10:
                        testLimpiarMovimientosValidos();
                        break;
                    case 11:
                        testEsValido();
                        break;
                    case 12:
                        testColocarFicha();
                        break;
                    case 13:
                        testColocarFichaMaquina();
                        break;
                    case 14:
                        testGirarFichas();
                        break;
                    case 15:
                        testGiroDir();
                    case 16:
                        testGirarFicha();
                        break;
                    case 17:
                        testPrintTablero();
                        break;
                    case 18:
                        testTableroLleno();
                        break;
                    case 19:
                        testSaltaTurno();
                        break;
                    case 20:
                        testGetNumNegras();
                        break;
                    case 21:
                        testGetNumBlancas();
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
