package es.ucm.arblemar.gamelogic.estados;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input.TouchEvent;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.GameObject;
import es.ucm.arblemar.gamelogic.Texto;
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

            Rectangle tRect = new Rectangle(g.getWidth() / 2 - 100,g.getHeight() / 2 - 50,100,50);
            Texto t = new Texto(tRect,0X333333FF,Assets.josefinSans32,50,00);
            t.setTexto("JUGAR");
            t.setInteractive();
            gameObjects.add(t);

            Rectangle tituloRect = new Rectangle(g.getWidth() / 2 - 100,g.getHeight() / 3 - 100,100,100);
            Texto tituloText = new Texto(tituloRect,0X333333FF,Assets.molle,100,01);
            tituloText.setTexto("Oh no");
            gameObjects.add(tituloText);

            Rectangle infoRect = new Rectangle(g.getWidth() / 2 - 150,g.getHeight() / 2 + 100,100,100);
            Texto infoText = new Texto(infoRect,0X333333FF,Assets.josefinSans32,(float)g.getHeight() / 30,02);
            infoText.setTexto("Un juego copiado a Q42");
            gameObjects.add(infoText);

            Rectangle nameRect = new Rectangle(g.getWidth() / 2 - 150,g.getHeight() / 2 + 150,100,100);
            Texto nameText = new Texto(nameRect,0X333333FF,Assets.josefinSans32,(float)g.getHeight() / 30,03);
            nameText.setTexto("Creado por Martin Kool");
            gameObjects.add(nameText);
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

        // Fondo de pantalla
        g.setColor(0xFFFFFFFF);
        g.fillRect(0, 0, 400, 600);

        //Ponemos la imagen de la empresa (si conseguimos cargar la imagen)
        if (Assets.q42 != null) {
            g.drawImage(Assets.q42, 280, 350);
        }

        for(GameObject obj : gameObjects){
            obj.render(g);
        }
    }

    @Override
    //  Gestiona las colisiones del ratón con los objetos de la escena
    public void handleInput() {
        List<TouchEvent> events = _mainEngine.getInput().GetTouchEvents();

        for(int i = 0 ; i < events.size() ; i++){
            TouchEvent currEvent = events.get(i);
            Vector2 eventPos = new Vector2(currEvent.x, currEvent.y);
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

    // Devuelve el objecto que ha sido pulsado
    private GameObject getObjectClicked(Vector2 eventPos){
        boolean encontrado = false;
        int gameObjIndex = 0;
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