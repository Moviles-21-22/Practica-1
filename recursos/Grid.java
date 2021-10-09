public class Grid {
    /*
    *   Tamaño del grid: size x size
     */
    int _size = 4;
    // Numero de celdas grises cambiadas de color
    int contador = 0;

    Circulo[,] circulos;
    boolean [,] locks;

    boolean rendirse = false;

    public Grid(int size){
        _size = size;
        // Inicialización de la matriz de círculos
        circulos = new Circulo[size, size];
    
        Init();
    }
    
    // Inicializa el tablero y asegura que tenga solución
    private Init(){
        /*
        *   Construye cada botico del juego
        */
        for(...){
            // Construimos cada celda de forma aleatoria
            Circulo circulo = new Celda();
        }

        // En caso de que no haya solución para el tablero
        // se vuelve a construir uno nuevo
        if (!SolucionarTablero())
            Init();
    }

    public boolean CompruebaTablero(){
        
    }
}