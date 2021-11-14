package es.ucm.arblemar.gamelogic.gameobject;

/**
 * Clase abstracta de las celdas
 */

import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;

/**
 * Comportamiento de la celda cuando se le da click
 * @return Devuelve si la celda está bloqueada o no, de manera que
 * las celdas rojas y azules iniciales no se puedan modificar, solo las
 * que se hayan inicializado como grises
 */
public abstract class Celda extends GameObject {

    // Determina si esta celda está bloqueada
    protected boolean _lock = false;
    //  Tipo de celda
    protected TipoCelda _tipoCelda = TipoCelda.GRIS;
    //  Index dentro del tablero
    protected Vector2 index;

    //  color para pintar el circulo
    protected int color;
    //  Radio del circulo
    protected float _diametro;
    //  Valor de esta celda
    protected int valor;

    public Celda(TipoCelda t, Vector2 ind,int _id) {
        super(_id);
        _tipoCelda = t;
        index = ind;
    }

    //  Determina si la celda ha sido "clickeada"
    @Override
    public boolean isClicked(es.ucm.arblemar.engine.Vector2 mouseClicked){
        double xDiff = (pos._x + (_diametro / 2)) - mouseClicked._x;
        double yDiff = (pos._y + (_diametro / 2)) - mouseClicked._y;
        double distance = Math.sqrt((Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
        return distance <= (int)(_diametro / 2);
    }

    public boolean isInteractive() {
        return interactive;
    }

    public boolean isLock(){
        return _lock;
    }

    @Override
    public void clicked() {

    }

    public void setLock(boolean lock){
        _lock = lock;
    }

    public void setTypeColor(TipoCelda type) {
        _tipoCelda = type;
    }

    public void setColor(int c) {
        color = c;
    }

    public void setValue(int v){
        valor = v;
    }

    public TipoCelda getTypeColor(){
        return _tipoCelda;
    }

    public Vector2 getIndex(){
        return index;
    }

    public int getValue(){
        return valor;
    }
};