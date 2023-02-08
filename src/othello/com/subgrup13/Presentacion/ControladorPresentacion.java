package com.subgrup13.Presentacion;

import com.subgrup13.Dominio.*;
import com.subgrup13.Dominio.Color;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ControladorPresentacion {
    private VistaMenuInicio vistaMenuInicio;
    private ControladorDominio ctrlDominio;

    private TableroPanel tableroPanel;
    private TableroPanelJugar tableroPanelJugar;

    private VistaPartida vistaPartida;
    private VistaConfigurarPartida vistaConfigurarPartida;
    private VistaConfigurarPartida2 vistaConfigurarPartida2;
    private VistaPartidaTerminada vistaPartidaTerminada;
    private VistaConsultarRanking vistaConsultarRanking;
    private VistaConsultarRecord vistaConsultarRecord;
    private VistaConfigurarTablero vistaConfigurarTablero;
    private VistaEleccionTablero vistaEleccionTablero;
    private JFrame mainFrame;
    private JPanel mainPanel;
    private CardLayout mainLayout;
    private int[] casilla_seleccionada = new int[2];
    private VistaCargarPartida vistaCargarPartida;
    public ControladorPresentacion() throws InterruptedException, IOException {

        ctrlDominio = new ControladorDominio(this);
        mainFrame = new JFrame("Othello");
        tableroPanel = new TableroPanel(this);
        tableroPanelJugar = new TableroPanelJugar(this);

        vistaMenuInicio = new VistaMenuInicio(this);
        vistaConfigurarPartida = new VistaConfigurarPartida(this);
        vistaConfigurarPartida2 = new VistaConfigurarPartida2(this);
        vistaConsultarRanking = new VistaConsultarRanking(this);
        vistaConsultarRecord = new VistaConsultarRecord(this);
        vistaCargarPartida = new VistaCargarPartida(this);
        vistaPartidaTerminada = new VistaPartidaTerminada (this);
        vistaConfigurarTablero = new VistaConfigurarTablero(this);
        vistaPartida = new VistaPartida(this);
        vistaEleccionTablero = new VistaEleccionTablero(this);

        mainPanel = new JPanel();
        mainLayout = new CardLayout();
        mainPanel.setLayout(mainLayout);
        mainPanel.add(vistaMenuInicio.getPanel(), "menu_inicio");
        mainPanel.add(vistaConsultarRanking.getPanel(), "vista_consultar_ranking");
        mainPanel.add(vistaConsultarRecord.getPanel(), "vista_consultar_record");
        mainPanel.add(vistaConfigurarPartida.getPanel(), "vista_configurar_partida");
        mainPanel.add(vistaConfigurarPartida2.getPanel(), "vista_configurar_partida_2");
        mainPanel.add(vistaPartidaTerminada.getPanel(), "vista_partida_terminada");
        mainPanel.add(vistaConfigurarTablero.getPanel(), "vista_configurar_tablero");
        mainPanel.add(vistaPartida.getPanel(), "vista_partida");
        mainPanel.add(vistaEleccionTablero.getPanel(), "vista_eleccion_tablero");
        mainLayout.show(mainPanel, "menu_inicio");
        mainPanel.add(vistaCargarPartida.getPanel(), "vista_cargar_partida");
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setSize(800, 667);
        mainFrame.setVisible(true);


    }

    public void confNormas(Boolean[] n) {
        this.ctrlDominio.setTablero_normas(n);
    }

    public void ajusta_ventana()
    {

        mainFrame.pack();

    }

    public void sincronizacionVistaEleccionT_a_VistaConfigurarT()
    {

        mainLayout.show(mainPanel, "vista_configurar_tablero");
        mainFrame.pack();


    }

    public void changePanel(String new_panel)
    {

        mainLayout.show(mainPanel, new_panel);

    }
    public void reset_a_configurar (){
        ctrlDominio.vaciar_tablero();
        changePanel("vista_configurar_partida");
    }

    public void reset_a_menu (){
        ctrlDominio.vaciar_tablero();
        changePanel("menu_inicio");

    }

    public boolean setEstadoTableroPres(CeldaBoton[][] cells)
    {
        int[][] estados = new int[8][8];

        for(int i = 0; i < 8; ++i)
        {
            for(int j = 0; j < 8; ++j){
                estados[i][j] = cells[i][j].getEstado();
            }

        }

        return ctrlDominio.setEstadoTablero(estados);

    }

    public void sincronizacionVistaMI_a_VistaCRanking()
    {

        vistaConsultarRanking.setTable(ctrlDominio.getRanking());
        mainLayout.show(mainPanel, "vista_consultar_ranking");

    }

    public void sincronizacionVistaMI_a_VistaCRecord()
    {

        ctrlDominio.importDataRecord();
        vistaConsultarRecord.setTable(ctrlDominio.getRecord());
        mainLayout.show(mainPanel, "vista_consultar_record");

    }


    public void setJugadores(String n1, String n2, Dificultad d1, Dificultad d2) {
        ctrlDominio.setJugadores(n1, n2, d1, d2);
    }

    public void inicializarVistaMI() { vistaMenuInicio.setVistaMenuInicio(this); }



    public void sincronizacion_VistaPartidaterminada (){
        vistaPartidaTerminada.setall(ctrlDominio.getNameB(),ctrlDominio.getNameW(),ctrlDominio.getPuntuacionB(),ctrlDominio.getPuntuacionW());
        changePanel("vista_partida_terminada");
    }

    public void borrarPartida(int id) throws IOException {

        ctrlDominio.borrarPartida(id);

    }

    public void actualizarListpartidas()
    {
        vistaCargarPartida.actualizarLista();
        mainLayout.show(mainPanel, "vista_cargar_partida");

    }

    public void setTipo(int in_tipo) {
        this.ctrlDominio.setTipo(in_tipo);
    }

    public int getTipoPartida() {
        return this.ctrlDominio.getTipo();
    }

    public Tablero getTablero() {
        return this.ctrlDominio.getTablero();
    }

    public Integer getRecordByNamePres(String name){
        return ctrlDominio.getRecordByNameDom( name);
    }

    public void updateRecordsPres(String jugador, Integer puntuacion){
        ctrlDominio.updateRecordsDom(jugador,puntuacion);
    }

    public void updateRankingPres(String n, int p) {
        ctrlDominio.updateRankingDom(n,p);
    }

    public int[][] getEstadoTablero() {
        return ctrlDominio.getEstadoTablero();
    }

    public String getInfoGuardado(Integer id) {
        return ctrlDominio.getInfoGuardado(id);
    }

    public Integer numPartidas() {
        return ctrlDominio.numPartidas();
    }

    public void importDataPartida(Integer id) throws IOException {
        ctrlDominio.importDataPartida(id);
    }

    public void storeDataPartida(Color turno) throws IOException {
        ctrlDominio.storeDataPartida(turno);
    }

    public void wakeUpDominio(int i, int j)
    {

        synchronized (this) {
            casilla_seleccionada = new int[]{i, j};
            System.out.println(casilla_seleccionada);
            this.notifyAll();

        }


    }

    public int[] getCasilla_seleccionada()
    {

        return casilla_seleccionada;

    }

    public void updateTableroGrafico(int[][] tabl)
    {

        vistaPartida.getTableroGrafico().setCeldas(tabl);
        //mainFrame.repaint();

    }

    public ControladorDominio getDominio()
    {

        return ctrlDominio;

    }

    public void play(int tipo) {
        updateTableroGrafico(ctrlDominio.getEstadoTablero());
        ctrlDominio.playOthello(tipo);
    }

    public static void main(String[] Argv)
    {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    ControladorPresentacion ctrl  = new ControladorPresentacion();
                    ControladorDominio ctrlD = new ControladorDominio(ctrl);
                    ctrl.changePanel("vista_partida");

                    ctrlD.gameLoopHM();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
