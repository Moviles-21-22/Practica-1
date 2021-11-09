package es.ucm.arblemar.engine;

import java.awt.Graphics2D;

public abstract class AbstractGraphics implements Graphics {
    protected AbstractGraphics(float w, float h){
        _wLogWindow = w;
        _hLogWindow = h;
    }

    @Override
    public void translate(float x, float y) {
        //_graphics.translate((int)x, (int)y);
    }

    @Override
    public Rect scaleRect(Vector2 winSize, Vector2 pos, Vector2 size) {
        // Escalado horizontal
        if(winSize._x < _wLogWindow){
            // Calculos para el translate
        }

        float xLogImg = winSize._x * pos._x / _wLogWindow;
        float wLogImg = winSize._x * size._x / _wLogWindow;
        // Escalado vertical
        float yLogImg = winSize._y * pos._y / _hLogWindow;
        float hLogImg = winSize._y * size._y / _hLogWindow;

        return new Rect(xLogImg, yLogImg, wLogImg, hLogImg);
    }

    private float _wLogWindow;
    private float _hLogWindow;
}
