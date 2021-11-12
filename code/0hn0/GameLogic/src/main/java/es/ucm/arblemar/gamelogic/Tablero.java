package es.ucm.arblemar.gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.assets.Assets;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaAzul;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaGris;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaRoja;

public class Tablero {

    private int _size;
    private int adyacentes = 0;
    //  Contiene todas las celdas de todos los tipos del tablero
    private Celda[][] casillas;
    //  Index de las celdas azules puestas por el juego
    private Vector2[] indexAzulesOriginales;
    //  Index de las celdas rojas puestas por el juego
    private Vector2[] indexRojosOriginales;
    //  Index de las celdas azules puestas por el jugador
    private Vector<Vector2> indexAzulesPuestas;
    //  Index de las celdas rojas puestas por el jugador
    private Vector<Vector2> indexRojasPuestas;
    //  Vector de todas las pistas encontradas en el tablero
    private List<Pista> pistasEncontradas;
    //  Distancia que existe entre las celdas para posicionarlas
    private final float celdaDistancia = 100;
    //  Posición de la primera celda a colocar
    private Vector2 initPos;
    //  Puntero al engine
    private Engine engine;
    // Boolean que determina si el tablero tiene solución única
    private boolean solUnica = false;
    // Boolean que determina si el tablero es correcto
    private boolean tabCorrecto = false;

    public Tablero(int size,Engine _eng){
        engine = _eng;
        _size = size;
        /*
        * Inicialización de las celdas en modo gris
        * */
        Graphics g = _eng.getGraphics();

        while (!tabCorrecto) {
            tabCorrecto = true;
            casillas = new Celda[_size][_size];
            pistasEncontradas = new ArrayList<>();
            indexRojasPuestas = new Vector<>();
            indexAzulesPuestas = new Vector<>();

            float celdaPosX = (float) g.getWidth() / 4 * (size * 0.1f);
            float celdaPosY = (float) g.getHeight() / 3 * (size * 0.1f);
            initPos = new Vector2((int) celdaPosX, (int) celdaPosY);
            for (int i = 0; i < _size; i++) {
                for (int j = 0; j < _size; j++) {
                    Vector2 ind = new Vector2(i, j);
                    casillas[i][j] = new CeldaGris(ind, 0, new Vector2(initPos._x, initPos._y));
                    initPos._x += celdaDistancia;
                }
                initPos._x = (int) celdaPosX;
                initPos._y += celdaDistancia;
            }

            //Si sale del while y tabCorrecto es false vuelve a empezar
            while (tabCorrecto && !solUnica) {
                Random r = new Random();
                //  Inicializamos los rojos aleatoriamente y lo menos descartable
                InitRojas(r);
                //  Inicializamos los azules aleatoriamente y lo menos descartable
                InitAzules(r);

                compruebaTab();
            }
        }
        // Existen algunos casos incorrectos

        // Falta analizar que el tablero tiene una única solución antes de gestionar las pistas
//
        //GestorPistas p = new GestorPistas(this);
//
        //RenderizaConsola();
    }

