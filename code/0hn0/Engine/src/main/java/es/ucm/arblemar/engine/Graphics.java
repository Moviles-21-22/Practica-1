package es.ucm.arblemar.engine;

import java.awt.Color;

public interface Graphics {

    void drawLine();
    void fillRect(double x, double y, int width, int height);
    void drawRect(double x,double y, int width, int height);
    void drawText(String text, double x, double y);
    void drawCircle(float radio);

    void setColor(float r, float g,float b, float a);

    int getWidth();
    int getHeight();
    void getFixed();
    void getMove();

    void translate(double x, double y);
    void save();
    void restore();
}
