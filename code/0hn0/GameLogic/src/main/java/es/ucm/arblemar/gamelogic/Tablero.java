package es.ucm.arblemar.gamelogic;


import com.sun.tools.javac.util.Pair;

import java.util.Random;


public class Tablero {

    private int _size = 4;
    private int adyacentes = 0;
    private Celda[][] casillas;
    private Pair<Integer,Integer> [] indexAzules;
    private Pair<Integer,Integer> [] indexRojos;



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
        indexAzules = new Pair[circulosAzules];

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
                    indexAzules[contAzul] = new Pair<Integer, Integer>(indX,indY);
                    contAzul++;
                }
            }
            azulesPuesto = contAzul >= circulosAzules;
        }


        System.out.println("Azules hecho");

        //  Número de rojos a poner
        int circulosRojos = r.nextInt(_size) + 1;
        //  Bucle para poner los rojos
        boolean rojosPuesto = false;
        //  Contador de cuantos rojos se han puesto
        int contRojos = 0;
        while (!rojosPuesto){
            int indX = r.nextInt(_size);
            int indY = r.nextInt(_size);
            if(!casillas[indX][indY].IsLock()){
                if(RedValid(indX,indY)){
                    casillas[indX][indY] = new CeldaRoja(-1);
                    casillas[indX][indY]._lock = true;
                    contRojos++;
                }
            }
            rojosPuesto = contRojos >= circulosRojos;

        }

        System.out.println("Rojos hecho");

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
                || !casillas[(int)coors._x][(int)coors._y].IsLock()
                || casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO) {
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
            else if(casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.AZUL)
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

    private boolean RedValid(int x, int y){

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
        //  Cantidad de salidas que tiene un azul
        int salidas = 0;
        boolean finish = false;
        while(!finish) {
            /**
             * Comprobación de si la casilla adyacente es gris o está fuera de los límites del grid
             * para cambiar de dirección o parar de buscar cuando ya no haya más que comprobar
             * */
            if (coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    //|| (Math.abs(y - coors._y)) >= (valor + 2)
                    || casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.ROJO) {

                index++;

                if (index < dirs.length) {
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
            else if (casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.AZUL) {

                //if((index == 0 || index == 1) && (Math.abs(y - coors._y) <= ((CeldaAzul)casillas[x][y]).getValue())){
//
                //    //salidas++;
                //}
                //else if((index == 2 || index == 3) && (Math.abs(x - coors._x) <= ((CeldaAzul)casillas[x][y]).getValue()) ){
                //    salidas++;
                //}
                index++;

                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces no es válido
                 *  Se termina la búsqueda
                 */
                finish = salidas >= dirs.length - 1;
            }
            // La casilla es gris
            else {
                coors._x = x + currentDir._x;
                coors._y = y + currentDir._y;
            }

        }
        /**
         * Si la cantidad de salidas que tiene este azul es menor o igual a 3,
         * existe una solución para este azul.
         * */
        return salidas <= dirs.length - 1;
    }

    public Pair<Integer,Integer> [] GetIndexAzules(){
        return indexAzules;
    }
    public Pair<Integer,Integer> [] GetIndexRojas(){
        return indexRojos;
    }

    public Celda[][] GetCasillas(){
        return casillas;
    }

    public int GetSize() {
        return _size;
    }
}