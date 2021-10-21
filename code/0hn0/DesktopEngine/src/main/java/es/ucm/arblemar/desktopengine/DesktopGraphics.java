package es.ucm.arblemar.desktopengine;

import java.awt.Color;

import es.ucm.arblemar.engine.Graphics;

public class DesktopGraphics implements Graphics {

    private DesktopScreen screen;
    private java.awt.Graphics graphics;

    public DesktopGraphics(String titulo){
        screen = new DesktopScreen(titulo);
        screen.createScreen();
        show();

    }


    public void show() {
        graphics = screen.getStrategy().getDrawGraphics();
        graphics.dispose();
        screen.getStrategy().show();
    }

    @Override
    public void drawLine() {

    }

    @Override
    public void fillRect() {

    }

    @Override
    public void drawText() {

    }

    @Override
    public void drawCircle(float radio) {

    }


    @Override
    public int getWidth() {
       return screen.getWidth();
    }

    @Override
    public int getHeight() {
        return screen.getHeight();
    }

    @Override
    public void getFixed() {

    }

    @Override
    public void getMove() {

    }
}
