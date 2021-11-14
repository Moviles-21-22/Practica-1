package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.ButtonCallback;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;


public abstract class GameObject {
    protected boolean renderActive = true;
    protected boolean interactive = false;
    protected Vector2 _pos;
    protected Vector2 _size;

    //protected int id;
    protected TipoGO _type;
    protected ButtonCallback _cb;

    GameObject(TipoGO t){
        _type = t;
    }

    final public TipoGO get_type(){
        return _type;
    }

    public void setCallback(ButtonCallback cb){
        _cb = cb;
    }

    public Vector2 get_pos() {
        return _pos;
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

    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;
        return mouseClicked._x > _pos._x && mouseClicked._x < _pos._x + _size._x
                && mouseClicked._y > _pos._y && mouseClicked._y < _pos._y + _size._y;
    }

    //  TODO: implementarlo
    public void clicked(){
        if(_cb != null){
            _cb.doSomething();
        }
    };

    public abstract void init();
    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);
}
