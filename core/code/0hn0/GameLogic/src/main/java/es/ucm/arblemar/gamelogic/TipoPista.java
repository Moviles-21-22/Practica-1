package es.ucm.arblemar.gamelogic;

public enum TipoPista {
    /**
     * Si un número tiene ya visibles el número de celdas que dice, entonces se puede
     * “cerrar”, es decir, poner paredes en los extremos. Ocurre en el de la posición A
     * de la figura, o en el primer movimiento del ejemplo anterior.
     */
    DONETE(0),

    /**
     * Si pusiéramos un punto azul en una celda vacía, superaríamos el número de visibles
     * del número, y por tanto, debe ser una pared . Ocurre en el de la posición B;
     * no podemos poner un punto en la posición de su derecha, porque pasaría a tener
     * tres visibles.
     */
    AZUL_INCORRECTO(1),

    /**
     * Si no ponemos un punto en alguna celda vacía, entonces es imposible alcanzar el
     * número. Ocurre en el de la posición C. Obligatoriamente tenemos que poner un
     * punto en la celda de su izquierda, porque por la derecha (que es la única otra forma
     * de añadir visibles) no vamos a conseguir llegar al 2 nunca.
     */
    ADYACENTE_DONETE(2),

    //  Hay algunas pistas que pueden proporcionarse si el jugador se ha equivocado:
    /**
     * Un número tiene más casillas azules visibles de las que debería.
     */
    SOBRE_ADYACENCIA_AZUL(3),

    /**
     * Un número tiene una cantidad insuficiente de casillas azules visibles y sin embargo
     * ya está “cerrada” (no puede ampliarse más por culpa de paredes).
     */
    SOBRE_ADYACENCIA_ROJA(4),

    /**
     * Si una celda está vacía y cerrada y no ve ninguna celda azul, entonces es pared (todos
     * los puntos azules deben ver al menos a otro).
     */
    NO_VEO_AZUL(5),

    //  Además, hay pistas que ocurren sobre casillas que no son números:

    /**
     * En sentido opuesto, si hay una celda punto puesta por el usuario que está cerrada
     * y no ve a ninguna otra, entonces se trata de un error por el mismo motivo.
     */
    AZUL_AISLADA(6),

    /**
     * Un número que no ve suficientes puntos no está aún cerrado y solo tiene abierta una
     * dirección. Está cubierta por la pista 3.
     */
    ONE_DIRECTION(7),

    //  Es posible añadir pistas adicionales que resulten más explicativas, aunque no sean
    //  estrictamente necesarias porque están cubiertas por las anteriores:


    /**
     * Un número no está cerrado y tiene varias direcciones, pero la suma alcanzable es el
     * valor que hay que conseguir
     * */
    SUMA_ALCANZABLE(8),

    /**
     * En sentido opuesto, una celda de tipo número no está cerrada pero si se ponen en
     * punto el resto de celdas vacías que tiene alcanzables no llegará a su valor, por lo
     * que es un futuro error
     * */
    SUMA_MENOR(9),

    MAX(10);

    private int value;

    public int getValue(){
        return value;
    }

    TipoPista(int i) {
        this.value = i;
    }
}