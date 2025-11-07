package Graphics_Lab1;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow(){
        super("Main Window");


        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new SimpleGLEventListener());
        add(glcanvas, BorderLayout.CENTER);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(this);
        setVisible(true);

    }


}
