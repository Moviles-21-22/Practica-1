package es.ucm.arblemar.androidengine;

import es.ucm.arblemar.engine.Image;

public class AndroidImage implements Image {
    public AndroidImage(int w, int h){
        _width = w;
        _height = h;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    int _width;
    int _height;
}
