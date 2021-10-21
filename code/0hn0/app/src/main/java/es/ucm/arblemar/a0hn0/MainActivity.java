package es.ucm.arblemar.a0hn0;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.arblemar.androidengine.AndroidEngine;
import es.ucm.arblemar.gamelogic.GameLogic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Lanzar engine y lógica
        AndroidEngine engine = new AndroidEngine(this);
        //  TODO :Pasar el tamaño del tablero a construir
        GameLogic logic = new GameLogic(4);
    }
}