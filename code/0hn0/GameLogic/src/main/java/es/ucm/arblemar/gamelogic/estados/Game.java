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
import es.ucm.arblemar.gamelogic.gameobject.Boton;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.Tablero;
import es.ucm.arblemar.gamelogic.gameobject.Texto;
import es.ucm.arblemar.gamelogic.assets.Assets;

public class Game implements App {
    Engine engine;
    Graphics _graphics;
    private int tam;
    private Tablero tab;
    private List<GameObject> objects;
    private List<Boton> images;

    Game(Engine _engine,int _tam){
        engine = _engine;
        tam = _tam;
        _graphics = engine.getGraphics();
        objects = new ArrayList<>();
        images = new ArrayList<>();
    }

    @Override
    public boolean init() {
        try {
            tab = new Tablero(tam,engine);

            float width = (_graphics.getLogWidth() / 2) * 3, height = (_graphics.getLogWidth() / 7),
                    posX = (_graphics.getLogWidth() / 3) - 15, posY = (_graphics.getLogHeight() / 12) - 10;
            //_graphics.getWidth() / 2 - 80, _graphics.getHeight() / 8 + 20,200,100
            Rectangle texSuperRect = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            Texto textoSuperior = new Texto(texSuperRect,0X313131FF ,Assets.jose,72,0);
            textoSuperior.setTexto(tam + " x " + tam);
            objects.add(textoSuperior);

            //  botón para volver
            Boton backButton = new Boton(0,new Vector2(30, _graphics.getLogHeight() - Assets.close.getHeight() - 30),Assets.close);
            objects.add(backButton);

            //  botón para restarurar
            Boton restButton = new Boton(1,new Vector2((_graphics.getLogWidth() / 2) - (Assets.history.getWidth() / 2), _graphics.getLogHeight() - Assets.history.getHeight() - 30), Assets.history);
            objects.add(restButton);

            //  botón para pista
            Boton pistabutton = new Boton(2,new Vector2((_graphics.getLogWidth() - 100), _graphics.getLogHeight() - Assets.eye.getHeight() - 30), Assets.eye);
            objects.add(pistabutton);

        }
        catch (Exception e){
            System.out.println("Fallo al intentar crear el juego");
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

        //  Render de las celdas
        if (tab != null) {
            Celda casillas[][] = tab.GetCasillas();
            for (int i = 0; i < tab.GetSize(); i++) {
                for (int j = 0; j < tab.GetSize(); j++) {
                    casillas[i][j].render(g);
                }
            }
        }
        // Render de los demás objetos
        for(GameObject obj : objects){
            obj.render(g);
        }
    }

    @Override
    public void handleInput() {
        List<Input.TouchEvent> events = engine.getInput().GetTouchEvents();
        AbstractGraphics g = (AbstractGraphics) _graphics;

        for(int i = 0 ; i < events.size() ; i++){
            Input.TouchEvent currEvent = events.get(i);
            Vector2 eventPos = g.logPos(new Vector2(currEvent.x, currEvent.y));
            switch (currEvent.type){
                case Input.TouchEvent.touchDown:{
                    GameObject obj = getObjectClicked(eventPos);
                    //  Es de tipo texto o imagen
                    if(obj != null){
                        switch (obj.getId()){
                            case 0://BackButton
                            {
                                SelectionMenu menu = new SelectionMenu(engine);
                                engine.initNewApp(menu);
                                break;
                            }
                            case 1://HistoryButton
                            {
                                //  Deshacer movimiento
                                break;
                            }
                            case 3://pistaButton
                            {
                                //  pedir pistas

                                break;
                            }
                        }
                    }
                    else{
                        //  Es de tipo celda
                        obj = tab.getCeldaClicked(eventPos);
                        if(obj != null){
                            switch (((Celda)obj).getTypeColor()){
                                case GRIS:{
                                    tab.AgregaCeldaAzul(((Celda)obj).getIndex());
                                    break;
                                }
                                case AZUL:{
                                    tab.AgregaCeldaRoja(((Celda)obj).getIndex());
                                    tab.QuitaCeldaAzul(((Celda)obj).getIndex());
                                    break;
                                }
                                case ROJO:{
                                    tab.QuitaCeldaRoja(((Celda)obj).getIndex());
                                    break;
                                }
                                default:{
                                    break;
                                }
                            }
                            obj.clicked();
                        }
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
            //  Buscando entre los objetos que son textos, img ,etc (no incluidas las celdas)
            if(objects.get(gameObjIndex).isInteractive() && objects.get(gameObjIndex).isClicked(eventPos)){
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
