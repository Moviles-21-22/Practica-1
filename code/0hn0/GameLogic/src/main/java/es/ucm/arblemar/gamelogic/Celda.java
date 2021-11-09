package es.ucm.arblemar.gamelogic;

/**
 * Clase abstracta de las celdas
 */

import java.awt.Graphics;


/**
 * Comportamiento de la celda cuando se le da click
 * @return Devuelve si la celda está bloqueada o no, de manera que
 * las celdas rojas y azules iniciales no se puedan modificar, solo las
 * que se hayan inicializado como grises
 */
public abstract class Celda {

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

    public Celda(TipoCelda t, Vector2 ind) {
        _tipoCelda = t;
        index = ind;
    }

    public void setRadio(float imp){
        radio = imp / 4;
    }

    public void setCircle(){

    }


    protected abstract boolean Click();

    public TipoCelda GetColor(){
        return _tipoCelda;
    }

    public boolean IsLock(){
        return _lock;
    }

    public Vector2 getIndex(){
        return index;
    }

    public abstract void render(Graphics g);

};