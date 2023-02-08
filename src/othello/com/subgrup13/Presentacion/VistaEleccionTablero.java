package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaEleccionTablero {
    private ControladorPresentacion ctrlPresentacion;
    private JButton botonTableroClasico;
    private JButton botonConfigurarTablero;
    private JPanel panelEleccionTablero;
    private JButton atrasButton;

    /**
     * Constructora, crea una vistaEleccionTablero con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param CtrlPresentacion Controlador presentación.
     */
    public VistaEleccionTablero(ControladorPresentacion CtrlPresentacion) {
        ctrlPresentacion = CtrlPresentacion;

        botonTableroClasico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacion.changePanel("vista_partida");

                    ctrlPresentacion.updateTableroGrafico(ctrlPresentacion.getEstadoTablero());
                    ctrlPresentacion.play(ctrlPresentacion.getTipoPartida());

            }
        });

        botonConfigurarTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacion.sincronizacionVistaEleccionT_a_VistaConfigurarT();
            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacion.changePanel("vista_configurar_partida_2");
            }
        });
    }

    public JPanel getPanel() {
        return panelEleccionTablero;
    }




}
