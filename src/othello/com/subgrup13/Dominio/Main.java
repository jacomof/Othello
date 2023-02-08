package com.subgrup13.Dominio;

import com.subgrup13.Presentacion.ControladorPresentacion;
import com.subgrup13.Presentacion.VistaMenuInicio;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] Argv) throws InterruptedException, IOException {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        //new VistaMenuInicio().hacerVisible();
                        ControladorPresentacion ctrlPresentacion = null;
                        try {
                            ctrlPresentacion = new ControladorPresentacion();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ctrlPresentacion.inicializarVistaMI();
                    }
                });
    }
}
