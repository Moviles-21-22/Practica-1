package es.ucm.arblemar.gamelogic;

import java.awt.Rectangle;
import es.ucm.arblemar.engine.Graphics;

public abstract class GameObject {
    protected boolean renderActive = true;
    protected boolean interactive = false;
    protected Vector2 pos;

    //protected Rectangle rect;
    //protected float anchura;
    //protected float altura;

    protected int id;

    GameObject(int _id){
        //rect = _rect;
        //pos = new Vector2(rect.x,rect.y);
        //anchura = _rect.width;
        //altura = _rect.height;
        id = _id;
    }

    public boolean renderIsActive(){
        return renderActive;
    }

    public void activeRender(){
        renderActive = !renderActive;
    }

    public boolean isInteractive(){
        return interactive;
    }

    public void setInteractive(){
        interactive = !interactive;
    }

    public void setPosition(Vector2 _pos){
        //pos = _pos;
        //rect.x = (int)pos._x;
        //rect.y = (int)pos._y;
    }

    public void translate(Vector2 _pos){
        //pos._x += _pos._x;
        //pos._y += _pos._y;
        //rect.x = (int)pos._x;
        //rect.y = (int)pos._y;
    }

    public abstract boolean isClicked(es.ucm.arblemar.engine.Vector2 mouseClicked);

    //  TODO: implementarlo
    public void clicked(){};

    public abstract void init();
    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);

    public int getId(){return id;}


}
