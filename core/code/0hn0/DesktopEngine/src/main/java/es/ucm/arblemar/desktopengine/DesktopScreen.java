package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import es.ucm.arblemar.engine.Font;

/**
 * Clase encargada de inicializar la ventana de Java
 * */
public class DesktopScreen extends JFrame {
    public DesktopScreen(String titulo){
        super(titulo);
    }

    public boolean init(int winWidth, int winHeight){
        setSize(winWidth, winHeight);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // Renderizado activo
        setIgnoreRepaint(true);

        int intentos = 100;
        while (intentos-- > 0) {
            try {
                createBufferStrategy(2);
                break;
            } catch (Exception e) {
            }
        }
        if (intentos <= 0) {
            System.err.println("No pude crear la BufferStrategy");
            return false;
        } else {
            System.out.println("BufferStrategy tras " + (100 - intentos) + " intentos.");
        }

        _strategy = getBufferStrategy();
        return true;
    }

    public BufferStrategy getStrategy(){
        return _strategy;
    }

    private BufferStrategy _strategy;
}
