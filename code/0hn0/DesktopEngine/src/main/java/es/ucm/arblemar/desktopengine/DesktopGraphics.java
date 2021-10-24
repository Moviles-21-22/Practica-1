package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import es.ucm.arblemar.engine.Graphics;

public class DesktopGraphics implements Graphics {

    private DesktopScreen screen;
    private java.awt.Graphics graphics;
    private AffineTransform old;

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
    public void fillRect(double x, double y, int width, int height) {
        graphics.fillRect((int)x * getWidth() ,(int)y* getHeight() ,width* getWidth() ,height* getHeight() );

    }

    @Override
    public void drawRect(double x, double y, int width, int height) {
        graphics.drawRect((int)x ,(int)y ,width ,height);
    }

    @Override
    public void drawText(String text, double x, double y) {
        graphics.drawString(text,(int)x,(int)y);
    }

    @Override
    public void drawCircle(float radio) {
        //graphics.fillRoundRect();
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        //Color c = new Color((int)r, (int)g, (int)b, (int)a);
        graphics.setColor(Color.red);
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

    @Override
    public void translate(double x, double y) {
        graphics.translate((int)x, (int)y);
    }

    @Override
    public void save() {
        old = ((Graphics2D)graphics).getTransform();
    }

    @Override
    public void restore() {
        ((Graphics2D) graphics).setTransform(old);
    }
}
