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

}
