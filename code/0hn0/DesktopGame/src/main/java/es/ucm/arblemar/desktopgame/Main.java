package es.ucm.arblemar.desktopgame;
import es.ucm.arblemar.desktopengine.DesktopEngine;
import es.ucm.arblemar.gamelogic.GameLogic;
import es.ucm.arblemar.gamelogic.TipoCelda;

public class Main {
    public static void main (String [] args){
        TipoCelda t = TipoCelda.AZUL;

        // TODO: pasar el tamaño del tablero
        //GameLogic game = new GameLogic(4);
        DesktopEngine engine = new DesktopEngine();
        //game.initGame();
        engine.run();
     }
}