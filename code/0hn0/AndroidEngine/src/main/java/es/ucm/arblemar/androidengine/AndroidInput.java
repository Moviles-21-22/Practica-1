package es.ucm.arblemar.androidengine;

import java.util.List;

import es.ucm.arblemar.engine.Input;

public class AndroidInput implements Input {

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public List<TouchEvent> GetTouchEvents() {
        return null;
    }
}
