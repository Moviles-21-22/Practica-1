package es.ucm.arblemar.androidengine;

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
    private App _newApp;
    private volatile boolean running = false;
    private Thread thread;
    private long _lastFrameTime = 0;
    private long _currentTime = 0;
    private double _deltaTime = 0;
    private boolean _changeApp = false;

    public AndroidEngine(AppCompatActivity activity, int logicW, int logicH){

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        boolean landScape = activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int bufferW = landScape ? logicH : logicW;
        int bufferH = landScape ? logicW : logicH;
        Bitmap buffer = Bitmap.createBitmap(bufferW,bufferH, Bitmap.Config.RGB_565);

        surface = new SurfaceView(activity.getApplicationContext());
        graphics = new AndroidGraphics(activity,logicW,logicH,buffer);
        input = new AndroidInput(surface);
        activity.setContentView(surface);
    }

    public boolean init(App initAp, String nameGame, int w, int h) {
        currApp = initAp;
        return currApp.init();
    }

    @Override
    public void run(){

        SurfaceHolder holder = surface.getHolder();
        _lastFrameTime = System.nanoTime();

        running = true;
        while (running){
            updateDeltaTime();

            currApp.handleInput();
            currApp.update(_deltaTime);

            while (!holder.getSurface().isValid());
            Canvas canvas = holder.lockCanvas();

            graphics.setCanvas(canvas);
            graphics.prepareFrame();

            graphics.clear(0xFFFFFFFF);
            currApp.render();

            holder.unlockCanvasAndPost(canvas);

            // Inicialización en diferido del nuevo estado
            if(_changeApp)
            {
                _changeApp = false;
                currApp = _newApp;
                currApp.init();
            }
        }
    }

    private void updateDeltaTime(){
        _currentTime = System.nanoTime();
        long nanoElapsedTime = _currentTime - _lastFrameTime;
        _lastFrameTime = _currentTime;
        _deltaTime = (double) nanoElapsedTime / 1.0E9;
    }

    @Override
    public void initNewApp(App newApp) {
        _changeApp = true;
        _newApp = newApp;
    }


    @Override
    public Graphics getGraphics(){
        return graphics;
    }

    @Override
    public Input getInput(){
        return input;
    }

    public void onResume() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void onPause() {
        running = false;
        while (true) {
            try {
                thread.join();
                break;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

