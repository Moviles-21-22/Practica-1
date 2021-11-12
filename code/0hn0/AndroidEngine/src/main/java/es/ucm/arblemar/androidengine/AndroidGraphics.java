package es.ucm.arblemar.androidengine;

import android.content.Context;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Input;
import es.ucm.arblemar.engine.Rect;
import es.ucm.arblemar.engine.Vector2;

public class AndroidGraphics extends AbstractGraphics {

    private AndroidScreen screen;
    private Graphics graphics;
    private AndroidInput input;

    public AndroidGraphics(AndroidInput i){
        super(400, 600);
        input = i;
        screen = new AndroidScreen(screen.getContext(), this,i);

    }

    @Override
    public boolean init() {
        screen = new AndroidScreen(screen.getContext(), this,input);
        return true;
    }

    @Override
    public Image newImage(String name){
        return null;
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold){
        return null;
    }

    @Override
    public void clear(int color){

    }

    @Override
    public void setColor(int color){

    }

    @Override
    public void setFont(Font font, int tam) {
        
    }

    @Override
    public void drawImage(Image image, int x, int y, int w, int h) {

    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q){

    }

    @Override
    public void drawCircle(Vector2 centro, int radio) {

    }

    @Override
    public void drawText(String text, int x, int y, Font font, int tam) {

    }

    @Override
    public void drawRect(int x, int y, int width, int height){

    }

    @Override
    public void fillCircle(Vector2 centro, int dm){

    }

    @Override
    public void fillRect(int x, int y, int width, int height) {

    }

    @Override
    public int getWidth() {
        //TODO: ojito
        return  1;
    }

    @Override
    public int getHeight() {
        //TODO: ojito
        return  1;
    }

    @Override
    public void updateGraphics() {

    }

    @Override
    public void prepareFrame() {

    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(float x, float y){

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

}
