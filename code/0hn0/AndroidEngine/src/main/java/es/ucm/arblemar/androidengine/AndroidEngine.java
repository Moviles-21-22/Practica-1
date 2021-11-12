package es.ucm.arblemar.androidengine;

import android.content.Context;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;

public class AndroidEngine implements Engine {

    private AndroidGraphics graphics;
    private AndroidInput input;
    private Thread renderThread;
    volatile boolean running = false;
    private App currApp;

    public AndroidEngine(){ }

//    public AndroidScreen getScreen(){
//        return graphics.
//    }

    @Override
    public boolean init(App initAp, String nameGame) {
        currApp = initAp;
        input = new AndroidInput();
        graphics = new AndroidGraphics(input);
        return graphics.init() && input.init() && currApp.init();
    }

    @Override
    public void run(){

    }

    @Override
    public boolean initNewApp(App newApp) {
        return false;
    }


    @Override
    public Graphics getGraphics(){
        return graphics;
    }

    @Override
    public Input getInput(){
        return input;
    }
}

