public class Celda {

    protected boolean lock = false;
    protected Color color = Color.gris;

    protected Celda(Color c) : color(c) {}
    protected bool Click() = 0;
    protected void Init() = 0;

    public Color GetColor(){
        return color;
    }
};