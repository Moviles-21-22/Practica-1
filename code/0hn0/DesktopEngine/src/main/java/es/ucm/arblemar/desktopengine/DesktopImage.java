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
            _javaImage = resize(_width, _heigth);
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

    @Override
    public void setSize(int newWidth, int newHeight) {
        _javaImage = resize(newWidth, newHeight);
    }

    private BufferedImage resize(int newW, int newH) {
        _width = newW;
        _heigth = newH;
            BufferedImage dimg = new BufferedImage(_width, _heigth, TYPE_INT_ARGB);
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(_javaImage, 0, 0, _width, _heigth,null);
        g.dispose();
        return dimg;
    }

    public BufferedImage getBuffImage() {
        return _javaImage;
    }

    private BufferedImage _javaImage;
    private String _fileName;
    private int _width;
    private int _heigth;
}
