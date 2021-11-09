package es.ucm.arblemar.gamelogic.estados;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;

public class Game implements App {
    Engine engine;

    Game(Engine _engine){
        engine = _engine;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {

    }

    @Override
    public void handleInput() {

    }
}
