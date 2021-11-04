package es.ucm.arblemar.gamelogic;



public class Pistas {

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

    private Celda _celdaFeedback;



    /**
     * Arriba (0), Abajo (1), Izquierda (2), Derecha (3)
     */
    final Vector2[] _dirs = {
        new Vector2(0, -1),
        new Vector2(0, 1),
        new Vector2(-1, 0),
        new Vector2(1, 0)
    };


    public Pistas(Tablero t){

        _casillas = t.GetCasillas();
        _indexAzules = t.GetIndexAzules();
        _size = t.GetSize();

        for(int i = 0 ; i < 2; i++){
            BuscaPistas(t,i);
        }
    }

    public void BuscaPistas(Tablero tab, int choice){

        switch (choice){
            case 0:
            {
                //  Posición de la pista si es encontrada
                Vector2 doneteIndex = PistaUno();

                // Pista encontrada
                if(doneteIndex._x != -1){
                    index = doneteIndex;
                    tab.AgregaPista(this);
                }
                break;
            }
            case 1:
            {
                //  Posición de la pista si es encontrada
                Vector2 azulIncorrecto = AzulIncorrecto();
                if(azulIncorrecto._x == -1){
                    index = azulIncorrecto;
                    tab.AgregaPista(this);
                }
                break;
            }
            case 2:
            {
                TipoPista pista = BuscaPista();
                if(pista == TipoPista.MAX){
                }
                else {
                    // Feedback de la pista 9: la suma alcanzable
                }
                break;
            }
            default:{
                break;
            }
        }
        //return TipoPista.AZUL_AISLADA;
    }

    /**
     * Si pusiéramos un punto azul en una celda vacía, superaríamos el número de visibles
     * del número, y por tanto, debe ser una pared.
     */
    Vector2 AzulIncorrecto(){
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
                    || !_casillas[(int)coors._x][(int)coors._y].IsLock()
                    || _casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO) {
                index++;
                if(index < _dirs.length) {
                    // Reseteamos los valores para comprobar en la siguiente dirección
                    currentDir = _dirs[index];
                    coors._x = _indexAzules[indexAz]._x + currentDir._x;
                    coors._y = _indexAzules[indexAz]._y + currentDir._y;
                }
                /**
                 * Nos quedamos sin azules para donetiar
                 */
                else if(adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._x]).getValue()){
                    wrongBlue._x = _indexAzules[indexAz]._x;
                    wrongBlue._y = _indexAzules[indexAz]._y;
                    finish = true;
                }
                else if(indexAz >= _indexAzules.length){
                    wrongBlue._x = -1;
                    wrongBlue._y = -1;
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
            else if(_casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.AZUL)
            {
                adyacentes++;
                // Nos movemos a la siguiente casilla
                coors._y += currentDir._y;
                coors._x += currentDir._x;

                /**
                 *  Si hay más adyacentes que el valor de la celda azul, entonces no es válido
                 *  Se termina la búsqueda
                 */
                finish = adyacentes > ((CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._x]).getValue();
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
    Vector2 PistaUno(){

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
                    || _casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.GRIS
                    || _casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO) {
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
            else if(_casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.AZUL) {
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
    TipoPista BuscaPista(){
        //Vector2 adyDonete = new Vector2(-1,-1);
        TipoPista feedback = TipoPista.MAX;
        int indexAz = 0;
        int adyacentes = 0;

        Vector2 coors = new Vector2(_indexAzules[0]._x, _indexAzules[0]._y);

        Vector2 currentDir = _dirs[0];
        coors._x += currentDir._x;
        coors._y += currentDir._y;

        int index = 0;
        boolean finish = false;

        // Determina si la celda azul tiene solo una direccion
        Vector2 oneDirection;
        while (!finish)
        {
            TipoPista auxPista = EsSumaAlcanzable(_indexAzules[indexAz]);
            oneDirection = OneDirection(_indexAzules[indexAz]);

            if(auxPista == TipoPista.SUMA_MENOR)
            {
                _celdaFeedback = (CeldaAzul)_casillas[(int)_indexAzules[indexAz]._x][(int)_indexAzules[indexAz]._x];
                feedback = auxPista;
                finish = true;
            }
            else if(auxPista == TipoPista.SUMA_ALCANZABLE)
            {
                if(oneDirection._x > -1 && oneDirection._y > -1)
                {
                    feedback = TipoPista.ONE_DIRECTION;
                }
                else if(oneDirection._x == -1)
                {
                    feedback = auxPista;
                }

                _celdaFeedback = (CeldaAzul) _casillas[(int) _indexAzules[indexAz]._x][(int) _indexAzules[indexAz]._x];
                finish = true;
            }
            // Si no, entonces pasamos al siguiente azul
            else if(auxPista == TipoPista.MAX)
            {

            }
        }

        return feedback;
    }

    /**
     * Comprobamos si el azul tiene o más direcciones
     */
    Vector2 OneDirection(Vector2 currAzul){
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
            if(currAzul._y + _dirs[0]._y < _size && _casillas[(int)currAzul._x][(int)(currAzul._y + _dirs[0]._y)]._tipoCelda == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[0];
            }
            else if(currAzul._y + _dirs[1]._y >= 0 && _casillas[(int)currAzul._x][(int)(currAzul._y + _dirs[0]._y)]._tipoCelda == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[1];
            }
            else if(currAzul._x + _dirs[2]._x >= 0 && _casillas[(int)(currAzul._x + _dirs[2]._x)][(int)currAzul._y]._tipoCelda == TipoCelda.GRIS)
            {
                laterales++;
                oneDirection = _dirs[2];
            }
            else if(currAzul._x + _dirs[3]._x < _size && _casillas[(int)(currAzul._x + _dirs[3]._x)][(int)currAzul._y]._tipoCelda == TipoCelda.GRIS)
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
    TipoPista EsSumaAlcanzable(Vector2 vAzul) {
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
                    || _casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.ROJO)
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
            else if(_casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.GRIS
                    || _casillas[(int)coors._x][(int)coors._y]._tipoCelda == TipoCelda.AZUL)
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

    TipoPista GetTipoPista(){
        return _pista;
    }
}
