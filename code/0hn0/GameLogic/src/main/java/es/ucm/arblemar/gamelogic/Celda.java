package es.ucm.arblemar.gamelogic;

/**
 * Clase abstracta de las celdas
 */
public abstract class Celda {
    protected Celda(TipoCelda t) {
        _tipoCelda = t;
    }

    /**
     * Comportamiento de la celda cuando se le da click
     * @return Devuelve si la celda está bloqueada o no, de manera que
     * las celdas rojas y azules iniciales no se puedan modificar, solo las
     * que se hayan inicializado como grises
     */
    protected abstract boolean Click();

    protected abstract void Init();

    public TipoCelda GetColor(){
        return _tipoCelda;
    }

    /**
     * Atributos de Celda
     */
    protected boolean _lock = false;
    protected TipoCelda _tipoCelda = TipoCelda.GRIS;
};