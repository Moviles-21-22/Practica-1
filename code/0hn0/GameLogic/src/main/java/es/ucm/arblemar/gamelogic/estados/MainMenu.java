package es.ucm.arblemar.gamelogic.estados;
import java.awt.Color;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;


public class MainMenu implements App {
    public MainMenu(Engine engine){
        _mainEngine = engine;
    }

    @Override
    public boolean init() {
        // TODO: Aquí se iniciarian cosas de la lógica del mainMenu
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {
        // Estas van siempre en tos lados
        Graphics g = _mainEngine.getGraphics();
        g.clear(0xFF0000FF);

        g.setColor(0x00FF00FF);
        g.fillCircle(new Vector2(500, 500), 50);
    }

    @Override
    public void handleInput() {

    }

    Engine _mainEngine;
}
