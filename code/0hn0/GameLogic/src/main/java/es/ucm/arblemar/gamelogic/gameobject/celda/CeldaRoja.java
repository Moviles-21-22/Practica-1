package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaRoja extends Celda {
    private Font _font;
    private int _tamFont;

    public CeldaRoja(Vector2 ind, int _id, Vector2 _pos, float rd){
        super(TipoCelda.ROJO,ind,_id);
        _lock = true;
        pos = _pos;
        color = 0xFF384BFF;
        radio = rd;
        valor = 0;
    }

    public CeldaRoja(Vector2 ind, int _id, Vector2 _pos,int _valor, Font font, int tamFont, float rd){
        super(TipoCelda.ROJO,ind,_id);
        _lock = true;
        pos = _pos;
        color = 0xFF384BFF;
        radio = rd;
        valor = _valor;
        _font = font;
        _tamFont = tamFont;
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
    public void clicked() {

    }

    @Override
    public void init() {

    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(pos, (int)radio);

        if (valor > 0) {
            g.setColor(0XFFFFFFFF);
            g.drawText(Integer.toString(valor), (int)(pos._x + (radio / 2) - _tamFont/4), (int)(pos._y + (radio / 2) + _tamFont/3), _font, _tamFont);
        }
//        if(interactive){
//            g.setColor(0X333333FF);
//            g.drawCircle(pos,radio);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}