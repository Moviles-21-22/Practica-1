package es.ucm.arblemar.androidengine;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidScreen extends SurfaceView implements Runnable {
    private AndroidGraphics graphics;
    private AndroidInput input;
    private SurfaceHolder holder;

    public AndroidScreen(Context context, AndroidGraphics g, AndroidInput i){
        super(context);
        graphics = g;
        input = i;
        holder = getHolder();
    }

    @Override
    public void run() {

    }
}
