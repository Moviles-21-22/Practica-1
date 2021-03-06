package es.ucm.arblemar.gamelogic.estados;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.ButtonCallback;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.Icon;
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
            Rectangulo fondo = new Rectangulo(true, 0xFFFFFFFF, posX, posY, width, height, 100);
            gameObjects.add(fondo);

            width = (g.getLogWidth() / 2); height = (g.getLogWidth() / 8);
            posX = (g.getLogWidth() / 5); posY = (g.getLogHeight() / 14) + 20;
            //  Texto de cabecera
            //Rectangle rectCabecera = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto textoCabecera = new Texto(new Vector2(posX, posY), new Vector2(width, height),0x313131FF, Assets.molle,80,00);
            textoCabecera.setTexto("Oh no");
            gameObjects.add(textoCabecera);

            width = (g.getLogWidth() / 7) * 5; height = (g.getLogWidth() / 25);
            posX = (g.getLogWidth() / 7); posY = (g.getLogHeight() / 3) - 30;
            //  Texto informativo
            //Rectangle rectInfo = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto textInfo = new Texto(new Vector2(posX, posY), new Vector2(width, height),0x313131FF, Assets.jose,32,01);
            textInfo.setTexto("Elija el tama??o a jugar");
            gameObjects.add(textInfo);

            final CeldaAzul c1 = new CeldaAzul(Assets.jose, 43,4,new Vector2(0,0), new Vector2(88,230), 70);
            c1.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c1.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });

            gameObjects.add(c1);

            final CeldaRoja c2 = new CeldaRoja(new Vector2(0,0), new Vector2(165,230),5,Assets.jose, 43, 70);
            c2.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c2.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });
            gameObjects.add(c2);

            final CeldaAzul c3 = new CeldaAzul(Assets.jose, 43, 6,new Vector2(0,0), new Vector2(243,230), 70);
            c3.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c3.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });
            gameObjects.add(c3);

            final CeldaRoja c4 =  new CeldaRoja(new Vector2(0,0), new Vector2(88,308),7,Assets.jose, 43, 70);
            c4.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c4.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });
            gameObjects.add(c4);

            final CeldaAzul c5 = new CeldaAzul(Assets.jose, 43, 8,new Vector2(0,0), new Vector2(165,308), 70);
            c5.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c5.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });
            gameObjects.add(c5);

            final CeldaRoja c6 = new CeldaRoja(new Vector2(0,0), new Vector2(243,308),9, Assets.jose, 43, 70);
            c6.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    //System.out.println(eventPos._x + " " + eventPos._y);
                    int numGame = c6.getValue();
                    Game game = new Game(engine,numGame);
                    engine.initNewApp(game);
                }
            });
            gameObjects.add(c6);

            width = (g.getLogWidth() / 16) * 2 ; height = (g.getLogWidth() / 16) * 2;
            posX = (g.getLogWidth() / 2)  - (width / 2); posY = g.getLogHeight() - 200;
            Icon icono = new Icon(Assets.close, (int)posX, (int)posY + 100, (int)width, (int)height, 05);
            icono.setInteractive();
            icono.setCallback(new ButtonCallback() {
                @Override
                public void doSomething() {
                    MainMenu main = new MainMenu(engine);
                    engine.initNewApp(main);
                }
            });
            gameObjects.add(icono);
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

        for(GameObject obj : gameObjects){
            obj.render(g);
        }
    }

    @Override
    //  Gestiona las colisiones del rat??n con los objetos de la escena
    public void handleInput() {
        List<Input.TouchEvent> events = engine.getInput().GetTouchEvents();
        AbstractGraphics g = (AbstractGraphics) engine.getGraphics();

        for(int i = 0 ; i < events.size() ; i++) {
            Input.TouchEvent currEvent = events.get(i);
            Vector2 eventPos = g.logPos(new Vector2(currEvent.getX(), currEvent.getY()));
            switch (currEvent){
                case touchDown:{
                    GameObject obj = getObjectClicked(eventPos);
                    if(obj != null) {
                        obj.clicked();
                    }

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
            if(gameObjects.get(gameObjIndex).isInteractive() && (gameObjects.get(gameObjIndex)).isClicked(eventPos)){
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
