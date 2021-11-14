package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;

public class Icon extends GameObject{
    private Image _image;

    public Icon(Image image, int x, int y, int w, int h, int id){
        super(TipoGO.Icon);
        _pos = new Vector2(x, y);
        _size = new Vector2(w, h);
        _image = image;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        if(renderActive){
            g.drawImage(_image, _pos._x , _pos._y, _size._x, _size._y);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
