package es.ucm.arblemar.engine;

import java.util.List;

public interface Input {
    public boolean init();

    static class TouchEvent{
        public static final int touchDown = 0;
        public static final int touchUp = 1;
        public int type, x,y;
    }
    public List<TouchEvent> GetTouchEvents();
}
