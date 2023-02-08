package com.subgrup13.Persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.lang.*;


public class FicheroPartidas {

    private File file;

    /**
     * Constructora, crea fichero para contener las partidas guardadas.
     */
    public  FicheroPartidas() throws IOException {
        abrirFicheroPartidas();
    }

    /**
     * Crea el fichero contenedor de partidas guardadas si no existe.
     */
    public void abrirFicheroPartidas() throws IOException {
        this.file = new File("FicheroPartidas.txt");
        if (!file.exists()) {
            System.out.println("Archivo de guardadado de partidas creado");
            this.file.createNewFile();
        }
    }

    /**
     * Guarda la información de una partida en curso en el fichero de partidas guardadas.
     */
    public void savePartida(String partida, String color) throws IOException {
        if (!file.exists()) abrirFicheroPartidas();
        partida = partida + " " + color;
        Escribir(file, partida);
    }

    /**
     * Carga la información de una partida guardada en el fichero de partidas guardadas.
     */
    public String loadPartida(Integer id) {
        if (!file.exists()) {
            throw new IllegalArgumentException("No existe ningun archivo de partidas guardadas.");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Integer idFile = 0;
            while ((line = br.readLine()) != null) {
                if (idFile == id) return line;
                ++idFile;
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return null;
    }

    private void Escribir(File file,String info) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file,true));
            if (file.length() == 0) bw.write(info);
            else {
                bw.newLine();
                bw.write(info);
            }
            bw.close();

        }catch(IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Devuelve info de una partida guardada en el fichero de partidas guardadas.
     * @param id Posicion de la partida en el fichero de partidas guardadas.
     * @return String que contiene info de la partida.
     */
    public String getInfo(Integer id) {
        if (!file.exists()) {
            throw new IllegalArgumentException("No existe ningun archivo de partidas guardadas.");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String info = null;
            Integer idFile = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (idFile == id) {
                    String[] partidaInfo = line.split(" ");
                    info = partidaInfo[2] + " " + partidaInfo[3] + " " + partidaInfo[4];
                    return info;
                }
                ++idFile;
            }
        }
        catch (IOException ex) {System.out.println(ex);}
        return null;
    }

    /**
     * Devuelve el número de partidas guardadas.
     * @return Número de partidas guardadas.
     */
    public Integer numPartidas() {
        if (!file.exists()) {
            throw new IllegalArgumentException("No existe ningun archivo de partidas guardadas.");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Integer idFile = 0;
            String line;
            while ((line = br.readLine()) != null) {
                ++idFile;
            }
            return idFile;
        }
        catch (IOException ex) {System.out.println(ex);}
        return 0;
    }

    private void borrar(File Ffichero){
        try {
            if(Ffichero.exists()){
                Ffichero.delete();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Borra una partida de la lista de partidas guardadas.
     * @param id Posicion de la partida a borrar en la lista.
     */
    public void borrarPartida(Integer id) throws IOException {
        File temporal = new File("FicheroTemporal.txt");
        System.out.println("Archivo de guardadado temporal creado");
        temporal.createNewFile();

        if (!file.exists()) {
            throw new IllegalArgumentException("No existe ningun archivo de partidas guardadas.");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Integer idFile = 0;
            while ((line = br.readLine()) != null) {
                if (idFile != id) Escribir(temporal, line);
                ;
                ++idFile;
            }
            br.close();
            borrar(file);
            abrirFicheroPartidas();
            copyFileUsingStream(temporal, file);
            borrar(temporal);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
