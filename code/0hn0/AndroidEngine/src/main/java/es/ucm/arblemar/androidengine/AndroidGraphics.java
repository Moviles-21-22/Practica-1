package es.ucm.arblemar.androidengine;

import android.content.Context;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Rect;
import es.ucm.arblemar.engine.Vector2;

public class AndroidGraphics implements Graphics {

    private AndroidScreen screen;

    public AndroidGraphics(Context context, AndroidInput i){
        screen = new AndroidScreen(context,this,i);
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public Image newImage(String name, int w, int h){
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
    public void drawImage(Image image, int x, int y){

    }

    @Override
    public void setColor(int color){

    }

    @Override
    public void setFont(Font font) {
        
    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q){

    }

    @Override
    public void drawCircle(Vector2 centro, float radio) {

    }

    @Override
    public void drawText(String text, float x, float y) {

    }

    @Override
    public void drawRect(float x,float y, int width, int height){

    }

    @Override
    public void fillCircle(Vector2 centro, int dm){

    }

    @Override
    public void fillRect(float x1, float y1, int width, int height) {

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
    public void translate(float x, float y) {

    }

    @Override
    public Rect scaleRect(Vector2 winSize, Vector2 pos, Vector2 size){
        return  null;
    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

}
