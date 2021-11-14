package es.ucm.arblemar.gamelogic.gameobject;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;

public class Texto  extends GameObject {
    //  Color de la fuente
    private int color;
    //  Tipo de fuente
    private Font fuente;
    //  Tamaño de la fuente
    private int tam;
    //  Texto a escribir
    private String texto;

    public Texto(Vector2 pos, Vector2 size ,int _color, Font _fuente, int _tam,int _id) {
        super(TipoGO.Texto);
        color = _color;
        fuente = _fuente;
        tam = _tam;
        _pos = pos;
        _size = size;
    }

    public int getColor(){
        return color;
    }

    @Override
    public void init() {

    }

    public void render(Graphics g){
        if(renderActive){
            g.setColor(color);
            g.setFont(fuente, tam);
            g.drawText(texto, _pos._x , _pos._y + _size._y, fuente, tam);
            if(interactive){
                g.setColor(0xFF0000FF);
                g.drawRect(_pos._x, _pos._y, _size._x, _size._y);
            }
        }
    }

    @Override
    public void update(float deltaTime) {

    }


    public void setTexto(String _texto){
        texto = _texto;
    }


}
