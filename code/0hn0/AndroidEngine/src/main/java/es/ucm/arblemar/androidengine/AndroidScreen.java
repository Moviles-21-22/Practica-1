package es.ucm.arblemar.androidengine;

import android.content.Context;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

public class AndroidScreen extends SurfaceView implements Runnable {
    private AndroidGraphics graphics;
    private AndroidInput input;
    private SurfaceHolder holder;
    private Thread renderThread;
    private volatile boolean running = false;

    public AndroidScreen(Context context, AndroidGraphics g, AndroidInput i){
        super(context);
        graphics = g;
        input = i;
        holder = getHolder();

        InputStream data = null;
        try{
            //data = context.get
        }
        catch (Exception e){
            System.err.println(e);
        }

        setOnTouchListener(input);
    }

    @Override
    public void run() {

    }
}
