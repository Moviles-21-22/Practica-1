package es.ucm.arblemar.androidengine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import es.ucm.arblemar.engine.AbstractGraphics;
import es.ucm.arblemar.engine.Font;
import es.ucm.arblemar.engine.Image;
import es.ucm.arblemar.engine.Vector2;
import android.graphics.Rect;

public class AndroidGraphics extends AbstractGraphics {

    private AndroidScreen screen;
    private AndroidGraphics graphics;
    private AndroidInput input;
    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;
    private AssetManager assetManager;


    public AndroidGraphics(AppCompatActivity appCmtAct,int w,int h,Bitmap bt){
        super(w, h);
        this.bitmap = bt;
        this.canvas = new Canvas(bt);
        this.paint = new Paint();
        this.assetManager = appCmtAct.getAssets();
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public Image newImage(String name){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = null;
        Bitmap currBitMap = null;
        InputStream inputStream = null;

        try{
            inputStream = assetManager.open(name);
            currBitMap = BitmapFactory.decodeStream(inputStream);
            if(currBitMap == null){
                throw new RuntimeException("No se ha podido cargar el bitmap del asset " + name);
            }
        }
        catch (IOException e){
            throw new RuntimeException("No se ha podido cargar el bitmap del asset " + name);
        }
        finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }
                catch (IOException e){
                    throw new RuntimeException("InputStream null en la carga del asset " + name);
                }
            }
        }
        return new AndroidImage(currBitMap);
    }

    @Override
    public Font newFont(String filename, int size, boolean isBold){
        return new AndroidFont(Typeface.createFromAsset(assetManager,filename),size,filename);
    }

    @Override
    public void clear(int color){
        canvas.drawColor(color);
    }

    @Override
    public void setColor(int color){
        paint.setColor(color);
    }

    @Override
    public void setFont(Font font, int tam) {
        paint.setTypeface(((AndroidFont)font).getFont());
    }

    @Override
    public void drawImage(Image image, int x, int y, int w, int h) {
        Rect source = new Rect(0,0,image.getWidth(),image.getHeight());
        Rect destiny = new Rect(x,y,x+w,y + h);
        canvas.drawBitmap(((AndroidImage)image).getBitmap() ,source,destiny,null);
    }

    @Override
    public void drawLine(Vector2 P, Vector2 Q){

    }

    @Override
    public void drawCircle(Vector2 centro, int radio) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawCircle(centro._x, centro._y, radio, this.paint);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void drawText(String text, int x, int y, Font font, int tam) {
        Typeface currFont = ((AndroidFont)font).getFont();
        paint.setTypeface(currFont);
        paint.setTextSize(realSize(realSize(tam)));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text,x,y,paint);
        paint.reset();
    }

    @Override
    public void drawRect(int x, int y, int width, int height){
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x,y,x + width, y + width, paint);
        paint.reset();
    }

    @Override
    public void fillCircle(Vector2 centro, int dm){
        canvas.drawCircle(centro._x,centro._y,dm,paint);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x,y,x + width,y +width,paint);
        paint.reset();
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
