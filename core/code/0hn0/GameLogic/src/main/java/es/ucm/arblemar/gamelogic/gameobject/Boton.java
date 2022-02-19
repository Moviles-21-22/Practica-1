package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;


public class Boton extends GameObject {
    private Image image;

    public Boton(Vector2 _pos, Image _image){
        super(TipoGO.Boton);
        this._pos = _pos;
        image = _image;
        interactive = true;
        _size = new Vector2(image.getWidth(),image.getHeight());
    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, _pos._x, _pos._y, image.getWidth(), image.getHeight());
        //if(interactive){
        //    g.drawRect(_pos._x , _pos._y ,image.getWidth(), image.getHeight());
        //    g.setColor(0X333333FF);
        //}
    }

    @Override
    public void update(double deltaTime) {

    }
}
