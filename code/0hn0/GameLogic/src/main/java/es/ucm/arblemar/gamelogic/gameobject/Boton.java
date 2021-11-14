package es.ucm.arblemar.gamelogic.gameobject;

import java.awt.Rectangle;

import es.ucm.arblemar.engine.ButtonCallback;
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
    }

    @Override
    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;

        Rectangle clickRect = new Rectangle(mouseClicked._x,mouseClicked._y,1,1);
        Rectangle imageRect = new Rectangle(_pos._x, _pos._y, image.getWidth(), image.getHeight());
        return imageRect.intersects(clickRect);
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
