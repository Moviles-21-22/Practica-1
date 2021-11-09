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
     * Dibuja una línea desde el P(x1, y1) hasta Q(x2, y2) con un color
     * */
    void drawLine(float x1, float y1, float x2, float y2);
    /**
     * Dibuja un rectángulo con un color específico
     * */
    void drawRect(float x,float y, int width, int height);
    /**
     * Dibuja un círculo con un color específico
     * */
    void drawCircle(Vector2 centro, float radio);
    /**
     * Renderiza texto
     * @param text texto a mostrar
     * @param x posición X en pantalla
     * @param y posición Y en pantalla
     * */
    void drawText(String text, float x, float y);
    /**
     * Rellena un circulo con un color específico
     * */
    void fillCircle(Vector2 centro, int dm);
    /**
     * Rellena un rectángulo de un color específico
     * */
    void fillRect(float x, float y, int width, int height);
    /**
     * Obtiene el ancho de la ventana
     * */
    int getWidth();
    /**
     * Obtiene el alto de la ventana
     * */
    int getHeight();

    //==============METODOS-CONTROL-CANVAS=================
    /**
     *
     * */
    void translate(float x, float y);
    /**
     *
     * @return*/
    Rect scale(float x, float  y, float w, float h);
    /**
     *
     * */
    void save();
    /**
     *
     * */
    void restore();
}
