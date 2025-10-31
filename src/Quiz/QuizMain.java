package Quiz;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizMain extends JFrame implements ActionListener {
    private final GLCanvas glcanvas;
    private final QuizEventListener glevent = new QuizEventListener();

    public static void main(String[] args) {
        new QuizMain();
    }
    public QuizMain() {
        super("Quiz Frame");

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
