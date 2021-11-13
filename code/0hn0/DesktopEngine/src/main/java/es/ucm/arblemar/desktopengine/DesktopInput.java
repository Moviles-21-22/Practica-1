package es.ucm.arblemar.desktopengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;

public class DesktopInput implements Input, MouseListener, MouseMotionListener {

    private Engine _mainEngine;
    //  Lista de los eventos que maneja deskopPc
    private List<TouchEvent> events;

    public DesktopInput(Engine e){
        super();
        _mainEngine = e;
        events = new ArrayList<>();
    }

    public DesktopInput GetInput(){
        return this;
    }

    @Override
    public boolean init() {
        return true;
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
        //  Cuando se implementen los hilos usar el método de abajo
//        //  sincro para la hebra
//        synchronized (this){
//            //  Si tenemos eventos que devolver
//            if(!events.isEmpty()){
//                List<TouchEvent> touchEvents = new ArrayList<>();
//                touchEvents.addAll(events);
//                events.clear();
//                return touchEvents;
//            }
//            else return events;
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       // System.out.println("MOUSE CLICK");
       // TouchEvent currEvent =  new TouchEvent();
       // currEvent.type = TouchEvent.touchDown;
       // currEvent.x = e.getX();
       // currEvent.y = e.getY();
       // events.add(currEvent);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TouchEvent currEvent =  new TouchEvent();
        currEvent.type = TouchEvent.touchDown;
        currEvent.x = e.getX();
        currEvent.y = e.getY();
        events.add(currEvent);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        TouchEvent currEvent =  new TouchEvent();
        currEvent.type = TouchEvent.touchUp;
        currEvent.x = e.getX();
        currEvent.y = e.getY();
        events.add(currEvent);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }
}
