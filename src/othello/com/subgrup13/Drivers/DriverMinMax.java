package com.subgrup13.Drivers;

import com.subgrup13.Dominio.Casilla;
import com.subgrup13.Dominio.Color;
import com.subgrup13.Dominio.Jugador;
import com.subgrup13.Dominio.Tablero;
import com.subgrup13.Drivers.Stubs.*;
import com.subgrup13.Dominio.IA.MinMax;


public class DriverMinMax {

    private static void testConstructor()
    {
        /*Dado que la constructora solo asigna dos variables, el test solo comprueba que el programa no tenga un error de ejecución al
        *llamar a la constructora
        */
        System.out.println("Iniciar test constructora");
        StubJugador jugS = new StubJugador();
        StubTablero tabS = new StubTablero();
        Jugador p = jugS.GetJugador(Color.BLACK);
        Tablero t = tabS.GetTablero();
        MinMax minimaxi = new MinMax(t, p, 5);
        System.out.println("testConstructor resultados: se ha creado correctamente");


    }

    private static void testGetSiguienteMov()
    {
        //Comprueba si GetSiguienteMov se ejecuta correctamente, no evalua calidad de solución.
        System.out.println("Iniciar test constructora");
        StubTablero tabS = new StubTablero();
        StubJugador jugS = new StubJugador();
        Tablero tab = tabS.GetTablero();
        Jugador p = jugS.GetJugador(Color.WHITE);
        MinMax minimaxi = new MinMax(tab, p, 5);
        Casilla movi = minimaxi.getSiguienteMov();
        System.out.println("testGetSiguienteMov se ha ejecutado con éxito (no hay error de ejecución). Resultado: ");
        if(movi != null) System.out.println("Movimiento posible encontrado!");
        else System.out.println("Movimiento no encontrado!");

    }
}
