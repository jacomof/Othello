package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CeldaBoton extends JButton {
    /**
     * El tablero gráfico visible para el usuario es en realidad una matriz de botones interactivos
     * para responder a sus inputs. El objetivo de esta clase es extender la clase de Swing JButton para ajustarla a las funcionalidades
     * necesarias para este tablero gráfico.
     */
    protected int fila;
    protected int col;
    protected int estado;
    protected Color vacio;
    protected Color negra;
    protected Color blanca;
    protected ControladorPresentacion ctrlPresentacion;
    //ImageIcon candidata;

    /**
     * Contructora de una celda del tablero grafico utilizado para configurar el tablero de una partida.
     * @param f entero, fila de la celda en el tablero.
     * @param c entero, columna de la celda en el tablero.
     * @param v ImageIcon, uno de los iconos correspondiente al estado 0 del boton.
     * @param n ImageIcon, uno de los iconos correspondiente al estado 1 del boton.
     * @param b ImageIcon, uno de los iconos correspondiente al estado 2 del boton.
     */
    public CeldaBoton(ControladorPresentacion ctrlP, int f, int c, Color v, Color n, Color b){
        super();//"\u26AB");
        vacio = v;
        negra = n;
        blanca = b;
        //candidata = cand;

        ctrlPresentacion = ctrlP;
        fila = f;
        col = c;
        estado = 0;
        addListeners();

        this.setFont(new Font("GNU Unifont", Font.PLAIN, 62));
        this.setBackground(new Color(0, 153, 0));
        this.setText("\u26AB");
        this.setForeground(vacio);

    }

    public CeldaBoton(Color v, Color n, Color b)
    {

        super();
        vacio = v;
        negra = n;
        blanca = b;

        fila = 0;
        col = 0;
        estado = 0;

        this.setFont(new Font("GNU Unifont", Font.PLAIN, 62));
        this.setBackground(new Color(0, 153, 0));
        this.setText("\u26AB");
        this.setForeground(vacio);

    }
    /**
     * Getter del atributo fila, que identifica la posición en el eje Y de la celda en el tablero (siendo la primera fila la superior verticalmente,
     * y descendiendo según aumenta).
     */

    public int getFila() {return fila;}
    /**
     * Getter del atributo columna, que identifica la posición en el eje X de la celda en el tablero (las posiciones en este eje incrementan hacia la derecha).
     */
    public int getColumna() {return col;}

    /**
     * Getter del atributo estado.
     * @return retorna un entero que representa el estado del botón.
     */
    public int getEstado() {return estado;}

    /**
     * Setter para cambiar el estado de una celda del tablero de la GUI. Se actualiza el entero estado que representa su
     * concepto homonimo y el icono del boton.
     * @param est entero que identifica el estado del boton. Si es 1, la celda tendrá la imagen de una pieza negra, y si es 2 la celda tendrá
     *            la imagen de una pieza blanca. Con cualquier otro valor la celda queda con la imagen de celda vacia.
     */
    public void setEstado(int est) {

        switch (est){

            //case 1:  this.setIcon(negra); estado = 1;  break;
            case 1:  this.setForeground(negra); estado = 1;  break;
            case 2: this.setForeground(blanca); estado = 2; break;
            default: this.setForeground(vacio); estado = 0; break;

        }


    }

    private void addListeners(){

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                switch (estado){

                    case 0: setEstado(1); break;
                    case 1: setEstado(2);  break;
                    default: setEstado(0); break;

                }



            }
        });

    }
}
