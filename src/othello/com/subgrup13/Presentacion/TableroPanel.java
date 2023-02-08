package com.subgrup13.Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TableroPanel extends JPanel {

    protected CeldaBoton[][] misCeldas;

    protected Color celda_vacia;
    protected Color celda_negra;
    protected Color celda_blanca;

    ControladorPresentacion ctrlPresentacion;

    public TableroPanel(ControladorPresentacion ctrlP) {
        super();

        ctrlPresentacion = ctrlP;

        celda_vacia = new Color(0, 153, 0);
        celda_blanca = new Color(255, 255, 255);
        celda_negra = new Color(0, 0, 0);

        if(!(this instanceof TableroPanelJugar))
            inicializarCeldas();

        this.setLayout(new GridLayout(8, 8));

    }

    public void inicializarCeldas() {
        if (misCeldas == null) misCeldas = new CeldaBoton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                CeldaBoton baux = new CeldaBoton(ctrlPresentacion, i, j, celda_vacia, celda_negra, celda_blanca);
                baux.setVisible(true);
                misCeldas[i][j] = baux;
                add(baux);
            }
        }
    }

    public CeldaBoton[][] getCeldas()
    {

        return misCeldas;

    }

    public void  setCelda(int i, int j, int est)
    {

        CeldaBoton caux = misCeldas[i][j];
        caux.setEstado(est);

    }

    public void setCeldas(int[][] newCeldas)
    {

        for(int i= 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j)
                setCelda(i, j, newCeldas[i][j]);

    }

    public  void clearTablero()
    {
        for(int i = 0; i < 8; ++i)
            for(int j = 0; j < 8; ++j){

                setCelda(i, j, 0);

            }

    }


}
