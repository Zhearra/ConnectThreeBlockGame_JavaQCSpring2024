import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TileMatching extends JFrame{

    public static void main(String[] args) {

        //1. Ask the user to input the number of rows, columns, and types using JOptionPane.
        JOptionPane.showMessageDialog(null, "To win: create a set of three tiles horizontally or diagonally!");
        String stringRows = JOptionPane.showInputDialog(null, "Enter the number of rows: ");
        int rows = Integer.parseInt(stringRows);
        String stringColumns = JOptionPane.showInputDialog(null, "Enter the number of columns: ");
        int cols = Integer.parseInt(stringColumns);
        String stringTypes = JOptionPane.showInputDialog(null, "Enter the amount of types (colors): ");
        int types = Integer.parseInt(stringTypes);

        Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.RED, Color.PINK, Color.black, Color.blue, Color.cyan, Color.darkGray, Color.lightGray, Color.green, Color.yellow, Color.magenta, Color.orange, Color.red, Color.pink};
        Color[] colorsTypeAmount = new Color[types];
        boolean colorsBool = false;

        //2. Display a set of randomly chosen colors equal to the number of types and ask the user whether those colors are okay.
        Random random = new Random();
        while (colorsBool == false) {

            for (int i = 0; i < types; i++) {
                int randomInt = random.nextInt(colors.length);
                colorsTypeAmount[i] = colors[randomInt];
            }
            ColorPalletFrame colorPalletFrame = new ColorPalletFrame(types, colorsTypeAmount);

            int reply = JOptionPane.showConfirmDialog(null, "Are these colors ok?");
            if (reply == JOptionPane.YES_OPTION) {
                colorsBool = true;
                colorPalletFrame.dispose();
            }
            if (reply == JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            }
            if (reply == JOptionPane.NO_OPTION) {
                //3. Repeat step 2 until the user accepts the colors.
                colorPalletFrame.dispose();
            }
        }

        //Find project requirements 4,5,6,7,8 in this class:
        GameFrame gameFrame = new GameFrame(rows, cols, types, colorsTypeAmount);
    }
}
