package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;

public class StaticImage extends GameObject{
    private Image _image;
    private int width;
    private int height;

    public StaticImage(Image image, int x, int y, int w, int h, int id){
        super(id);
        pos = new Vector2(x, y);
        width = w;
        height = h;
        _image = image;
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
        if(renderActive){
            g.drawImage(_image, pos._x , pos._y, width, height);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
