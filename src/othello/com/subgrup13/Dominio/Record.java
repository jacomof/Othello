package com.subgrup13.Dominio;

import java.util.HashMap;
import java.util.Map;

public class                   Record {

    private HashMap<String, Integer> records;

    /**
     * Constructora de la clase, simplemente crea un nuevo HashMap para contener los records
     */
    public Record() {
        this.records = new HashMap<>();
    }

    /**
     * Consultora de la referencia a la instancia de HashMap que contiene los records.
     * @return retorna una referencia a la instancia de HashMap que contiene los records.
     */
    public HashMap<String, Integer> getRecords() {
        return this.records;
    }

    /**
     * Consultora del record de una persona.
     * @param n_persona nombre de la persona de la que se quiere conocer el record.
     * @return retorna la puntuación máxima registrada en records de la persona con nombre n_persona, o null en el caso de que no exista.
     */

    public Integer getRecordByName(String n_persona) {
        return this.records.get(n_persona);
    }


    /**
     * Modificadora de los records. Si la persona ya estaba registrada en los records, actualiza su puntuación máxima con el máximo entre la puntuación contenida en el parámetro "puntuacion"
     * y la puntuación ya contenida en el registro de records.
     * Si no, añade una nueva entrada a los records con la puntuación del parámetro "puntuacion".
     * @param jugador nombre del jugador cuya puntuacion quiere ser registrada o actualizada.
     * @param puntuacion puntuacion del jugador nombrado por el atributo "jugador". Es la puntuacion que se asignara al jugador "jugador" si este no tiene ninguna puntuación en los records o
     * si la que ya tiene es menor.
     */
    public void updateRecords(String jugador, Integer puntuacion) {
        if (this.records.get(jugador) == null || this.records.get(jugador) < puntuacion) {
            this.records.put(jugador, puntuacion);
        }
    }

    public void clearRecords() {
        this.records.clear();
    }


    //Pequeño programa para probar el codigo
    public static void main(String[] Argv)
    {

        Record r = new Record();
        r.updateRecords("Perro", 20);
        r.updateRecords("Gato", 30);
        r.updateRecords("Gato", 30);
        r.updateRecords("Gato", 40);
        r.updateRecords("Pepe", 20);
        r.updateRecords("Pepe", 15);

        for(Map.Entry<String, Integer> elem : r.getRecords().entrySet()){

            System.out.println(elem.getKey() + " tiene la puntuacion máxima: " + elem.getValue());

        }

        //System.out.println("Perro tiene la puntuación: " + r.getRecordByName("Perro"));
        //System.out.println("Gato tiene la puntuación: " + r.getRecordByName("Gato"));
    }
}
