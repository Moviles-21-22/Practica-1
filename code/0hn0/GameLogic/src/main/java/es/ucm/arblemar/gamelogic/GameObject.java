package es.ucm.arblemar.gamelogic;

import es.ucm.arblemar.engine.Graphics;

public abstract class GameObject {

    protected boolean active = true;

    public void render(Graphics g){};
    public void update(float deltaTime){};
}
