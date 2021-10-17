package es.ucm.arblemar.gamelogic;

public class Tablero {
    /*
     *   Tamaño del grid: size x size
     */
    int _size = 4;
    // Numero de celdas grises cambiadas de color
    int contador = 0;

    Celda[] celdas;
    boolean [] locks;

    boolean rendirse = false;

    public Tablero(int size){
        _size = size;
        // Inicialización de la matriz de círculos
        celdas = new Celda[size * size];

        Init();
    }

    // Inicializa el tablero y asegura que tenga solución
    private void Init(){
        /*
         *   Construye cada botico del juego
         */
        //for(...){
        //    // Construimos cada celda de forma aleatoria
        //Circulo circulo = new Celda();
        //}

        /**
         * Recursividad para inicializar un nuevo tablero
         * cada vez que el que se haya generado no tenga solución
         */
        if (!ExistSolution())
            Init();
    }

    public boolean ExistSolution(){

        return false;
    }
}