package es.ucm.arblemar.gamelogic;

import java.awt.Graphics;

public class CeldaGris extends Celda {

    private int valor = 0;

    CeldaGris(Vector2 ind){
        super(TipoCelda.GRIS,ind);

        _lock = false;
        valor = 0;
        color = 0x9C9C9C9C;
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
    public void render(Graphics g) {
        //Círculo azul

        //setRadio();
//
        //g.setColor(0x1CC0E0FF);
        //int tam = g.getHeight() / 4;
        //int margin = tam / 20;
        //g.fillCircle(new es.ucm.arblemar.engine.Vector2((g.getWidth() / 2) - (tam + margin), (g.getHeight() / 2) - (tam / 2)), tam);
        ////Círculo rojo
        //g.setColor(0xFF384BFF);
        //g.fillCircle(new es.ucm.arblemar.engine.Vector2((g.getWidth() / 2) + margin, (g.getHeight() / 2) - (tam / 2)), tam);
    }
}