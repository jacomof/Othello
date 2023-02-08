package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VistaGuardarPartida {

    private ControladorPresentacion ctrlPresentacion;
    private JButton guardarPartidaButton;
    private JPanel panel1;
    private JButton sortirSenseGuardarButton;

    /**
     * Constructora, crea una vistaGuardarPartida con los datos pertinentes del controlador de presentacion pasado como parametro
     * @param vCtrlPresentacion Controlador presentación.
     */
    public VistaGuardarPartida(ControladorPresentacion vCtrlPresentacion) {
        ctrlPresentacion = vCtrlPresentacion;
    }

    public static void main(String[] args) {
    }
}
