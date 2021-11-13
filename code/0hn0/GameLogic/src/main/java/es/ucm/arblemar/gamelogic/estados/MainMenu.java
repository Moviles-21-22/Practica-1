package es.ucm.arblemar.gamelogic.estados;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input.TouchEvent;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.gameobject.Rectangulo;
import es.ucm.arblemar.gamelogic.gameobject.StaticImage;
import es.ucm.arblemar.gamelogic.gameobject.Texto;
import es.ucm.arblemar.gamelogic.assets.Assets;


public class MainMenu implements App {
    //  Lista con todos los objectos de la escena
    private List<GameObject> gameObjects;

    public MainMenu(Engine engine){
        _mainEngine = engine;
    }

    @Override
    public boolean init() {
        // TODO: Aquí se iniciarian cosas de la lógica del mainMenu
        try{
            gameObjects = new ArrayList<>();
            Graphics g = _mainEngine.getGraphics();

            float width = (g.getLogWidth() / 5) * 3, height = (g.getLogWidth() / 7),
            posX = (g.getLogWidth() / 5) - 30, posY = (g.getLogHeight() / 14) + 30;
            Rectangle tituloRect = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto tituloText = new Texto(tituloRect,0X313131FF,Assets.molle,100,01);
            tituloText.setTexto("Oh no");
            gameObjects.add(tituloText);

            width = (g.getLogWidth() / 3); height = (g.getLogWidth() / 7);
            posX = (g.getLogWidth() / 3); posY = (g.getLogWidth() / 7) * 2 + 10;
            Rectangle tRect = new Rectangle((int)posX,(int)posY + 100,(int)width, (int)height);
            Texto t = new Texto(tRect,0X313131FF,Assets.jose,56,02);
            t.setTexto("Jugar");
            t.setInteractive();
            gameObjects.add(t);

            width = (g.getLogWidth() / 5) * 4 ; height = (g.getLogWidth() / 12);
            posX = (g.getLogWidth() / 10) + 60; posY = (g.getLogWidth() / 3) * 2 + 10;
            Rectangle infoRect = new Rectangle((int)posX, (int)posY + 100, (int)width, (int)height);
            Texto infoText = new Texto(infoRect,0XB6B3B6FF, Assets.jose, 20,03);
            infoText.setTexto("Un juego copiado a Q42");
            gameObjects.add(infoText);

            posY = (g.getLogWidth() / 4) * 3 + 10;
            Rectangle nameRect = new Rectangle((int)posX, (int)posY + 100, (int)width, (int)height);
            Texto nameText = new Texto(nameRect,0XB6B3B6FF, Assets.jose, 20,04);
            nameText.setTexto("Creado por Martin Kool");
            gameObjects.add(nameText);

            width= g.getLogWidth(); height = g.getLogWidth();
            posX = 0; posY = 0;
            Rectangulo fondo = new Rectangulo(0x00000000, (int)posX, (int)posY, (int)width, (int)height, 06);
            gameObjects.add(fondo);

            width = (g.getLogWidth() / 16) * 2 ; height = (g.getLogWidth() / 11) * 2;
            posX = (g.getLogWidth() / 5) * 2 + 20; posY = (g.getLogWidth() / 9) * 8 + 20;
            StaticImage icono = new StaticImage(Assets.q42, (int)posX, (int)posY + 100, (int)width, (int)height, 05);
            gameObjects.add(icono);

        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {
        Graphics g = _mainEngine.getGraphics();
        g.clear(0);

        for(GameObject obj : gameObjects){
            obj.render(g);
        }
    }

    @Override
    //  Gestiona las colisiones del ratón con los objetos de la escena
    public void handleInput() {
        List<TouchEvent> events = _mainEngine.getInput().GetTouchEvents();
        AbstractGraphics g = (AbstractGraphics) _mainEngine.getGraphics();
        for(int i = 0 ; i < events.size() ; i++){
            TouchEvent currEvent = events.get(i);
            // Posición donde se hizo click
            Vector2 eventPos = g.logPos(new Vector2(currEvent.x, currEvent.y));
            switch (currEvent.type){
                case TouchEvent.touchDown:{
                    GameObject obj = getObjectClicked(eventPos);
                    if(obj != null){
                        SelectionMenu sMenu = new SelectionMenu(_mainEngine);
                        _mainEngine.initNewApp(sMenu);
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }

    /**
     * Devuelve el objeto que ha sido pulsado
     * */
    private GameObject getObjectClicked(Vector2 eventPos){
        boolean encontrado = false;
        int gameObjIndex = 0;
        AbstractGraphics g = (AbstractGraphics) _mainEngine.getGraphics();
        while (!encontrado && gameObjIndex < gameObjects.size()){

            if(gameObjects.get(gameObjIndex).isClicked(eventPos)){
                encontrado = true;
                return gameObjects.get(gameObjIndex);
            }
            else{
                gameObjIndex++;
            }
        }
        return null;
    }

    Engine _mainEngine;
}