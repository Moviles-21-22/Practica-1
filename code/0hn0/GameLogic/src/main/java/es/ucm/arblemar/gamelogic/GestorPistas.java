package es.ucm.arblemar.gamelogic;


import java.util.Vector;

import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaAzul;

public class GestorPistas {

    //  Tipo de pista
    private TipoPista _pista;
    //  Todas las celdas del grid
    private Celda[][] _casillas;
    //  Index de las celdas azules instanciadas
    private Vector2 [] _indexAzules ;
    //  Tamaño del tablero
    private int _size ;
    //  index que pertenece a esta pista
    private Vector2 index;

    private Vector<Vector2> _indexAzulesPuestas;

    /**
     * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
     */
    final Vector2[] _dirs = {
        new Vector2(0, -1),
        new Vector2(0, 1),
        new Vector2(-1, 0),
        new Vector2(1, 0)
    };


    public GestorPistas(Celda[][] t, Vector2 [] idA, int s, Vector<Vector2> idAP) {
        _casillas = t;
        _indexAzules = idA;
        _size = s;
        _indexAzulesPuestas = idAP;

        //actualizaPistas(t);
    }

    public void actualizaPistas(Tablero t) {
        for(int i = 0 ; i < TipoPista.MAX.getValue(); i++){
            BuscaPistas(t,i);
        }
    }

