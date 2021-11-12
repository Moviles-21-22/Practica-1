package es.ucm.arblemar.desktopgame;
import es.ucm.arblemar.desktopengine.DesktopEngine;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.gamelogic.GameLogic;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.estados.LoadAssets;

public class Main {
    public static void main (String [] args){
        DesktopEngine engine = new DesktopEngine();
        App loadAssets = new LoadAssets(engine);
        if(!engine.init(loadAssets, "0hn0", 400, 600)) {
            System.out.println("Algo fue mal");
            return;
        }
        engine.run();
    }
}