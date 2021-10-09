package es.ucm.arblemar.a0hn0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameSelectionActivity extends AppCompatActivity  implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);



    }

    public void goToGrid(View view){

    }

    @Override
    public void onClick(View view){
        Button button = (Button) findViewById(R.id.button8x8);
        string txt = button.getText();

    }
}


