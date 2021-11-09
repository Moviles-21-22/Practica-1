package es.ucm.arblemar.desktopgame;
import es.ucm.arblemar.desktopengine.DesktopEngine;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.gamelogic.GameLogic;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.estados.LoadAssets;

public class Main {
    public static void main (String [] args){
        TipoCelda t = TipoCelda.AZUL;

        DesktopEngine engine = new DesktopEngine();

        App loadAssets = new LoadAssets(engine);

        if(!engine.init(loadAssets, "TESTEO")) {
            System.out.println("Algo fue mal");
            return;
        }

        engine.run();
    }
}