package es.ucm.arblemar.gamelogic;

import java.awt.Graphics;

public class CeldaAzul extends Celda {

    private int valor = 0;

    CeldaAzul(int _valor, Vector2 ind) {
        super(TipoCelda.AZUL, ind);
        _lock = true;
        valor = _valor;
    }


    /**
     * Se hace grande y pequeño para indicar que no se puede modificar
     * @return devuelve true (bloqueado)
     */
    @Override
    public boolean Click(){
        // se hace grande y pequeño para indicar que no se puede
        // avisa de alguna manera al tablero para que las rojas muestren icono de bloqueado

        return _lock;
    }

    @Override
    public void render(Graphics g) {

    }

    public int getValue(){
        return valor;
    }

}