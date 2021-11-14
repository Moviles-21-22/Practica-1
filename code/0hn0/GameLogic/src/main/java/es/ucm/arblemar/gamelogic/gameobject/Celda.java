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
    protected int anSt;  //0: no anim; 1: anim grande; 2: anim pequeña
    protected int contAnim;
    protected double timer;

    public Celda(TipoCelda t, Vector2 ind) {
        super(TipoGO.Celda);
        anSt = 0;
        contAnim = 0;
        _tipoCelda = t;
        index = ind;
        interactive = true;
        timer = 0;
    }

    //  Determina si la celda ha sido "clickeada"
    @Override
    public boolean isClicked(es.ucm.arblemar.engine.Vector2 mouseClicked){
        double xDiff = (_pos._x + (_diametro / 2)) - mouseClicked._x;
        double yDiff = (_pos._y + (_diametro / 2)) - mouseClicked._y;
        double distance = Math.sqrt((Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
        return distance <= (int)(_diametro / 2);
    }

    public boolean isInteractive() {
        return interactive;
    }

    public boolean isLock(){
        return _lock;
    }

    public void setLock(boolean lock){
        _lock = lock;
    }

    public void setTypeColor(TipoCelda type) {
        _tipoCelda = type;
        switch (type){
            case GRIS:
                color = 0XEEEEEEFF;
                break;
            case AZUL:
                color = 0x1CC0E0FF;
                break;
            case ROJO:
                color = 0xFF384BFF;
                break;
        }
    }

    public void setColor(int c) {
        color = c;
    }

    public void setValue(int v){
        valor = v;
    }

    public void setAnimState(int s) {
        anSt = s;
    }

    public TipoCelda getTypeColor(){
        return _tipoCelda;
    }

    public int getColor(){
        return color;
    }

    public Vector2 getIndex(){
        return index;
    }

    public int getValue(){
        return valor;
    }
};