package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConfigurarPartida {

    private ControladorPresentacion ctrlPresentacion;

    private JPanel panelCP;
    private JComboBox comboBox1;
    private JButton classicButton;
    private JCheckBox diagonalCheckBox;
    private JCheckBox horitzontalCheckBox;
    private JCheckBox verticalCheckBox;
    private JButton seguentButton;
    private JLabel info;
    private JButton anteriorButton;

    /**
     * Constructora, crea una vistaConfigurarPartida con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param vCtrlPresentacion Controlador presentación.
     */
    public VistaConfigurarPartida(ControladorPresentacion vCtrlPresentacion) {
        ctrlPresentacion = vCtrlPresentacion;
        classicButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                verticalCheckBox.setSelected(true);
                horitzontalCheckBox.setSelected(true);
                diagonalCheckBox.setSelected(true);
            }
        });

        seguentButton.addActionListener(new ActionListener() {
            boolean corr = false;
            public void actionPerformed (ActionEvent e) {
                corr = configNormas();
                if (corr) {
                    ctrlPresentacion.changePanel("vista_configurar_partida_2");
                } else {
                    JOptionPane.showMessageDialog(null, "Has d'escollir com a mínim una opció.");

                }
            }
        });

        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verticalCheckBox.setSelected(false);
                horitzontalCheckBox.setSelected(false);
                diagonalCheckBox.setSelected(false);
                ctrlPresentacion.changePanel("menu_inicio");
            }
        });
    }

    public JPanel getPanel() {
        return panelCP;
    }

    private boolean configNormas() {
        Boolean[] normaspartida = {verticalCheckBox.isSelected(), horitzontalCheckBox.isSelected(), diagonalCheckBox.isSelected()};
        ctrlPresentacion.confNormas(normaspartida);
        return normaspartida[0] || normaspartida[1] || normaspartida[2];
    }
}
