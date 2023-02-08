package com.subgrup13.Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TableroPanelJugar extends TableroPanel{

    private Color celda_candidata;


    TableroPanelJugar(ControladorPresentacion ctrlP){
        super(ctrlP);

        celda_candidata = new Color(0, 255, 0);

        inicializarCeldas();
    }

    public void inicializarCeldas()
    {

        if(misCeldas == null) misCeldas = new CeldaBoton[8][8];

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    CeldaBoton baux = new CeldaBotonJugar(ctrlPresentacion, i, j, celda_vacia, celda_negra, celda_blanca, celda_candidata);
                    baux.setVisible(true);
                    baux.setForeground(celda_vacia);
                    misCeldas[i][j] = baux;
                    add(baux);
                }
            }

        }

        public static void main(String[] args)
        {
            javax.swing.SwingUtilities.invokeLater (
                    new Runnable() {

                        ControladorPresentacion myctrl;

                        public void run() {
                            try {
                                myctrl = new ControladorPresentacion();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            myctrl.changePanel("vista_configurar_tablero");

                        }
                    });

        }

        public CeldaBotonJugar consultaCelda(int i, int j)
        {

            return (CeldaBotonJugar) misCeldas[3][3];

        }
}
