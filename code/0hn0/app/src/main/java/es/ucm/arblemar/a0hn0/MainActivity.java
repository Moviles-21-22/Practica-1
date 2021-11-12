package es.ucm.arblemar.a0hn0;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.arblemar.androidengine.AndroidEngine;
import es.ucm.arblemar.gamelogic.GameLogic;
import es.ucm.arblemar.gamelogic.estados.LoadAssets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidEngine engine = new AndroidEngine();
        LoadAssets assets = new LoadAssets(engine);
        //  Lanzar app
        // NOTA DE STIWI: Hay que pasarle el ancho y alto de la ventana
        // supongo que en Android no hace falta porque pilla la resolución
        // del móvil, así que pásale 0, 0 aunque sea para que no dé error
//        if(!engine.init(assets,"Oh no")){
//            return;
//        }
        engine.run();
    }
}