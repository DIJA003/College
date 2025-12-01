package TexturesLab.Monster;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import javax.media.opengl.GLCanvas;
import javax.swing.JFrame;

public class MonsterApp extends JFrame {

    private Animator animator;
    private GLCanvas glcanvas;
    private MonstersGLEventListener listener = new MonstersGLEventListener();

    public static void main(String[] args) {
        new MonsterApp().animator.start();
    }

    public MonsterApp() {
        super("Wave Monsters Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        animator = new FPSAnimator(10);
        animator.add(glcanvas);

        add(glcanvas, BorderLayout.CENTER);
        setSize(700, 700);
        setLocationRelativeTo(this);
        setVisible(true);
    }

}
