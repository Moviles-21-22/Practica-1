package es.ucm.arblemar.a0hn0;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import es.ucm.arblemar.androidengine.AndroidEngine;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.gamelogic.estados.LoadAssets;
import es.ucm.arblemar.gamelogic.estados.MainMenu;

public class MainActivity extends AppCompatActivity {

    private AndroidEngine engine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        engine = new AndroidEngine(this,400,600);
        LoadAssets assets = new LoadAssets(engine);
        if(!engine.init(assets,"oh no",400,600)){
            System.err.println("Error al inicializar el engine");
            return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        engine.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        engine.onPause();
    }
}