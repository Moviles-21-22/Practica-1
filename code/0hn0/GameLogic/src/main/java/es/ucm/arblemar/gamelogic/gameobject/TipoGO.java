package es.ucm.arblemar.gamelogic.gameobject;

public enum TipoGO {
    Boton(0),
    Celda(1),
    Icon(2),

    Rect(3),
    Texto(4);

    private int value;
    private TipoGO(int i) {
        this.value = i;
    }

    public int getValue(){
        return value;
    }
};
