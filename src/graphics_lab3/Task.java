package graphics_lab3;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Task extends JFrame {

    private final GLCanvas glcanvas;
    private final EventListner glevent = new EventListner();

    public static void main(String[] args) {
        new Task();
    }

    public Task() {
        super("Happy Village");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }
}
