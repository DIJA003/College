package MyRandom.MyFirstGame;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;

public class PongMain extends JFrame {
    private FPSAnimator anim;
    private GLCanvas glCanvas;
    private PongEventListener pong = new PongEventListener();
    public static void main(String[] args){
        new PongMain();
    }
    public PongMain(){
        super("Pong!");
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
