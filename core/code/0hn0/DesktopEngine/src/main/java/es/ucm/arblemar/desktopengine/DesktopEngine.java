package es.ucm.arblemar.desktopengine;


import java.awt.image.BufferStrategy;
import java.util.Vector;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;

public class DesktopEngine implements Engine {
    public DesktopEngine(){
    }

    public boolean init(App initApp, String nameGame, int w, int h) {
        _currentApp = initApp;
        _input = new DesktopInput(this);
        _graphics = new DesktopGraphics(nameGame, this, w, h);
        return _graphics.init() && _input.init() && _currentApp.init();
    }

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
                    _graphics.updateGraphics();
                    _graphics.clear(0xFFFFFFFF);
                    try {
                        _currentApp.render();
                    }
                    finally {
                        _graphics.restore();
                    }
                } while(strategy.contentsRestored());
                strategy.show();
            } while(strategy.contentsLost());
        }
    }

    @Override
    public boolean initNewApp(App newApp){
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
