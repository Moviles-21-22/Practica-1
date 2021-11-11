package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Rect;
import es.ucm.arblemar.engine.Vector2;

public class DesktopGraphics extends AbstractGraphics {
    public DesktopGraphics(String titulo, Engine engine){
        super(600, 400);
        _mainEngine = engine;
        _titulo = titulo;
    }

    @Override
    public boolean init() {
        // Creación de la ventana
        _screen = new DesktopScreen(_titulo);
        _screen.addMouseListener((DesktopInput) _mainEngine.getInput());
        _screen.addMouseMotionListener((DesktopInput) _mainEngine.getInput());
        return _screen.init(1000, 800);
    }

    public DesktopScreen getScreen(){
        return _screen;
    }

    @Override
    public Image newImage(String name, int w, int h) throws Exception {
        Image newImage = new DesktopImage("./recursos/sprites/" + name, h, w);
        if (!newImage.init())
            throw new Exception();
        return newImage;
    }

    @Override
    public Font newFont(String name, int size, boolean isBold) throws Exception {
        Font newFont = new DesktopFont("./recursos/fonts/" + name, size, isBold);
        if (!newFont.init())
            throw new Exception();
        return newFont;
    }

    @Override
    public void clear(int color){
        _graphics = getStrategy().getDrawGraphics();
        setColor(color);
        _graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void setColor(int newColor) {
        float r = ((newColor >> 24) & 0xff) / 255.0f;
        float g = ((newColor >> 16) & 0xff) / 255.0f;
        float b = ((newColor >> 8) & 0xff) / 255.0f;
        float a = ((newColor >> 0) & 0xff) / 255.0f;
        Color c = new Color(r, g, b, a);
        _graphics.setColor(c);
    }

    @Override
    public void setFont(Font font) {
        _graphics.setFont(((DesktopFont) font).getJavaFont());
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        Rect rect = scaleRect(new Vector2(getWidth(), getHeight()),
                                new Vector2(x, y),
                                new Vector2(image.getWidth(), image.getHeight()));
        _graphics.drawImage(((DesktopImage) image).getBuffImage(),
                                (int)rect.x1(),
                                (int)rect.y1(),
                                (int)rect.getWidth(),
                                (int)rect.getHeight(), null);
    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q) {
        _graphics.drawLine((int)P._x, (int)P._y, (int)Q._x, (int)Q._y);
    }

    @Override
    public void drawRect(float x, float y, int width, int height) {
        _graphics.drawRect((int)x ,(int)y ,width ,height);
    }

    @Override
    public void drawCircle(Vector2 centro, float radio) {
        //graphics.fillRoundRect();
        //_graphics.fillRoundRect(centro._x, centro._y, (int)radio, (int)radio, 360, 360);
        //_graphics.fillArc(centro._x, centro._y, (int)radio, (int)radio, 0, 360);
    }

    @Override
    public void drawText(String text, float x, float y) {
        _graphics.drawString(text,(int)x,(int)y);
    }

    @Override
    public void fillCircle(Vector2 centro, int dm){
        _graphics.fillOval(centro._x, centro._y, dm, dm);
    }

    @Override
    public void fillRect(float x, float y, int width, int height) {
        _graphics.fillRect((int)x * getWidth() ,(int)y* getHeight() ,width* getWidth() ,height* getHeight() );
    }

    @Override
    public int getWidth() {
       return _screen.getWidth();
    }

    @Override
    public int getHeight() {
        return _screen.getHeight();
    }

    @Override
    public void save() {
        _old = ((Graphics2D) _graphics).getTransform();
    }

    @Override
    public void restore() {
        ((Graphics2D) _graphics).setTransform(_old);
    }

    //---------------------------------------//
    public BufferStrategy getStrategy(){
        return _screen.getStrategy();
    }

    public java.awt.Graphics getJavaGraphics(){
        return _graphics;
    }

    // VARIABLES
    private Engine _mainEngine;
    private String _titulo;
    private DesktopScreen _screen;
    private java.awt.Graphics _graphics;
    private AffineTransform _old;
}
