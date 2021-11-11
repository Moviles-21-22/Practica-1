package es.ucm.arblemar.desktopengine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import es.ucm.arblemar.engine.Image;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class DesktopImage implements Image {
    public DesktopImage(String filename, int w, int h){
        _fileName = filename;
        _width = w;
        _heigth = h;
    }

    @Override
    public boolean init(){
        try (InputStream is = new FileInputStream(_fileName)) {
            _javaImage = ImageIO.read(new File(_fileName));
        }
        catch (Exception e) {
            // Ouch. No está.
            System.err.println("Error cargando la imagen: " + e);
            return false;
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

    public BufferedImage getBuffImage() {
        return _javaImage;
    }

    private BufferedImage _javaImage;
    private String _fileName;
    private int _width;
    private int _heigth;
}