    private void compruebaTab() {
        Tablero solucion = this;
        GestorPistas p = new GestorPistas(solucion);

        //Hay que tratar de resolver el tablero solucion usando las pistas
        //Si en una iteración del tablero usando las pistas NO aparece ningún cambio:
        //  -> Faltan datos (varias soluciones): solUnica = false;
        //Si en una iteración del tablero solucion aparece alguna pista de ERROR:
        //  -> El tablero es erroneo: tabCorrecto = false;

        //for (int i = 0; i < solucion.pistasEncontradas.size(); ++i) {
        //    switch (solucion.pistasEncontradas.get(i).getPista()) {
        //        case DONETE:
        //        {
        //            //Se pueden poner paredes en los extremos
        //            Vector2 ind = new Vector2(solucion.pistasEncontradas.get(i).getIndex()._x, solucion.pistasEncontradas.get(i).getIndex()._y);
//
        //            boolean finish = false;
//
        //            Vector2 coors = new Vector2(ind._x, ind._y);
        //            Vector2[] dirs = new Vector2[4];
//
        //            dirs[0] = new Vector2(0, -1);
        //            dirs[1] = new Vector2(0, 1);
        //            dirs[2] = new Vector2(-1, 0);
        //            dirs[3] = new Vector2(1, 0);
//
        //            int index = 0;
//
        //            coors._x += dirs[0]._x;
        //            coors._y += dirs[0]._y;
//
        //            while (!finish){
        //                if (coors._y < 0 || coors._y >= _size
        //                        || coors._x < 0 || coors._x >= _size
        //                        || !solucion.casillas[coors._x][coors._y]._lock
        //                        || solucion.casillas[ coors._x][ coors._y]._tipoCelda == TipoCelda.ROJO) {
//
        //                    index++;
//
        //                    if (index < dirs.length) {
        //                        // Reseteamos los valores para comprobar en la siguiente dirección
        //                        coors._x = ind._x + dirs[index]._x;
        //                        coors._y = ind._y + dirs[index]._y;
        //                    }
        //                    else {
        //                        finish = true;
        //                    }
        //                }
        //                //  Si encontramos una celda gris, la ponemos en rojo y cambiamos dirección
        //                else if (solucion.casillas[coors._x][coors._y]._tipoCelda == TipoCelda.GRIS) {
        //                    Vector2 indR = new Vector2(coors._x, coors._y);
        //                    solucion.casillas[coors._x][coors._y] = new CeldaRoja(indR,0, casillas[coors._x][coors._y].pos);
//
        //                    index++;
//
        //                    if (index < dirs.length) {
        //                        // Reseteamos los valores para comprobar en la siguiente dirección
        //                        coors._x = ind._x + dirs[index]._x;
        //                        coors._y = ind._y + dirs[index]._y;
        //                    }
        //                    else {
        //                        finish = true;
        //                    }
        //                }
        //                //  Si es azul, pasamos a la siguiente
        //                else if(casillas[coors._x][coors._y]._tipoCelda == TipoCelda.AZUL){
        //                    coors._x += dirs[index]._x;
        //                    coors._y += dirs[index]._y;
        //                }
        //            }
        //            break;
        //        }
        //        case AZUL_INCORRECTO:
        //        {
        //            //Si se pusiese azul sería error, luego es pared
        //            Vector2 ind = new Vector2(solucion.pistasEncontradas.get(i).getIndex()._x, solucion.pistasEncontradas.get(i).getIndex()._y);
        //            solucion.casillas[ind._x][ind._y] = new CeldaRoja(ind,0, casillas[ind._x][ind._y].pos);
        //            break;
        //        }
        //        case ADYACENTE_DONETE:
        //        {
        //            //Hay que poner azul obligatoriamente
        //            Vector2 ind = new Vector2(solucion.pistasEncontradas.get(i).getIndex()._x, solucion.pistasEncontradas.get(i).getIndex()._y);
        //            solucion.casillas[ind._x][ind._y] = new CeldaAzul(-1, ind,0, casillas[ind._x][ind._y].pos);
        //            //TODO: Necesitamos un celda azul sin valor
        //            break;
        //        }
        //        //case SOBRE_ADYACENCIA_AZUL:
        //        //{
        //        //    break;
        //        //}
        //        //case SOBRE_ADYACENCIA_ROJA:
        //        //{
        //        //    break;
        //        //}
        //        //case NO_VEO_AZUL:
        //        //{
        //        //    break;
        //        //}
        //        //case AZUL_AISLADA:
        //        //{
        //        //    break;
        //        //}
        //        //case ONE_DIRECTION:
        //        //{
        //        //    break;
        //        //}
        //        //case SUMA_ALCANZABLE:
        //        //{
        //        //    break;
        //        //}
        //        //case SUMA_MENOR:
        //        //{
        //        //    break;
        //        //}
        //        //case PISTA_10:
        //        //{
        //        //    break;
        //        //}
        //        //case MAX:
        //        //{
        //        //    break;
        //        //}
        //    }
        //}

        tabCorrecto = true;
        solUnica = true;
    }

    private void RenderizaConsola() {
        System.out.println("Rojos: R   Azules: v   Grises: X");

        for(int x = 0 ; x < _size ; x++){
            for(int y = 0 ; y < _size ; y++){
                if(casillas[x][y].GetColor() == TipoCelda.GRIS){
                    System.out.print("X ");
                }
                else if(casillas[x][y].GetColor() == TipoCelda.ROJO){
                    System.out.print("R ");
                }
                else if(casillas[x][y].GetColor() == TipoCelda.AZUL){
                    System.out.print(((CeldaAzul)casillas[x][y]).getValue() + " ");
                }
            }
            System.out.println();
        }
    }

