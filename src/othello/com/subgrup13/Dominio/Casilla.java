package com.subgrup13.Dominio;

/**
 *
 */
public class Casilla {

    public Color color;
    public int fila;
    public int columna;

    /**
     * Constructora, crea una casilla vacía.
     */
    public Casilla() {
        this.color = Color.EMPTY;
    }

    /**
     * Constructora, crea una casilla con diferentes parametros.
     * @param fila Fila de la casilla.
     * @param columna Columna de la casilla.
     * @param color Color para la casilla.
     */
    public Casilla(int fila, int columna, Color color) {
        this.fila=fila;
        this.columna=columna;
        this.color=color;
    }

    /**
     * Cambia el color de la casilla.
     * @param color Nuevo color para la casilla.
     */
    public void cambiarColorCasilla(Color color) {
        this.color = color;
    }

    /**
     * Devuelve el color de la casilla.
     * @return Color de la casilla.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Imprime el color de la casilla por el terminal.
     */
    public void imprimir_color() {
        if (this.color == Color.EMPTY) System.out.print("• ");
        else if (this.color == Color.BLACK) System.out.print("X ");
        else if (this.color == Color.WHITE) System.out.print("O ");
        else System.out.print("? ");
    }

    /**
     * Devuelve el estado de la casilla.
     * @return Integer que representa el estado de la casilla.
     */
    public int getEstado() {
        if (this.color == Color.WHITE) return 0;
        if (this.color == Color.BLACK) return 1;
        if (this.color == Color.EMPTY) return 2;
        return 3;
    }

    public String getCoord() {
        char[] coordX = {'1', '2', '3', '4', '5', '6', '7', '8'};
        char[] coordY = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        return new StringBuilder().append(coordY[this.fila]).append(coordX[this.columna]).toString();
    }

    /**
     * Hace una copia de una casilla existente.
     * @return Devuelve una 'shallow copy' de la casilla.
     */
    public Casilla copy()
    {
        Casilla c_aux = new Casilla(fila, columna, color);
        return c_aux;
    }

}
