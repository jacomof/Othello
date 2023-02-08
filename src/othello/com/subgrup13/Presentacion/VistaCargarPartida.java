package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.IOException;

public class VistaCargarPartida {

    private ControladorPresentacion ctrlPresentacion;
    private JList list1;
    private JPanel panelcarg;
    private JButton acceptarButton;
    private JButton volverButton;
    private JScrollPane scrollPane;
    private JFrame frameVista;
    private JButton borrarButton;
    private Boolean v = false;

    /**
     * Constructora, crea una vistaCargarPartida con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param vCtrlPresentacion Controlador presentación.
     */
    public VistaCargarPartida(ControladorPresentacion vCtrlPresentacion) {

        frameVista = new JFrame();
        frameVista.setContentPane(panelcarg);
        frameVista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ctrlPresentacion = vCtrlPresentacion;
        frameVista.setVisible(true);

        DefaultListModel <String> listPartidas = new DefaultListModel<>();

        for (int i = 0; i < ctrlPresentacion.numPartidas(); ++i) {
            String jugadores = ctrlPresentacion.getInfoGuardado(i);
            String[] jugador = jugadores.split(" ");
            listPartidas.addElement("  " + jugador[0] + " VS " + jugador[1] + "        Ronda:" + jugador[2]); // + Fecha y hora
        }
        initComponents();
        list1.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        list1.setModel(listPartidas);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    public void actualizarLista() {
        DefaultListModel<String> listPartidas = new DefaultListModel<>();
        for (int i = 0; i < ctrlPresentacion.numPartidas(); ++i) {
            String jugadores = ctrlPresentacion.getInfoGuardado(i);
            String[] jugador = jugadores.split(" ");
            listPartidas.addElement("  " + jugador[0] + " VS " + jugador[1] + "        Ronda:" + jugador[2]); // + Fecha y hora
        }
        initComponents();
        list1.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        list1.setModel(listPartidas);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


        private void initComponents() {

        acceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ActionPerformed(evt);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctrlPresentacion.changePanel("menu_inicio");
            }
        });


        borrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (selectProblem() > -1) {
                    try {
                        v = true;
                        ctrlPresentacion.borrarPartida(selectProblem());
                        actualizarLista();
                        JLabel error = new JLabel("Partida esborrada correctament");
                        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.WARNING_MESSAGE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (!v){
                    JLabel error = new JLabel("Has d'escollir que partida vols esborrar");
                    JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.WARNING_MESSAGE);
                    v = true;
                }
            }
        });
    }

    private void ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, InterruptedException {
        if (selectProblem() > -1) {
            ctrlPresentacion.importDataPartida(selectProblem());
            ctrlPresentacion.changePanel("vista_partida");
            ctrlPresentacion.play(ctrlPresentacion.getTipoPartida());

        }
        else {
            JLabel error = new JLabel("Has d'escollir que partida vols jugar");
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int selectProblem() {
        int selectedIndex = list1.getSelectedIndex();
        return selectedIndex;
    }

    public JPanel getPanel(){return panelcarg;}

}
