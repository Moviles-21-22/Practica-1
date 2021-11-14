package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;

public class Rectangulo extends GameObject{
    private int _color;
    private boolean _filled;
    public Rectangulo(boolean filled, int color, int x, int y, int w, int h, int id)
    {
        super(TipoGO.Rect);
        _pos = new Vector2(x, y);
        _size = new Vector2(w, h);
        _color = color;
        _filled = filled;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        if(!renderActive) return;
        g.setColor(_color);
        if(_filled){
            g.fillRect(_pos._x, _pos._y, _size._x, _size._y);
        }
        else{
            g.drawRect(_pos._x, _pos._y, _size._x, _size._y);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
