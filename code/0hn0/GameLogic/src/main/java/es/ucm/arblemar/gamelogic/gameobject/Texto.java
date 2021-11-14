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
    //  Anchura de la zona interactuable
    private int _w;
    //  Altura de la zona interactuable
    private int _h;

    public Texto(Vector2 _pos, Vector2 _size ,int _color, Font _fuente, int _tam,int _id) {
        super(_id);
        color = _color;
        fuente = _fuente;
        tam = _tam;
        pos = _pos;
        _w = _size._x;
        _h = _size._y;
    }

    public int getColor(){
        return color;
    }

    @Override
    // TODO : TIWARDO
    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;
        return mouseClicked._x > pos._x && mouseClicked._x < pos._x + _w    // Limite superior
                && mouseClicked._y > pos._y && mouseClicked._y < pos._y + _h;                     // Limite inferior
    }

    @Override
    public void clicked() {

    }

    @Override
    public void init() {

    }

    public void render(Graphics g){
        if(renderActive){
            g.setColor(color);
            g.setFont(fuente, tam);
            g.drawText(texto, pos._x , pos._y + _h, fuente, tam);
            if(interactive){
                g.drawRect(pos._x, pos._y, _w, _h);
                g.setColor(0X333333FF);
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
