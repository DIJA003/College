package Graphics_JFrame_Task;

import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class WindowClass extends JFrame {
    private int clicks = 0;
    private final String[] texts = {
            " Harder!",
            " Is that all you got?",
            " Come on, Click here!",
            " Even your grandma clicks faster!",
            " Are you even trying?",
            " You call that a click?",
            " Pathetic...",
            " Keep going, maybe you'll get better!",
            " Almost there... not really!",
            " So Close.."
    };
    private int textsLen = texts.length;
    private final Random rand = new Random();
    private JButton click;

    public WindowClass() {
        super("CLICK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("C:\\Users\\Moustafa Abdulhammed\\Downloads\\9-91819_troll-face-troll-face-pixel-art.png");
        JLabel back = new JLabel(img);
        back.setLayout(null);
        setContentPane(back);

        click = new JButton("Click Me");
        click.setFont(new Font("Arial", Font.BOLD, 16));
        click.setBounds(200, 150, 180, 60);
        back.add(click);

        JButton exit = new JButton("quit");
        exit.setBounds(0, 0, 70, 30);
        exit.setBackground(Color.RED);
        back.add(exit);

        playSound("C:\\Users\\Moustafa Abdulhammed\\Downloads\\laugh.mp3");

        Timer timer = new Timer(800, t -> click.setForeground(Color.BLACK));

        click.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    click.setForeground(Color.RED);
                    timer.start();
                }
                if (clicks < textsLen) {
                    if (clicks == textsLen - 2) {
                        playSound("C:\\Users\\Moustafa Abdulhammed\\Downloads\\shaco-closer.mp3");
                    }
                    click.setText(texts[clicks]);
                    click.setSize(click.getPreferredSize());
                    clicks++;
                    move_btn();
                } else {
                    back.setIcon(new ImageIcon("C:\\Users\\Moustafa Abdulhammed\\Downloads\\Crying_Cat.jpg"));
                    click.setText("you are weak :P");
                    click.setSize(click.getPreferredSize());
                    JOptionPane.showMessageDialog(WindowClass.this, "Go to the GYM!!");
                    click.setEnabled(false);
                }
            }
        });

        exit.addActionListener(e -> System.exit(0));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void move_btn() {
        int fWidth = getWidth() - 40, fHeight = getHeight() - 60;
        int xNew = rand.nextInt(Math.max(1, fWidth - click.getWidth())), yNew = rand.nextInt(Math.max(1, fHeight - click.getHeight()));
        new Thread(() -> {
            int currX = click.getX(), currY = click.getY(), steps = 10;
            for (int i = 0; i < steps; i++) {
                int x = currX + (xNew - currX) * i / steps;
                int y = currY + (yNew - currY) * i / steps;
                click.setLocation(x, y);

                try {
                    Thread.sleep(15);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            //click.setLocation(xNew,yNew);
        }).start();
    }

    private void playSound(String filePath) {
        new Thread(() -> {
            FileInputStream file = null;
            try {
                file = new FileInputStream(filePath);
                Player play = new Player(file);
                play.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

}
