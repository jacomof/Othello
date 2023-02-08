package com.subgrup13.Dominio;

import java.util.ArrayList;


public class Tablero {
    //////////////////// ATRIBUTOS //////////////////////////////////////
    private Casilla[][] tablero;
    private ArrayList<Casilla> posiblesMovimientos;  // El contenido depende del turno que se esta jugando

    /*
     * Atributo que define el tipo de partida segun jugadores:
     * 1 - HvsH
     * 2 - HvsM
     * 3 - MvsH
     * 4 - MvsM
     * Entendiendo que se fija que el jugador1 lleva las negras ya que tipicamente el juego funciona
     * de esa manera.
     */
    private int tipoPartidaJugadores; // util de cara a integrar en ctrl de dom

    private Jugador jugador1; // negras
    private Jugador jugador2; // blancas

    private Color turnoActual;

    private Integer ronda = 0;

    private Boolean[] normas = {false, false, false};//normas[0]==VERTICAL normas[1]==HORIZONTAL normas[2]==DIAGONAL

    private Integer[] n_fichas = {2, 2};

    ///////////////// METODOS PUBLICOS ///////////////////////////////////////////////
    // Constructoras

    /**
     * Constructora, crea un tablero vacío
     */
    public Tablero() {
        inicializaTablero();
    }

    /**
     * Constructora, crea un tablero con una apertura dependiendo del valor del parámetro
     */
    public Tablero(Boolean[] normaspartida) {

        inicializaTablero();
        normas = normaspartida;

        if (normas[2] == true && normas[0] == false && normas[1] == false) {

            tablero[3][3].cambiarColorCasilla(Color.WHITE);
            tablero[3][4].cambiarColorCasilla(Color.WHITE);
            tablero[4][3].cambiarColorCasilla(Color.BLACK);
            tablero[4][4].cambiarColorCasilla(Color.BLACK);
        } else {

            tablero[3][3].cambiarColorCasilla(Color.WHITE);
            tablero[4][4].cambiarColorCasilla(Color.WHITE);
            tablero[3][4].cambiarColorCasilla(Color.BLACK);
            tablero[4][3].cambiarColorCasilla(Color.BLACK);

        }
    }

    /**
     * Constructora, crea un tablero y copia los datos del tablero t en el nuevo tablero
     */
    public Tablero(Tablero t) {
        //Copiar tablero
        this.tablero = new Casilla[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {

                Casilla c_aux = t.tablero[i][j];
                this.tablero[i][j] = new Casilla(c_aux.fila, c_aux.columna, c_aux.color);

            }
        }
        //Copiar normas (normas podría ser static ya que no cambia entre estados)
        for (int i = 0; i < normas.length; ++i) {
            this.normas[i] = t.normas[i];
        }
        this.jugador1 = t.jugador1;
        this.jugador2 = t.jugador2;
        this.n_fichas[0] = Integer.valueOf(t.n_fichas[0].intValue());
        this.n_fichas[1] = Integer.valueOf(t.n_fichas[1].intValue());
        this.ronda = Integer.valueOf(t.ronda.intValue());

    }

    /**
     * Constructora, crea un tablero a partir de todos los atributos pasados como parámetros.
     */
    public Tablero(Casilla[][] tableroF, Integer tipoPartidaJugadoresF, String jugador1F, String jugador2F, Integer rondaF, Boolean[] normasF, Integer[] n_fichasF, Dificultad d1, Dificultad d2) {
        this.tablero = new Casilla[8][8];
        tablero = tableroF;
        tipoPartidaJugadores = tipoPartidaJugadoresF;
        ronda = rondaF;
        normas = normasF;
        n_fichas = n_fichasF;
        setJugadores(jugador1F, jugador2F, d1, d2);
    }

    // setters

    /**
     * Establece el tipo de partida.
     * @param in_tipo Integer que representa el tipo de partida.
     */
    public void setTipoPartidaJugadores(int in_tipo) {
        this.tipoPartidaJugadores = in_tipo;
    }

    public void setNormas(Boolean[] bs) {

        normas = bs;

    }

