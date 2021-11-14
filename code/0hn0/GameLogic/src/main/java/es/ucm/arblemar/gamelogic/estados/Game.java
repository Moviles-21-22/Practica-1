package es.ucm.arblemar.gamelogic.estados;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.GestorPistas;
import es.ucm.arblemar.gamelogic.Pista;
import es.ucm.arblemar.gamelogic.gameobject.Boton;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.Tablero;
import es.ucm.arblemar.gamelogic.gameobject.Icon;
import es.ucm.arblemar.gamelogic.gameobject.Texto;
import es.ucm.arblemar.gamelogic.assets.Assets;

public class Game implements App {
    Engine engine;
    Graphics _graphics;
    private int tam;
    private Tablero tab;
    private List<GameObject> objects;
    private List<Boton> images;
    private Icon[] candados;
    private Vector2 posPista;
    private Texto textoSuperior;
    private Texto textoSupDos;
    private boolean pistaPuesta = false;
    private boolean muestraCandados = false;

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

            Rectangle texSuperRect = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
            textoSuperior = new Texto(texSuperRect,0X313131FF ,Assets.jose,72,0);
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
        if (tab != null && tab.EsSolucion()) {
            SelectionMenu menu = new SelectionMenu(engine);
            engine.initNewApp(menu);
        }
    }

    @Override
    public void render() {
        Graphics g = engine.getGraphics();
        g.clear(0xFFFFFFFF);

        //  Render de las celdas
        if (tab != null) {
            if (pistaPuesta) {
                _graphics.setColor(0x313131FF);
                _graphics.fillCircle(new Vector2(posPista._x - 3, posPista._y - 3), (int)tab.GetCeldaSize() + 6);
            }
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
                            case 2://pistaButton
                            {
                                //  pedir pistas
                                GestorPistas p = new GestorPistas(tab.GetCasillas(), tab.GetIndexAzules(), tab.GetSize(), tab.GetIndexAzulesPuestas());
                                tab.PistasToEmpty();
                                p.actualizaPistas(tab);
                                List<Pista> pistasEncontradas = tab.GetPistasEncontradas();
                                Random r = new Random();
                                int id = r.nextInt(pistasEncontradas.size());
                                System.out.println("Pistas totales " + pistasEncontradas.size());
                                objects.remove(textoSuperior);
                                if (textoSupDos != null) {
                                    objects.remove(textoSupDos);
                                    textoSupDos = null;
                                }

                                //Cambia entre _size x _size y una pista
                                if (!pistaPuesta && id < 10) {
                                    //Ponemos una pista
                                    pistaPuesta = true;
                                    stringText(id);
                                    posPista = tab.GetCasillas()[pistasEncontradas.get(id).getIndex()._x][pistasEncontradas.get(id).getIndex()._y].getPos();
                                    System.out.println("Pistas celda x: " + pistasEncontradas.get(id).getIndex()._x + " y: " + pistasEncontradas.get(id).getIndex()._y);
                                }
                                else {
                                    //Volvemos a poner el _size x _size
                                    pistaPuesta = false;
                                    float width = (_graphics.getLogWidth() / 2) * 3, height = (_graphics.getLogWidth() / 7),
                                            posX = (_graphics.getLogWidth() / 3) - 15, posY = (_graphics.getLogHeight() / 12) - 10;

                                    Rectangle texSuperRect = new Rectangle((int)posX, (int)posY, (int)width, (int)height);
                                    textoSuperior = new Texto(texSuperRect,0X313131FF ,Assets.jose,72,0);
                                    textoSuperior.setTexto(tam + " x " + tam);
                                    objects.add(textoSuperior);
                                }
                                break;
                            }
                        }
                    }
                    else {
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
                        else {
                            obj = tab.getCeldaBlockClicked(eventPos);
                            if(obj != null){
                                //TODO: Hacer animación
                                muestraCandados = !muestraCandados;
                                if (!muestraCandados) {
                                    for (int r = 0; r < tab.GetIndexRojas().length; ++r) {
                                        objects.remove(candados[r]);
                                    }
                                }
                                else {
                                    candados = new Icon[tab.GetIndexRojas().length];
                                    for (int r = 0; r < tab.GetIndexRojas().length; ++r) {
                                        Vector2 pos = tab.GetCasillas()[tab.GetIndexRojas()[r]._x][tab.GetIndexRojas()[r]._y].getPos();
                                        float width = tab.GetCeldaSize() / 2, height = tab.GetCeldaSize() / 2;

                                        candados[r] = new Icon(Assets.lock, (int)(pos._x + width / 2), (int)(pos._y + height / 2), (int)width, (int)height, 1);
                                        objects.add(candados[r]);
                                    }
                                }
                            }
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

    //Escribe un texto en función de la pista elegida
    private void stringText(int id) {
        String text = "", text2 = "";
        float width = (_graphics.getLogWidth() / 2) * 3, height = (_graphics.getLogWidth() / 7),
                posX = (_graphics.getLogWidth() / 22), posY = (_graphics.getLogHeight() / 50),
                dist = height / 2;

        switch (id) {
            case 0: {
                posX = (_graphics.getLogWidth() / 4);
                text = "Azul completa,";
                text2 = "se puede cerrar";
                break;
            }
            case 1: {
                posX = (_graphics.getLogWidth() / 6);
                text = "   Se puede poner";
                text2 = "una pared adyacente";
                break;
            }
            case 2: {
                posX = (_graphics.getLogWidth() / 4);
                text = "  Es necesario";
                text2 = "poner una azul";
                break;
            }
            case 3: {
                posX = (_graphics.getLogWidth() / 10);
                text = "Tiene más vecinas azules";
                text2 = "    de las que debería";
                break;
            }
            case 4: {
                text = "Necesita más vecinas azules";
                text2 = " y, en cambio, está cerrada";
                break;
            }
            case 5:
            case 6: {
                posX = (_graphics.getLogWidth() / 8);
                text = "No puede haber celdas";
                text2 = "   azules sin vecinas";
                break;
            }
            case 7: {
                text = "   Se deben poner azules";
                text2 = "en la única dirección abierta";
                break;
            }
            case 8: {
                posX = (_graphics.getLogWidth() / 11);
                text = "   Suma alcanzable de";
                text2 = "adyacentes igual al valor";
                break;
            }
            case 9: {
                text = "No puede alcanzar su valor";
                text2 = "con las adyacentes que tiene";
                break;
            }
        }
        Rectangle texSuperRect = new Rectangle((int) posX, (int) posY, (int) width, (int) height);
        textoSuperior = new Texto(texSuperRect, 0X313131FF, Assets.jose, 32, 0);
        textoSuperior.setTexto(text);
        objects.add(textoSuperior);
        texSuperRect = new Rectangle((int) posX, (int) posY + (int) dist, (int) width, (int) height);
        textoSupDos = new Texto(texSuperRect, 0X313131FF, Assets.jose, 32, 0);
        textoSupDos.setTexto(text2);
        objects.add(textoSupDos);
    }
}
