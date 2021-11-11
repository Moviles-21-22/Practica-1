package es.ucm.arblemar.desktopengine;


import java.awt.image.BufferStrategy;
import java.util.Vector;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;
import es.ucm.arblemar.engine.Vector2;


public class DesktopEngine implements Engine {
    public DesktopEngine(){
    }

    @Override
    public boolean init(App initApp, String nameGame) {
        _currentApp = initApp;
        _input = new DesktopInput(this);
        _graphics = new DesktopGraphics(nameGame, this);
        return _graphics.init() && _input.init() && _currentApp.init();
    }

    @Override
    public void run() {
        BufferStrategy strategy = _graphics.getStrategy();
        _lastFrameTime = System.nanoTime();

        while(true) {
            // Refresco del deltaTime
            updateDeltaTime();

            // Refresco del estado actual
            _currentApp.handleInput();
            _currentApp.update(_deltaTime);

            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    java.awt.Graphics graphics = strategy.getDrawGraphics();

                    _graphics.updateGraphics();
                    _graphics.prepareFrame();
                    try {
                        _currentApp.render();
                    }
                    finally {
                        graphics.dispose();
                    }


                } while(strategy.contentsRestored());
                strategy.show();
            } while(strategy.contentsLost());
        }
    }

    @Override
    public boolean initNewApp(App newApp){
        // Borrar estado anterior?
        _currentApp = newApp;
        return _currentApp.init();
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    /**
     * Cálculo del deltaTime
     * */
    private void updateDeltaTime(){
        _currentTime = System.nanoTime();
        long nanoElapsedTime = _currentTime - _lastFrameTime;
        _lastFrameTime = _currentTime;
        _deltaTime = (double) nanoElapsedTime / 1.0E9;
    }

    // VARIABLES
    private long _lastFrameTime = 0;
    private long _currentTime = 0;
    private double _deltaTime = 0;
    private App _currentApp;
    private DesktopGraphics _graphics;
    private DesktopInput _input;
}
