package es.ucm.arblemar.engine;

public abstract class AbstractGraphics implements Graphics {

    protected AbstractGraphics(int w, int h){
        _wLogWindow = w;
        _hLogWindow = h;
        _posLogX = 0.0f;
        _posLogY = 0.0f;
    }

    protected AbstractGraphics() {
    }

    /**
     * Devuelve el factor de escala de la ventana
     * */
    private float scaleFactor(){
        float wFactor = getWidth() / _wLogWindow;
        float hFactor = getHeight() / _hLogWindow;

        // Nos interesa el factor más pequeño
        return wFactor < hFactor ? wFactor : hFactor;
    }

    public Vector2 realPos(int x, int y){
        _scaleFactor = scaleFactor();
        float offsetX = (getWidth() - (_wLogWindow * _scaleFactor)) / 2.0f;
        float offsetY = (getHeight() - (_hLogWindow) * _scaleFactor) / 2.0f;

        int newPosX = (int)((x * _scaleFactor) + offsetX);
        int newPosY = (int)((y * _scaleFactor) + offsetY);

        return new Vector2((int)(newPosX), (int)(newPosY));
    }

    public Vector2 realSize(int w, int h){
        _scaleFactor = scaleFactor();
        int newW = (int)(w * _scaleFactor);
        int newH = (int)(h * _scaleFactor);

        return new Vector2(newW, newH);
    }

    public int realSize(int size){
        _scaleFactor = scaleFactor();
        return (int)(size * _scaleFactor);
    }
    private Vector2 translateWindow() {
        float offsetX = (getWidth() - (_wLogWindow * _scaleFactor)) / 2.0f;
        float offsetY = (getHeight() - (_hLogWindow) * _scaleFactor) / 2.0f;

        int newPosX = (int)((_posLogX * _scaleFactor) + offsetX);
        int newPosY = (int)((_posLogY * _scaleFactor) + offsetY);

        return new Vector2((int)(newPosX), (int)(newPosY));
    }

    public void prepareFrame(){
        _scaleFactor = scaleFactor();
        Vector2 newPos = translateWindow();

        translate(150, 100);
        scale(_scaleFactor, _scaleFactor);
    }

    @Override
    public int getLogWidth(){
        return (int)_wLogWindow;
    }

    @Override
    public int getLogHeight(){
        return (int)_hLogWindow;
    }

    // Posaición lógica
    protected float _posLogX;
    protected float _posLogY;

    // Dimensión lógica
    protected float _wLogWindow;
    protected float _hLogWindow;

    // Factor de escala
    protected float _scaleFactor;
}
