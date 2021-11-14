package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaRoja extends Celda {
    private Font _font;
    private int _tamFont;

    public CeldaRoja(Vector2 ind, Vector2 _pos, float d){
        super(TipoCelda.ROJO,ind);
        _lock = true;
        this._pos = _pos;
        color = 0xFF384BFF;
        _diametro = d;
        valor = 0;
    }

    public CeldaRoja(Vector2 ind, Vector2 _pos,int _valor, Font font, int tamFont, float d){
        super(TipoCelda.ROJO,ind);
        _lock = true;
        this._pos = _pos;
        color = 0xFF384BFF;
        _diametro = d;
        valor = _valor;
        _font = font;
        _tamFont = tamFont;
    }

    @Override
    public void init() {

    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(_pos, (int) _diametro);

        if (valor > 0) {
            g.setColor(0XFFFFFFFF);
            g.drawText(Integer.toString(valor), (int)(_pos._x + (_diametro / 2) - _tamFont/4), (int)(_pos._y + (_diametro / 2) + _tamFont/3), _font, _tamFont);
        }
//        if(interactive){
//            g.setColor(0xFF000FF);
//            g.drawCircle(_pos, (int) _diametro);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}