    /**
     * Determina que pistas existen en el tablero y las agrega al vector de pistasActivas
     * */
    private void BuscaPistas(Tablero tab, int choice) {
        Pista p;
        Vector2 indexFeedback;
        switch (choice){
            case 0:
            {
                //  Posición de la pista si es encontrada
                indexFeedback = PistaUno();

                // Pista encontrada
                if(indexFeedback._x != -1){
                    //index = indexFeedback;
                    p = new Pista(TipoPista.DONETE,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 1:
            {
                //  Posición de la pista si es encontrada
                indexFeedback = PistaDos();
                //  Pista encontrada
                if(indexFeedback._x != -1){
                    p = new Pista(TipoPista.AZUL_INCORRECTO,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 2:
            {
                indexFeedback = PistaTres();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.ADYACENTE_DONETE,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 3:
            {
                indexFeedback = PistaCuatro();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.SOBRE_ADYACENCIA_AZUL,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 4:
            {
                indexFeedback = PistaCinco();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.SOBRE_ADYACENCIA_ROJA,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 5:
            {
                indexFeedback = PistaSeis();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.NO_VEO_AZUL,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            //case 6:{
            //    indexFeedback = PistaSiete();
            //    if(indexFeedback._y != -1){
            //        p = new Pista(TipoPista.AZUL_AISLADA,indexFeedback);
            //        tab.AgregaPista(p);
            //    }
            //    break;
            //}
            case 7:{
                indexFeedback = PistaOcho();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.ONE_DIRECTION,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 8:{
                indexFeedback = PistaNueve();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.SUMA_ALCANZABLE,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            case 9:{
                indexFeedback = PistaDiez();
                if(indexFeedback._y != -1){
                    p = new Pista(TipoPista.SUMA_MENOR,indexFeedback);
                    tab.AgregaPista(p);
                }
                break;
            }
            default:{
                break;
            }
        }
    }

    /**
     * En sentido opuesto, una celda de tipo número no está cerrada pero si se ponen en
     * punto el resto de celdas vacías que tiene alcanzables no llegará a su valor, por lo
     * que es un futuro error. Si no se incluye esta pista, el programa dará incorrectamente
     * varias veces la pista 3 para al final terminar indicando el error de la 5.
     * */
    private  Vector2 PistaDiez() {
        Vector2 azulIncompleto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas grises que "ve"
        int grisesAdy = 0;
        //  Numero de celdas azules que "ve"
        int azulesAdy = 0;

        boolean finish = false;
        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Al acabar de ver en todas las direcciones
                //  He encontrado las condiciones necesarias para determinar que esta pista existe
                else if((grisesAdy + azulesAdy) < ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                    azulIncompleto = _indexAzules[indexAz];
                    finish = true;
                }
                //  Se acaban las direcciones, pasamos al siguiente azul y reiniciamos las direcciones
                else {
                    indexAz++;
                    //  No quedan más azules para procesar
                    if(indexAz >= _indexAzules.length){
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                    //  Reinicio de las direcciones para el siguiente azul
                    else {
                        grisesAdy = 0;
                        indexDir = 0;
                        azulesAdy = 0;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                }
            }
            //  Encuentro una celda gris sigo en la misma dirección
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS
            || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL){

                if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL){
                    azulesAdy++;
                }
                else {
                    grisesAdy++;
                }

                if((grisesAdy + azulesAdy) >= ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()) {
                    indexAz++;
                    //  No quedan más azules para procesar
                    if(indexAz >= _indexAzules.length){
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                    //  Reinicio de las direcciones para el siguiente azul
                    else {
                        grisesAdy = 0;
                        azulesAdy = 0;
                        indexDir = 0;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                }
                else{
                    coors._y += currentDir._y;
                    coors._x += currentDir._x;
                }
            }
        }
        return azulIncompleto;
    }


    /**
     * Un número no está cerrado y tiene varias direcciones, pero la suma alcanzable es el
     * valor que hay que conseguir. Basta con llenar el resto de celdas vacías para resolverlo.
     * Está también cubierta por la pista 3.
     * */
    private Vector2 PistaNueve(){
        Vector2 azulIncompleto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas azules que "ve"
        int adyacentes = 0;
        //  El numero de salidas que tiene esta celda
        int salidas = 0;

        boolean finish = false;
        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Al acabar de ver las direcciones, compruebo de que tenga salidas y que el numero
                //  de adyacentes sea menor que el valor de la celda.
                //  He encontrado las condiciones necesarias para determinar que esta pista existe
                else if(salidas > 1 && adyacentes == ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                    azulIncompleto = _indexAzules[indexAz];
                    finish = true;
                }
                //  Se acaban las direcciones, pasamos al siguiente azul y reiniciamos las direcciones
                else {
                    indexAz++;
                    //  No quedan más azules para procesar
                    if(indexAz >= _indexAzules.length){
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                    //  Reinicio de las direcciones para el siguiente azul
                    else {
                        adyacentes = 0;
                        indexDir = 0;
                        salidas = 0;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                }
            }
            //  Encuentro una celda gris, es una salida y cambio de dirección
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS){

                salidas++;
                indexDir++;
                //  Cambio de dirección al encontrar una salida
                if(indexDir < _dirs.length){
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Caso en el que la salida sea el ultimo caso posible de salida
                else{
                    //  He encontrado las condiciones necesarias para determinar que esta pista existe
                    if(salidas > 1 && adyacentes == ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                        azulIncompleto = _indexAzules[indexAz];
                        finish = true;
                    }
                    //  Paso al siguiente azul
                    else{
                        indexAz++;
                        if(indexAz < _indexAzules.length){
                            adyacentes = 0;
                            indexDir = 0;
                            salidas = 0;
                            currentDir = _dirs[indexDir];
                            coors._x = _indexAzules[indexAz]._x + currentDir._x;
                            coors._y = _indexAzules[indexAz]._y + currentDir._y;
                        }
                        else {
                            azulIncompleto._x = -1;
                            azulIncompleto._y = -1;
                            finish = true;
                        }
                    }
                }
            }
            //  Si encontramos una celda azul, sumamos adyacente
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL)
            {
                adyacentes++;
                // Pasamos al siguiente azul
                if(adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                    indexAz++;
                    //  Existen más azules
                    if(indexAz < _indexAzules.length){
                        adyacentes = 0;
                        indexDir = 0;
                        salidas = 0;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                    //  No quedan más azules por procesar
                    else {
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                }
                // Nos movemos a la siguiente casilla en la misma dirección
                else{
                    coors._y += currentDir._y;
                    coors._x += currentDir._x;
                }
            }
        }
        return azulIncompleto;
    }

    /**
     * Un número que no ve suficientes puntos no está aún cerrado y solo tiene abierta una
     * dirección. Está cubierta por la pista 3.
     * */
    private Vector2 PistaOcho(){
        Vector2 azulIncompleto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas azules que "ve"
        int adyacentes = 0;
        //  Determina si esta celda tiene una salida
        boolean salida = false;

        boolean finish = false;
        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Al acabar de ver las direcciones, compruebo de que tenga salida y que el numero
                //  de adyacentes sea menor que el valor de la celda.
                //  He encontrado las condiciones necesarias para determinar que esta pista existe
                else if(salida && adyacentes < _casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y].getValue()){
                    azulIncompleto = _indexAzules[indexAz];
                    finish = true;
                }
                //  Se acaban las direcciones, pasamos al siguiente azul y reiniciamos las direcciones
                else {
                    indexAz++;
                    indexDir = 0;

                    //  No quedan más azules para procesar
                    if(indexAz >= _indexAzules.length){
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                    //  Reinicio de las direcciones para el siguiente azul
                    else {
                        adyacentes = 0;
                        salida = false;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                }
            }
            //  Encuentro una celda gris, es una salida.
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS){
                //  Ya existe una salida encontrada anteriormente, descartada
                if(salida){
                    indexAz++;
                    azulIncompleto._x = -1;
                    azulIncompleto._y = -1;
                    //  Si todavia quedan azules, pasamos al siguiente azul y reiniciamos las direcciones
                    if(indexAz < _indexAzules.length){
                        adyacentes = 0;
                        indexDir = 0;
                        salida = false;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                    //  No quedan azules que procesar, pista no encontrada
                    else {
                        finish = true;
                    }
                }
                //  Se encuentra una salida por primera vez
                else {
                    salida = true;
                    indexDir++;
                    //  Cambio de dirección al encontrar una salida
                    if(indexDir < _dirs.length) {
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                    //  Caso en el que la salida sea el ultimo caso posible de salida
                    else {
                        //  He encontrado las condiciones necesarias para determinar que esta pista existe
                        if(adyacentes < ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                            azulIncompleto = _indexAzules[indexAz];
                            finish = true;
                        }
                    }
                }
            }
            //  Si encontramos una celda azul, sumamos adyacente
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL)
            {
                adyacentes++;
                //  No he encontrado las condiciones necesarias para determinar que esta pista existe, descartada
                if(adyacentes >= _casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y].getValue()){
                    indexAz++;
                    //  Si todavia quedan azules, pasamos al siguiente azul y reiniciamos las direcciones
                    if(indexAz < _indexAzules.length){
                        adyacentes = 0;
                        indexDir = 0;
                        salida = false;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    }
                    //  No quedan azules que procesar, pista no encontrada
                    else {
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                }
                // Nos movemos al siguiente azul
                else{
                    coors._y += currentDir._y;
                    coors._x += currentDir._x;
                }
            }

        }
        return azulIncompleto;
    }

    /**
     * En sentido opuesto, si hay una celda punto puesta por el usuario que está cerrada
     * y no ve a ninguna otra, entonces se trata de un error por el mismo motivo.
     * */
    private Vector2 PistaSiete(){
        //  Index de la celda con la pista
        Vector2 AzulIncorrecto = new Vector2(-1,-1);
        //  Index para moverse entre las casillas
        int indexAzules = 0;
        //  Coordenadas de la celda a recorrer
        Vector2 celdaCoors = _indexAzulesPuestas.elementAt(indexAzules);
        // Coordenadas de los adyacentes de la celda
        Vector2 coors = new Vector2(celdaCoors._x , celdaCoors._y);
        //  Dirección en la que se mueven las coordenadas
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;

        boolean finish = false;

        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = celdaCoors._x + currentDir._x;
                    coors._y = celdaCoors._y + currentDir._y;

                }
                //  Se acaban las direcciones,
                else {
                    //  He encontrado las condiciones necesarias para determinar que esta pista exista,
                    //  Si logra llegar hasta aqui sin descartarse, significa que esta celda está cerrada
                    //  y no "ve" ningún azul
                    AzulIncorrecto = celdaCoors;
                    finish = true;
                }
            }
            //  Si encontramos una celda azul o gris, se descarta y pasamos al siguiente
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL
            || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS) {
                indexAzules++;
                //  No quedan más celdas que procesar
                if(indexAzules >= _size ){
                    AzulIncorrecto._x = -1;
                    AzulIncorrecto._y = -1;
                    finish = true;
                    continue;
                }
                //  Reiniciamos las direcciones
                indexDir = 0;
                currentDir = _dirs[indexDir];
                celdaCoors = _indexAzulesPuestas.elementAt(indexAzules);
                coors._x = celdaCoors._x + currentDir._x;
                coors._y = celdaCoors._y + currentDir._y;
            }
        }
        return AzulIncorrecto;
    }


    /**
     * Si una celda está vacía y cerrada y no ve ninguna celda azul, entonces es pared (todos
     * los puntos azules deben ver al menos a otro).
     *      Recorre las todas las celdas
     * */
    private Vector2 PistaSeis(){
        //  Index de la celda con la pista
        Vector2 celdaCerrada = new Vector2(-1,-1);
        //  Index para moverse entre las casillas
        Vector2 indexCeldas = new Vector2(0,0);
        //  Coordenadas de la celda a recorrer
        Vector2 celdaCoors = _casillas[(int)indexCeldas._x][(int)indexCeldas._y].getIndex();
        // Coordenadas de los adyacentes de la celda
        Vector2 coors = new Vector2(celdaCoors._x , celdaCoors._y);
        //  Dirección en la que se mueven las coordenadas
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;


        boolean finish = false;

        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = celdaCoors._x + currentDir._x;
                    coors._y = celdaCoors._y + currentDir._y;

                }
                //  Se acaban las direcciones,
                else {
                    //  He encontrado las condiciones necesarias para determinar que esta pista existe
                    //  Si logra llegar hasta aqui sin descartarse, significa que esta celda está cerrada
                    //  y no "ve" ningún azul
                    celdaCerrada = celdaCoors;
                    finish = true;
                }
            }
            //  Si encontramos una celda azul o gris, se descarta
            else
            {
                indexCeldas._x++;
                if(indexCeldas._x >= _size){
                    indexCeldas._x = 0;
                    indexCeldas._y++;
                    //  No quedan más celdas para procesar
                    if(indexCeldas._y >= _size){
                        celdaCerrada._x = -1;
                        celdaCerrada._y = -1;
                        finish = true;
                    }
                }
                else {
                    //  Reiniciamos las direcciones
                    indexDir = 0;
                    currentDir = _dirs[indexDir];
                    celdaCoors = _casillas[(int) indexCeldas._x][(int) indexCeldas._y].getIndex();
                    coors._x = celdaCoors._x + currentDir._x;
                    coors._y = celdaCoors._y + currentDir._y;
                }
            }
        }
        return celdaCerrada;
    }

    /**
     * Recorre las celdas azules y cuando encuentra una celda cerrada y con insuficientes adyacentes
     * reconoce que ha encontrado la pista
     * */
    private Vector2 PistaCinco(){
        Vector2 cerradoIncorrecto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas azules que "ve"
        int adyacentes = 0;

        boolean finish = false;

        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Se acaban las direcciones, pasamos al siguiente azul y reiniciamos las direcciones
                else {
                    //  He encontrado las condiciones necesarias para determinar que esta pista existe
                    if(adyacentes < ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                        cerradoIncorrecto._x = _indexAzules[indexAz]._x;
                        cerradoIncorrecto._y = _indexAzules[indexAz]._y;
                        finish = true;
                    }
                    else{
                        indexAz++;
                        indexDir = 0;

                        //  No quedan más azules para procesar
                        if(indexAz >= _indexAzules.length){
                            cerradoIncorrecto._x = -1;
                            cerradoIncorrecto._y = -1;
                            finish = true;
                        }
                        //  Reinicio de las direcciones para el siguiente azul
                        else {
                            adyacentes = 0;
                            currentDir = _dirs[indexDir];
                            coors._x = _indexAzules[indexAz]._x + currentDir._x;
                            coors._y = _indexAzules[indexAz]._y + currentDir._y;

                        }
                    }

                }

            }
            //  Si encuentra una celda gris, pasamos al siguiente azul
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS) {
                indexAz++;
                //  No quedan más azules para procesar
                if(indexAz >= _indexAzules.length){
                    cerradoIncorrecto._x = -1;
                    cerradoIncorrecto._y = -1;
                    finish = true;
                }
                else{
                    indexDir = 0;
                    adyacentes = 0;
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
            }
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL)
            {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;
            }

        }
        return cerradoIncorrecto;
    }

    /**
     * Busca entre las celdas azules una celda que "ve" más celdas que el valor que tiene
     * */
    private Vector2 PistaCuatro(){
        Vector2 azulIncompleto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas azules que "ve"
        int adyacentes = 0;

        boolean finish = false;
        while (!finish){
            if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                indexDir++;

                //  Cambio de dirección
                if(indexDir < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[indexDir];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;

                }
                //  Se acaban las direcciones, pasamos al siguiente azul y reiniciamos las direcciones
                else {
                    indexAz++;
                    indexDir = 0;

                    //  No quedan más azules para procesar
                    if(indexAz >= _indexAzules.length){
                        azulIncompleto._x = -1;
                        azulIncompleto._y = -1;
                        finish = true;
                    }
                    //  Reinicio de las direcciones para el siguiente azul
                    else {

                        adyacentes = 0;
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;

                    }

                }

            }
            /**
             * Comprobación de si la casilla adyacente es azul
             * */
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL)
            {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;

                //  He encontrado las condiciones necesarias para determinar que esta pista existe
                if(adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()){
                    azulIncompleto._x = _indexAzules[indexAz]._x;
                    azulIncompleto._y = _indexAzules[indexAz]._y;
                    finish = true;
                }
            }

        }
        return azulIncompleto;
    }

