package com.subgrup13.Drivers.Stubs;

import com.subgrup13.Dominio.Color;
import com.subgrup13.Dominio.Jugador;
import com.subgrup13.Dominio.Persona;

public class StubJugador {
    public Jugador GetJugador(Color color){
        return new Persona("a", color);
    }
}
