package es.ucm.arblemar.androidengine;

import android.content.Context;
import android.graphics.Color;

import es.ucm.arblemar.engine.Graphics;

public class AndroidGraphics implements Graphics {

    private AndroidScreen screen;

    public AndroidGraphics(Context context, AndroidInput i){
        screen = new AndroidScreen(context,this,i);
    }

    @Override
    public void drawLine() {

    }

    @Override
    public void fillRect(double x1, double y1, int width, int height) {

    }

    @Override
    public void drawRect(double x, double y, int width, int height) {

    }

    @Override
    public void drawText(String text, double x, double y) {

    }

    @Override
    public void drawCircle(float radio) {

    }

    @Override
    public void setColor(float r, float g, float b, float a) {

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
    public void getFixed() {

    }

    @Override
    public void getMove() {

    }

    @Override
    public void translate(double x, double y) {

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

}
