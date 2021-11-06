package es.ucm.arblemar.engine;

public interface Engine {
    /**
     * Inicializa el engine
     * */
    public boolean init(App initAp, String nameGame);
    /**
     * Bucle principal del juego. Encargado de actualizar render y update de los
     * estados del juego, así como de llevar la cuenta del deltaTime
     * */
    public void run();

    public boolean initNewApp(App newApp);

    public Graphics getGraphics();
    public Input getInput();
}