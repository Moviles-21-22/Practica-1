package es.ucm.arblemar.gamelogic;

import es.ucm.arblemar.engine.Vector2;

public class Pista {
    //  Tipo de pista
    private TipoPista tipo;
    //  Index de esta pista en el tablero
    private Vector2 index;

    public Pista(TipoPista t, Vector2 ind){
        tipo = t;
        index = ind;
    }

    public TipoPista getPista(){
        return tipo;
    }

    public Vector2 getIndex(){
        return index;
    }
}
