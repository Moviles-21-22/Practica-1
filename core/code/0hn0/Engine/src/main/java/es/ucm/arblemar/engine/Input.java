package es.ucm.arblemar.engine;

import java.util.List;

public interface Input {
    static enum TouchEvent {
        touchDown(0),
        touchUp(1),
        touchDrag(2);

        private int x, y;
        private int value;

        public void setX(int _x){
            x = _x;
        }
        public void setY(int _y){
            y = _y;
        }

        public int getValue() {
            return value;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }

        TouchEvent(int i) {
            this.value = i;
        }
    }

    //static class TouchEvent{
    //    public static final int touchDown = 0;
    //    public static final int touchUp = 1;
    //    public int type, x,y;
    //}
    public List<TouchEvent> GetTouchEvents();
}
