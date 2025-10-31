package task;

import graphics_postLab4_assignments.solar_system.solarListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskMain extends JFrame implements ActionListener {
    private final GLCanvas glcanvas;
    private final TaskEventListener glevent = new TaskEventListener();
    JLabel label;
    public static void main(String[] args){
        new TaskMain();
    }
    public TaskMain(){
        super("Floating boat");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        label = new JLabel();


        JButton rotateClockWise = new JButton("Rotate Clock Wise");
        rotateClockWise.setActionCommand("rcw");
        rotateClockWise.addActionListener(this);
        bottomPanel.add(rotateClockWise);

        JButton rotateCounterClockWise = new JButton("Rotate Counter Clock Wise");
        rotateCounterClockWise.setActionCommand("rccw");
        rotateCounterClockWise.addActionListener(this);
        bottomPanel.add(rotateCounterClockWise);

        JButton incSpeed = new JButton("move light");
        incSpeed.setActionCommand("faster");
        incSpeed.addActionListener(this);
        bottomPanel.add(incSpeed);

        JButton decSpeed = new JButton("move left");
        decSpeed.setActionCommand("slower");
        decSpeed.addActionListener(this);
        bottomPanel.add(decSpeed);

        bottomPanel.add(label);

        JPanel topPanel = new JPanel();

        JButton zoomIn = new JButton("Zoom In");
        zoomIn.setActionCommand("zin");
        zoomIn.addActionListener(this);
        topPanel.add(zoomIn);

        JButton zoomOut = new JButton("Zoom Out");
        zoomOut.setActionCommand("zout");
        zoomOut.addActionListener(this);
        topPanel.add(zoomOut);

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
                repaint();
                break;
            case "slower":
                glevent.decreaseSpeed();
                repaint();
                break;
        }
        glcanvas.display();
    }
}
