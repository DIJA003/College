package graphics_lab6;

import MyRandom.MyFirstGame.PongEventListener;
import MyRandom.MyFirstGame.PongMain;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class lab6Main extends JFrame {
    private FPSAnimator anim;
    private GLCanvas glCanvas;
    private lab6Listener pong = new lab6Listener();
    public static void main(String[] args){
        new lab6Main();
    }

    public lab6Main(){
        super("lab6!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        glCanvas = new GLCanvas();
        glCanvas.addGLEventListener(pong);
        glCanvas.addKeyListener(pong);
        anim = new FPSAnimator(glCanvas,30);
        anim.start();

        add(glCanvas);
        setSize(800,500);
        setLocationRelativeTo(this);
        setVisible(true);
        glCanvas.requestFocus();


    }
}
