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
    private int anchura;
    //  Altura de la zona interactuable
    private int altura;

    public Texto(Vector2 _pos ,int _color, Font _fuente, int _tam,int _id,int _altura, int _anchura) {
        super(_id);
        color = _color;
        fuente = _fuente;
        tam = _tam;
        pos = _pos;
        altura = _altura;
        anchura = _anchura;
    }

    public int getColor(){
        return color;
    }

    @Override
    // TODO : TIWARDO
    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;
        //Rectangle clickRect = new Rectangle(mouseClicked._x,mouseClicked._y,1,1);
        return false;
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
            g.drawText(texto, pos._x , pos._y + altura, fuente, tam);
            if(interactive){
                g.drawRect(pos._x, pos._y, anchura, altura);
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
