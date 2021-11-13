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
    private List<GameObject> objects;

    public SelectionMenu(Engine _engine){
        engine = _engine;
    }
    @Override
    public boolean init() {
        try{
            Graphics g = engine.getGraphics();
            objects = new ArrayList<>();

            //  Texto de cabecera
            Rectangle rectCabecera = new Rectangle((g.getWidth()/2) - 110 ,g.getHeight() / 4 - 45,100,80);
            Texto textoCabecera = new Texto(rectCabecera,0x313131FF, Assets.molle,80,00);
            textoCabecera.setTexto("Oh no");
            objects.add(textoCabecera);

            //  Texto informativo
            Rectangle rectInfo = new Rectangle((g.getWidth()/2) - 150 ,(g.getHeight() / 3) - 10,100,80);
            Texto textInfo = new Texto(rectInfo,0x313131FF, Assets.jose,32,01);
            textInfo.setTexto("Elija el tamaño a jugar");
            objects.add(textInfo);


            CeldaAzul c1 = new CeldaAzul(Assets.jose, 43,4,new Vector2(0,0),0,new Vector2(88,230), 70);
            c1.setInteractive();
            objects.add(c1);

            CeldaRoja c2 = new CeldaRoja(new Vector2(0,0),0,new Vector2(165,230),5,Assets.jose, 43, 70);
            c2.setInteractive();
            objects.add(c2);

            CeldaAzul c3 = new CeldaAzul(Assets.jose, 43, 6,new Vector2(0,0),0,new Vector2(243,230), 70);
            c3.setInteractive();
            objects.add(c3);

            CeldaRoja c4 =  new CeldaRoja(new Vector2(0,0),0,new Vector2(88,308),7,Assets.jose, 43, 70);
            c4.setInteractive();
            objects.add(c4);

            CeldaAzul c5 = new CeldaAzul(Assets.jose, 43, 8,new Vector2(0,0),0,new Vector2(165,308), 70);
            c5.setInteractive();
            objects.add(c5);

            CeldaRoja c6 = new CeldaRoja(new Vector2(0,0),0,new Vector2(243,308),9, Assets.jose, 43, 70);
            c6.setInteractive();
            objects.add(c6);

            Rectangulo fondo = new Rectangulo(0xFF0000FF, 0, 0, g.getLogWidth(), g.getLogWidth(), 10);
            objects.add(fondo);
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
        g.clear(0xFFFFFFFF);

        for(GameObject obj : objects){
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
                        engine.initNewApp(game);
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

        while (!encontrado && gameObjIndex < objects.size()){
            if(objects.get(gameObjIndex).isInteractive() && ((Celda)objects.get(gameObjIndex)).isClicked(eventPos)){
                encontrado = true;
                return objects.get(gameObjIndex);
            }
            else{
                gameObjIndex++;
            }
        }
        return null;
    }
}
