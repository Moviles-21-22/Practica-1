package es.ucm.arblemar.engine;

import java.awt.Color;

public interface Graphics {

    void drawLine();
    void fillRect();
    void drawText();
    void drawCircle(float radio);

    int getWidth();
    int getHeight();
    void getFixed();
    void getMove();

}
