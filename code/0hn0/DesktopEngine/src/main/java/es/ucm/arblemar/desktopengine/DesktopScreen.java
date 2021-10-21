package es.ucm.arblemar.desktopengine;

import java.awt.Color;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class DesktopScreen extends JFrame {

    private BufferStrategy strategy;

    public DesktopScreen(String titulo){
        super(titulo);
    }

    public void createScreen(){
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setVisible(true);
        setIgnoreRepaint(false);
        int intentos = 100;
        while (intentos-- > 0) {
            try {
                createBufferStrategy(2);
                break;
            } catch (Exception e) {
            }
        }
        if (intentos == -1) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        } else {

            System.out.println("BufferStrategy tras " + (100 - intentos) + " intentos.");
        }

        strategy = getBufferStrategy();
    }

    public BufferStrategy getStrategy(){
        return strategy;
    }
}
