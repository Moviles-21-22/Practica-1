package es.ucm.arblemar.gamelogic.estados;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.gamelogic.Tablero;

public class Game implements App {
    Engine engine;
    private int tam;
    private Tablero tab;

    Game(Engine _engine,int _tam){
        engine = _engine;
        tam = _tam;
    }

    @Override
    public boolean init() {
        try {
            tab = new Tablero(tam,engine);
        }
        catch (Exception e){
            System.out.println("Fallo al intentar crear el juego");
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {
        Graphics g = engine.getGraphics();
        g.clear(0xFFFFFFFF);
    }

    @Override
    public void handleInput() {

    }
}
