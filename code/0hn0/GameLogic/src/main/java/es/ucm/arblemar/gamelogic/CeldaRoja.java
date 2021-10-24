package es.ucm.arblemar.gamelogic;

public class CeldaRoja extends Celda{

    private int valor = 0;

    CeldaRoja(int _valor){
        super(TipoCelda.ROJO);
        _lock = true;
        valor = _valor;
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