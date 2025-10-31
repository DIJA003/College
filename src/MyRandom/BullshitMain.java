package MyRandom;

import graphics_lab3.EventListner;
import graphics_lab3.Task;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BullshitMain extends JFrame implements ActionListener {
    private final GLCanvas glcanvas;
    private final BullshitListner glevent = new BullshitListner();

    public static void main(String[] args) {
        new BullshitMain();
    }

    public BullshitMain() {
        super("Bull Shit Frame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(glevent);
        add(glcanvas, BorderLayout.CENTER);

//        JButton rightBtn = new JButton("Move right");
//        rightBtn.setActionCommand("right");
//        rightBtn.addActionListener(this);
//        add(rightBtn, BorderLayout.SOUTH);
//
//        JButton leftBtn = new JButton("Move left");
//        leftBtn.setActionCommand("left");
//        leftBtn.addActionListener(this);
//        add(leftBtn, BorderLayout.NORTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