    //  Devuelve la celda que ha sido pulsada del tablero
    public GameObject getCeldaClicked(Vector2 mousePos){
        boolean encontrado = false;
        int indX = 0;
        int indY = 0;
        while (!encontrado && indY < _size){
            if(casillas[indX][indY].isClicked(mousePos)){
                encontrado = true;
                return casillas[indX][indY];
            }
            else{
                indX++;
                if(indX >= _size){
                    indX = 0;
                    indY++;
                }
            }
        }
        return null;
    }

    /**
    * Inicializa las celdas azules indescartable
    */
    private void InitAzules(Random r){
        /**
        * Número de azules a poner
        */
        int circulosAzules = r.nextInt(_size) + 2;
        indexAzulesOriginales = new Vector2[circulosAzules];

        /**
         * Bucle para poner los azules
         * */
        boolean azulesPuesto = false;
        //  Contador de cuantos azules se han puesto
        int contAzul = 0;
        while (!azulesPuesto){
            int indX = r.nextInt(_size);
            int indY = r.nextInt(_size);
            if(!casillas[indX][indY].IsLock()) {
                //  Inicializamos el valor de la celda de forma aleatoria
                int valor = r.nextInt(_size) + 1;
                if(AzulesValidos(indX, indY, valor)) {
                    Vector2 ind = new Vector2(indX,indY);
                    Vector2 pos = casillas[indX][indY].getPos();
                    casillas[indX][indY] = new CeldaAzul(Assets.jose, 10, valor, ind,0,pos);
                    casillas[indX][indY].setLock(true);
                    indexAzulesOriginales[contAzul] = new Vector2(indX,indY);
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
        indexRojosOriginales = new Vector2[circulosRojos];

        //  Bucle para poner los rojos
        boolean rojosPuesto = false;
        //  Contador de cuantos rojos se han puesto
        int contRojos = 0;
        while (!rojosPuesto){
            int indX = r.nextInt(_size);
            int indY = r.nextInt(_size);
            if(!casillas[indX][indY].IsLock()){
                //if(RojosValidos(indX,indY)){
                if(casillas[indX][indY].GetColor() == TipoCelda.GRIS) {
                    Vector2 ind = new Vector2(indX,indY);
                    Vector2 pos = casillas[indX][indY].getPos();
                    casillas[indX][indY] = new CeldaRoja(ind,0,pos);
                    casillas[indX][indY].setLock(true);
                    indexRojosOriginales[contRojos] = new Vector2(indX,indY);
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
     * @return: Si colocar esta celda en la posición xy es válida, devuelve true
    */
    private boolean RojosValidos(int x, int y) {
        boolean finish = false;

        Vector2 coors = new Vector2(x,y);
        Vector2[] dirs = new Vector2[4];

        dirs[0] = new Vector2(0, -1);
        dirs[1] = new Vector2(0, 1);
        dirs[2] = new Vector2(-1, 0);
        dirs[3] = new Vector2(1, 0);

        int index = 0;

        coors._x += dirs[0]._x;
        coors._y += dirs[0]._y;

        boolean existeSolucion = false;

        while (!finish && !existeSolucion){
            if (coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || !casillas[coors._x][coors._y].IsLock()
                    || casillas[ coors._x][ coors._y].GetColor() == TipoCelda.ROJO) {

                index++;

                if (index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    coors._x = x + dirs[index]._x;
                    coors._y = y + dirs[index]._y;
                }
                else {
                    finish = true;
                }
            }
            //  Si encontramos una celda gris, pasamos a la siguiente celda en la misma dirección
            else if (casillas[coors._x][coors._y].GetColor() == TipoCelda.GRIS) {
                coors._x += dirs[index]._x;
                coors._y += dirs[index]._y;
            }
            //  Si es azul, comprobamos que dicho azul tiene más salidas para no bloquear una posible solución
            else if(casillas[coors._x][coors._y].GetColor() == TipoCelda.AZUL){
                existeSolucion = AzulConSalidas(coors._x,coors._y,x,y);
            }

        }
        return existeSolucion;
    }

    /**
     * Recorre el rango del valor en el eje x e y, mientras la suma de los adyacentes
     * no sea mayor al valor, es correcto.
     * @return: Si colocar esta celda en la posición xy es válida, devuelve true
     */
    private boolean AzulesValidos(int x, int y, int valor) {
        adyacentes = 0;
        int adyAzules = 0;
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
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        int index = 0;
        boolean finish = false;
        while(!finish) {
            /**
             * Comprobación de si la casilla adyacente es gris o está fuera de los límites del grid
             * para cambiar de dirección o parar de buscar cuando ya no haya más que comprobar
             * */
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || !casillas[coors._x][coors._y].IsLock()
                    || casillas[coors._x][coors._y].GetColor() == TipoCelda.ROJO) {
                index++;
                if(index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = dirs[index];
                    coors._x = x + currentDir._x;
                    coors._y = y + currentDir._y;
                }
                else {
                    return adyacentes <= valor;
                }
            }
            /**
             * Comprobación de si la casilla adyacente es azul
             * */
            else if(casillas[coors._x][coors._y].GetColor() == TipoCelda.AZUL
            || casillas[coors._x][coors._y].GetColor() == TipoCelda.GRIS)
            {
                adyacentes++;
                if(casillas[coors._x][coors._y].GetColor() == TipoCelda.AZUL)
                    adyAzules++;

                // Nos movemos a la siguiente casilla
                coors._x += currentDir._x;
                coors._y += currentDir._y;

                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces
                 *  Se termina la búsqueda
                 */
                if (adyAzules > valor)
                    return false;
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return true;
    }

    /**
     * Determina si una celda azul tiene posibles salidas
     * @return: Si esta celda azul tiene suficientes adyacentes, devuelve true
     * */
    private boolean AzulConSalidas(float x, float y, int redX, int redY){
        boolean finish = false;

        Vector2 coors = new Vector2((int)x,(int)y);
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
                    || casillas[coors._x][coors._y].GetColor() == TipoCelda.ROJO
                    //  Descartar de donde vengo
                    || (coors._x == redX && coors._y == redY)) {

                index++;

                if (index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    coors._x = (int)x +dirs[index]._x;
                    coors._y = (int)y +dirs[index]._y;
                }
                else {
                    finish = true;
                }
            }
            else if (casillas[coors._x][coors._y].GetColor() == TipoCelda.GRIS
                || casillas[coors._x][coors._y].GetColor() == TipoCelda.AZUL ) {
                coors._x += dirs[index]._x;
                coors._y += dirs[index]._y;
                elementos++;
                finish = elementos >= casillas[(int)x][(int)y].getValue();
            }
        }
        return  elementos >= casillas[(int)x][(int)y].getValue();
    }

    /**
     * Agrega una celda azul en el tablero
     * */
    public void AgregaCeldaAzul(Vector2 ind){
        indexAzulesPuestas.add(ind);
    }

    /**
     * Agrega una celda azul en el tablero
     * */
    public void QuitaCeldaAzul(Vector2 ind){
        int p = indexAzulesPuestas.indexOf(ind);
        if(p != -1){
            indexAzulesPuestas.remove(p);
        }
    }

    /**
     * Agrega una celda azul en el tablero
     * */
    public void QuitaCeldaRoja(Vector2 ind){
        int p = indexRojasPuestas.indexOf(ind);
        if(p != -1){
            indexRojasPuestas.remove(p);
        }
    }

    /**
     * Agrega una celda azul en el tablero
     * */
    public void AgregaCeldaRoja(Vector2 ind){
        indexRojasPuestas.add(ind);
    }

    /**
     * @return: Devuelve los index de todas las celdas azules instanciadas
     * */
    public Vector2 [] GetIndexAzules(){
        return indexAzulesOriginales;
    }

    /**
     * @return: Devuelve los index de todas las celdas azules instanciadas
     * */
    public Vector<Vector2> GetIndexAzulesPuestas(){
        return indexAzulesPuestas;
    }

    /**
     * @return: Todos los index de las celdas rojas instanciadas
     * */
    public Vector2 [] GetIndexRojas(){
        return indexRojosOriginales;
    }

    /**
     * @return: Todas las celdas instanciadas
     * */
    public Celda[][] GetCasillas(){
        return casillas;
    }

    /**
     * @return: El tamaño del tablero
     * */
    public int GetSize() {
        return _size;
    }

    public void AgregaPista(Pista p){
        pistasEncontradas.add(p);
    }
}