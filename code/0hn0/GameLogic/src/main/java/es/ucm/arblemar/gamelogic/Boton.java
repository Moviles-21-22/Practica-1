package es.ucm.arblemar.gamelogic;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.assets.Assets;

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
        return false;
    }

    @Override
    public void clicked() {

    }

    @Override
    public void init() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, pos._x, pos._y);
    }

    @Override
    public void update(float deltaTime) {

    }
}
