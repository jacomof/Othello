package com.subgrup13.Dominio;

import com.subgrup13.Dominio.IA.MinMax;

import java.util.Random;


public class Maquina extends Jugador{
    private String name;
    private Color color;
    private Dificultad dificultad;

    /**
     * Constructora, crea un jugador máquina.
     */
    public Maquina (){
        name = "";
        setPuntuacion(0);
    }

    /**
     * Constructora, crea un jugador máquina con parámetros.
     * @param in_name Nombre del jugador máquina.
     * @param in_color Color del jugador máquina.
     * @param  dif Dificultad del jugador máquina.
     */
    public Maquina (String in_name, Color in_color, Dificultad dif){
        color = in_color;
        name = in_name;
        setPuntuacion(0);
        dificultad = dif;
    }

    /**
     * Devuelve el nombre del jugador máquina.
     * @return Nombre del jugador máquina.
     */
    public String getName() {
        return name;
    }
    /**
     * Devuelve una casilla random de los posibles movimientos del jugador máquina.
     * @param  tab Tablero que contiene los posibles movimientos.
     * @return Casilla random de los posibles movimientos.
     */
    public Casilla eleccion_maquina_random (Tablero tab) { //elige de las ? casillas, una random.
        Random r = new Random();
        tab.limpiarMovimientosValidos();
        tab.inicializar_direcciones_maquina(color);
        int n = r.nextInt(tab.getDirecciones().size());
        //tab.limpiarMovimientosValidos();
        return tab.getDirecciones().get(n);
    }

    /**
     * Devuelve la casilla del siguiente movimiento del jugador máquina utilizando MinMax según la profundidad.
     * @param tab Tablero que contiene los posibles movimientos.
     * @param prof Profundidad del algoritmo MinMax.
     * @return Casilla del siguiente movimiento.
     */
    public Casilla eleccion_maquina_informado(Tablero tab, int prof){
        tab.limpiarMovimientosValidos();
        MinMax brain = new MinMax(tab, this, prof);
        return brain.getSiguienteMov();

    }

    /**
     * Devuelve la casilla del siguiente movimiento del jugador máquina utilizando MinMax según la dificultad del jugador máquina.
     * @param tab Tablero que contiene los posibles movimientos.
     * @return Casilla del siguiente movimiento.
     */
    public Casilla eleccion_maquina(Tablero tab)
    {

            switch (dificultad) {
                case MUY_FACIL:
                    return eleccion_maquina_random(tab);
                case FACIL:
                    return eleccion_maquina_informado(tab, 3);
                case MEDIA:
                    return eleccion_maquina_informado(tab, 6);
                case DIFICIL:
                    return eleccion_maquina_informado(tab, 9);
                default:
                    System.out.println("Dificultad no disponible!");
                    break;

        }
        return eleccion_maquina_random(tab);
    }

    /**
     * Devuelve el color del jugador máquina.
     * @return Color del jugador máquina.
     */
    public Color getColor() {
        return color;
    }
}