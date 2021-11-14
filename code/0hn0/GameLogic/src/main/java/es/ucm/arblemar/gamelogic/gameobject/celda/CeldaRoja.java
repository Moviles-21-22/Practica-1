package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaRoja extends Celda {
    private Font _font;
    private int _tamFont;

    public CeldaRoja(Vector2 ind, int _id, Vector2 _pos, float d){
        super(TipoCelda.ROJO,ind,_id);
        _lock = true;
        pos = _pos;
        color = 0xFF384BFF;
        _diametro = d;
        valor = 0;
    }

    public CeldaRoja(Vector2 ind, int _id, Vector2 _pos,int _valor, Font font, int tamFont, float d){
        super(TipoCelda.ROJO,ind,_id);
        _lock = true;
        pos = _pos;
        color = 0xFF384BFF;
        _diametro = d;
        valor = _valor;
        _font = font;
        _tamFont = tamFont;
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
        g.fillCircle(pos, (int) _diametro);

        if (valor > 0) {
            g.setColor(0XFFFFFFFF);
            g.drawText(Integer.toString(valor), (int)(pos._x + (_diametro / 2) - _tamFont/4), (int)(pos._y + (_diametro / 2) + _tamFont/3), _font, _tamFont);
        }
//        if(interactive){
//            g.setColor(0X333333FF);
//            g.drawCircle(pos, (int) diametro);
//            g.drawRect(pos._x, pos._y, (int) diametro, (int) diametro);
//            Vector2 realPos = ((AbstractGraphics)g).realPos(pos);
//            g.drawRect(realPos._x, realPos._y, (int)(diametro), (int) diametro);
//        }
    }

    @Override
    public void update(float deltaTime) {

    }
}