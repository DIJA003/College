package TexturesLab.test2;

import TexturesLab.AnimListener;
import TexturesLab.Example1.Anim;
import TexturesLab.Example1.AnimGLEventListener3;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Soldier extends JFrame {

    public static void main(String[] args) {
        new Soldier();
    }


    public Soldier() {
        GLCanvas glcanvas;
        Animator animator;

        SoliderGLEventListener listener = new SoliderGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}