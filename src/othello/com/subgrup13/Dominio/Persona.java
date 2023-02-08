package com.subgrup13.Dominio;


public class Persona extends Jugador {

    private String name;
    private Color color;

    /**
     * Constructora, crea un jugador persona.
     */
    public Persona () {
        setPuntuacion(0);
    }

    /**
     * Constructora, crea un jugador persona con parámetros.
     * @param in_name Nombre del jugador persona.
     * @param in_color Color del jugador persona.
     */
    public Persona (String in_name, Color in_color){
        color = in_color;
        name = in_name;
        setPuntuacion(0);
    }

    /*public Casilla eleccion_maquina_random (Tablero tab) {
        return null;
    }*/

    /*public Casilla eleccion_maquina_informado(Tablero tab, Dificultad d){
        return null;
    }*/

    public Casilla eleccion_maquina(Tablero tab) {return null;}

    /**
     * Devuelve el nombre del jugador persona.
     * @return Nombre del jugador persona.
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve el color del jugador persona.
     * @return Color del jugador persona.
     */
    public Color getColor() {
        return color;
    }

}
