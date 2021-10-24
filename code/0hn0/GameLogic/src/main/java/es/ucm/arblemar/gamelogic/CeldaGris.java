package es.ucm.arblemar.gamelogic;

public class CeldaGris extends Celda {

    private int valor = 0;
    CeldaGris(int _valor){
        super(TipoCelda.GRIS);
        _lock = false;
        valor = _valor;
    }

    @Override
    /**
     * Alterna el color cada vez que se le da click
     */
    public boolean Click(){
        // Cambia de color
        switch (_tipoCelda){
            case GRIS:
                _tipoCelda = TipoCelda.AZUL;
                break;
            case AZUL:
                _tipoCelda = TipoCelda.ROJO;
            case ROJO:
                _tipoCelda = TipoCelda.GRIS;
            default:
                break;
        }

        // LLamada a Graphics para avisar del cambio de color
        // Graphics->changeColor(Color);

        return _lock;
    }

    @Override
    protected void Init() {

    }
}