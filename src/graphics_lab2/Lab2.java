package graphics_lab2;

import com.sun.opengl.util.Animator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab2 extends JFrame implements ActionListener {
    private GLCanvas glcanvas;
    private SimpleGLEventListener glevent = new SimpleGLEventListener();
    private Animator anim = new Animator();

    public static void main(String[] args) {
        new Lab2();
    }

    public Lab2() {
        super("Lab2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        glcanvas = new GLCanvas();
        JButton triangleBtn = new JButton("Draw Triangle");
        triangleBtn.setActionCommand("triangle");
        triangleBtn.addActionListener(this);
        add(triangleBtn, BorderLayout.NORTH);

        JButton quadBtn = new JButton("Draw Quadrant");
        quadBtn.setActionCommand("quad");
        quadBtn.addActionListener(this);
        add(quadBtn, BorderLayout.SOUTH);

        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "triangle":
                glevent.DrawTriangle();
                glcanvas.repaint();
                break;
            case "quad":
                glevent.DrawQuad();
                glcanvas.repaint();
                break;
        }
    }
}
