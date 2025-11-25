package graphics_postLab4_assignments.trees_pyramdis;

import com.sun.opengl.util.FPSAnimator;
import graphics_postLab4_assignments.solar_system.solarListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TPMain extends JFrame  {

    public TPMain() {
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new TPlistener());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        add(canvas);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    public static void main(String[] args) {
        new TPMain();
    }
}
