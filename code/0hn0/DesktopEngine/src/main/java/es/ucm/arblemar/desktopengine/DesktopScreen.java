package es.ucm.arblemar.desktopengine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DesktopScreen extends JFrame {

    private BufferStrategy strategy;

    public DesktopScreen(String titulo){
        super(titulo);
    }

    public void createScreen(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setBackground(Color.BLACK);
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
