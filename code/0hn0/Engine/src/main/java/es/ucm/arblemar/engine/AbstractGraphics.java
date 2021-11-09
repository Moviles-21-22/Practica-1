package es.ucm.arblemar.engine;

public class AbstractGraphics implements Graphics {

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public Image newImage(String name, int w, int h) throws Exception {
        return null;
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold) throws Exception {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void setFont(Font font) {

    }

    @Override
    public void drawImage(Image image, int x, int y) {

    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {

    }

    @Override
    public void drawRect(float x, float y, int width, int height) {

    }

    @Override
    public void drawCircle(Vector2 centro, float radio) {

    }

    @Override
    public void drawText(String text, float x, float y) {

    }

    @Override
    public void fillCircle(Vector2 centro, int dm) {

    }

    @Override
    public void fillRect(float x, float y, int width, int height) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void translate(float x, float y) {

    }

    @Override
    public Rect scale(float x, float y, float w, float h) {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }
}
