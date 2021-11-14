package es.ucm.arblemar.androidengine;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.arblemar.engine.App;
import es.ucm.arblemar.engine.Engine;
import es.ucm.arblemar.engine.Graphics;
import es.ucm.arblemar.engine.Input;

public class AndroidEngine implements Engine, Runnable {

    private AndroidGraphics graphics;
    private AndroidInput input;
    private SurfaceView surface;
    private App currApp;
    private float _lastFrameTime;
    private volatile boolean running;

    public AndroidEngine(AppCompatActivity activity, int logicW, int logicH){

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        boolean landScape = activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int bufferW = landScape ? logicH : logicW;
        int bufferH = landScape ? logicW : logicH;
        Bitmap buffer = Bitmap.createBitmap(bufferW,bufferH, Bitmap.Config.RGB_565);

        surface = new SurfaceView(activity.getApplicationContext());
        graphics = new AndroidGraphics(activity,600,400,buffer);
        input = new AndroidInput(surface);
        activity.setContentView(surface);
    }

    @Override
    public boolean init(App initAp, String nameGame, int w, int h) {
        currApp = initAp;
        return currApp.init();
        //return graphics.init() && input.init() && currApp.init();
    }

    @Override
    public void run(){

        SurfaceHolder holder = surface.getHolder();
        _lastFrameTime = System.nanoTime();

        running = true;
        while (running){
            Canvas canvas = holder.lockCanvas();
            graphics.setCanvas(canvas);

            currApp.handleInput();
            //currApp.update(_deltaTime);

            while (!holder.getSurface().isValid());

            currApp.render();

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean initNewApp(App newApp) {
        return false;
    }


    @Override
    public Graphics getGraphics(){
        return graphics;
    }

    @Override
    public Input getInput(){
        return input;
    }
}

