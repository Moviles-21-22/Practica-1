package es.ucm.arblemar.gamelogic;

import java.util.Random;


public class Tablero {

    private int _size;
    private int adyacentes = 0;
    private Celda[][] casillas;
    private Vector2[] indexAzules;
    private Vector2[] indexRojos;


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

        Random r = new Random();
        //  Inicializamos los azules aleatoriamente y lo menos descartable
        InitAzules(r);
        //  Inicializamos los rojos aleatoriamente y lo menos descartable
        InitRojas(r);
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
    private void InitAzules(Random r){
        /**
        * Número de azules a poner
        */
        int circulosAzules = r.nextInt(_size) + 1;
        indexAzules = new Vector2[circulosAzules];

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
                //  Inicializamos el valor de la celda de forma aleatoria
                int valor = r.nextInt(_size) + 1;
                if(AzulesValidos(indX,indY,valor)){
                    casillas[indX][indY] = new CeldaAzul(valor);
                    casillas[indX][indY]._lock = true;
                    indexAzules[contAzul] = new Vector2(indX,indY);
                    contAzul++;
                }
            }
            azulesPuesto = contAzul >= circulosAzules;
        }

        System.out.println("Azules hecho");

    }

    /**
     * Inicializa a las celdas rojas
     * */
    private void InitRojas(Random r){

        //  Número de rojos a poner
        int circulosRojos = r.nextInt(_size) + 1;
        indexRojos = new Vector2[circulosRojos];

        //  Bucle para poner los rojos
        boolean rojosPuesto = false;
        //  Contador de cuantos rojos se han puesto
        int contRojos = 0;
        while (!rojosPuesto){
            int indX = r.nextInt(_size);
            int indY = r.nextInt(_size);
            if(!casillas[indX][indY].IsLock()){
                if(RojosValidos(indX,indY)){
                    casillas[indX][indY] = new CeldaRoja(-1);
                    casillas[indX][indY]._lock = true;
                    indexRojos[contRojos] = new Vector2(indX,indY);
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

    private boolean RojosValidos(int x, int y){
        boolean finish = false;

        Vector2 coors = new Vector2(x,y);
        Vector2[] dirs = new Vector2[4];

        dirs[0] = new Vector2(0, -1);
        dirs[1] = new Vector2(0, 1);
        dirs[2] = new Vector2(-1, 0);
        dirs[3] = new Vector2(1, 0);

        int index = 0;

        coors._x += (int)dirs[0]._x;
        coors._y += (int)dirs[0]._y;

        boolean existeSolucion = false;

        while (!finish && !existeSolucion){
            if (coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.ROJO) {

                index++;

                if (index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    coors._x = x + (int)dirs[index]._x;
                    coors._y = y + (int)dirs[index]._y;
                }
                else {
                    finish = true;
                }
            }
            else if (casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.GRIS) {
                coors._x += (int)dirs[index]._x;
                coors._y += (int)dirs[index]._y;
            }
            else if(casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.AZUL){
                existeSolucion = AzulConSalidas((int)coors._x,(int)coors._y,x,y);
            }

        }
        return existeSolucion;
    }

    /**
     * Recorre el rango del valor en el eje x e y, mientras la suma de los adyacentes
     * no sea mayor al valor, es correcto.
     */
    private boolean AzulesValidos(int x, int y, int valor){
        adyacentes = 0;
        Vector2 coors = new Vector2(x,y);
        Vector2[] dirs = new Vector2[4];

        /**
         * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
         */
        dirs[0] = new Vector2(0, -1);
        dirs[1] = new Vector2(0, 1);
        dirs[2] = new Vector2(-1, 0);
        dirs[3] = new Vector2(1, 0);

        Vector2 currentDir = dirs[0];
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
            else if(casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.AZUL
                || casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.GRIS)
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

    /**
     * Determina si una celda azul tiene posibles salidas
     * @return: Si esta casilla azul tiene suficientes adyacentes
     * */
    private boolean AzulConSalidas(float x, float y, int redX, int redY){
        boolean finish = false;

        Vector2 coors = new Vector2(x,y);
        Vector2[] dirs = new Vector2[4];

        dirs[0] = new Vector2(0, -1);
        dirs[1] = new Vector2(0, 1);
        dirs[2] = new Vector2(-1, 0);
        dirs[3] = new Vector2(1, 0);

        int index = 0;
        int elementos = 0;

        coors._x += dirs[0]._x;
        coors._y += dirs[0]._y;
        while (!finish){
            if (coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.ROJO
                    //  Descartar de donde vengo
                    || ((int)coors._x == redX && (int)coors._y == redY)) {

                index++;

                if (index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    coors._x = x + (int)dirs[index]._x;
                    coors._y = y + (int)dirs[index]._y;
                }
                else {
                    finish = true;
                }
            }
            else if (casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.GRIS
                || casillas[(int) coors._x][(int) coors._y]._tipoCelda == TipoCelda.AZUL ) {
                coors._x += dirs[index]._x;
                coors._y += dirs[index]._y;
                elementos++;
                finish = elementos >= ((CeldaAzul)(casillas[(int)x][(int)y])).getValue();
            }

        }
        return  elementos >= ((CeldaAzul)(casillas[(int)x][(int)y])).getValue();
    }


    public Vector2 [] GetIndexAzules(){
        return indexAzules;
    }

    public Vector2 [] GetIndexRojas(){
        return indexRojos;
    }

    public Celda[][] GetCasillas(){
        return casillas;
    }

    /**
     * Devuelve el tamaño del tablero actual
     * */
    public int GetSize() {
        return _size;
    }
}