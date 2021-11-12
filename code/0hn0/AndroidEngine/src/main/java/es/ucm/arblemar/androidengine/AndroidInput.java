package es.ucm.arblemar.androidengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.Input;

public class AndroidInput implements Input , View.OnTouchListener {

    private List<TouchEvent> events;

    public AndroidInput(){
        super();
        events = new ArrayList<>();
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public List<TouchEvent> GetTouchEvents() {
        if(!events.isEmpty()){
            List<TouchEvent> touchEvents = new ArrayList<>();
            touchEvents.addAll(events);
            events.clear();
            return touchEvents;
        }
        else return events;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TouchEvent currEvent = new TouchEvent();
        currEvent.type = TouchEvent.touchUp;
        currEvent.x = (int)event.getX();
        currEvent.y = (int)event.getY();
        return true;
    }
}
