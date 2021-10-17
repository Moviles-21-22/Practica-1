package es.ucm.arblemar.gamelogic;

public class CeldaAzul extends Celda{

    CeldaAzul(){
        super(TipoCelda.AZUL);
        _lock = true;
    }

    @Override
    protected void Init(){

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
}