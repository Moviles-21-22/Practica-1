package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;

public class Rectangulo extends GameObject{
    private int _width;
    private int _height;
    private int _color;
    public Rectangulo(int color, int x, int y, int w, int h, int id)
    {
        super(id);
        pos = new Vector2(x, y);
        _width = w;
        _height = h;
        _color = color;
    }

    @Override
    public void clicked() {

    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        if(renderActive){
            g.setColor(_color);
            g.fillRect(pos._x, pos._y, _width, _height);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
