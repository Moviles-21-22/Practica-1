package es.ucm.arblemar.gamelogic;
import java.awt.Rectangle;

import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;

public class Texto  extends GameObject{
    //  Color de la fuente
    private int color;
    //  Tipo de fuente
    private Font fuente;
    //  Tamaño de la fuente
    private float tam;
    //  Texto a escribir
    private String texto;
    //  Rectangulo para las colisiones
    Rectangle rect;

    public Texto(Rectangle _rect, int _color, Font _fuente,float _tam,int _id) {
        super(_id);

        color = _color;
        fuente = _fuente;
        tam = _tam;
        rect = _rect;
        pos = new es.ucm.arblemar.gamelogic.Vector2(-1,-1);
    }

    public Font getFuente(){
        return fuente;
    }

    public int getColor(){
        return color;
    }

    public void setFuenteTam(float _tam){
        tam = _tam;
        fuente.setSize(tam);
    }

    public void setRect(Rectangle _rect){
        rect = _rect;
        pos._x = rect.x - ((float)rect.width / 2);
        pos._y = rect.y - ((float)rect.height / 2);
    }


    @Override
    public boolean isClicked(Vector2 mouseClicked) {
        if(!interactive) return  false;
        Rectangle clickRect = new Rectangle(mouseClicked._x,mouseClicked._y,10,10);
        return rect.intersects(clickRect);
    }

    @Override
    public void init() {

    }

    public void render(Graphics g){
        if(renderActive){
            //float size = Math.abs( rect.width - rect.height) + 5;
            //fuente.setSize(size);
            //fuente.setPos(pos._x,pos._y + altura);
            g.setColor(color);
            g.setFont(fuente);
            g.drawText(texto, pos._x , pos._y);
            if(interactive){
                g.drawRect(pos._x ,pos._y ,(int)rect.width, (int)rect.height);
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
