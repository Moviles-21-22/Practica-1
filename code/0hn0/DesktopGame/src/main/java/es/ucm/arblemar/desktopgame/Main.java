package es.ucm.arblemar.desktopgame;
import es.ucm.arblemar.gamelogic.TipoCelda;

public class Main {
    public static void main (String [] args){
        TipoCelda t = TipoCelda.AZUL;

        System.out.println("Celda: " + t + " / " + t.getValue());
    }
}