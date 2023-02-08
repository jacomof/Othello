package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaPartidaTerminada {

    private ControladorPresentacion ctrlPresentacion;
    int puntuacionj1;
    int puntuacionj2;
    String nom1;
    String nom2;
    int recordj1;
    int recordj2;
    Boolean j1winner;
    Boolean j2winner;
    String textoganador;
    private JPanel PanelPartidaTerminada;
    private JButton desarRankingButton;
    private JButton tornarAJugarButton;
    private JButton sortirAlMenúButton;
    private JLabel GuanyadorTexto;
    private JButton desarRecordButton;

    /**
     * Constructora, crea una vistaPartidaTerminada con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param cCtrlPresentacion Controlador presentación.
     */
    public VistaPartidaTerminada(ControladorPresentacion cCtrlPresentacion)  throws InterruptedException{

        ctrlPresentacion = cCtrlPresentacion;
        desarRecordButton.setVisible(false);
        desarRankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipo = ctrlPresentacion.getTipoPartida(); //solo rankings de humanos
                if (tipo ==1 ){
                    ctrlPresentacion.updateRankingPres(nom1,puntuacionj1);
                    ctrlPresentacion.updateRankingPres(nom2,puntuacionj2);

                }else if (tipo ==2 ){
                    ctrlPresentacion.updateRankingPres(nom1,puntuacionj1);
                }else if (tipo == 3){
                    ctrlPresentacion.updateRankingPres(nom2,puntuacionj2);
                }
            }
        });


        tornarAJugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentacion.reset_a_configurar();

            }
        });

        sortirAlMenúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ctrlPresentacion.reset_a_menu();
            }
        });

        desarRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int tipo = ctrlPresentacion.getTipoPartida(); //solo record de humanos
                if (tipo ==1 ){
                    if (j1winner) ctrlPresentacion.updateRecordsPres(nom1,puntuacionj1);
                    if (j2winner) ctrlPresentacion.updateRecordsPres(nom2,puntuacionj2);
                }else if (tipo ==2 ){
                    if (j1winner) ctrlPresentacion.updateRecordsPres(nom1,puntuacionj1);
                }else if (tipo == 3) {
                    if (j2winner) ctrlPresentacion.updateRecordsPres(nom2, puntuacionj2);
                }
            }
        });
    }




    public JPanel getPanel(){return PanelPartidaTerminada ;}

    public void setall(String nom1, String nom2, int puntuacio1, int puntuacio2){
        this.nom1 = nom1;
        this.nom2 = nom2;
        this.puntuacionj1 = puntuacio1;
        this.puntuacionj2 = puntuacio2;

        if (puntuacionj1>puntuacionj2){
            j1winner=true;
            j2winner=false;
        } else if (puntuacionj2>puntuacionj1){
            j1winner=false;
            j2winner=true;
        } else if (puntuacionj2 == puntuacionj1) {
            j1winner=true;
            j2winner=true;
        }

        if (j1winner &&  !j2winner){
            textoganador =  nom1+" ha guanyat la partida, amb una puntació de "+puntuacionj1;
        } else if (j2winner &&!j1winner){
            textoganador = nom2+" ha guanyat la partida, amb una puntuació de "+puntuacionj2;
        }else if(j2winner && j1winner){
            textoganador = "Hi ha hagut un empat entre "+nom1+" i "+nom2+" en "+puntuacionj1+" punts ";
        }

        GuanyadorTexto.setText(textoganador);

        if (j1winner) {
            if (ctrlPresentacion.getRecordByNamePres(nom1) != null) {
                recordj1 = ctrlPresentacion.getRecordByNamePres(nom1);
            } else recordj1=0;
                if (puntuacionj1 > recordj1) {
                    desarRecordButton.setVisible(true);
                }

        }
        if (j2winner) {
            if (ctrlPresentacion.getRecordByNamePres(nom2)!=null) {
                recordj2 = ctrlPresentacion.getRecordByNamePres(nom2);
            } else recordj2=0;
                if (puntuacionj2 > recordj2) {
                    desarRecordButton.setVisible(true);
                }
            }
        }




}
