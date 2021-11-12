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
        if(!engine.init(assets,"Oh no")){
            return;
        }
        engine.run();
    }
}