package es.ucm.arblemar.desktopengine;

import java.io.FileInputStream;
import java.io.InputStream;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;

public class DesktopFont implements Font {
    public DesktopFont(String fileName, float size, boolean isBold) {
        _fileName = fileName;
        _size = size;
        _isBold = isBold;
    }

    @Override
    public boolean init() {
        try (InputStream is = new FileInputStream(_fileName)) {
            _javaFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
            setSize(_size);
        }
        catch (Exception e) {
            // Ouch. No está.
            System.err.println("Error: " + e);
            return false;
        }
        return true;
    }

    @Override
    public void setSize(float newSize) {

        _javaFont = _javaFont.deriveFont(newSize);
    }

    public java.awt.Font getJavaFont() {
        return _javaFont;
    }

    java.awt.Font _javaFont;
    String _fileName;
    float _size;
    boolean _isBold;
}
