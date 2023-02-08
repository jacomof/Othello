package com.subgrup13.Persistencia;

import com.subgrup13.Dominio.Ranking;
import com.subgrup13.Dominio.ValorRanking;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FicheroRanking {

    private File file;
    public FicheroRanking()
    {
        loadFile();
    }

    /**
     * Carga la referencia al archivo record_file de records en disco en el atributo file.
     */
    private void loadFile()
    {
        try {

            file = new File("ranking_file");
            if(file.createNewFile()) System.out.println("Nuevo archivo de guardadado de ranking creado");

        }catch (IOException e){

            System.out.println("No se pudo crear o leer el archivo");

        }
    }
    /**
     * Guarda en el fichero ranking_file los Rankings contenidos en rank.
     * @param rank: instancia de la clase Ranking que se desea guardar en disco.
     */
    public void saveRanking(Ranking rank)
    {

        try{
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bwriter = new BufferedWriter(writer);

            ArrayList<ValorRanking> rk = rank.getRanking();
            for(ValorRanking ent : rk)
            {

                bwriter.write(ent.nombre + " " + ent.puntuacion);
                bwriter.newLine();

            }
            bwriter.close();
            writer.close();
        }catch (IOException e){

            System.out.println("No se pudo guardar el registro de ranking.");

        }

    }
    /**
     * Carga de disco los rankings contenidos en el fichero ranking_file y los introduce en la instancia de la clase Ranking rank.
     * @param rank: instancia de la clase Ranking en la cual se desean introducir los datos leidos de memoria.
     */
    public void loadRanking(Ranking rank)
    {

        try{
            Scanner myReader = new Scanner(file);

            while(myReader.hasNext("[a-zA-Z0-9]*"))
            {

                String name = myReader.next("[a-zA-Z0-9]*");
                int puntuacion = myReader.nextInt();
                rank.updateRanking(new ValorRanking(name, puntuacion));

                myReader.nextLine();

            }

            myReader.close();

        }catch (FileNotFoundException e){

            System.out.println("No se encontró archivo de guardado de ranking. Se creará uno" +
                    "nuevo al terminar una partida");

        }

    }
    //Pequeño programa para probar el codigo
    public static void main(String[] Argv)
    {

        FicheroRanking rankf = new FicheroRanking();
        Ranking rank = new Ranking();
        rank.updateRanking(new ValorRanking( "Perro", 20));
        rank.updateRanking(new ValorRanking( "Gato", 30));
        rank.updateRanking(new ValorRanking( "Gato", 30));
        rank.updateRanking(new ValorRanking( "Gato", 40));
        rank.updateRanking(new ValorRanking("Pepe", 20));
        rank.updateRanking(new ValorRanking("Pepe", 15));


        System.out.println("Imprimiendo rank:");
        for(ValorRanking elem : rank.getRanking())
            System.out.println(elem.nombre + " tiene la puntuacion: " + elem.puntuacion);
        System.out.println("\n");

        rankf.saveRanking(rank);
        Ranking rank2 = new Ranking();
        rankf.loadRanking(rank2);

        System.out.println("Imprimiendo rank2, tiene que ser igual a rank!");
        for(ValorRanking elem : rank2.getRanking())
            System.out.println(elem.nombre + " tiene la puntuacion: " + elem.puntuacion);

    }

}
