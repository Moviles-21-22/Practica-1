package es.ucm.arblemar.desktopengine;

import es.ucm.arblemar.engine.Image;

public class DesktopImage implements Image {
    public DesktopImage(int w, int h){
        _width = w;
        _heigth = h;
    }

    @Override
    public int getWidth() {
        return _heigth;
    }

    @Override
    public int getHeight() {
        return _width;
    }

    private int _width;
    private int _heigth;
}
