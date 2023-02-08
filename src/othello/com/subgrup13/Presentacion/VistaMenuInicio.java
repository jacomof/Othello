package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class VistaMenuInicio {
    private ControladorPresentacion ctrlPresentacion;
    private JPanel panelMI;
    private JButton recordsButton;
    private JButton rankingsButton;
    private JButton novaPartidaButton;
    private JButton carregaPartidaButton;

    /**
     * Constructora, crea una vistaMenuInicio con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param cCtrlPresentacion Controlador presentación.
     */
    public VistaMenuInicio(ControladorPresentacion cCtrlPresentacion) {

        ctrlPresentacion = cCtrlPresentacion;

        novaPartidaButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                ctrlPresentacion.changePanel("vista_configurar_partida");

            }
        });



        rankingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ctrlPresentacion.sincronizacionVistaMI_a_VistaCRanking();
            }
        });

        recordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ctrlPresentacion.sincronizacionVistaMI_a_VistaCRecord();
            }
        });

        carregaPartidaButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                ctrlPresentacion.actualizarListpartidas();
            }
        });
    }

    public void setVistaMenuInicio(ControladorPresentacion vCtrlPresentacion) {
        ctrlPresentacion = vCtrlPresentacion;
    }

    public JPanel getPanel(){return panelMI;}

}
