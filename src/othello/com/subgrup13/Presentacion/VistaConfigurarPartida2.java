package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.Dificultad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConfigurarPartida2 {
    private ControladorPresentacion ctrlPresentacion;
    private JPanel panelCP2;
    private JComboBox blanquesCB;
    private JComboBox negresCB;
    private JComboBox difficultyWCB;
    private JComboBox difficultyBCB;
    private JTextField nameWTF;
    private JTextField nameBTF;
    private JLabel difficultyMW;
    private JLabel difficultyMB;
    private JButton seguent2Button;
    private JPanel panelJ1;
    private JPanel panelJ2;
    private JButton enrereButton;

    private String nom1;
    private String nom2;
    /**
     * Constructora, crea una vistaConfigurarPartida2 con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param cCtrlPresentacion Controlador presentación.
     */
    public VistaConfigurarPartida2(ControladorPresentacion cCtrlPresentacion) {
        ctrlPresentacion = cCtrlPresentacion;

        blanquesCB.addItem("Persona");
        blanquesCB.addItem("Màquina");

        negresCB.addItem("Persona");
        negresCB.addItem("Màquina");

        seguent2Button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                if (negresCB.getSelectedItem().toString() == "Persona")
                    nom1 = nameBTF.getText();

                if (blanquesCB.getSelectedItem().toString() == "Persona")
                    nom2 = nameWTF.getText();

                if (nom1.isBlank() || nom2.isBlank())
                    JOptionPane.showMessageDialog(null, "Has d'introduir un nom per a cada jugador.");

                else if (nom1.equals(nom2))
                    JOptionPane.showMessageDialog(null, "Els jugadors no poden tenir el mateix nom.");
                else {
                    String auxi1 = negresCB.getSelectedItem().toString();
                    String auxi2 = blanquesCB.getSelectedItem().toString();
                    configurarTipoPartida(negresCB.getSelectedItem().toString(), blanquesCB.getSelectedItem().toString());
                    Dificultad aux1 = stringToDificultad(nom1);
                    Dificultad aux2 = stringToDificultad(nom2);

                    ctrlPresentacion.setJugadores(nom1, nom2, stringToDificultad(nom1), stringToDificultad(nom2));
                    ctrlPresentacion.changePanel("vista_eleccion_tablero");

                        //ctrlPresentacion.sincronizacionVistaPartida(ctrlPresentacion.getTipoPartida());

                }
            }
        });
        negresCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (negresCB.getSelectedItem().toString() == "Màquina") {
                    panelJ1.remove(nameBTF);
                    panelJ1.add(difficultyBCB);
                    nom1 = difficultyBCB.getSelectedItem().toString();
                    difficultyBCB.setVisible(true);

                    difficultyMB.setText("Nivell:");
                } else {
                    panelJ1.remove(difficultyBCB);
                    panelJ1.add(nameBTF);
                    nom1 = "J1";
                    nameBTF.setVisible(true);

                    difficultyMB.setText("Nom:");
                }
            }
        });

        difficultyWCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    nom2 = difficultyWCB.getSelectedItem().toString();

            }
        });

        difficultyBCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                nom1 = difficultyBCB.getSelectedItem().toString();

            }
        });

        blanquesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blanquesCB.getSelectedItem().toString() == "Màquina") {
                    panelJ2.remove(nameWTF);
                    panelJ2.add(difficultyWCB);
                    nom2 = difficultyWCB.getSelectedItem().toString();
                    difficultyWCB.setVisible(true);

                    difficultyMW.setText("Nivell:");
                } else {
                    panelJ2.remove(difficultyWCB);
                    panelJ2.add(nameWTF);
                    nom2 = "J2";
                    nameWTF.setVisible(true);

                    difficultyMW.setText("Nom:");
                }
            }
        });

        difficultyBCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((blanquesCB.getSelectedItem().toString() == "Màquina") &&
                        (difficultyWCB.getSelectedItem().toString() == difficultyBCB.getSelectedItem().toString())) {
                    difficultyWCB.setSelectedIndex((difficultyWCB.getSelectedIndex() + 1) % 4);
                }
            }
        });

        difficultyWCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((negresCB.getSelectedItem().toString() == "Màquina") &&
                        (difficultyBCB.getSelectedItem().toString() == difficultyWCB.getSelectedItem().toString())) {
                    difficultyBCB.setSelectedIndex((difficultyBCB.getSelectedIndex() + 1) % 4);
                }
            }
        });
        enrereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameBTF.setText("");
                nameWTF.setText("");
                negresCB.setSelectedIndex(0);
                blanquesCB.setSelectedIndex(0);
                ctrlPresentacion.changePanel("vista_configurar_partida");
            }
        });
    }

    public JPanel getPanel() {
        return panelCP2;
    }

    private void configurarTipoPartida(String J1, String J2) {
        if (J1.equals("Persona") && J2.equals("Persona")) {
            ctrlPresentacion.setTipo(1);
        } else if (J1.equals("Persona") && J2.equals("Màquina")) {
            ctrlPresentacion.setTipo(2);
        } else if (J1.equals("Màquina") && J2.equals("Persona")) {
            ctrlPresentacion.setTipo(3);
        } else {
            ctrlPresentacion.setTipo(4);
        }
    }

    private Dificultad stringToDificultad(String nivel) {
        if (nivel.equals("Fàcil")) return Dificultad.MUY_FACIL;
        if (nivel.equals("Mitjana")) return Dificultad.FACIL;
        if (nivel.equals("Difícil")) return Dificultad.MEDIA;
        if(nivel.equals("Expert")) return Dificultad.DIFICIL;
        return Dificultad.MEDIA;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        nameBTF = new JFormattedTextField();
        panelJ1 = new JPanel();
        panelJ1.setLayout(new CardLayout());
        panelJ1.add(nameBTF);
        panelJ1.setVisible(true);

        nameWTF = new JFormattedTextField();
        panelJ2 = new JPanel();
        panelJ2.setLayout(new CardLayout());
        panelJ2.add(nameWTF);
        panelJ2.setVisible(true);

        difficultyBCB = new JComboBox();
        difficultyBCB.addItem("Fàcil");
        difficultyBCB.addItem("Mitjana");
        difficultyBCB.addItem("Difícil");
        difficultyBCB.addItem("Expert");

        difficultyWCB = new JComboBox();
        difficultyWCB.addItem("Fàcil");
        difficultyWCB.addItem("Mitjana");
        difficultyWCB.addItem("Difícil");
        difficultyWCB.addItem("Expert");

        difficultyWCB.setSelectedIndex(1);
    }
}
