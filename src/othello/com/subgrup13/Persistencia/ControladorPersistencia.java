package com.subgrup13.Persistencia;

import com.subgrup13.Dominio.Ranking;
import com.subgrup13.Dominio.Record;

import java.io.IOException;


public class ControladorPersistencia {

    private FicheroRanking rank;
    private FicheroRecord record;
    private FicheroPartidas partis;

    public ControladorPersistencia()
    {

        rank = new FicheroRanking();
        record = new FicheroRecord();

        try {
            partis = new FicheroPartidas();
        } catch (IOException e){
            System.out.println("No se puede abrir o crear el fichero de partidadas guardadas!");
        }

    }


    public void saveRecord(Record rcd)
    {

        record.saveRecord(rcd);

    }

    public void saveRanking(Ranking rk)
    {
        rank.saveRanking(rk);

    }

    public void loadRecord(Record rcd)
    {

        record.loadRecord(rcd);


    }
    public void loadRanking(Ranking rk)
    {

        rank.loadRanking(rk);

    }

    public void savePartida(String partida, String color)
    {
        try {
            this.partis.savePartida(partida, color);
        }catch(IOException e){

            System.out.println("No se pudo salvar la partida!");
        }
    }

    public String loadPartida(Integer id)
    {

        return partis.loadPartida(id);

    }

    public  String getInfo(Integer id) {
        return partis.getInfo(id);
    }

    public  Integer numPartidas() {
        return partis.numPartidas();
    }

    public void borrarPartida(Integer id) throws IOException {

        partis.borrarPartida(id);

    }


}
