package es.ucm.arblemar.gamelogic.estados;
import java.util.List;


import java.util.ArrayList;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input.TouchEvent;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.assets.Assets;


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
        g.clear(0xAA0010FF);

        g.setColor(0x00AA10FF);
        g.fillCircle(new Vector2(500, 500), 50);

        // Ponemos el rótulo (si conseguimos cargar la fuente)
        if (Assets.molle != null) {
            g.setColor(0X000000FF);
            g.setFont(Assets.molle);
            g.drawText("RENDERIZADO ACTIVO", 100, 100);
        }

        if (Assets.close != null) {
            g.drawImage(Assets.close, 200, 200);
        }
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