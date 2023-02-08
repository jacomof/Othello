package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class VistaPartida {

    private JPanel myJPanel = new JPanel();
    private GridLayout myLayout = new GridLayout(8, 8);
    private JButton volverB = new JButton();
    private JPanel volverP = new JPanel();
    private TableroPanelJugar board;
    private ControladorPresentacion ctrlPresentacion;
    int turno = 1;

    /**
     * Crea una vistaPartida para jugar una partida. Utiliza tableroPanelJugar para implementar el tablero del juego y tiene una serie
     * de botones para salir de la partida y guardarla.
     * @param ctrlP
     * @throws InterruptedException
     */
    public VistaPartida(ControladorPresentacion ctrlP) throws InterruptedException {

        ctrlPresentacion = ctrlP;
        volverB.setText("Volver");
        volverB.setVisible(true);
        volverB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Object[] options = {"Guardar i sortir","Sortir sense guardar"
                };
                int n = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                        "Que vol fer?",
                        "Sortir de la partida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,//do not use a custom Icon
                        options,//the titles of buttons
                        options[1]);//default button title
                if (n == JOptionPane.YES_OPTION){
                    try {
                        ctrlPresentacion.storeDataPartida(Color.BLACK);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ctrlPresentacion.changePanel("menu_inicio");
                }
                else if (n == JOptionPane.NO_OPTION)ctrlPresentacion.changePanel("menu_inicio");
            }
        });
        JPanel volverP2 = new JPanel();
        volverP2.setLayout(new BoxLayout(volverP2, BoxLayout.Y_AXIS));
        volverP2.add(volverB);
        JSeparator sep = new JSeparator();
        sep.setOrientation(SwingConstants.VERTICAL);
        volverP2.add(sep);
        volverP2.setVisible(true);
        volverP.setLayout(new BoxLayout(volverP, BoxLayout.X_AXIS));
        volverP.add(volverP2);
        myJPanel.setLayout(new BoxLayout(myJPanel, BoxLayout.X_AXIS));
        myJPanel.add(volverP);
        board = new TableroPanelJugar(ctrlPresentacion);
        myJPanel.add(board);

    }
    public JPanel getPanel() {
        return myJPanel;
    }
    public TableroPanelJugar getTableroGrafico() {return board;}


}