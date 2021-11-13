package es.ucm.arblemar.gamelogic.gameobject;

import java.awt.Rectangle;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;


public class Boton extends GameObject {
    private Image image;

    public Boton(int _id, Vector2 _pos, Image _image){
        super(_id);
        pos = _pos;
        image = _image;
        interactive = true;
    }

    @Override
    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;

        Rectangle clickRect = new Rectangle(mouseClicked._x,mouseClicked._y,1,1);
        Rectangle imageRect = new Rectangle(pos._x, pos._y, image.getWidth(), image.getHeight());
        return imageRect.intersects(clickRect);
    }

    @Override
    public void clicked() {

    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, pos._x, pos._y, image.getWidth(), image.getHeight());
        if(interactive){
            g.drawRect(pos._x ,pos._y ,image.getWidth(), image.getHeight());
            g.setColor(0X333333FF);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