    /**
     *  Recorre las celdas azules en busca de una celda incompleta
     * */
    private Vector2 PistaTres(){
        Vector2 azulIncompleto = new Vector2(-1,-1);

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;
        //  Index que recorre las direcciones
        int indexDir = 0;
        //  Index que reccore las celdas azules
        int indexAz = 0;
        //  Numero de celdas azules que "ve"
        int adyacentes = 0;
        //  Numero de grises en la direcc actual
        int currGris = 0;
        //  Numero de grises con los que no me paso del valor azul totales
        int gris = 0;
        //  Gris que debería ser azul
        Vector2 grisAAzul = new Vector2(-1,-1);
        //  Determina si ya había encontrado una direcc con más grises que valor
        boolean yaCaminoGris = false;

        boolean finPista = false;
        while(!finPista) {
            boolean finish = false;
            boolean finish2 = false;
            while (!finish) {
                if (coors._y < 0 || coors._y >= _size
                        || coors._x < 0 || coors._x >= _size
                        || _casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.GRIS
                        || _casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.ROJO) {
                    indexDir++;

                    //  Cambio de dirección
                    if (indexDir < _dirs.length) {
                        // Reseteamos los valores para comprobar en la siguiente dirección
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;

                    } else {
                        finish = true;
                    }
                }
                /**
                 * Comprobación de si la casilla adyacente es azul
                 * */
                else if (_casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.AZUL) {
                    adyacentes++;
                    // Nos movemos a la siguiente casilla
                    coors._y += currentDir._y;
                    coors._x += currentDir._x;

                    if (adyacentes >= ((CeldaAzul) _casillas[(int) _indexAzules[indexAz]._x][(int) _indexAzules[indexAz]._y]).getValue()) {
                        //No se trata de esta pista, pasamos buscar en la siguiente azul
                        indexAz++;
                        indexDir = 0;
                        finish = true;
                        finish2 = true;

                        //  No quedan más azules para procesar
                        if (indexAz >= _indexAzules.length) {
                            azulIncompleto._x = -1;
                            azulIncompleto._y = -1;
                            finPista = true;
                        }
                        //  Reinicio de las direcciones para el siguiente azul
                        else {
                            adyacentes = 0;
                            currentDir = _dirs[indexDir];
                            coors._x = _indexAzules[indexAz]._x + currentDir._x;
                            coors._y = _indexAzules[indexAz]._y + currentDir._y;
                        }
                    }
                }
            }

            if (!finish2) {
                indexDir = 0;
                currentDir = _dirs[indexDir];
                coors._x = _indexAzules[indexAz]._x + currentDir._x;
                coors._y = _indexAzules[indexAz]._y + currentDir._y;
            }

            while (!finish2) {
                if (coors._y < 0 || coors._y >= _size
                        || coors._x < 0 || coors._x >= _size
                        || _casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.ROJO) {
                    indexDir++;

                    gris += currGris;
                    currGris = 0;

                    //  Cambio de dirección
                    if (indexDir < _dirs.length) {
                        // Reseteamos los valores para comprobar en la siguiente dirección
                        currentDir = _dirs[indexDir];
                        coors._x = _indexAzules[indexAz]._x + currentDir._x;
                        coors._y = _indexAzules[indexAz]._y + currentDir._y;
                    } else {
                        if (grisAAzul._x != -1) {
                            //Si he llegado aquí y se han acabado las direcc es porque grisAAzul debe ser azul y se trata de esta pista
                            azulIncompleto._x = grisAAzul._x;
                            azulIncompleto._y = grisAAzul._y;
                            finish2 = true;
                            finPista = true;
                        } else {
                            //No se trata de esta pista, pasamos buscar en la siguiente azul
                            indexAz++;
                            indexDir = 0;

                            //  No quedan más azules para procesar
                            if (indexAz >= _indexAzules.length) {
                                azulIncompleto._x = -1;
                                azulIncompleto._y = -1;
                                finish2 = true;
                                finPista = true;
                            }
                            //  Reinicio de las direcciones para el siguiente azul
                            else {
                                adyacentes = 0;
                                gris = 0;
                                currGris = 0;
                                yaCaminoGris = false;
                                currentDir = _dirs[indexDir];
                                coors._x = _indexAzules[indexAz]._x + currentDir._x;
                                coors._y = _indexAzules[indexAz]._y + currentDir._y;
                                grisAAzul = new Vector2(-1, -1);
                                finish2 = true;
                            }
                        }
                    }
                } else if (_casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.GRIS) {
                    currGris++;
                    if (!yaCaminoGris && currGris == 1) {
                        grisAAzul._x = (int) coors._x;
                        grisAAzul._y = (int) coors._y;
                    }
                    if (currGris + gris + adyacentes <= ((CeldaAzul) _casillas[(int) _indexAzules[indexAz]._x][(int) _indexAzules[indexAz]._y]).getValue()) {
                        // Nos movemos a la siguiente casilla
                        coors._y += currentDir._y;
                        coors._x += currentDir._x;
                    } else {
                        if (yaCaminoGris) {
                            //No se trata de esta pista, pasamos a buscar en la siguiente azul
                            indexAz++;
                            indexDir = 0;

                            //  No quedan más azules para procesar
                            if (indexAz >= _indexAzules.length) {
                                azulIncompleto._x = -1;
                                azulIncompleto._y = -1;
                                finish2 = true;
                                finPista = true;
                            }
                            //  Reinicio de las direcciones para el siguiente azul
                            else {
                                adyacentes = 0;
                                gris = 0;
                                currGris = 0;
                                yaCaminoGris = false;
                                currentDir = _dirs[indexDir];
                                coors._x = _indexAzules[indexAz]._x + currentDir._x;
                                coors._y = _indexAzules[indexAz]._y + currentDir._y;
                                grisAAzul = new Vector2(-1, -1);
                                finish2 = true;
                            }
                        } else {
                            yaCaminoGris = true;

                            //Cambiamos de direccion
                            indexDir++;

                            //  Cambio de dirección
                            if (indexDir < _dirs.length) {
                                // Reseteamos los valores para comprobar en la siguiente dirección
                                currentDir = _dirs[indexDir];
                                coors._x = _indexAzules[indexAz]._x + currentDir._x;
                                coors._y = _indexAzules[indexAz]._y + currentDir._y;

                            } else {
                                azulIncompleto._x = grisAAzul._x;
                                azulIncompleto._y = grisAAzul._y;
                                finish2 = true;
                                finPista = true;
                            }
                        }
                    }
                }
                /**
                 * Comprobación de si la casilla adyacente es azul
                 * */
                else if (_casillas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.AZUL) {
                    // Nos movemos a la siguiente casilla
                    coors._y += currentDir._y;
                    coors._x += currentDir._x;
                }
            }
        }
        return azulIncompleto;
    }

