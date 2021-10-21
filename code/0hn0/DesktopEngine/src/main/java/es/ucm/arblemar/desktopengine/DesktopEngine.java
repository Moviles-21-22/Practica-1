package es.ucm.arblemar.desktopengine;


import java.util.Vector;

import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;



public class DesktopEngine implements Engine {


    private DesktopGraphics graphics;
    private Vector<DesktopFont> fonts = new Vector<>();
    private DesktopInput input;

    public DesktopEngine(){
        String titulo = "TESTEO";
        graphics = new DesktopGraphics(titulo);

        graphics.setColor(256, 1, 1, 255);
        graphics.save();
        //graphics.translate(0, 50);
        graphics.fillRect(100, 100, 100, 100);
        graphics.restore();
    }

    @java.lang.Override
    public void init() {

    }


    @java.lang.Override
    public void update(double deltaTime) {

    }

    @java.lang.Override
    public void handleInput() {

    }

    @java.lang.Override
    public void render() {

    }

    @java.lang.Override
    public void run() {

    }


    @java.lang.Override
    public Graphics getGraphics() {
        return graphics;
    }

    @java.lang.Override
    public Input getInput() {
        return input;
    }
}
