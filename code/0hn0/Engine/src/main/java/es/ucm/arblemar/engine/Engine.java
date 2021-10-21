package es.ucm.arblemar.engine;

public interface Engine {
    public void update(double deltaTime);
    public void handleInput();
    public void render();
    public void run();
    public void init();

    public Graphics getGraphics();
    public Input getInput();
}