package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaGris extends Celda {

    public CeldaGris(Vector2 ind, Vector2 _pos, float d){
        super(TipoCelda.GRIS,ind);
        this._pos = _pos;
        _lock = false;
        valor = 0;
        //  Color gris
        color = 0XEEEEEEFF;
        _diametro = d;
    }

    @Override
    public void render(es.ucm.arblemar.engine.Graphics g) {
        g.setColor(color);
        g.fillCircle(_pos, (int) _diametro);
    }
}