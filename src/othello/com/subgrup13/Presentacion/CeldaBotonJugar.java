package com.subgrup13.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CeldaBotonJugar extends CeldaBoton{

    private Color candidata;


    public CeldaBotonJugar(ControladorPresentacion ctrlP, int f, int c, Color v, Color n, Color b, Color cand){
        super(v, n, b);
        candidata = cand;
        fila = f;
        col = c;
        estado = 0;
        ctrlPresentacion = ctrlP;
        addListeners();



    }

    public void setEstado(int est) {


        CeldaBotonJugar cd = this;

        switch (est){

            case 1:  cd.setForeground(negra); estado = 1;  break;
            case 2: cd.setForeground(blanca); estado = 2; break;
            case 3: cd.setForeground(candidata); estado = 3; break;
            default: cd.setForeground(vacio); estado = 0; break;

        }
        //repaint();
    }

    private void addListeners(){

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(estado == 3) {
                    ctrlPresentacion.wakeUpDominio(fila, col);
                }

                //falta notificarle al talbero real los cambios

            }
        });

    }

    public void setCell()
    {

        setEstado(1);

    }

}
