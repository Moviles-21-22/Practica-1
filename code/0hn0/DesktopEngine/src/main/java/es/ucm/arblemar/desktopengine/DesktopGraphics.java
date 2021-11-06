package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;

public class DesktopGraphics implements Graphics, ComponentListener {
    public DesktopGraphics(String titulo){
        _titulo = titulo;
    }

    @Override
    public boolean init() {
        // Creación de la ventana
        _screen = new DesktopScreen(_titulo);
        _screen.addComponentListener(this);
        return _screen.init(1000, 800);
    }

    public DesktopScreen getScreen(){
        return _screen;
    }

    @Override
    public Image newImage(String name){
        return null;
    }

    @Override
    public Font newFont(String fileName, int size, boolean isBold){
        Font newFont = new DesktopFont(fileName, size, isBold);

        return null;
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
    public void drawImage(Image image){

    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
    }

    @Override
    public void drawRect(float x, float y, int width, int height) {
        _graphics.drawRect((int)x ,(int)y ,width ,height);
    }

    @Override
    public void drawCircle(Vector2 centro, float radio) {
        //graphics.fillRoundRect();
    }

    @Override
    public void drawText(String text, float x, float y) {
        _graphics.drawString(text,(int)x,(int)y);
    }

    @Override
    public void fillCircle(Vector2 centro, int radio){
        _graphics.fillOval(centro._x, centro._y, radio, radio);
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

    //=========METODOS-CONTROL-CANVAS===========
    @Override
    public void translate(float x, float y) {
        _graphics.translate((int)x, (int)y);
    }

    @Override
    public void scale(float x, float  y){

    }

    @Override
    public void save() {
        _old = ((Graphics2D) _graphics).getTransform();
    }

    @Override
    public void restore() {
        ((Graphics2D) _graphics).setTransform(_old);
    }

    public BufferStrategy getStrategy(){
        return _screen.getStrategy();
    }

    public java.awt.Graphics getJavaGraphics(){
        return _graphics;
    }

    private String _titulo;
    private DesktopScreen _screen;
    private java.awt.Graphics _graphics;
    private AffineTransform _old;

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
