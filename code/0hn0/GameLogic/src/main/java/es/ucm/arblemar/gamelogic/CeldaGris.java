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
        color = 0XEEEEEEFF;
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
    public void init() {

    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(pos, (int)radio);
//        if(interactive){
//            g.setColor(0X333333FF);
//            g.drawCircle(pos,radio);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}