    /**
     * Si pusiéramos un punto azul en una celda vacía, superaríamos el número de visibles
     * del número, y por tanto, debe ser una pared.
     */
    private Vector2 PistaDos(){
        Vector2 wrongBlue = new Vector2(-1,-1);

        int indexAz = 0;
        int adyacentes = 0;

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);

        Vector2 currentDir = _dirs[0];
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
                    || !_casillas[(int)coors._x][(int)coors._y].isLock()
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS) {
                index++;
                if(index < 4) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[index];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                /**
                 * Nos quedamos sin azules para donetiar
                 */
                if(adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue()) {
                    wrongBlue._x = _indexAzules[indexAz]._x;
                    wrongBlue._y = _indexAzules[indexAz]._y;
                    finish = true;
                }
                else {
                    indexAz++;
                    adyacentes = 0;
                    index = 0;
                    if(indexAz >= _indexAzules.length) {
                        wrongBlue._x = -1;
                        wrongBlue._y = -1;
                        finish = true;
                    }
                }
            }
            /**
             * Comprobación de si la casilla adyacente es azul
             * */
            else
            {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;


                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces no es válido
                 *  Se termina la búsqueda
                 */
                finish = adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._y]).getValue();
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return wrongBlue;
    }

    /**
     * Si un número tiene ya visibles el número de celdas que dice, entonces se puede
     * “cerrar”, es decir, poner paredes en los extremos.
     * */
    private Vector2 PistaUno(){

        Vector2 donete = new Vector2(-1,-1);

        int indexAz = 0;
        int adyacentes = 0;

        Vector2 coors = new Vector2(_indexAzules[0]._x,_indexAzules[0]._y);

        Vector2 currentDir = _dirs[0];
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
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO) {
                index++;
                if(index < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[index];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                //  Pasamos al siguiente azul
                else {
                    indexAz++;
                    adyacentes = 0;
                    index = 0;
                    //  Me he quedado sin azules
                    if(indexAz >= _indexAzules.length ){
                        donete._x = -1;
                        donete._y = -1;
                        finish = true;
                    }
                }
            }
            /**
             * Comprobación de si la casilla adyacente es azul
             * */
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL) {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;

                //  Hemos encontrado las condiciones necesarias para esta pista (valor de la celda == al numero de adyacentes)
                if (adyacentes >= ((CeldaAzul) _casillas[(int) _indexAzules[indexAz]._x][(int) _indexAzules[indexAz]._y]).getValue()) {
                    donete._x = _indexAzules[indexAz]._x;
                    donete._y = _indexAzules[indexAz]._y;
                    finish = true;
                }

                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces no es válido
                 *  Se termina la búsqueda
                 */
                //finish = adyacentes >= ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._x]).getValue();
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return donete;
    }

    /**
     * Si no ponemos un punto en alguna celda vacía, entonces es imposible alcanzar el
     * número.
     */
