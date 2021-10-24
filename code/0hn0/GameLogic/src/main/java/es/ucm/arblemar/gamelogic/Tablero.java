package es.ucm.arblemar.gamelogic;


import java.util.Random;

public class Tablero {

    int _size = 4;
    private int adyacentes = 0;

    /*
    * Número de celdas grises cambiadas de color
    * */

    int contador = 0;
    private Celda[][] casillas;
    boolean [] locks;
    boolean rendirse = false;

    public Tablero(int size){
        _size = size;
        /*
        * Inicialización de las celdas en modo gris
        * */
        casillas = new Celda[_size][_size];
        for(int i = 0 ; i < _size ; i++){
            for(int j = 0 ; j < _size ; j++){
                casillas[i][j] = new CeldaGris(0);
            }
        }

        Init();
        RenderizaConsola();
    }

    private void RenderizaConsola() {
        System.out.println("Rojos: R   Azules: v   Grises: X");

        for(int x = 0 ; x < _size ; x++){
            for(int y = 0 ; y < _size ; y++){
                if(casillas[x][y]._tipoCelda == TipoCelda.GRIS){
                    System.out.print("X ");
                }
                else if(casillas[x][y]._tipoCelda == TipoCelda.ROJO){
                    System.out.print("R ");
                }
                else if(casillas[x][y]._tipoCelda == TipoCelda.AZUL){
                    System.out.print(((CeldaAzul)casillas[x][y]).getValue() + " ");
                }
            }
            System.out.println();
        }
    }

    /**
    * Inicializa el tablero y asegura que tenga solución
    */
    private void Init(){
        Random r = new Random();

        /**
        * Número de azules a poner
        */
        int circulosAzules = r.nextInt(_size) + 1;

        /**
         * Bucle para poner los azules
         * */
        boolean azulesPuesto = false;
        //  Contador de cuantos azules se han puesto
        int contAzul = 0;
        while (!azulesPuesto){
            int indX = r.nextInt(_size);
            int indY = r.nextInt(_size);
            if(!casillas[indX][indY].IsLock()){
                int valor = r.nextInt(_size) + 1;
                if(BlueValid(indX,indY,valor)){
                    //System.out.println("Pos " + indX + " " +indY);
                    casillas[indX][indY] = new CeldaAzul(valor);
                    casillas[indX][indY]._lock = true;
                    contAzul++;
                }
            }
            azulesPuesto = contAzul >= circulosAzules;
        }
        System.out.println("Azules hecho");

        //  Número de rojos a poner
        //int circulosRojos = r.nextInt(_size - 1) + 1;
        ////  Bucle para poner los azules
        //boolean rojosPuesto = false;
        ////  Contador de cuantos azules se han puesto
        //int contRojos = 0;
        //while (!rojosPuesto){
        //    int indX = r.nextInt(_size - 1) + 1;
        //    int indY = r.nextInt(_size - 1) + 1;
        //    if(!casillas[indX][indY].IsLock()){
        //        int valor = r.nextInt(_size - 1) + 1;
        //        if(BlueValid(indX,indY,valor)){
        //            //System.out.println("Pos " + indX + " " +indY);
        //            casillas[indX][indY] = new CeldaRoja(valor);
        //            casillas[indX][indY]._lock = true;
        //            contRojos++;
        //        }
        //    }
        //    rojosPuesto = contRojos >= circulosRojos;

        //}

    }

    /**
    * Recorre el rango del valor en el eje x e y, mientras la suma de los adyacentes
    * no sea mayor al valor, es correcto.
    */
    private boolean BlueValid(int x, int y, int valor){
        adyacentes = 0;
        Vector2D coors = new Vector2D(x,y);
        Vector2D [] dirs = new Vector2D[4];

        /**
        * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
        */
        dirs[0] = new Vector2D(0, -1);
        dirs[1] = new Vector2D(0, 1);
        dirs[2] = new Vector2D(-1, 0);
        dirs[3] = new Vector2D(1, 0);

        Vector2D currentDir = dirs[0];
        int index = 0;
        boolean finish = false;
        while(!finish) {
            /**
             * Comprobación de si la casilla adyacente es gris o está fuera de los límites del grid
             * para cambiar de dirección o parar de buscar cuando ya no haya más que comprobar
             * */
            if(coors._y < 0 || coors._y >= _size
                || coors._x < 0 || coors._x >= _size
                || !casillas[(int)coors._x][(int)coors._y].IsLock()) {
                index++;
                if(index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = dirs[index];
                    coors._x = x + currentDir._x;
                    coors._y = y + currentDir._y;
                }
                else {
                    finish = true;
                }
            }
            /**
             * Comprobación de si la casilla adyacente es azul
             * */
            else if(casillas[(int)coors._x][(int)coors._y].IsLock())
            {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;

                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces no es válido
                 *  Se termina la búsqueda
                 */
                finish = adyacentes > valor;
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return adyacentes <= valor;
    }
}