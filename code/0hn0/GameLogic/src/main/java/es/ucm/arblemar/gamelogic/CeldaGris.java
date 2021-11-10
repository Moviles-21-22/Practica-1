package es.ucm.arblemar.gamelogic;

import es.ucm.arblemar.engine.Vector2;

public class CeldaGris extends Celda {

    public CeldaGris(Vector2 ind, int _id,Vector2 _pos){
        super(TipoCelda.GRIS,ind,_id);
        pos = _pos;
        _lock = false;
        //  Las celdas grises no tienen valor
        valor = 0;
        //  TODO: poner color gris
        //  Color gris
        color = 0X333333FF;
        //  TODO : puede variar
        radio = 100;
        interactive = true;
    }

    @Override
    /**
     * Alterna el color cada vez que se le da click
     */
    public boolean Click(){


        // LLamada a Graphics para avisar del cambio de color
        // Graphics->changeColor(Color);

        return _lock;
    }

    @Override
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
                color = 0X333333FF;
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(pos,radio);
        if(interactive){
            g.setColor(0X333333FF);
            g.drawCircle(pos,radio);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}