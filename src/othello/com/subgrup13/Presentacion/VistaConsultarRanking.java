package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.Ranking;
import com.subgrup13.Dominio.Record;
import com.subgrup13.Dominio.ValorRanking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VistaConsultarRanking {

    private JPanel panel1;
    private JButton volverButton;
    private JTable table1;
    private JMenuBar bar;
    private JFrame frameVista;
    private ControladorPresentacion ctrlPresentacion;

    Ranking rank;

    /**
     * Constructora, crea una vistaConsultarRanking con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param vCtrlPresentacion Controlador presentación.
     */
    public VistaConsultarRanking(ControladorPresentacion vCtrlPresentacion){

        ctrlPresentacion = vCtrlPresentacion;
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                ctrlPresentacion.changePanel("menu_inicio");
            }
        });

    }

    public static void main(String[] args) {

        final ControladorPresentacion[] ct = new ControladorPresentacion[1];
            javax.swing.SwingUtilities.invokeLater (
                    new Runnable() {
                        public void run() {
                            ControladorPresentacion ctrlp = null;
                            try {
                                ctrlp = new ControladorPresentacion();
                                ct[0] = ctrlp;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                                ctrlp.changePanel("vista_consultar_ranking");
                        }});
    }


    public JPanel getPanel(){
        return panel1;
    }

    public void setTable(Ranking rank)
    {

        ArrayList<ValorRanking> list = rank.getRanking();
        DefaultTableModel t = new DefaultTableModel(list.size(), list.size());
        t.setColumnIdentifiers(new String[]{"Nom", "Puntuació"});
        int i = 0;
        for(ValorRanking elem: list)
        {

            t.setValueAt(elem.nombre, i, 0);
            t.setValueAt(elem.puntuacion, i, 1);
            i++;

        }

        table1.setModel(t);
        table1.updateUI();


    }
}
