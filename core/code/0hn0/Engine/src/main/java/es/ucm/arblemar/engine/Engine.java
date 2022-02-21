package es.ucm.arblemar.engine;

public interface Engine {
    public void initNewApp(App newState);

    public Graphics getGraphics();
    public Input getInput();
}