    /**
     * Establece los nombres de los jugadores y sus dificultade en el caso que sean jugadores máquina.
     * @param namej1 Nombre del primer jugador.
     * @param namej2 Nombre del segundo jugador.
     * @param dificultad1 Dificultad del primer jugador.
     * @param dificultad2 Dificultad del segundo jugador.
     */
    public void setJugadores(String namej1, String namej2, Dificultad dificultad1, Dificultad dificultad2) {
        switch (this.tipoPartidaJugadores) {
            case 1:
                this.jugador1 = new Persona(namej1, Color.BLACK);
                this.jugador2 = new Persona(namej2, Color.WHITE);
                break;
            case 3:
                this.jugador1 = new Maquina(namej1, Color.BLACK, dificultad1);
                this.jugador2 = new Persona(namej2, Color.WHITE);
                break;
            case 4:
                this.jugador1 = new Maquina(namej1, Color.BLACK, dificultad1);
                this.jugador2 = new Maquina(namej2, Color.WHITE, dificultad2);
                break;
            default:
                this.jugador1 = new Persona(namej1, Color.BLACK);
                this.jugador2 = new Maquina(namej2, Color.WHITE, dificultad2);
                break;

        }
        turnoActual = Color.BLACK;
    }

    /**
     * Coloca la ficha i prepara el tablero para el siguiente turno.
     * @param turno Color del turno actual.
     * @param fila Fila de la ficha que se quiere colocar.
     * @param  col Columna de la ficha que se quiere colocar.
     */
    public Color juegaTurno(Color turno, int fila, int col) {
        colocarFicha(fila, col, turno);

        limpiarMovimientosValidos();

        //Cambiar el color para el siguiente turno
        if (turno == Color.BLACK)
            turnoActual =  Color.WHITE;
        else
            turnoActual = Color.BLACK;
        return turnoActual;
    }

    /**
     * Consultora para conocer el que tipo de partida se esta jugando en este tablero.
     * @return Integer que representa que tipo de partida es.
     */
    public int getTipoPartidaJugadores() {
        return this.tipoPartidaJugadores;
    }

    /**
     * Incrementa en uno la ronda.
     */
    public void updateRonda() {
        this.ronda += 1;
    }

    /**
     * Función que obtiene y "traduce" el movimiento de la maquina para que sea del mismo formato que la que utiliza
     * el usuario. Permite utilizar las mismas funciones.
     *
     * @param turno para saber qué color mueve
     * @return la posición en coordenadas alfanumericas
     */
    public Color juegaTurnoMaquina(Color turno) {
        Casilla coordmaquina = new Casilla();
        switch (this.tipoPartidaJugadores) {
            case 2:
                coordmaquina = jugador2.eleccion_maquina(this);
                break;
            case 3:
                coordmaquina = jugador1.eleccion_maquina(this);
                break;
            case 4:
                if (turno == Color.BLACK) {
                    inicializar_direcciones(turno);
                    coordmaquina = jugador1.eleccion_maquina(this);
                } else
                    coordmaquina = jugador2.eleccion_maquina(this);
                break;
            default:
                break;
        }
        colocarFicha(coordmaquina.fila, coordmaquina.columna, turno);
        if (turno == Color.BLACK)
            turnoActual =  Color.WHITE;
        else
            turnoActual = Color.BLACK;
        return turnoActual;
    }

