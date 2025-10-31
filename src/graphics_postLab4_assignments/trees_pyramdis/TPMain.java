package graphics_postLab4_assignments.trees_pyramdis;

import graphics_postLab4_assignments.solar_system.solarListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TPMain extends JFrame implements ActionListener {

    private final GLCanvas glcanvas;
    private final TPlistener glevent = new TPlistener();
    JLabel label;

    public static void main(String[] args){
        new TPMain();
    }

    public TPMain() {
        super("IDK");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
