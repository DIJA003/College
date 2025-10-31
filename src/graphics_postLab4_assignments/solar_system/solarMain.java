package graphics_postLab4_assignments.solar_system;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class solarMain extends JFrame implements ActionListener {
    private final FPSAnimator anim;
    private final GLCanvas glcanvas;
    private final solarListener glevent = new solarListener();
    JLabel label;
    public static void main(String[] args){
        new solarMain();
    }

    public solarMain(){
        super("Solar System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);
        anim = new FPSAnimator(glcanvas,30);
        anim.start();

        JPanel bottomPanel = new JPanel();
        label = new JLabel("speed: " + glevent.getSpeed());
        label.setForeground(Color.WHITE);


        JButton rotateClockWise = new JButton("Rotate Anti-Clock Wise");
        rotateClockWise.setActionCommand("rcw");
        rotateClockWise.addActionListener(this);
        bottomPanel.add(rotateClockWise);

        JButton rotateCounterClockWise = new JButton("Rotate Clock Wise");
        rotateCounterClockWise.setActionCommand("rccw");
        rotateCounterClockWise.addActionListener(this);
        bottomPanel.add(rotateCounterClockWise);

        JButton incSpeed = new JButton("Increase Rotation");
        incSpeed.setActionCommand("faster");
        incSpeed.addActionListener(this);
        bottomPanel.add(incSpeed);

        JButton decSpeed = new JButton("Decrease Rotation");
        decSpeed.setActionCommand("slower");
        decSpeed.addActionListener(this);
        bottomPanel.add(decSpeed);

        bottomPanel.add(label);

        bottomPanel.setBackground(new Color(50, 50, 50));
        bottomPanel.setForeground(Color.WHITE);

        JPanel topPanel = new JPanel();

        JButton zoomIn = new JButton("Zoom In");
        zoomIn.setActionCommand("zin");
        zoomIn.addActionListener(this);
        topPanel.add(zoomIn);


        JButton zoomOut = new JButton("Zoom Out");
        zoomOut.setActionCommand("zout");
        zoomOut.addActionListener(this);
        topPanel.add(zoomOut);

        topPanel.setBackground(new Color(30, 80, 200));
        topPanel.setForeground(Color.WHITE);

        add(bottomPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        setSize(825, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "rcw":
                glevent.rotateCW();
                break;
            case "rccw":
                glevent.rotateCCW();
                break;
            case "zin":
                glevent.zoomIN();
                break;
            case "zout":
                glevent.zoomOut();
                break;
            case "faster":
                glevent.increaseSpeed();
                label.setText("speed: " + glevent.getSpeed());
//                repaint();
                break;
            case "slower":
                glevent.decreaseSpeed();
                label.setText("speed: " + glevent.getSpeed());
//                repaint();
                break;
        }
//        glcanvas.display();
    }
}
