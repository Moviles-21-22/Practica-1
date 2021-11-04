package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;

public class DesktopGraphics implements Graphics {

    private DesktopScreen screen;
    private java.awt.Graphics graphics;
    private AffineTransform old;

    public DesktopGraphics(String titulo){
        // Creación de la ventana
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
    public Image newImage(String name){
        return null;
    }

    @Override
    public Font newFont(String filename, Vector2 size, boolean isBold){
        return null;
    }

    @Override
    public void clear(int color){

    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        //Color c = new Color((int)r, (int)g, (int)b, (int)a);
        graphics.setColor(Color.red);
    }

    @Override
    public void drawImage(Image image){

    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
    }

    @Override
    public void drawRect(float x, float y, int width, int height) {
        graphics.drawRect((int)x ,(int)y ,width ,height);
    }

    @Override
    public void drawCircle(Vector2 centro, float radio) {
        //graphics.fillRoundRect();
    }

    @Override
    public void drawText(String text, float x, float y) {
        graphics.drawString(text,(int)x,(int)y);
    }

    @Override
    public void fillCircle(Vector2 centro, int radio){
        graphics.fillOval(centro._x, centro._y, radio, radio);
    }

    @Override
    public void fillRect(float x, float y, int width, int height) {
        graphics.fillRect((int)x * getWidth() ,(int)y* getHeight() ,width* getWidth() ,height* getHeight() );
    }

    @Override
    public int getWidth() {
       return screen.getWidth();
    }

    @Override
    public int getHeight() {
        return screen.getHeight();
    }

    //=========METODOS-CONTROL-CANVAS===========
    @Override
    public void translate(float x, float y) {
        graphics.translate((int)x, (int)y);
    }

    @Override
    public void scale(float x, float  y){

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
