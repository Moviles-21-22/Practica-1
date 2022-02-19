package es.ucm.arblemar.engine;

public interface Engine {
    public boolean initNewApp(App newApp);

    public Graphics getGraphics();
    public Input getInput();
}