    /**
     * Inicializa las casillas que son posibles movimientos.
     */
    public void inicializar_direcciones(Color color) {
        posiblesMovimientos = new ArrayList<Casilla>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], color)) {
                    posiblesMovimientos.add(tablero[row][col]);
                    tablero[row][col].color = Color.EMPTY_MOVE;
                }
            }
        }
    }

    /**
     * Borra el conjunto de posibles movimientos.
     */
    public void limpiarMovimientosValidos() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (this.tablero[row][col].color == Color.EMPTY_MOVE) this.tablero[row][col].color = Color.EMPTY;
            }
        }
    }

    /**
     * Inicializa las casillas que son posibles movimientos.
     */
    public void setMatTablero(Casilla[][] matTab) {
        tablero = matTab;
    }

    /**
     * @pre Las posiciones estan debidamente inicializadas
     * Retorna un arraylist con las posiciones en las que se puede capturar
     */
    public ArrayList<Casilla> inicializar_direcciones_maquina(Color color) {
        posiblesMovimientos = new ArrayList<Casilla>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], color)) {
                    posiblesMovimientos.add(tablero[row][col]);
                }
            }
        }

        return posiblesMovimientos;
    }

    /**
     * Coloca una ficha en el tablero.
     * @param fila Fila de la ficha que se quiere colocar.
     * @param  columna Columna de la ficha que se quiere colocar.
     * @param color
     */
    public void colocarFicha(int fila, int columna, Color color) {

        tablero[fila][columna].cambiarColorCasilla(color);
        if (color == Color.BLACK)
            ++n_fichas[0];
        else
            ++n_fichas[1];
        girarFichas(tablero[fila][columna]);
        jugador1.setPuntuacion(n_fichas[0]);
        jugador2.setPuntuacion(n_fichas[1]);
    }

    /**
     * Coloca una ficha en el tablero por parte del jugador máquina.
     * @param casilla Casilla que se quiere colocar.
     * @param color Color de la ficha que se quiere colocar en la casilla.
     */
    public void colocarFicha_maquina(Casilla casilla, Color color) {
        tablero[casilla.fila][casilla.columna].cambiarColorCasilla(color);
        if (color == Color.BLACK)
            ++n_fichas[0];
        else
            ++n_fichas[1];
        girarFichas(tablero[casilla.fila][casilla.columna]);
        jugador1.setPuntuacion(n_fichas[0]);
        jugador2.setPuntuacion(n_fichas[1]);
    }

    /**
     * Cambia de color una casilla del tablero
     *
     * @param fila    Fila de la casilla
     * @param columna Columna de la casilla
     * @param color   Color nuevo de la casilla
     */
    public void girarFicha(int fila, int columna, Color color) {
        this.tablero[fila][columna].cambiarColorCasilla(color);
    }

    // getters

    /**
     * Devuelve si es valido el movimiento en las coordenadas marcadas.
     * @param coord Coordenadas del movimiento que se quiere comprobar si es válido.
     * @return True si el movimiento esta en el conjunto de posibles movimientos.
     */
    public Boolean esValido(String coord) {
        Integer fila = (int) coord.charAt(0) - 65;
        Integer columna = (int) coord.charAt(1) - 49;
        if ((fila < 0 || columna < 0 || fila >= 8 || columna >= 8)) return false;
        if (tablero[fila][columna].color == Color.EMPTY_MOVE) return true;
        else return false;
    }

    /**
     * Valora si para un cierto color de jugador se puede hacer un movimiento en ese turno
     *
     * @param turno, tipo Color, solo puede valer blanco y negro
     * @return Cierto si el jugador que juega con ese color puede hacer una jugada en ese turno
     */
    public boolean movimientosPosibles(Color turno) {
        int i = 0;
        while (i < 64) {
            if (movimientoValido(tablero[i/8][i%8], turno))
                return true;
            ++i;
        }
        return false;
    }

    /**
     * Consultora que devuelve las casillas del tablero.
     * @return Casillas que forman el tablero.
     */
    public Casilla[][] getTableroMat() {
        return tablero;
    }

    /**
     * Consultora que devuelve los posibles movimientos del jugador en la ronda actual.
     * @return Posibles movimientos del jugador.
     */
    public ArrayList<Casilla> getDirecciones() {
        return posiblesMovimientos;
    }

    /**
     * Consultora para conocer el primer jugador.
     * @return Primer jugador.
     */
    public Jugador getJugador1() {
        return this.jugador1;
    }

    /**
     * Consultora para conocer el segundo jugador.
     * @return Segundo jugador.
     */
    public Jugador getJugador2() {
        return this.jugador2;
    }

    /**
     * Consultora para conocer la ronda actual.
     * @return Ronda actual.
     */
    public Integer getRonda() {
        return this.ronda;
    }

    /**
     * Consultora para conocer las normas del tablero en la partida actual.
     * @return Normas del tablero.
     */
    public Boolean[] getNormas() {
        return this.normas;
    }

    /**
     * Comprueba si el tablero tiene movimientos posibles o por lo contrario la partida ha terminado.
     * @return Devuelve false si aún hay movimientos válidos.
     */
    public Boolean tableroLleno() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], Color.WHITE) || movimientoValido(tablero[row][col], Color.BLACK)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean tableroLlenoNegras()
    {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], Color.BLACK)) {
                    return false;
                }
            }
        }
        return true;

    }

    public Boolean tableroLlenoBlancas()
    {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], Color.WHITE)) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Comprueba si el jugador con el color color tiene movimientos posibles o se salta su turno
     * @param color Color del jugador.
     * @return Devuelve false si el jugador tiene movimientos posibles.
     */
    public Boolean saltaTurno(Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (movimientoValido(tablero[row][col], color)) {
                    return false;
                }
            }
        }
        if (color == Color.BLACK)
            updateRonda();
        return true;
    }

    /**
     * Consultora para conocer el número de piezas negras en el tablero (asumiendo que n_fichas[0] son las negras).
     * @return Número de fichas negras en el tablero.
     */
    public double getNumNegras() {


        return (double) n_fichas[0].intValue();

    }

    /**
     * Consultora para conocer el número de piezas blancas en el tablero (asumiendo que n_fichas[1] son las blancas).
     * @return Número de fichas blancas en el tablero.
     */
    public double getNumBlancas() {


        return (double) n_fichas[1].intValue();

    }

    /**
     * Vacía el terminal (No funciona en el terminal de Intellij).
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /////////// METODOS PRIVADOS ////////////////////////////////////////

    private boolean direccionValida(Casilla casilla, Color color, int xDir, int yDir) {
        Color colorOponente;
        if (color == Color.BLACK) colorOponente = Color.WHITE;
        else colorOponente = Color.BLACK;

        int x = casilla.fila;
        int y = casilla.columna;

        x += xDir;
        y += yDir;
        if ((x < 0 || y < 0 || x >= 8 || y >= 8) || tablero[x][y].color != colorOponente) return false;

        x += xDir;
        y += yDir;
        while (x >= 0 && y >= 0 && x < 8 && y < 8) {
            if (tablero[x][y].color == color) {
                return true;
            } else if (tablero[x][y].color == Color.EMPTY || tablero[x][y].color == Color.EMPTY_MOVE) {
                return false;
            }
            x += xDir;
            y += yDir;
            ;
        }
        return false;
    }

    private boolean movimientoValido(Casilla casilla, Color color) {
        if (casilla.color != Color.EMPTY) return false;
        if (normas[0] && direccionValida(casilla, color, 1, 0)) return true; //vertical
        else if (normas[0] && direccionValida(casilla, color, -1, 0)) return true; //vertical
        else if (normas[1] && direccionValida(casilla, color, 0, 1)) return true; // horizontal
        else if (normas[1] && direccionValida(casilla, color, 0, -1)) return true; //horizontal
        else if (normas[2] && direccionValida(casilla, color, 1, 1)) return true; //diagonal
        else if (normas[2] && direccionValida(casilla, color, 1, -1)) return true; //diagonal
        else if (normas[2] && direccionValida(casilla, color, -1, 1)) return true; //diagonal
        else if (normas[2] && direccionValida(casilla, color, -1, -1)) return true; //diagonal
        return false;
    }

    private void giroDir(Casilla casilla, int xDir, int yDir) {
        Color colorOponente;
        if (casilla.color == Color.BLACK) colorOponente = Color.WHITE;
        else colorOponente = Color.BLACK;

        int x = casilla.fila;
        int y = casilla.columna;

        if (!direccionValida(casilla, casilla.color, xDir, yDir)) return;

        x += xDir;
        y += yDir;
        while ((x >= 0 && y >= 0 && x < 8 && y < 8) && tablero[x][y].color == colorOponente) {
            girarFicha(x, y, casilla.color);
            if (colorOponente == Color.WHITE) {
                ++n_fichas[0];
                --n_fichas[1];
            } else {
                --n_fichas[0];
                ++n_fichas[1];
            }
            x += xDir;
            y += yDir;
        }
    }

    private void girarFichas(Casilla casilla) {
        giroDir(casilla, 1, 0);
        giroDir(casilla, -1, 0);
        giroDir(casilla, 0, 1);
        giroDir(casilla, 0, -1);
        giroDir(casilla, 1, 1);
        giroDir(casilla, 1, -1);
        giroDir(casilla, -1, 1);
        giroDir(casilla, -1, -1);
    }

    /**
     * Inicializadora de las casillas del tablero.
     */
    private void inicializaTablero() {
        posiblesMovimientos = new ArrayList<Casilla>();
        tablero = new Casilla[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tablero[row][col] = new Casilla(row, col, Color.EMPTY);
            }
        }
    }
}


