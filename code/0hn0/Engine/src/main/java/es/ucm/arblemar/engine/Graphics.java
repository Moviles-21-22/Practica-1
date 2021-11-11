package es.ucm.arblemar.engine;

public interface Graphics {
    /**
     * Inicializa la interfaz gráfica
     * @return false si algo ha ido mal
     * */
    boolean init();
    /**
     * Carga una imagen almacenada en el contenedor de recursos de la aplicación a partir de su nombre
     * */
    Image newImage(String name, int w, int h) throws Exception;
    /**
     * Crea una nueva fuente del tamaño especificado a partir de un fichero .ttf. Se indica si se desea o no fuente
     * en negrita
     * */
    Font newFont(String filename, int size, boolean isBold) throws Exception;
    /**
     * Borra el contenido completo de la ventana, rellenándolo con un color recibido como parámetro
     * */
    void clear(int color);
    /**
     * Actualiza el color de las operaciones de renderizado posteriores
     * @param color se espera un color en hexadecimal
     * */
    void setColor(int color);
    /**
     * Actualiza la fuente de las operaciones de renderizado posteriores
     * @param font se espera una fuente tipo Font
     * */
    void setFont(Font font);
    /**
     * Dibuja una imagen
     * @param x posición X en pantalla
     * @param y posición Y en pantalla
     * */
    void drawImage(Image image, int x, int y);
    /**
     * Dibuja una línea desde el P(x1, y1) hasta Q(x2, y2)
     * */
    void drawLine(Vector2 P, Vector2 Q);
    /**
     * Dibuja un rectángulo en la posición (x, y) con
     * un tamaño (width, height)
     * */
    void drawRect(int x, int y, int width, int height);
    /**
     * Dibuja un círculo
     * */
    void drawCircle(Vector2 centro, int radio);
    /**
     * Renderiza texto
     * @param text texto a mostrar
     * @param x posición X en pantalla
     * @param y posición Y en pantalla
     * */
    void drawText(String text, int x, int y);
    /**
     * Rellena un circulo
     * */
    void fillCircle(Vector2 centro, int dm);
    /**
     * Rellena un rectángulo
     * */
    void fillRect(int x, int y, int width, int height);
    /**
     * Obtiene el ancho de la ventana
     * */
    int getWidth();
    /**
     * Obtiene el alto de la ventana
     * */
    int getHeight();

    //==============METODOS-CONTROL-CANVAS=================
    void updateGraphics();
    /**
     * Prepara el frame siguiente para que sea
     * escalado y transladado
     * */
    void prepareFrame();
    /**
     *  Traslada un objeto con una posición "pos"
     *  en función de "winSize"
     * @return Devuelve la posición trasladada
     * */
    void translate(int x, int y);
    /**
     *  Escala un objeto con un tamaño "size" en función de
     *  "winSize"
     * @return Devuelve el tamaño escalado*/
    void scale(float x, float y);
    /**
     *
     * */
    void save();
    /**
     *
     * */
    void restore();
}
