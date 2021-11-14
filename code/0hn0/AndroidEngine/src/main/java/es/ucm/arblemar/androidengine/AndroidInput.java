package es.ucm.arblemar.androidengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.Input;

public class AndroidInput implements Input , View.OnTouchListener {

    private List<TouchEvent> events;

    public AndroidInput(View view){
        super();
        events = new ArrayList<>();
        view.setOnTouchListener(this);
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public List<TouchEvent> GetTouchEvents() {
        synchronized (events){
            if(!events.isEmpty()){
                List<TouchEvent> touchEvents = new ArrayList<>();
                touchEvents.addAll(events);
                events.clear();
                return touchEvents;
            }
            else return events;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (events){
            TouchEvent currEvent = new TouchEvent();
            currEvent.type = TouchEvent.touchDown;
            currEvent.x = (int)event.getX();
            currEvent.y = (int)event.getY();
            events.add(currEvent);
            return true;
        }
    }
}
