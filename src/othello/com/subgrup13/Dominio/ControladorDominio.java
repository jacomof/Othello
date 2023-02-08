package com.subgrup13.Dominio;

import com.subgrup13.Persistencia.ControladorPersistencia;
import com.subgrup13.Persistencia.FicheroPartidas;
import com.subgrup13.Presentacion.ControladorPresentacion;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ControladorDominio {

    private Tablero tablero;
    private Ranking ranking;
    private Record record;
    private ControladorPersistencia ctrlPersistencia;
    private Color colorUltimoTurno;
    private ControladorPresentacion ctrlPresentacion;

    public ControladorDominio(ControladorPresentacion ctrlP) throws IOException {
        this.tablero = new Tablero(new Boolean[]{true, true, true});
        this.ranking = new Ranking();
        this.record = new Record();
        this.ctrlPersistencia = new ControladorPersistencia();
        this.ctrlPresentacion = ctrlP;
        importDataRanking();
        importDataRecord();


    }

    public void vaciar_tablero(){
        this.tablero= new Tablero();

    }

    public void setTablero_normas(Boolean[] normaspartida){ this.tablero = new Tablero(normaspartida);}
    
    public void setJugadores (String in_namej1, String in_namej2, Dificultad dificultad1, Dificultad dificultad2){ this.tablero.setJugadores(in_namej1, in_namej2, dificultad1, dificultad2);}
    
    public Tablero getTablero() { return this.tablero;}

    public int getTipo(){
        return (int) this.tablero.getTipoPartidaJugadores();
    }

    public void setTipo(int in_tipo) {
        tablero.setTipoPartidaJugadores(in_tipo);
    }

    public int getPuntuacionB() {
        return (int) this.tablero.getNumNegras();
    }

    public int getPuntuacionW() {
        return (int) this.tablero.getNumBlancas();
    }

    public String getNameB() {
        return this.tablero.getJugador1().getName();
    }

    public String getNameW() {
        return this.tablero.getJugador2().getName();
    }

    public Ranking getRanking() { return this.ranking;}

    public Record getRecord() {return this.record;}

    public Integer getRecordByNameDom(String n_persona){
        return record.getRecordByName(n_persona);
    }

    public void updateRecordsDom(String jugador, Integer puntuacion) {

        record.updateRecords(jugador,puntuacion);
        storeDataRecord();

    }

    public void updateRankingDom(String n, int p) {
        ValorRanking vr = new ValorRanking(n,p);
        ranking.updateRanking(vr);
        storeDataRanking();


    }

    public int[][] getEstadoTablero() {
        Casilla[][] casTab;
        casTab = tablero.getTableroMat();
        int[][] intTab = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                intTab[i][j] = colorToEstado(casTab[i][j].getColor());
            }
        }
        return intTab;
    }

    public void updateTableroGrafico()
    {

        ctrlPresentacion.updateTableroGrafico(getEstadoTablero());

    }

    public int colorToEstado(Color color) {
        if (color == Color.EMPTY)
            return 0;
        if (color == Color.BLACK)
            return 1;
        if (color == Color.WHITE)
            return 2;
        return 3;
    }

    public Color estadoToColor(int estado) {
        if (estado == 0)
            return Color.EMPTY;
        if (estado == 1)
            return Color.BLACK;
        if (estado == 2)
            return Color.WHITE;
        return Color.EMPTY_MOVE;
    }

    public boolean setEstadoTablero(int[][] intTab) {
        //ArrayList<Casilla> casTab = new ArrayList<Casilla>();
        Casilla[][] casTab = new Casilla[8][8];
        Tablero t = new Tablero(tablero.getNormas());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casTab[i][j] = new Casilla(i, j, estadoToColor(intTab[i][j]));
            }
        }
        t.setMatTablero(casTab);
        if(!t.tableroLleno())
        {
            tablero.setMatTablero(casTab);
            return true;
        }
        return false;

    }


    public void storeDataRecord(){

        ctrlPersistencia.saveRecord(record);

    }

    public void storeDataRanking(){

        ctrlPersistencia.saveRanking(ranking);

    }

    public void importDataPartida(Integer id) throws IOException {
        String partida = ctrlPersistencia.loadPartida(id);

        String[] partidaData = partida.split(" ");

        Casilla[][] casillas = new Casilla[8][8];
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                Integer ind = row*8+col;
                if(partidaData[0].charAt(ind) == 'X') {
                    casillas[row][col] = new Casilla(row,col,Color.BLACK);
                    System.out.print(partidaData[0].charAt(ind));
                }
                else if(partidaData[0].charAt(ind) == 'O') {
                    casillas[row][col] = new Casilla(row,col,Color.WHITE);
                    System.out.print(partidaData[0].charAt(ind));
                }
                else {
                    casillas[row][col] = new Casilla(row,col,Color.EMPTY);
                    System.out.print(partidaData[0].charAt(ind));
                }
            }
        }

        Integer tipoPartidaJugadores = Integer.parseInt(partidaData[1]);

        String jugador1 = partidaData[2];
        String jugador2 = partidaData[3];

        Integer ronda = Integer.parseInt(partidaData[4]);

        Boolean[] normas = {false,false,false};
        String[] normasData = partidaData[5].split(",");
        for(int i = 0; i < normasData.length; ++i) {
            if (normasData[i] == "true") {
                normas[i] = true;
            }
        }

        Integer[] n_fichas = {2,2};
        String[] n_fichasData = partidaData[6].split(",");
        n_fichas[0] = (int) Double.parseDouble(n_fichasData[0]);
        n_fichas[1] = (int) Double.parseDouble(n_fichasData[1]);

        colorUltimoTurno = Color.valueOf(partidaData[7]);
        this.tablero = new Tablero(casillas, tipoPartidaJugadores, jugador1, jugador2, ronda, normas, n_fichas, Dificultad.MUY_FACIL, Dificultad.MEDIA);

        System.out.println("set tablero");
    }

    public void storeDataPartida(Color turno) throws IOException {
        String partida;
        String temp = "";

        Casilla[][] casillas = tablero.getTableroMat();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(casillas[row][col].color == Color.BLACK) {
                    temp = temp + "X";
                }
                else if(casillas[row][col].color == Color.WHITE) {
                    temp = temp + "O";
                }
                else if(casillas[row][col].color == Color.EMPTY_MOVE) {
                    temp = temp + "?";
                }
                else temp = temp + "•";
            }
        }

        partida = temp + " ";

        partida = partida + String.valueOf(tablero.getTipoPartidaJugadores()) + " " + tablero.getJugador1() + " " + tablero.getJugador1() + " " + String.valueOf(tablero.getRonda()) + " ";

        Boolean[] norm = tablero.getNormas();
        temp = norm[0] + "," + norm[1] + "," + norm[2];
        partida = partida + temp + " " + String.valueOf(tablero.getNumNegras()) + "," + String.valueOf(tablero.getNumBlancas());

        ctrlPersistencia.savePartida(partida,turno.name());
    }

    public void borrarPartida(int id) throws IOException {

        ctrlPersistencia.borrarPartida(id);

    }

    public void importDataRecord() {

        ctrlPersistencia.loadRecord(record);

    }

    public void importDataRanking()  {

        ctrlPersistencia.loadRanking(ranking);

    }

    public String getInfoGuardado(Integer id) {
        return ctrlPersistencia.getInfo(id);
    }

    public  Integer numPartidas() {
        return ctrlPersistencia.numPartidas();
    }

    public void gameLoopHM()
    {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                updateTableroGrafico();
            }
        });


        Thread gameLogicThread = new Thread(){
            boolean finalizar = false;
            public void run() {
                while(!finalizar){

                    synchronized (ctrlPresentacion) {
                        if(tablero.tableroLlenoNegras()) {
                            if(tablero.tableroLlenoBlancas()) finalizar = true;
                            else{

                                tablero.juegaTurnoMaquina(Color.WHITE);

                            }
                        }else{
                            tablero.inicializar_direcciones(Color.BLACK);
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    updateTableroGrafico();
                                }
                            });
                            try {ctrlPresentacion.wait();}
                            catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                            int[] selev = ctrlPresentacion.getCasilla_seleccionada();
                            tablero.juegaTurno(Color.BLACK, selev[0], selev[1]);
                            if(!tablero.tableroLlenoBlancas())tablero.juegaTurnoMaquina(Color.WHITE);

                        }



                    }
                }
                tablero.inicializar_direcciones(Color.WHITE);
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        updateTableroGrafico();
                    }
                });

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ctrlPresentacion.sincronizacion_VistaPartidaterminada();
                    }
                });




            }


        };
        gameLogicThread.start();

    }

    public void gameLoopMH()
    {

        Thread gameLogicThread = new Thread(){

            boolean finalizar = false;

            public void run() {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        updateTableroGrafico();
                    }
                });
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("No pude esperar");
                }
                while(!finalizar){

                    synchronized (ctrlPresentacion) {

                        if(tablero.tableroLlenoNegras()) {
                            if(tablero.tableroLlenoBlancas()){
                                finalizar = true;
                            }else{
                                System.out.println("Salto Turno");
                                tablero.inicializar_direcciones(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {ctrlPresentacion.wait();}
                                catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                                System.out.println("Me desperte!!");

                                int[] selev = ctrlPresentacion.getCasilla_seleccionada();
                                tablero.juegaTurno(Color.WHITE, selev[0], selev[1]);
                            }
                        }else{
                            tablero.juegaTurnoMaquina(Color.BLACK);
                            if(!tablero.tableroLlenoBlancas()){

                                tablero.inicializar_direcciones(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {ctrlPresentacion.wait();}
                                catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                                System.out.println("Me desperte!!");

                                int[] selev = ctrlPresentacion.getCasilla_seleccionada();
                                tablero.juegaTurno(Color.WHITE, selev[0], selev[1]);

                            }
                        }
                    }
                }

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        updateTableroGrafico();
                    }
                });
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ctrlPresentacion.sincronizacion_VistaPartidaterminada();
                    }
                });

            }


        };
        gameLogicThread.start();

    }

    public void gameLoopHH()
    {

        Thread gameLogicThread = new Thread(){

            boolean finalizar = false;

            public void run() {

                while(!finalizar){

                    synchronized (ctrlPresentacion) {
                        //Mueven las negras
                        if(tablero.tableroLlenoNegras()) {
                            if(tablero.tableroLlenoBlancas()){
                                finalizar = true;
                            }else{
                                System.out.println("Salto Turno");
                                tablero.inicializar_direcciones(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {ctrlPresentacion.wait();}
                                catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                                System.out.println("Me desperte!!");

                                int[] selev = ctrlPresentacion.getCasilla_seleccionada();
                                tablero.juegaTurno(Color.WHITE, selev[0], selev[1]);
                            }
                        }else{
                            tablero.inicializar_direcciones(Color.BLACK);
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    updateTableroGrafico();
                                }
                            });
                            try {ctrlPresentacion.wait();}
                            catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                            System.out.println("Me desperte!!");

                            int[] selev = ctrlPresentacion.getCasilla_seleccionada();
                            tablero.juegaTurno(Color.BLACK, selev[0], selev[1]);
                            if(!tablero.tableroLlenoBlancas()){

                                tablero.inicializar_direcciones(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {ctrlPresentacion.wait();}
                                catch(IllegalMonitorStateException | InterruptedException e1){System.out.println("Error al esperar!!");}
                                System.out.println("Me desperte!!");

                                int[] selev2 = ctrlPresentacion.getCasilla_seleccionada();
                                tablero.juegaTurno(Color.WHITE, selev2[0], selev2[1]);



                            }else System.out.println("Salto turno2");
                        }
                    }
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        updateTableroGrafico();
                    }
                });
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ctrlPresentacion.sincronizacion_VistaPartidaterminada();
                    }
                });

            }


        };
        gameLogicThread.start();


    }

    public void gameLoopMM()
    {

        Thread gameLogicThread = new Thread(){

            boolean finalizar = false;

            public void run() {

                while(!finalizar){

                    synchronized (ctrlPresentacion) {
                        //Mueven las negras
                        if(tablero.tableroLlenoNegras()) {
                            if(tablero.tableroLlenoBlancas()){
                                finalizar = true;
                            }else{
                                tablero.juegaTurnoMaquina(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                System.out.println("Saltar turno");

                            }
                        }else{
                            tablero.juegaTurnoMaquina(Color.BLACK);
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    updateTableroGrafico();
                                }
                            });
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            if(!tablero.tableroLlenoBlancas()){

                                tablero.juegaTurnoMaquina(Color.WHITE);
                                SwingUtilities.invokeLater(new Runnable() {

                                    @Override
                                    public void run() {
                                        updateTableroGrafico();
                                    }
                                });
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }



                            }else System.out.println("Salto turno2");
                        }
                    }
                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        updateTableroGrafico();
                    }
                });
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ctrlPresentacion.sincronizacion_VistaPartidaterminada();
                    }
                });

            }


        };
        gameLogicThread.start();

    }

    public void playOthello(int tipo) {
        switch (tipo) {
            case 1:
                gameLoopHH();
                break;
            case 2:
                gameLoopHM();
                break;
            case 3:
                gameLoopMH();
                break;

            default:
                gameLoopMM();
                break;
        }
    }

}