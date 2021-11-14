package es.ucm.arblemar.gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Vector2;
import es.ucm.arblemar.gamelogic.assets.Assets;
import es.ucm.arblemar.gamelogic.gameobject.Celda;
import es.ucm.arblemar.gamelogic.gameobject.GameObject;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaAzul;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaGris;
import es.ucm.arblemar.gamelogic.gameobject.celda.CeldaRoja;

public class Tablero {

    // Tamaño del tablero
    private int _size;
    //  Contiene todas las celdas de todos los tipos del tablero
    private Celda[][] casillas;
    //  Contiene todas las celdas de la solucion
    private Celda[][] casillasSol;
    private Celda[][] casillasPistas;
    //  Index de las celdas azules puestas por el juego
    private Vector2[] indexAzulesOriginales;
    private Vector2[] indexAzulesOriginalesPistas;
    //  Index de las celdas rojas puestas por el juego
    private Vector2[] indexRojosOriginales;
    private Vector2[] indexRojosOriginalesPistas;
    //  Index de las celdas azules puestas por el jugador
    private Vector<Vector2> indexAzulesPuestas;
    private Vector<Vector2> indexAzulesPuestasPistas;
    //  Index de las celdas rojas puestas por el jugador
    private Vector<Vector2> indexRojasPuestas;
    //  Vector de todas las pistas encontradas en el tablero
    private List<Pista> pistasEncontradas;
    //  Distancia que existe entre las celdas para posicionarlas
    private float celdaDistancia;
    //  Radio de las celdas
    private float celdaRd;
    //  Posición de la primera celda a colocar
    private Vector2 initPos;
    //  Puntero al engine
    private Engine engine;
    // Boolean que determina si el tablero tiene solución única
    private boolean solUnica = false;
    // Boolean que determina si el tablero es correcto
    private boolean tabCorrecto = false;
    // Auxiliar para contar adyacentes
    private int adyacentes = 0;
    // Enunciado acabado
    private boolean enunAcabau = false;

    public Tablero(int size,Engine _eng){
        engine = _eng;
        _size = size;
        // Inicialización de las celdas en modo gris
        Graphics g = _eng.getGraphics();

        celdaRd = (float)g.getWidth() / (1.1f * _size + 0.1f);
        celdaDistancia = celdaRd * 0.1f;

        //while (!tabCorrecto) {
            tabCorrecto = true;
            casillas = new Celda[_size][_size];
            pistasEncontradas = new ArrayList<>();
            indexRojasPuestas = new Vector<>();
            indexAzulesPuestas = new Vector<>();

            float celdaPosX = celdaDistancia;
            float celdaPosY = (float) g.getLogHeight() / 5.5f;

            initPos = new Vector2((int) celdaPosX, (int) celdaPosY);

            for (int i = 0; i < _size; i++) {
                for (int j = 0; j < _size; j++) {
                    Vector2 ind = new Vector2(i, j);
                    casillas[i][j] = new CeldaGris(ind, 0, new Vector2(initPos._x, initPos._y), celdaRd);
                    initPos._x += celdaRd + celdaDistancia;
                }
                initPos._x = (int) celdaPosX;
                initPos._y += celdaRd + celdaDistancia;
            }

            enunAcabau = false;
            generaTab();
            //Si sale del while y tabCorrecto es false vuelve a empezar
            //while (tabCorrecto && !solUnica) {
            //    Random r = new Random();
            //    //  Inicializamos los rojos aleatoriamente y lo menos descartable
            //    InitRojas(r);
            //    //  Inicializamos los azules aleatoriamente y lo menos descartable
            //    InitAzules(r);

            //    compruebaTab();
            //}
        //}
        // Existen algunos casos incorrectos

        // Falta analizar que el tablero tiene una única solución antes de gestionar las pistas

        //GestorPistas p = new GestorPistas(this);

        //RenderizaConsola();
    }

