package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaAzul extends Celda {

    public CeldaAzul(int _valor, Vector2 ind, int _id, Vector2 _pos) {
        super(TipoCelda.AZUL, ind,_id);
        _lock = true;
        valor = _valor;
        pos = _pos;
        color = 0x1CC0E0FF;
        radio = 100;
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

    public int getValue(){
        return valor;
    }


    @Override
    public void init() {

    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(pos, (int)radio);
        g.setColor(0XFFFFFFFF);
        g.drawText(Integer.toString(valor), (int)(pos._x + (radio / 2)), (int)(pos._y + (radio / 2)));
//        if(interactive){
//            g.setColor(0X333333FF);
//            g.drawCircle(pos,radio);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}