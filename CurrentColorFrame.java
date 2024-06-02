import javax.swing.*;
import java.awt.*;

public class CurrentColorFrame extends JFrame{

    Color currentColor;
    int types;
    public CurrentColorFrame(int types, Color currentColor) {

        this.types = types;
        this.currentColor = currentColor;

        JFrame frame = new JFrame();
        this.setTitle("Current Color");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);
        this.setLocation(1250,0);

        JPanel panel = new JPanel(new GridLayout(1, 1));

        for (int i = 0; i < 1; i++) {
            JButton button = new JButton();
            button.setBackground(currentColor);
            button.setSize(50,50);
            button.setOpaque(true);
            button.setBorderPainted(false);
            panel.add(button);
        }

        this.getContentPane().add(panel);
        this.setVisible(true);

    }

}