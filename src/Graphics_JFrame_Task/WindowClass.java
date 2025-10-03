package Graphics_JFrame_Task;

import javax.swing.*;

public class WindowClass extends JFrame {
    public WindowClass(){
        super("CLICK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300,200);
        setVisible(true);
    }
}
