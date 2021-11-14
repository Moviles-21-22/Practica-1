package es.ucm.arblemar.androidengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;

public class AndroidGraphics extends AbstractGraphics {

    private AndroidScreen screen;
    private AndroidGraphics graphics;
    private AndroidInput input;
    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;


    public AndroidGraphics(AppCompatActivity appCmtAct,int w,int h,Bitmap bt){
        super(w, h);
        bitmap = bt;
        canvas = new Canvas(bitmap);
        paint = new Paint();
        screen = new AndroidScreen(appCmtAct, this,input);

    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public Image newImage(String name){
        return null;
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold){
        return null;
    }

    @Override
    public void clear(int color){

    }

    @Override
    public void setColor(int color){

    }

    @Override
    public void setFont(Font font, int tam) {
        
    }

    @Override
    public void drawImage(Image image, int x, int y, int w, int h) {
        //Rect srcRect = new Rect(x,y,w,h);
        //srcRect.left = 0;
        //srcRect.top = 0;
        //srcRect.right = image.getWidth();
        //srcRect.bottom = image.getHeight();
//
        //dstRect.left = x;
        //dstRect.top = y;
        //dstRect.right = x + w;
        //dstRect.bottom = y + h;
        //canvas.drawBitmap(((AndroidImage)image,srcRect,);
    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q){

    }

    @Override
    public void drawCircle(Vector2 centro, int radio) {
        canvas.drawCircle(centro._x, centro._y, radio, this.paint);
    }

    @Override
    public void drawText(String text, int x, int y, Font font, int tam) {
        canvas.drawText(text, x, y, paint);
        paint.reset();
    }

    @Override
    public void drawRect(int x, int y, int width, int height){

    }

    @Override
    public void fillCircle(Vector2 centro, int dm){

    }

    @Override
    public void fillRect(int x, int y, int width, int height) {

    }

    @Override
    public int getWidth() {
        //TODO: ojito
        return  1;
    }

    @Override
    public int getHeight() {
        //TODO: ojito
        return  1;
    }

    @Override
    public void updateGraphics() {

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

    public AndroidScreen getScreen() {
        return screen;
    }

    public void setCanvas(Canvas _canvas) {
        canvas = _canvas;
    }
}
