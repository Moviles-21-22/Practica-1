package es.ucm.arblemar.gamelogic.estados;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.gamelogic.assets.Assets;

/**
 * Estado que se encarga de cargar los recursos de la aplicación
 **/
public class LoadAssets implements App {
    public LoadAssets(Engine engine) {
        _mainEngine = engine;
    }

    @Override
    public boolean init() {
        try {
            Graphics graphics = _mainEngine.getGraphics();
            //Assets.close = graphics.newImage("close.png", 1, 1);
            Assets.molle = graphics.newFont("Molle-Regular.ttf", 50, false);

            App mainMenu = new MainMenu(_mainEngine);
            return _mainEngine.initNewApp(mainMenu);
        }
        catch (Exception e) {
            return false;
        }
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

    Engine _mainEngine;
}