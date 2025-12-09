package TexturesLab.task;

import TexturesLab.test.testGLEventListener;
import TexturesLab.test.testMain;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class taskMain extends JFrame {

    public static void main(String[] args) {
        new taskMain();
    }

    public taskMain() {
        GLCanvas glcanvas;
        Animator animator;

        taskGLEventListener listener = new taskGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(30);
        animator.add(glcanvas);
        animator.start();

        setTitle("task Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}