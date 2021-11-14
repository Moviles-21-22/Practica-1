package es.ucm.arblemar.gamelogic.gameobject.celda;

import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.TipoCelda;
import es.ucm.arblemar.gamelogic.gameobject.Celda;

public class CeldaGris extends Celda {

    public CeldaGris(Vector2 ind, int _id,Vector2 _pos, float d){
        super(TipoCelda.GRIS,ind,_id);
        pos = _pos;
        _lock = false;
        //  Las celdas grises no tienen valor
        valor = 0;
        //  TODO: poner color gris
        //  Color gris
        color = 0XEEEEEEFF;
        //  TODO : puede variar
        _diametro = d;
        interactive = true;
    }

    @Override
    public void clicked(){
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
                color = 0XEEEEEEFF;
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
        g.fillCircle(pos, (int) _diametro);
        if(interactive){
            g.setColor(0X333333FF);
            g.drawCircle(pos, (int) _diametro);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}