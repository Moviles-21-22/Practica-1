package es.ucm.arblemar.engine;

public interface Image {
    public boolean init();
    /**
     * Devuelve el ancho de la imagen
     * */
    int getWidth();

    /**
     * Devuelve el alto de la imagen
     * */
    int getHeight();
}