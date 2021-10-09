package es.ucm.arblemar.a0hn0;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class GameButton extends Button implements View.OnClickListener {

    //  Tamaño del grid a construir
    private int size = 8;

    public GameButton(Context context, int _size){
        super(context);
        size = _size;
    }

    @Override
    public void onClick(View v) {

    }

    public void setPosition(float x, float y){

    }
}
