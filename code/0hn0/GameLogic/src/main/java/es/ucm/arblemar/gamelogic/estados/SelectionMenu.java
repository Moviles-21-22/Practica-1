package es.ucm.arblemar.gamelogic.estados;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.Rectangulo;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaAzul;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaRoja;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.gameobject.Texto;
import es.ucm.arblemar.gamelogic.assets.Assets;

public class SelectionMenu implements App {

    private Engine engine;
    private List<GameObject> gameObjects;

    public SelectionMenu(Engine _engine){
        engine = _engine;
    }
    @Override
    public boolean init() {
        try{
            Graphics g = engine.getGraphics();
            gameObjects = new ArrayList<>();

            int width = g.getLogWidth(), height = g.getLogHeight(),
            posX = 0, posY = 0;
            Rectangulo fondo = new Rectangulo(0xFFFFFFFF, posX, posY, width, height, 100);
            gameObjects.add(fondo);

            width = (g.getLogWidth() / 2); height = (g.getLogWidth() / 8);
            posX = (g.getLogWidth() / 5); posY = (g.getLogHeight() / 14) + 20;
            //  Texto de cabecera
            Rectangle rectCabecera = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto textoCabecera = new Texto(rectCabecera,0x313131FF, Assets.molle,80,00);
            textoCabecera.setTexto("Oh no");
            gameObjects.add(textoCabecera);

            width = (g.getLogWidth() / 7) * 5; height = (g.getLogWidth() / 25);
            posX = (g.getLogWidth() / 7); posY = (g.getLogHeight() / 3) - 30;
            //  Texto informativo
            Rectangle rectInfo = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto textInfo = new Texto(rectInfo,0x313131FF, Assets.jose,32,01);
            textInfo.setTexto("Elija el tamaño a jugar");
            gameObjects.add(textInfo);

            CeldaAzul c1 = new CeldaAzul(Assets.jose, 43,4,new Vector2(0,0),0,new Vector2(88,230), 70);
            c1.setInteractive();
            gameObjects.add(c1);

            CeldaRoja c2 = new CeldaRoja(new Vector2(0,0),0,new Vector2(165,230),5,Assets.jose, 43, 70);
            c2.setInteractive();
            gameObjects.add(c2);

            CeldaAzul c3 = new CeldaAzul(Assets.jose, 43, 6,new Vector2(0,0),0,new Vector2(243,230), 70);
            c3.setInteractive();
            gameObjects.add(c3);

            CeldaRoja c4 =  new CeldaRoja(new Vector2(0,0),0,new Vector2(88,308),7,Assets.jose, 43, 70);
            c4.setInteractive();
            gameObjects.add(c4);

            CeldaAzul c5 = new CeldaAzul(Assets.jose, 43, 8,new Vector2(0,0),0,new Vector2(165,308), 70);
            c5.setInteractive();
            gameObjects.add(c5);

            CeldaRoja c6 = new CeldaRoja(new Vector2(0,0),0,new Vector2(243,308),9, Assets.jose, 43, 70);
            c6.setInteractive();
            gameObjects.add(c6);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render() {
        Graphics g = engine.getGraphics();
        g.clear(0);

        for(GameObject obj : gameObjects){
            obj.render(g);
        }
    }

    @Override
    //  Gestiona las colisiones del ratón con los objetos de la escena
    public void handleInput() {
        List<Input.TouchEvent> events = engine.getInput().GetTouchEvents();
        AbstractGraphics g = (AbstractGraphics) engine.getGraphics();

        for(int i = 0 ; i < events.size() ; i++){
            Input.TouchEvent currEvent = events.get(i);
            Vector2 eventPos = g.logPos(new Vector2(currEvent.x, currEvent.y));
            switch (currEvent.type){
                case Input.TouchEvent.touchDown:{
                    GameObject obj = getObjectClicked(eventPos);
                    if(obj != null){
                        System.out.println("SUCCESS");
                        int numGame = ((Celda)obj).getValue();
                        Game game = new Game(engine,numGame);
                        while(!engine.initNewApp(game)) {}
                    }
                    break;
                }
                case Input.TouchEvent.touchUp:{
                    break;
                }
            }
        }
    }

    // Devuelve el objecto que ha sido pulsado
    private GameObject getObjectClicked(Vector2 eventPos){
        boolean encontrado = false;
        int gameObjIndex = 0;

        while (!encontrado && gameObjIndex < gameObjects.size()){
            if(gameObjects.get(gameObjIndex).isInteractive() && ((Celda) gameObjects.get(gameObjIndex)).isClicked(eventPos)){
                encontrado = true;
                return gameObjects.get(gameObjIndex);
            }
            else{
                gameObjIndex++;
            }
        }
        return null;
    }
}
