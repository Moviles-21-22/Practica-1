package es.ucm.arblemar.engine;

public class Rect {
    public Rect(float x, float y, float width, float height)
    {
        this.x1 = x;
        this.y1 = y;
        this.x2 = x + width;
        this.y2 = y + height;

        //Width and height
        this.w = width;
        this.h = height;
    }

    /**
     * Getter
     * @return anchura del rectángulo
     */
    public float getWidth() { return w; };


    /**
     * Getter
     * @return altura del rectángulo
     */
    public float getHeight() { return h; };

    /**
     * Getter
     * @return coordenada horizontal de la esquina superior izquierda
     */
    public float x1() { return x1; };

    /**
     * Getter
     * @return coordenada horizontal de la esquina inferior derecha
     */
    public float x2() { return x2; };

    /**
     * Getter
     * @return coordenada vertical de la esquina superior izquierda
     */
    public float y1() { return y1; };

    /**
     * Getter
     * @return coordenada vertical de la esquina inferior derecha
     */
    public float y2() { return y2; };

    //Coordenadas de la esquina superior izquierda del rectángulo
    private float x1;
    private float x2;
    private float y1;
    private float y2;

    //Anchura y altura del rectángulo
    private float w;
    private float h;
}
