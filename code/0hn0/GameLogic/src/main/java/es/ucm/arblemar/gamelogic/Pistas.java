package es.ucm.arblemar.gamelogic;

import com.sun.tools.javac.util.Pair;

import java.util.Random;

import sun.tools.jconsole.Tab;

public class Pistas {
    private TipoPista pista;

    public Pistas(TipoPista _pista){
        pista = _pista;
    }

    TipoPista GetTipoPista(){
        return  pista;
    }

    public void GetPista(Tablero t,int choice){

        switch (choice){
            case 0:
            {
                Vector2D doneteIndex = DonetePista(t);
                if(doneteIndex._x == -1){
                    GetPista(t,choice + 1);
                }
                else{
                    // Feedback de la pista
                }
                break;
            }
            case 1:
            {
                Vector2D azulIncorrecto = AzulIncorrecto(t);
                if(azulIncorrecto._x == -1){
                    GetPista(t,choice + 1);
                }
                else{
                    // Feedback de la pista
                }
                break;
            }
            default:{
                break;
            }
        }
        //return TipoPista.AZUL_AISLADA;
    }

    Vector2D DonetePista(Tablero t){

        Celda[][] casillas = t.GetCasillas();
        Pair<Integer,Integer>[] indexAzules = t.GetIndexAzules();
        int size = t.GetSize();

        Vector2D donete = new Vector2D(-1,-1);

        int indexAz = 0;
        int adyacentes = 0;


        Vector2D coors = new Vector2D(indexAzules[0].fst,indexAzules[0].snd);
        Vector2D [] dirs = new Vector2D[4];

        /**
         * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
         */
        dirs[0] = new Vector2D(0, -1);
        dirs[1] = new Vector2D(0, 1);
        dirs[2] = new Vector2D(-1, 0);
        dirs[3] = new Vector2D(1, 0);

        Vector2D currentDir = dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;

        int index = 0;
        boolean finish = false;
        while(!finish) {
            /**
             * Comprobación de si la casilla adyacente es gris o está fuera de los límites del grid
             * para cambiar de dirección o parar de buscar cuando ya no haya más que comprobar
             * */
            if(coors._y < 0 || coors._y >= size
                    || coors._x < 0 || coors._x >= size
                    || !casillas[(int)coors._x][(int)coors._y].IsLock()
                    || casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO) {
                index++;
                if(index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = dirs[index];
                    coors._x = indexAzules[indexAz].fst + currentDir._x;
                    coors._y = indexAzules[indexAz].snd + currentDir._y;
                }
                /**
                 * Nos quedamos sin azules para donetiar
                 */
                else if(adyacentes == ((CeldaAzul)casillas[indexAzules[indexAz].fst][indexAzules[indexAz].fst]).getValue()){
                    donete._x = indexAzules[indexAz].fst;
                    donete._y = indexAzules[indexAz].snd;
                    finish = true;
                }
                else if(indexAz >= indexAzules.length){
                    donete._x = -1;
                    donete._y = -1;
                    finish = true;
                }
                else {
                    indexAz++;
                    adyacentes = 0;
                    index = 0;
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
                finish = adyacentes > ((CeldaAzul)casillas[indexAzules[indexAz].fst][indexAzules[indexAz].fst]).getValue();
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return donete;
    }

    Vector2D AzulIncorrecto(Tablero t){

        Celda[][] casillas = t.GetCasillas();
        Pair<Integer,Integer>[] indexAzules = t.GetIndexAzules();
        int size = t.GetSize();

        Vector2D donete = new Vector2D(-1,-1);

        int indexAz = 0;
        int adyacentes = 0;


        Vector2D coors = new Vector2D(indexAzules[0].fst,indexAzules[0].snd);
        Vector2D [] dirs = new Vector2D[4];

        /**
         * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
         */
        dirs[0] = new Vector2D(0, -1);
        dirs[1] = new Vector2D(0, 1);
        dirs[2] = new Vector2D(-1, 0);
        dirs[3] = new Vector2D(1, 0);

        Vector2D currentDir = dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;

        int index = 0;
        boolean finish = false;
        while(!finish) {
            /**
             * Comprobación de si la casilla adyacente es gris o está fuera de los límites del grid
             * para cambiar de dirección o parar de buscar cuando ya no haya más que comprobar
             * */
            if(coors._y < 0 || coors._y >= size
                    || coors._x < 0 || coors._x >= size
                    || !casillas[(int)coors._x][(int)coors._y].IsLock()
                    || casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO) {
                index++;
                if(index < dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = dirs[index];
                    coors._x = indexAzules[indexAz].fst + currentDir._x;
                    coors._y = indexAzules[indexAz].snd + currentDir._y;
                }
                /**
                 * Nos quedamos sin azules para donetiar
                 */
                else if(adyacentes > ((CeldaAzul)casillas[indexAzules[indexAz].fst][indexAzules[indexAz].fst]).getValue()){
                    donete._x = indexAzules[indexAz].fst;
                    donete._y = indexAzules[indexAz].snd;
                    finish = true;
                }
                else if(indexAz >= indexAzules.length){
                    donete._x = -1;
                    donete._y = -1;
                    finish = true;
                }
                else {
                    indexAz++;
                    adyacentes = 0;
                    index = 0;
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
                finish = adyacentes > ((CeldaAzul)casillas[indexAzules[indexAz].fst][indexAzules[indexAz].fst]).getValue();
            }
        }

        /**
         * Si los adyacentes del círculo son menores o iguales
         * que el valor que
         * */
        return donete;
    }
}
