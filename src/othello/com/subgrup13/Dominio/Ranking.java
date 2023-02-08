package com.subgrup13.Dominio;

import java.util.ArrayList;

public class Ranking {
    //representa el conjunto de pares puntuacion y nombre de jugador que han obtenido las puntuaciones max



    private ArrayList<ValorRanking> ranking;

    /**
     * Constructora, crea un ArrayList que contiene el ranking de puntuaciones.
     */
    public Ranking(){
        ranking = new ArrayList<ValorRanking>();
    }

    /**
     * Devuelve el ranking de jugadores.
     * @return Arraylist de puntuaciones de los jugadores.
     */
    public ArrayList<ValorRanking> getRanking() {
        return ranking;
    }

    /**
     * Consultora del ranking con filtro de nombre de jugador.
     * @param n_persona nombre del jugador
     * @return subconjunto del ranking cuyo valor sea el del parametro
     */
    public ArrayList<ValorRanking> getRankingByName(String n_persona) {
        ArrayList<ValorRanking> rankingPers =  new ArrayList<>();
        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).nombre.equals(n_persona)) {
                rankingPers.add(ranking.get(i));
            }
        }
        return rankingPers;
    }

    /**
     * Añade la puntuacion y el nombre de la persona que la obtuvo en ranking. Si el
     * ranking ya esta lleno (contiene 20 elem) se eliminará la puntuacion más baja.
     */
    public void updateRanking(ValorRanking v) {
        int i = 0;
        while (i < ranking.size()) {
            if (ranking.get(i).puntuacion <= v.puntuacion)
                break;

            i++;
        }
        ranking.add(i, v);
        if (ranking.size() == 21)
            ranking.remove(20);
    }


    // no se incluye en la funcionalidad pero nos puede hacer falta en el desarollo
    /**
     * Vacia el ranking.
     */
    public void clearRanking() {
        ranking.clear();
    }
    //Pequeño programa para probar el codigo
    public static void main(String[] Argv)
    {

        Ranking rank = new Ranking();
        rank.updateRanking(new ValorRanking( "Perro", 20));
        rank.updateRanking(new ValorRanking( "Gato", 30));
        rank.updateRanking(new ValorRanking( "Gato", 30));
        rank.updateRanking(new ValorRanking( "Gato", 40));
        rank.updateRanking(new ValorRanking("Pepe", 20));
        rank.updateRanking(new ValorRanking("Pepe", 15));

        ArrayList<ValorRanking> t = rank.getRankingByName("Pepe");

        System.out.println("Imprimiendo las puntuaciones registradas de Pepe: ");

        for(ValorRanking x: t)
            System.out.println(x.nombre + " tiene puntuacion: " + x.puntuacion);

        System.out.println("Imprimiendo TODAS las puntuaciones registradas: ");

        for(ValorRanking y: rank.getRanking())
            System.out.println(y.nombre + " tiene puntuacion: " + y.puntuacion);

    }
}
