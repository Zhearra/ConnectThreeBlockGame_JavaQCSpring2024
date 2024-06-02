import javax.swing.*;
import java.awt.*;

public class ColorPalletFrame extends JFrame{

    Color[] colors;
    int types;
    public ColorPalletFrame(int types, Color[] colors) {

        this.types = types;
        this.colors = colors;

        JFrame colorPalletFrame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 200);

        JPanel panel = new JPanel(new GridLayout(1, types));

        for (int i = 0; i < types; i++) {
            JButton button = new JButton();
            button.setBackground(colors[i]);
            button.setSize(50,50);
            button.setOpaque(true);
            button.setBorderPainted(false);
            panel.add(button);
        }

        this.getContentPane().add(panel);
        this.setVisible(true);

    }

}