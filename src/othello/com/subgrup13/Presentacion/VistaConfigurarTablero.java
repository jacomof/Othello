package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaConfigurarTablero {


    private JPanel myJPanel = new JPanel();

        private JButton volverB = new JButton();
        private JButton seguirB = new JButton();
        private JButton borrarB = new JButton();
        private JPanel volverP = new JPanel();

        private TableroPanel board;
        private ControladorPresentacion ctrlPresentacion;

    /**
     * Crea una VistaConfigurarTablero para configurar el tablero que se usará en la partida.
     * @param ctrlP referencia al controlador de presentación.
     */
    public VistaConfigurarTablero(ControladorPresentacion ctrlP)
        {


            ctrlPresentacion = ctrlP;
            configurarBotones();
            JPanel volverP2 = new JPanel();
            volverP2.setBackground(new Color(29, 163, 29));
            volverP2.setLayout(new BoxLayout(volverP2, BoxLayout.Y_AXIS));
            volverP2.add(volverB);
            JSeparator sep = new JSeparator();
            sep.setOrientation(SwingConstants.VERTICAL);
            volverP2.setBackground(Color.GREEN);
            volverP2.add(sep);
            volverP2.add(borrarB);
            volverP2.setVisible(true);
            volverP.setLayout(new BoxLayout(volverP, BoxLayout.X_AXIS));
            volverP.add(volverP2);
            volverP.setBackground(new Color(29, 163, 29));
            myJPanel.setLayout(new BoxLayout(myJPanel, BoxLayout.X_AXIS));
            myJPanel.add(volverP);
            board = new TableroPanel(ctrlPresentacion);
            board.setBackground(Color.GREEN);
            JPanel seguirYGuardar = new JPanel();
            seguirYGuardar.setLayout(new BoxLayout(seguirYGuardar, BoxLayout.X_AXIS));
            seguirYGuardar.add(Box.createHorizontalStrut(40));
            seguirYGuardar.add(board);
            seguirYGuardar.add(Box.createHorizontalStrut(40));
            seguirYGuardar.add(seguirB);
            seguirYGuardar.setBackground(Color.GREEN);
            myJPanel.add(seguirYGuardar);
            myJPanel.setOpaque(false);
            myJPanel.setBackground(Color.GREEN);
            myJPanel.setVisible(true);
            ctrlPresentacion.ajusta_ventana();




        }

    private void configurarBotones() {

        volverB.setText("Volver");
        volverB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ctrlPresentacion.changePanel("vista_eleccion_tablero");

            }
        });

        seguirB.setText("Seguir y guardar");
        seguirB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(ctrlPresentacion.setEstadoTableroPres(board.getCeldas()))
                {

                    ctrlPresentacion.changePanel("vista_eleccion_tablero");

                }else{

                    JOptionPane.showMessageDialog(null, "El tauler no té moviments possibles! " +
                            "Has de introduir un tauler jugable.");
                }

            }
        });

        borrarB.setText("Borrar Tablero");
        borrarB.setForeground(new Color(200, 0, 0, 226));
        borrarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.clearTablero();

            }
        });


    }

    public JPanel getPanel(){return myJPanel;}

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
                            myctrl.sincronizacionVistaEleccionT_a_VistaConfigurarT();

                        }
                    });
        }
}


