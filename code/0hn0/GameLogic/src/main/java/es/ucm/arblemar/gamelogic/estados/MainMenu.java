package es.ucm.arblemar.gamelogic.estados;
import java.util.List;


import java.util.ArrayList;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input.TouchEvent;
import es.ucm.arblemar.engine.Vector2;


public class MainMenu implements App {



    public MainMenu(Engine engine){
        _mainEngine = engine;
    }

    @Override
    public boolean init() {
        // TODO: Aquí se iniciarian cosas de la lógica del mainMenu
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {
        // Estas van siempre en tos lados
        Graphics g = _mainEngine.getGraphics();
        g.clear(0xFF0000FF);

        g.setColor(0x00FF00FF);
        g.fillCircle(new Vector2(500, 500), 50);
    }

    @Override
    public void handleInput() {
        List<TouchEvent> events = _mainEngine.getInput().GetTouchEvents();

        for(int i = 0 ; i < events.size() ; i++){
            TouchEvent currEvent = events.get(i);
            Vector2 eventPos = new Vector2(currEvent.x, currEvent.y);
            switch (currEvent.type){
                case TouchEvent.touchDown:{

                    break;
                }
                case TouchEvent.touchUp:{
                    break;
                }
            }
        }
    }




    private boolean inside(Vector2 pos){
        return false;
    }

    Engine _mainEngine;
}
