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
        //Assets.molle.setSize(10);
    }

    @Override
    public void render() {
        // Estas van siempre en tos lados
        Graphics g = _mainEngine.getGraphics();
        g.clear(0xFFFFFFFF);

        //Círculo azul
        g.setColor(0x1CC0E0FF);
        int tam = g.getHeight() / 4;
        int margin = tam / 20;
        g.fillCircle(new Vector2((g.getWidth() / 2) - (tam + margin), (g.getHeight() / 2) - (tam / 2)), tam);
        //Círculo rojo
        g.setColor(0xFF384BFF);
        g.fillCircle(new Vector2((g.getWidth() / 2) + margin, (g.getHeight() / 2) - (tam / 2)), tam);

        // Ponemos el rótulo (si conseguimos cargar la fuente)
        if (Assets.molle != null) {
            g.setColor(0X333333FF);
            g.setFont(Assets.molle);
            float size = (float)g.getHeight() / 6;
            Assets.molle.setSize(size);
            g.drawText("Oh no", (float)(g.getWidth() / 2) - (size * 24/17), (float)(g.getHeight() / 2) - (size * 24/17));
        }
        // Ponemos el primer texto de debajo (si conseguimos cargar la fuente)
        if (Assets.josefinSans64 != null) {
            g.setFont(Assets.josefinSans64);
            float size = (float)g.getHeight() / 20;
            Assets.josefinSans64.setSize(size);
            g.drawText("It's 0h h1's companion!", (float)(g.getWidth() / 2) - (size * 9/2), (float)(g.getHeight() / 2) + (size * 9/2));
        }
        // Ponemos los otros dos textos de debajo (si conseguimos cargar la fuente)
        if (Assets.josefinSans32 != null) {
            g.setFont(Assets.josefinSans32);
            float size = (float)g.getHeight() / 26;
            Assets.josefinSans32.setSize(size);
            g.setColor(0XAAAEC0FF);
            g.drawText("A game by Q42", (float)(g.getWidth() / 2) - (size * 7/2), (float)(g.getHeight() / 2) + (size * 15/2));
            g.drawText("Created by Martin Kool", (float)(g.getWidth() / 2) - (size * 5), (float)(g.getHeight() / 2) + (size * 9));
        }

        //Ponemos la imagen de la empresa (si conseguimos cargar la imagen)
        if (Assets.q42 != null) {
            g.drawImage(Assets.q42, 280, 350);
        }

        //if (Assets.close != null) {
        //    g.drawImage(Assets.close, 200, 200);
        //}
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