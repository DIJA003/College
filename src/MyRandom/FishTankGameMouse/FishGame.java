package MyRandom.FishTankGameMouse;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FishGame extends JFrame implements ActionListener {
    FPSAnimator animator;
    GLCanvas canvas;
    EventListener listener = new EventListener();
    public static void main(String[] args){
        new FishGame();
    }
    public FishGame(){
        super("Fish Tank!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new GLCanvas();
        canvas.addGLEventListener(listener);
        canvas.addKeyListener(listener);
        canvas.setFocusable(true);

        animator = new FPSAnimator(canvas, 30);
        animator.start();
        JButton restart = new JButton("Restart Game");
        restart.setFocusable(false);
        restart.setActionCommand("rstr");
        restart.addActionListener(this);
        restart.setBackground(Color.ORANGE);

        add(canvas, BorderLayout.CENTER);
        add(restart,BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("rstr")){
            listener.restartGame();
            canvas.requestFocus();
        }
    }
}
