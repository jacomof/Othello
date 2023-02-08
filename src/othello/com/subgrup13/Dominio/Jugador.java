package com.subgrup13.Dominio;

public abstract class Jugador {

        private int puntuacion;

        //public Jugador() {
        //}

        //public Jugador(Color in_color){
        //    mi_color = in_color;
        //}

        /**
         * Devuelve la puntuación del jugador.
         * @return Puntuación del jugador.
         */
        public int getPuntuacion() { return puntuacion; }

        /**
         * Cambia la puntuación del jugador.
         * @param new_p Nueva puntuación del jugador.
         */
        public void setPuntuacion(int new_p){
            puntuacion = new_p;
        }

        //public Color getMiColor(){
         //       return mi_color;
        //}

    //public abstract Casilla eleccion_maquina_random (Tablero tab);

    //public abstract Casilla eleccion_maquina_informado(Tablero tab, int prof);

    /**
     * Devuelve la casilla del siguiente movimiento del jugador máquina.
     */
    public abstract  Casilla eleccion_maquina(Tablero tab);

    /**
     * Devuelve el nombre del jugador.
     */
    public abstract String getName();

    /**
     * Devuelve el color del jugador.
     */
    public abstract Color getColor();

    public void mover_ficha(int x, int y){
        return;
    }
}