//    private TipoPista BuscaPista(){
//        //Vector2 adyDonete = new Vector2(-1,-1);
//        TipoPista feedback = TipoPista.MAX;
//        int indexAz = 0;
//        int adyacentes = 0;
//
//        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);
//
//        Vector2 currentDir = _dirs[0];
//        coors._x += currentDir._x;
//        coors._y += currentDir._y;
//
//        int index = 0;
//        boolean finish = false;
//
//        // Determina si la celda azul tiene solo una direccion
//        Vector2 oneDirection;
//        while (!finish)
//        {
//            TipoPista auxPista = EsSumaAlcanzable(_indexAzules[indexAz]);
//            oneDirection = OneDirection(_indexAzules[indexAz]);
//
//            if(auxPista == TipoPista.SUMA_MENOR)
//            {
//                _celdaFeedback = (CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._x];
//                feedback = auxPista;
//                finish = true;
//            }
//            else if(auxPista == TipoPista.SUMA_ALCANZABLE)
//            {
//                if(oneDirection._x > -1 && oneDirection._y > -1)
//                {
//                    feedback = TipoPista.ONE_DIRECTION;
//                }
//                else if(oneDirection._x == -1)
//                {
//                    feedback = auxPista;
//                }
//
//                _celdaFeedback = (CeldaAzul) _casillas[(int) _indexAzules[indexAz]._x][(int) _indexAzules[indexAz]._x];
//                finish = true;
//            }
//            // Si no, entonces pasamos al siguiente azul
//            else if(auxPista == TipoPista.MAX)
//            {
//
//            }
//        }
//
//        return feedback;
//    }

    /**
     * Comprobamos si el azul tiene o más direcciones
     */
    private Vector2 OneDirection(Vector2 currAzul){
        Vector2 oneDirection = new Vector2(-2, -2);
        boolean finish = false;
        int laterales = 0;
        /**
         * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
         */
        int i = 0;
        while(!finish){
            /*
             * Si arriba es menor que size y arriba es (rojo o azul)
             * Si abajo es mayor o igual que 0 y abajo es (rojo o azul)
             * Si izquierda es mayor o igual que 0 e izquierda es (rojo o azul)
             * Si derecha es menor que size y derecha es (rojo o azul)
            *   entonces se suma laterales
            * */
            if(currAzul._y + _dirs[0]._y < _size && _casillas[(int)currAzul._x][(int)(currAzul._y + _dirs[0]._y)].getTypeColor() == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[0];
            }
            else if(currAzul._y + _dirs[1]._y >= 0 && _casillas[(int)currAzul._x][(int)(currAzul._y + _dirs[0]._y)].getTypeColor() == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[1];
            }
            else if(currAzul._x + _dirs[2]._x >= 0 && _casillas[(int)(currAzul._x + _dirs[2]._x)][(int)currAzul._y].getTypeColor() == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[2];
            }
            else if(currAzul._x + _dirs[3]._x < _size && _casillas[(int)(currAzul._x + _dirs[3]._x)][(int)currAzul._y].getTypeColor() == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[3];
            }

            i++;
            if (laterales > 1 || i >= _dirs.length)
            {
                finish = true;
            }
        }

        // En caso de que haya más de un lateral, entonces la componente X de
        // oneDirection valdrá -1. En caso de que no haya laterales, i.e, está cerrado,
        // la componente Y de oneDirection es -1
        if(laterales > 1)
        {
            oneDirection._x = -1;
        }
        else if(laterales == 0)
        {
            oneDirection._y = -1;
        }

        return oneDirection;
    }

    /**
     * Comprobamos la suma de las celdas alcanzables (GRIS / AZUL) desde el azul actual.
     * En caso de que la suma sea menor se devuelve TipoCelda.SUMA_MENOR.
     * Si son iguales, TipoCelda.SUMA_ALCANZABLE
     * Si no, TipoCelda.MAX
     * */
    private TipoPista EsSumaAlcanzable(Vector2 vAzul) {
        int result = -1;
        CeldaAzul celdaAzul = (CeldaAzul)_casillas[(int)vAzul._x][(int)vAzul._x];
        boolean finish = false;
        /**
         * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
         */
        int sumaAdy = 0;
        int index = 0;
        Vector2 coors = new Vector2(vAzul._x, vAzul._y);
        coors._x += _dirs[0]._x;
        coors._y += _dirs[0]._y;

        while(!finish) {
            // Hemos comprobado todas las direcciones o la suma es mayor que el valor de la celda
            if(sumaAdy > celdaAzul.getValue())
            {
                finish = true;
            }
            // Cambio de direccion
            else if(coors._y < 0 || coors._y >= _size
                    || coors._x < 0 || coors._x >= _size
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.ROJO)
            {
                index++;
                // Reset
                if(index < _dirs.length) {
                    coors._x = vAzul._x + _dirs[index]._x;
                    coors._y = vAzul._y + _dirs[index]._y;
                }
                else {
                    finish = true;
                }
            }
            else if(_casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.GRIS
                    || _casillas[(int)coors._x][(int)coors._y].getTypeColor() == TipoCelda.AZUL)
            {
                sumaAdy++;
                coors._x += _dirs[index]._x;
                coors._y += _dirs[index]._y;
            }
        }

        if (sumaAdy < celdaAzul.getValue())
        {
            return TipoPista.SUMA_MENOR;
        }
        else if(sumaAdy == celdaAzul.getValue())
        {
            return  TipoPista.SUMA_ALCANZABLE;
        }

        return TipoPista.MAX;
    }

    void MuestraPista(TipoPista currPista){
        //switch (currPista){
        //
        //    case DONETE:
        //    {
        //
        //        break;
        //    }
        //    case SUMA_MENOR:
        //    {
        //        break;
        //    }
        //
        //
        //    default:{
        //        break;
        //    }
        //}
    }
}
