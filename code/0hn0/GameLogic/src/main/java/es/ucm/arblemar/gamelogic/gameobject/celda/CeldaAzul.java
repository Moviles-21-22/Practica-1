package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaAzul extends Celda {
    private Font _font;
    private int _tamFont;

    public CeldaAzul(Font font, int tamFont, int _valor, Vector2 ind, Vector2 _pos, float d) {
        super(TipoCelda.AZUL, ind);
        _font = font;
        _lock = true;
        valor = _valor;
        this._pos = _pos;
        color = 0x1CC0E0FF;
        _diametro = d;
        _tamFont = tamFont;
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
        g.fillCircle(_pos, (int) _diametro);
        g.setColor(0XFFFFFFFF);
        g.drawText(Integer.toString(valor), (int)(_pos._x + (_diametro / 2) - _tamFont/4), (int)(_pos._y + (_diametro / 2) + _tamFont/3), _font, _tamFont);

//        if(interactive){
//            g.setColor(0xFF000FF);
//            g.drawCircle(_pos, (int) _diametro);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}