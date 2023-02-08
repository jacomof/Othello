package com.subgrup13.Persistencia;

import com.subgrup13.Dominio.Record;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class FicheroRecord {
    private File file;
    public FicheroRecord()
    {
        loadFile();
    }

    /**
     * Carga la referencia al archivo record_file de records en disco en el atributo file.
     */

    private void loadFile()
    {
        try {

            file = new File("record_file");
            if(file.createNewFile()) System.out.println("Nuevo archivo de guardadado de ranking creado");

        }catch (IOException e){

            System.out.println("No se pudo crear o leer el archivo");

        }
    }
    /**
     * Guarda en disco los Records contenidos en record, reemplazando los anteriores.
     * @param record: instancia de la clase Record que desea ser guardado en disco.
     */
    public void saveRecord(Record record)
    {

        try{

            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bwriter = new BufferedWriter(writer);

            Map<String, Integer> mapa = record.getRecords();
            for(Map.Entry<String, Integer> ent : mapa.entrySet())
            {

                bwriter.write(ent.getKey() + " " + ent.getValue().toString());
                bwriter.newLine();
            }
            bwriter.close();
            writer.close();
        }catch (IOException e){

            System.out.println("No se pudo guardar el registro de records.");

        }

    }
    /**
     * Carga de disco los records contenidos en el fichero record_file y los introduce en la instancia de la clase Record record.
     * @param record: instancia de la clase Record en la cual se desean introducir los datos leidos de memoria.
     */
    public void loadRecord(Record record)
    {
        try{

            Scanner myReader = new Scanner(file);
            while(myReader.hasNext("[a-zA-Z0-9]*"))
            {
                String name = myReader.next("[a-zA-Z0-9]*");
                int puntuacion = myReader.nextInt();
                myReader.nextLine();
                record.updateRecords(name, puntuacion);
            }
            myReader.close();

        }catch (FileNotFoundException e){

            System.out.println("No se encontró archivo de guardado de records. Se creará uno" +
                    "nuevo al terminar una partida");

        }

    }
    //Pequeño programa para probar el codigo
    public static void main(String[] Argv)
    {

        FicheroRecord recf = new FicheroRecord();
        Record r = new Record();
        r.updateRecords("Perro", 20);
        r.updateRecords("Gato", 30);
        r.updateRecords("Gato", 30);
        r.updateRecords("Gato", 40);
        r.updateRecords("Pepe", 20);
        r.updateRecords("Pepe", 15);

        System.out.println("Imprimiendo r:");
        for(Map.Entry<String, Integer> elem : r.getRecords().entrySet()){

            System.out.println(elem.getKey() + " tiene la puntuacion máxima: " + elem.getValue());

        }

        System.out.println("\n");
        recf.saveRecord(r);
        Record r2 = new Record();
        recf.loadRecord(r2);

        System.out.println("Imprimiendo r2, tiene que ser igual a r!");
        for(Map.Entry<String, Integer> elem : r2.getRecords().entrySet()){

            System.out.println(elem.getKey() + " tiene la puntuacion máxima: " + elem.getValue());

        }

    }

}
