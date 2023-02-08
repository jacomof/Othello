package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.Ranking;
import com.subgrup13.Dominio.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class VistaConsultarRecord {
    private JPanel panel1;
    private JButton volverButton;
    private JTable table1;
    private ControladorPresentacion ctrlPresentacion;
    private DefaultListModel listModel;
    private JList previouslist;

    /**
     * Constructora, crea una vistaConsultarRecord con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param vCtrlPresentacion Controlador presentación.
     */
    public VistaConsultarRecord(/*Ranking r,*/ ControladorPresentacion
                                                        vCtrlPresentacion){
        //Cambiamos la lista que contiene el ranking para que el texto este centrado horizontalmente en el Widget.
        //DefaultListCellRenderer renderer = (DefaultListCellRenderer) list1.getCellRenderer();
        //renderer.setHorizontalAlignment(SwingConstants.CENTER);
        /*rank = r;*/
        ctrlPresentacion = vCtrlPresentacion;
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                ctrlPresentacion.changePanel("menu_inicio");
            }
        });


    }

    public JPanel getPanel() {
        return panel1;
    }

    public void setTable(Record rec)
    {

        HashMap<String, Integer> map = rec.getRecords();
        DefaultTableModel t = new DefaultTableModel(map.size(), map.size());
        t.setColumnIdentifiers(new String[]{"Nom", "Rècord"});
        int i = 0;
        for(Map.Entry<String, Integer> elem: map.entrySet())
        {

            t.setValueAt(elem.getKey(), i, 0);
            t.setValueAt(elem.getValue(), i, 1);
            i++;

        }

        table1.setModel(t);
        table1.updateUI();


    }
}
