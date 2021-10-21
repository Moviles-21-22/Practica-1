package es.ucm.arblemar.androidengine;

import android.content.Context;

import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;

public class AndroidEngine implements Engine {

    private AndroidGraphics graphics;
    private AndroidInput input;
    private Thread renderThread;
    volatile boolean running = false;

    public AndroidEngine(Context context){

    }

    @Override
    public void init() {
        //input = new AndroidInput();
        //graphics = new AndroidGraphics(this, input);
        //  ...
    }

    @Override
    public void update(double deltaTime){

    }

    @Override
    public void handleInput(){

    }

    @Override
    public void render(){

    }

    @Override
    public void run(){

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

