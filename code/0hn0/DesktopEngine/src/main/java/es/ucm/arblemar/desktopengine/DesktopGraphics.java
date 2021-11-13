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
import es.ucm.arblemar.engine.Vector2;

public class DesktopGraphics extends AbstractGraphics implements ComponentListener{
    // VARIABLES
    private Engine _mainEngine;
    private String _titulo;
    private DesktopScreen _screen;
    private java.awt.Graphics _graphics;
    private AffineTransform _old;

    public DesktopGraphics(String titulo, Engine engine, int w, int h){
        super(w, h);
        _mainEngine = engine;
        _titulo = titulo;
    }

    @Override
    public boolean init() {
        // Creación de la ventana
        _screen = new DesktopScreen(_titulo);
        _screen.addMouseListener((DesktopInput) _mainEngine.getInput());
        _screen.addMouseMotionListener((DesktopInput) _mainEngine.getInput());
        return _screen.init((int)_wLogWindow, (int)_hLogWindow);
    }

    public DesktopScreen getScreen(){
        return _screen;
    }

    @Override
    public Image newImage(String name) throws Exception {
        Image newImage = new DesktopImage("./recursos/sprites/" + name);
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
    public void setFont(Font font, int tam) {
        _graphics.setFont(((DesktopFont)font).getJavaFont().deriveFont(tam));
    }

    @Override
    public void drawImage(Image image, int x, int y, int w, int h) {
        Vector2 newPos = realPos(new Vector2(x, y));
        Vector2 newSize = realSize(new Vector2(w, h));
        _graphics.drawImage(((DesktopImage) image).getImage(), newPos._x, newPos._y,
                            newSize._x, newSize._y, null);
    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q) {
        Vector2 newP = realPos(P);
        Vector2 newQ = realPos(Q);
        _graphics.drawLine(newP._x, newP._y, newQ._x, newQ._y);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        Vector2 newPos = realPos(new Vector2(x, y));
        Vector2 newSize = realSize(new Vector2(width, height));
        _graphics.drawRect(newPos._x, newPos._y, newSize._x, newSize._y);
    }

    @Override
    public void drawCircle(Vector2 centro, int radio) {
        Vector2 newPos = realPos(centro);
        int newSize = realSize(radio);
        _graphics.drawOval(newPos._x, newPos._y, newSize, newSize);
    }

    @Override
    public void drawText(String text, int x, int y, Font font, int tam) {
        font.setSize(realSize(tam));
        _graphics.setFont(((DesktopFont)font).getJavaFont());
        Vector2 newPos = realPos(new Vector2(x, y));
        _graphics.drawString(text, newPos._x, newPos._y);
    }

    @Override
    public void fillCircle(Vector2 centro, int dm){
        Vector2 newPos = realPos(centro);
        int newSize = realSize(dm);
        _graphics.fillOval(newPos._x, newPos._y, newSize, newSize);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        Vector2 newPos = realPos(new Vector2(x, y));
        Vector2 newSize = realSize(new Vector2(width, height));
        _graphics.fillRect(newPos._x, newPos._y, newSize._x, newSize._y);
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
    public void updateGraphics(){
        while(getStrategy() == null){
            System.out.println("NULL");
        }
        _graphics = getStrategy().getDrawGraphics();
    }

    //    @Override
//    public void translate(int x, int y) {
//        ((Graphics2D)_graphics).translate(x, y);
//    }
//
//    @Override
//    public void scale(float x, float y) {
//        ((Graphics2D)_graphics).scale(x, y);
//    }

    @Override
    public void save() {
        _old = ((Graphics2D) _graphics).getTransform();
    }

    @Override
    public void restore() {
        _graphics.dispose();
    }

    //---------------------------------------//
    public BufferStrategy getStrategy(){
        return _screen.getStrategy();
    }

    public java.awt.Graphics getJavaGraphics(){
        return _graphics;
    }

    //---------------------------------------//
    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
