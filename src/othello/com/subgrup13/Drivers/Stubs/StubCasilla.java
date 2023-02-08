package com.subgrup13.Drivers.Stubs;

import com.subgrup13.Dominio.Casilla;
import com.subgrup13.Dominio.Color;

public class StubCasilla {
    public Casilla getCasilla(){
        return new Casilla(0, 0, Color.EMPTY);
    }
}
