package com.subgrup13.Dominio.IA;
import com.subgrup13.Dominio.Tablero;
import com.subgrup13.Dominio.Jugador;
import com.subgrup13.Dominio.Casilla;
import com.subgrup13.Dominio.Color;
import java.util.ArrayList;



public class MinMax {
    private Tablero tablero;
    private Jugador jugador;
    private int MAX_PROFUNDIDAD = 5;
    /**
    *Constructora de la clase. Asigna los parámetros de la función a los atributos correspondientes de la clase
    * @param t tablero inicial del que parte el algoritmo para encontrar el movimiento más adecuado. Se almacena en el atributo tablero
    * @param j jugador cuyo movimiento o jugada óptimo desea ser conocido
    */
    public MinMax(Tablero t, Jugador j, int prof){

        tablero = t;
        jugador = j;
        MAX_PROFUNDIDAD = prof;
    }

    /**
     * Devuelve la heurística o valor de una configuración de tablero determinado. Este valor es utilizado para decidir que estados son más
     * beneficiosos que otros. En el caso de esta clase, la heursística consiste en la diferencia de fichas de los jugadores (positiva cuando favorezca al jugador del atributo de la clase
     * y negativa cuando favorezca al contrincante)
     * @param tab tablero cuya heurística desea ser determinada
     * @return retorna la heurística o valor del tablero tab
     */
    private double heuristica(Tablero tab){

        double diff = tab.getNumBlancas() - tab.getNumNegras();
        return jugador.getColor() == Color.WHITE ? diff : -diff;

    }

    /**
     * Encuentra recursivamente, hasta una profundidad MAX_PROFUNDIDAD, el mínimo heurístico de todas los obtenibles a partir del estado actual tab.
     * Cuando se llama a esta función se asume la existencia de un contrincante hipótetico que debe elegir el movimiento
     * que menos convenga al jugador actual.
     * @param tab tablero a partir del cual se quiere calcular la jugada con puntuación mínima
     * @param prof entero que indica la profundidad alcanzada hasta el momento por el algoritmo MinyMaX.
     * @return retorna el heurístico mínimo obtenible por el jugador desde el estado tab.
     */
    private double min(Tablero tab, int prof, double alfa, double beta)
    {

        if(prof >= MAX_PROFUNDIDAD)
            return heuristica(tab);
        Color enemycolor = jugador.getColor() == Color.WHITE ?  Color.BLACK : Color.WHITE ;
        ArrayList<Casilla> sucesores = tab.inicializar_direcciones_maquina(enemycolor);
        if(sucesores.isEmpty()) {
            return heuristica(tab);
        }
        for(Casilla cas:sucesores){

            Tablero t_aux = new Tablero(tab);
            t_aux.colocarFicha_maquina(cas, enemycolor);
            beta = Math.min(max(t_aux, prof+1, alfa, beta), beta);

            if(alfa >= beta)
                return alfa;

        }
        return beta;

    }

    /**
     * Encuentra recursivamente, hasta una profundidad MAX_PROFUNDIDAD, el mayor heurístico obtenible a partir del estado actual tab.
     * Cuando se llama a esta función se asume que el valor de la jugada que se quiere determinar  corresponde a un movimiento del jugador actual.
     * @param tab tablero a partir del cual se quiere calcular la jugada con puntuación mínima
     * @param prof entero que indica la profundidad alcanzada hasta el momento por el algoritmo MinyMaX.
     * @return retorna el valor del heurístico máximo obtenible por el jugador desde el estado tab.
     */

    private double max(Tablero tab, int prof, double alfa, double beta)
    {
        if(prof >= MAX_PROFUNDIDAD)
            return heuristica(tab);
        ArrayList<Casilla> sucesores = tab.inicializar_direcciones_maquina(jugador.getColor());
        if(sucesores.isEmpty()) {
            return heuristica(tab);
        }
        for(Casilla cas:sucesores){

            Tablero t_aux = new Tablero(tab);
            t_aux.colocarFicha_maquina(cas, jugador.getColor());
            alfa = Math.max( min(t_aux, prof+1, alfa, beta), alfa);

            if(alfa >= beta)
                return beta;
        }
        return alfa;

    }

    /**
     * Encuentra recursivamente, hasta una profundidad MAX_PROFUNDIDAD, la jugada más beneficiosa para el jugador actual.
     * Cuando se llama a esta función se asume que el valor de la jugada que se quiere determinar  corresponde a un movimiento del jugador actual.
     * @return retorna la casilla correspondiente a la jugada de puntuación máxima
     */

    private Casilla minimax()
    {
        Color color_aux = jugador.getColor();
        ArrayList<Casilla> sucesores = tablero.inicializar_direcciones_maquina(jugador.getColor());
        if(sucesores.isEmpty()) System.out.println("Estan vacios los suceros, soy IA.");
        Casilla cmax = null;
        boolean first = true;
        double alfa = -Double.MAX_VALUE;
        double beta = Double.MAX_VALUE;
        for(Casilla cas:sucesores){
            Tablero t_aux = new Tablero(tablero);
            t_aux.colocarFicha_maquina(cas, jugador.getColor());
            double vtmp = min(t_aux, 1, alfa, beta);

            if(vtmp > alfa) {

                cmax = cas;
                alfa = vtmp;

            }
        }
        return cmax;

    }

    /**
     * @return retorna la casilla que, según la IA, es la mejor jugada que se puede realizar. Si no existe ningún movimiento posible, retorna null.
     */
    public Casilla getSiguienteMov(){

        return minimax();

    }

    public void setMaxProfundidad(int new_max)
    {

        MAX_PROFUNDIDAD = new_max;

    }

}
