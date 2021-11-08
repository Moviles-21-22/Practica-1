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

            //Sprites
            Assets.close = graphics.newImage("close.png", 30, 30);
            Assets.q42 = graphics.newImage("q42.png", 30, 30);

            //Fuentes
            Assets.molle = graphics.newFont("Molle-Regular.ttf", 32, false);
            Assets.josefinSans32 = graphics.newFont("JosefinSans-Bold.ttf", 32, true);
            Assets.josefinSans64 = graphics.newFont("JosefinSans-Bold.ttf", 64, true);

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