    private void generaTab() {
        Random r = new Random();
        int contAzul = 0;
        int contR = 0, contA = 0;
        Vector2[] indexAzules = new Vector2[_size * _size];
        //Recorremos el tablero
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                Vector2 ind = new Vector2(i, j);
                int prob = r.nextInt(10);
                Vector2 pos = casillas[i][j].getPos();
                if (prob < 4) {
                    casillas[i][j] = new CeldaRoja(ind, 0, pos, celdaRd);
                    contR++;
                }
                else {
                    casillas[i][j] = new CeldaAzul(Assets.jose, (int)(celdaRd * 2/3), 0, ind,0,pos, celdaRd);
                    indexAzules[contAzul] = new Vector2(i,j);
                    contAzul++;
                }
            }
        }
        contA = contAzul;
        //Guarda las vecinas de cada azul
        for (int i = 0; i < contAzul; ++i) {
            boolean finish = false;

            int ady = 0;

            Vector2 ind = new Vector2(indexAzules[i]._x, indexAzules[i]._y);
            Vector2 coors = new Vector2(ind._x, ind._y);
            Vector2[] dirs = new Vector2[4];

            dirs[0] = new Vector2(0, -1);
            dirs[1] = new Vector2(0, 1);
            dirs[2] = new Vector2(-1, 0);
            dirs[3] = new Vector2(1, 0);

            coors._x += dirs[0]._x;
            coors._y += dirs[0]._y;

            int index = 0;

            while (!finish) {
                if (coors._y < 0 || coors._y >= _size
                        || coors._x < 0 || coors._x >= _size
                        || !casillas[coors._x][coors._y].isLock()
                        || casillas[coors._x][coors._y].getTypeColor() == TipoCelda.ROJO) {

                    index++;

                    if (index < dirs.length) {
                        // Reseteamos los valores para comprobar en la siguiente dirección
                        coors._x = ind._x + dirs[index]._x;
                        coors._y = ind._y + dirs[index]._y;
                    } else {
                        finish = true;
                    }
                }
                //  Si es azul, pasamos a la siguiente
                else if (casillas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL) {
                    ady++;
                    coors._x += dirs[index]._x;
                    coors._y += dirs[index]._y;
                }
            }
            //Si no tiene vecinas
            if (ady == 0) {
                Vector2 pos = casillas[ind._x][ind._y].getPos();
                casillas[ind._x][ind._y] = new CeldaRoja(ind, 0, pos, celdaRd);
                contR++;
                contA--;
            }
            else {
                casillas[ind._x][ind._y].setValue(ady);
            }
        }

        casillasSol = new Celda[_size][_size];
        //Antes de elegir cuales mostrar guardamos la solucioncita
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                Vector2 ind = new Vector2(i, j);
                Vector2 pos = casillas[i][j].getPos();
                if (casillas[i][j].getTypeColor() == TipoCelda.AZUL) {
                    casillasSol[i][j] = new CeldaAzul(Assets.jose, (int)(celdaRd * 2/3), 0, ind,0,pos, celdaRd);
                }
                else {
                    casillasSol[i][j] = new CeldaRoja(ind, 0, pos, celdaRd);
                }
            }
        }

        indexAzulesOriginalesPistas = new Vector2[contA];
        indexRojosOriginalesPistas = new Vector2[contR];

        boolean ponAzul = true;
        contR = 0;
        contA = 0;
        //Elije cuales mostrar por pantalla
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                Vector2 ind = new Vector2(i, j);
                int prob = r.nextInt(2);
                Vector2 pos = casillas[i][j].getPos();
                if (prob == 0) {
                    if (casillas[i][j].getTypeColor() == TipoCelda.ROJO) {
                        ponAzul = true;
                        AgregaCeldaRoja(casillas[i][j].getIndex());
                        indexRojosOriginalesPistas[contR] = new Vector2(i,j);
                        contR++;
                    }
                    else {
                        ponAzul = false;
                        AgregaCeldaAzul(casillas[i][j].getIndex());
                        indexAzulesOriginalesPistas[contA] = new Vector2(i,j);
                        contA++;
                    }
                }
                else {
                    if (ponAzul && casillas[i][j].getTypeColor() == TipoCelda.AZUL) {
                        ponAzul = false;
                        AgregaCeldaAzul(casillas[i][j].getIndex());
                        indexAzulesOriginalesPistas[contA] = new Vector2(i,j);
                        contA++;
                    }
                    else
                        casillas[i][j] = new CeldaGris(ind, 0, pos, celdaRd);
                }
            }
            ponAzul = true;
        }
        indexAzulesOriginales = new Vector2[contA];
        indexRojosOriginales = new Vector2[contR];
        for (int i = 0; i < contA; ++i) {
            indexAzulesOriginales[i] = indexAzulesOriginalesPistas[i];
        }
        for (int i = 0; i < contR; ++i) {
            indexRojosOriginales[i] = indexRojosOriginalesPistas[i];
        }

        enunAcabau = true;

        //Comprueba si es sol unica
        compruebaTab();
    }

    private boolean compruebaTab() {
        casillasPistas = new Celda[_size][_size];
        //Antes de elegir cuales mostrar guardamos la solucioncita
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                Vector2 ind = new Vector2(i, j);
                Vector2 pos = casillas[i][j].getPos();
                if (casillas[i][j].getTypeColor() == TipoCelda.AZUL) {
                    casillasPistas[i][j] = new CeldaAzul(Assets.jose, (int)(celdaRd * 2/3), 0, ind,0,pos, celdaRd);
                }
                else {
                    casillasPistas[i][j] = new CeldaRoja(ind, 0, pos, celdaRd);
                }
            }
        }
        indexAzulesOriginalesPistas = indexAzulesOriginales;
        indexAzulesPuestasPistas = indexAzulesPuestas;
        GestorPistas p = new GestorPistas(casillasPistas, indexAzulesOriginalesPistas, _size, indexAzulesPuestasPistas);

        int maxIter = 1000000;
        int currIter = 0;
        solUnica = false;

        //Hay que tratar de resolver el tablero solucion usando las pistas
        //Si en una iteración del tablero usando las pistas NO aparece ningún cambio:
        //  -> Faltan datos (varias soluciones): solUnica = false;
        //Si en una iteración del tablero solucion aparece alguna pista de ERROR:
        //  -> El tablero es erroneo: tabCorrecto = false;

        boolean f;
        while(currIter < maxIter && !solUnica) {
            f = false;
            currIter++;
            pistasEncontradas = new ArrayList<>();
            p.actualizaPistas(this);
            for (int i = 0; i < pistasEncontradas.size() && !f; ++i) {
                switch (pistasEncontradas.get(i).getPista()) {
                    case DONETE: {
                        //Se pueden poner paredes en los extremos
                        Vector2 ind = new Vector2(pistasEncontradas.get(i).getIndex()._x, pistasEncontradas.get(i).getIndex()._y);

                        boolean finish = false;

                        Vector2 coors = new Vector2(ind._x, ind._y);
                        Vector2[] dirs = new Vector2[4];

                        dirs[0] = new Vector2(0, -1);
                        dirs[1] = new Vector2(0, 1);
                        dirs[2] = new Vector2(-1, 0);
                        dirs[3] = new Vector2(1, 0);

                        int index = 0;

                        coors._x += dirs[0]._x;
                        coors._y += dirs[0]._y;

                        while (!finish) {
                            if (coors._y < 0 || coors._y >= _size
                                    || coors._x < 0 || coors._x >= _size
                                    || !casillasPistas[coors._x][coors._y].isLock()
                                    || casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.ROJO) {

                                index++;

                                if (index < dirs.length) {
                                    // Reseteamos los valores para comprobar en la siguiente dirección
                                    coors._x = ind._x + dirs[index]._x;
                                    coors._y = ind._y + dirs[index]._y;
                                } else {
                                    finish = true;
                                }
                            }
                            //  Si encontramos una celda gris, la ponemos en rojo y cambiamos dirección
                            else if (casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.GRIS) {
                                casillasPistas[coors._x][coors._y].setTypeColor(TipoCelda.ROJO);
                                index++;

                                if (index < dirs.length) {
                                    // Reseteamos los valores para comprobar en la siguiente dirección
                                    coors._x = ind._x + dirs[index]._x;
                                    coors._y = ind._y + dirs[index]._y;
                                } else {
                                    finish = true;
                                }
                            }
                            //  Si es azul, pasamos a la siguiente
                            else if (casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL) {
                                coors._x += dirs[index]._x;
                                coors._y += dirs[index]._y;
                            }
                        }
                        break;
                    }
                    case AZUL_INCORRECTO:
                    case NO_VEO_AZUL: {
                        //AZUL_INCORRECTO: Si se pusiese azul sería error, luego es pared
                        //NO_VEO_AZUL: Celda gris cerrada, luego es roja
                        Vector2 ind = new Vector2(pistasEncontradas.get(i).getIndex()._x, pistasEncontradas.get(i).getIndex()._y);
                        casillasPistas[ind._x][ind._y].setTypeColor(TipoCelda.ROJO);
                        break;
                    }
                    case ADYACENTE_DONETE: {
                        //Hay que poner azul obligatoriamente
                        Vector2 ind = new Vector2(pistasEncontradas.get(i).getIndex()._x, pistasEncontradas.get(i).getIndex()._y);
                        casillasPistas[ind._x][ind._y].setTypeColor(TipoCelda.AZUL);
                        indexAzulesPuestasPistas.add(ind);
                        break;
                    }
                    case SOBRE_ADYACENCIA_AZUL:
                    case SOBRE_ADYACENCIA_ROJA:
                    case AZUL_AISLADA:
                    //case SUMA_MENOR:
                    {
                        //ERROR SOBRE_ADYACENCIA_AZUL: un azul tiene más visibles de las que debería
                        //ERROR SOBRE_ADYACENCIA_ROJA: un azul no tiene suficientes vecinas y está cerrada
                        //ERROR AZUL_AISLADA: una celda azul no tiene vecinos, debería ser pared
                        //ERROR SUMA_MENOR: futuro error porque una azul no puede alcanzar su valor
                        tabCorrecto = false;
                        break;
                    }
                    case ONE_DIRECTION: {
                        //Hay que poner todas las azules necesarias en la única dirección que queda
                        Vector2 ind = new Vector2(pistasEncontradas.get(i).getIndex()._x, pistasEncontradas.get(i).getIndex()._y);
                        Vector2 coors = new Vector2(ind._x, ind._y);
                        Vector2[] dirs = new Vector2[4];
                        dirs[0] = new Vector2(0, -1);
                        dirs[1] = new Vector2(0, 1);
                        dirs[2] = new Vector2(-1, 0);
                        dirs[3] = new Vector2(1, 0);
                        Vector2 currentDir = dirs[0];
                        coors._x += currentDir._x;
                        coors._y += currentDir._y;
                        //  Index que recorre las direcciones
                        int indexDir = 0;
                        //  Numero de celdas azules que "ve"
                        int adyacentes = 0;
                        boolean finish = false;
                        while (!finish) {
                            if (coors._y < 0 || coors._y >= _size
                                    || coors._x < 0 || coors._x >= _size
                                    || casillasPistas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.ROJO) {
                                indexDir++;
                                //  Cambio de dirección
                                if (indexDir < dirs.length) {
                                    // Reseteamos los valores para comprobar en la siguiente dirección
                                    currentDir = dirs[indexDir];
                                    coors._x = ind._x + currentDir._x;
                                    coors._y = ind._y + currentDir._y;
                                }
                                //Si tras terminar de reccorer todas las adyacentes no llego al valor deseado es error
                                else if (adyacentes != casillasPistas[ind._x][ind._y].getValue()) {
                                    tabCorrecto = false;
                                    finish = true;
                                }
                                else {
                                    finish = true;
                                }
                            }
                            //  Encuentro una celda gris, es la salida, miramos si hacen falta más azules
                            else if (casillasPistas[(int) coors._x][(int) coors._y].getTypeColor() == TipoCelda.GRIS) {
                                if (adyacentes < casillasPistas[ind._x][ind._y].getValue()) {
                                    //Si hacen falta más vecinas se pone la casilla gris en azul y se suma adyacentes
                                    adyacentes++;
                                    casillasPistas[(int) coors._x][(int) coors._y].setTypeColor(TipoCelda.AZUL);
                                    indexAzulesPuestasPistas.add(coors);
                                    //Continua en la misma dirección
                                    coors._x += currentDir._x;
                                    coors._y += currentDir._y;
                                } else {
                                    casillasPistas[(int) coors._x][(int) coors._y].setTypeColor(TipoCelda.ROJO);
                                    finish = true;
                                }
                            }
                            //  Si encontramos una celda azul, sumamos adyacente
                            else {
                                adyacentes++;
                                //Comprobamos que no haya concatenado una gris convertida a azul con otra azul y nos hayamos pasado
                                if (adyacentes > casillasPistas[ind._x][ind._y].getValue()) {
                                    tabCorrecto = false;
                                    finish = true;
                                }
                                //En caso contrario seguimos buscando para ver si hay más azules vecinas
                                else {
                                    //Continua en la misma dirección
                                    coors._x += currentDir._x;
                                    coors._y += currentDir._y;
                                }
                            }
                        }
                        break;
                    }
                    case SUMA_ALCANZABLE: {
                        //Todas las alcanzables suman la cantidad de valor buscada
                        Vector2 ind = new Vector2(pistasEncontradas.get(i).getIndex()._x, pistasEncontradas.get(i).getIndex()._y);

                        boolean finish = false;

                        Vector2 coors = new Vector2(ind._x, ind._y);
                        Vector2[] dirs = new Vector2[4];

                        dirs[0] = new Vector2(0, -1);
                        dirs[1] = new Vector2(0, 1);
                        dirs[2] = new Vector2(-1, 0);
                        dirs[3] = new Vector2(1, 0);

                        int index = 0;

                        coors._x += dirs[0]._x;
                        coors._y += dirs[0]._y;

                        while (!finish) {
                            if (coors._y < 0 || coors._y >= _size
                                    || coors._x < 0 || coors._x >= _size
                                    || !casillasPistas[coors._x][coors._y].isLock()
                                    || casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.ROJO) {

                                index++;

                                if (index < dirs.length) {
                                    // Reseteamos los valores para comprobar en la siguiente dirección
                                    coors._x = ind._x + dirs[index]._x;
                                    coors._y = ind._y + dirs[index]._y;
                                } else {
                                    finish = true;
                                }
                            }
                            //  Si encontramos una celda gris, la ponemos a azul y seguimos mirando en la misma línea
                            else if (casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.GRIS) {
                                casillasPistas[coors._x][coors._y].setTypeColor(TipoCelda.AZUL);
                                indexAzulesPuestasPistas.add(coors);

                                coors._x += dirs[index]._x;
                                coors._y += dirs[index]._y;
                            }
                            //  Si es azul, pasamos a la siguiente
                            else if (casillasPistas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL) {
                                coors._x += dirs[index]._x;
                                coors._y += dirs[index]._y;
                            }
                        }
                        break;
                    }
                    default: {
                        //Si no encuentra pistas posibles y el tablero no está completo es que tiene varias soluciones
                        int contR = indexRojosOriginales.length;
                        int contA = indexAzulesOriginales.length;
                        for (int x = 0; x < _size; x++) {
                            for (int y = 0; y < _size; y++) {
                                Vector2 ind = new Vector2(x, y);
                                Vector2 pos = casillas[x][y].getPos();
                                if (casillasPistas[x][y].getTypeColor() == TipoCelda.GRIS) {
                                    //return true;
                                    if (casillasSol[x][y].getTypeColor() == TipoCelda.AZUL) {
                                        casillas[x][y] = new CeldaAzul(Assets.jose, (int)(celdaRd * 2/3), 0, ind,0,pos, celdaRd);
                                        contA++;
                                    }
                                    else {
                                        casillas[x][y] = new CeldaRoja(ind, 0, pos, celdaRd);
                                        contR++;
                                    }
                                    f = true;
                                }
                            }
                        }
                        if (f) {
                            indexAzulesOriginales = new Vector2[contA];
                            indexRojosOriginales = new Vector2[contR];
                            contA = 0;
                            contR = 0;
                            for (int x = 0; x < _size; x++) {
                                for (int y = 0; y < _size; y++) {
                                    if (casillas[x][y].getTypeColor() == TipoCelda.AZUL) {
                                        indexAzulesOriginales[contA] = new Vector2(x, y);
                                        contA++;
                                    } else {
                                        indexRojosOriginales[contR] = new Vector2(x, y);
                                        contR++;
                                    }
                                }
                            }
                        }
                        else {
                            f = true;
                            solUnica = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void RenderizaConsola() {
        System.out.println("Rojos: R   Azules: v   Grises: X");

        for(int x = 0 ; x < _size ; x++){
            for(int y = 0 ; y < _size ; y++){
                if(casillas[x][y].getTypeColor() == TipoCelda.GRIS){
                    System.out.print("X ");
                }
                else if(casillas[x][y].getTypeColor() == TipoCelda.ROJO){
                    System.out.print("R ");
                }
                else if(casillas[x][y].getTypeColor() == TipoCelda.AZUL){
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
        AbstractGraphics g = (AbstractGraphics) engine.getGraphics();
        Vector2 logPos = g.logPos(mousePos);
        while (!encontrado && indY < _size){
            if(casillas[indX][indY].isClicked(logPos)){
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
            if(!casillas[indX][indY].isLock()) {
                //  Inicializamos el valor de la celda de forma aleatoria
                int valor = r.nextInt(_size) + 1;
                if(AzulesValidos(indX, indY, valor)) {
                    Vector2 ind = new Vector2(indX,indY);
                    Vector2 pos = casillas[indX][indY].getPos();
                    casillas[indX][indY] = new CeldaAzul(Assets.jose, (int)(celdaRd * 2/3), valor, ind,0,pos, celdaRd);
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
            if(!casillas[indX][indY].isLock()){
                //if(RojosValidos(indX,indY)){
                if(casillas[indX][indY].getTypeColor() == TipoCelda.GRIS) {
                    Vector2 ind = new Vector2(indX,indY);
                    Vector2 pos = casillas[indX][indY].getPos();
                    casillas[indX][indY] = new CeldaRoja(ind,0,pos, celdaRd);
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
                    || !casillas[coors._x][coors._y].isLock()
                    || casillas[ coors._x][ coors._y].getTypeColor() == TipoCelda.ROJO) {

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
            else if (casillas[coors._x][coors._y].getTypeColor() == TipoCelda.GRIS) {
                coors._x += dirs[index]._x;
                coors._y += dirs[index]._y;
            }
            //  Si es azul, comprobamos que dicho azul tiene más salidas para no bloquear una posible solución
            else if(casillas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL){
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
                    || !casillas[coors._x][coors._y].isLock()
                    || casillas[coors._x][coors._y].getTypeColor() == TipoCelda.ROJO) {
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
            else if(casillas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL
            || casillas[coors._x][coors._y].getTypeColor() == TipoCelda.GRIS)
            {
                adyacentes++;
                if(casillas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL)
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
                    || casillas[coors._x][coors._y].getTypeColor() == TipoCelda.ROJO
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
            else if (casillas[coors._x][coors._y].getTypeColor() == TipoCelda.GRIS
                || casillas[coors._x][coors._y].getTypeColor() == TipoCelda.AZUL) {
                coors._x += dirs[index]._x;
                coors._y += dirs[index]._y;
                elementos++;
                finish = elementos >= casillas[(int)x][(int)y].getValue();
            }
        }
        return  elementos >= casillas[(int)x][(int)y].getValue();
    }

    public boolean EsSolucion() {
        if (!enunAcabau)
            return false;
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                if (casillasSol[i][j].getTypeColor() != casillas[i][j].getTypeColor()) {
                    return false;
                }
            }
        }
        System.out.println("Hecho");
        return true;
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

    public List<Pista> GetPistasEncontradas() {
        return pistasEncontradas;
    }

    public void PistasToEmpty() {
        pistasEncontradas = new ArrayList<>();
    }

    public void AgregaPista(Pista p){
        pistasEncontradas.add(p);
    }
}