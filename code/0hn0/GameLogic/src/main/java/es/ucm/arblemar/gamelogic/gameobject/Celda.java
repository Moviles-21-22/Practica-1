package es.ucm.arblemar.gamelogic.gameobject;

/**
 * Clase abstracta de las celdas
 */

import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;

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
    //  Tamaño del render
    protected int tam;
    //  Margen de la celda
    protected int margen;
    //  Radio del circulo
    protected float radio;
    //  Valor de esta celda
    protected int valor;

    public Celda(TipoCelda t, Vector2 ind,int _id) {
        super(_id);
        _tipoCelda = t;
        index = ind;
    }

    public abstract void render(Graphics g);

    protected abstract boolean Click();

    //  Determina si la celda ha sido "clickeada"
    public boolean isClicked(es.ucm.arblemar.engine.Vector2 mouseClicked){
        if(!interactive) return false;
        double xDiff = pos._x - mouseClicked._x;
        double yDiff = pos._y - mouseClicked._y;
        double distance = Math.sqrt((Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
        return distance <= radio;
    }

    public boolean isLock(){
        return _lock;
    }

    public void clicked() {
        // Cambia de color
        switch (_tipoCelda){
            case GRIS:{
                _tipoCelda = TipoCelda.AZUL;
                color = 0x1CC0E0FF;
                break;
            }
            case AZUL: {
                _tipoCelda = TipoCelda.ROJO;
                color = 0xFF384BFF;
                break;
            }
            case ROJO: {
                _tipoCelda = TipoCelda.GRIS;
                color = 0XEEEEEEFF;
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setLock(boolean lock){
        _lock = lock;
    }

    public void setTypeColor(TipoCelda type) {
        _tipoCelda = type;
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