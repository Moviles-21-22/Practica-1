package es.ucm.arblemar.gamelogic;

public class GameLogic {

    private Tablero tablero;
    private int tamTablero;

    public GameLogic(int _tamTablero){
        tamTablero = _tamTablero;
        initGame();
    }

    private void initGame(){
        tablero = new Tablero(tamTablero);
    }
}
