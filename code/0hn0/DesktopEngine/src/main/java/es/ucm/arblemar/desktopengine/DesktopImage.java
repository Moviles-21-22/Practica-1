package es.ucm.arblemar.desktopengine;

import java.io.FileInputStream;
import java.io.InputStream;

import es.ucm.arblemar.engine.Image;

public class DesktopImage implements Image {
    public DesktopImage(String filename, int w, int h){
        _fileName = filename;
        _width = w;
        _heigth = h;
    }

    @Override
    public boolean init(){
        try (InputStream is = new FileInputStream(_fileName)) {
        //    _javaImage =
        //    _javaFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
        }
        catch (Exception e) {
        //    // Ouch. No está.
        //    System.err.println("Error cargando la fuente: " + e);
        //    return false;
        }

        return true;
    }

    @Override
    public int getWidth() {
        return _heigth;
    }

    @Override
    public int getHeight() {
        return _width;
    }

    private java.awt.Image _javaImage;
    private String _fileName;
    private int _width;
    private int _heigth;
}
