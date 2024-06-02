import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame implements ActionListener {
    int rows;
    int cols;
    JButton[][] grid;
    int types;
    Color[] colors;
    boolean checkLoss;
    Color currentColor;
    CurrentColorFrame currentColorFrame;
    int score = 0;
    JFrame gameFrame;

    public GameFrame(int rows, int cols, int types, Color[] colors) {
        this.rows = rows;
        this.cols = cols;
        this.types = types;
        this.colors = colors;

        gameFrame = new JFrame();
        gameFrame.setTitle("Tile Matching");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setBackground(null);

        JPanel gamePanel = new JPanel(new GridLayout(rows, cols));
        gamePanel.setBackground(null);

        /*4. For the game, display two windows. One should contain a grid of buttons with the given number
        of rows and columns. The other should simply display the color of the next tile (chosen
        randomly from the set of colors accepted by the user). */

        //4A: First Window:
        grid = new JButton[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JButton gridTile = new JButton();
                grid[r][c] = gridTile;
                grid[r][c].setBackground(null);
                grid[r][c].addActionListener(this);
                grid[r][c].setBounds(0, 0, 100, 100);
                grid[r][c].setOpaque(true);
                grid[r][c].setBorderPainted(false);
                gamePanel.add(grid[r][c]);
            }
        }

        gameFrame.getContentPane().add(gamePanel);
        gameFrame.setVisible(true);

        //4B: Second Window:
        updateColorFrame();

        //Check keyListener to see the rest of the project requirements
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       /*5. Whenever the user clicks on a button in the grid, add a tile of the displayed color to the lowest
        open space in the column of the button clicked. */
        int XY[] = findButton(e.getSource());
        setColor(XY);

        /*6. Display a message when a set of three or more in a row is made and remove the set after the
        user clicks OK*/
        while(findSets()){ //7. Handles chain reactions.
            JOptionPane.showMessageDialog(null, "You made a set!");
            removeSets();
        }

        //8. Display an endgame message when the user adds a tile to a full column.
        checkLoss(XY);
        currentColorFrame.dispose();
        updateColorFrame();

    }


    //Finds button location
    public int[] findButton(Object a) {

        int[] XY = new int[2]; //0 is Y 1 is X

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (a == grid[i][j]) {
                    XY[0] = i;
                    XY[1] = j;
                }
            }
        }
        return XY;
    }


    //Sets color of lowest button in column of choice to current color
    public void setColor(int XY[]) {

        boolean isColor = true;
        for (int i = 1; i < rows; i++) {

            //Lines 101-109, I use this loop structure often to account for the MacOS UImanager setting null colors to an OS based theme
            Color gridColor = grid[rows - i][XY[1]].getBackground();
            for (int j = 0; j < colors.length; j++) {
                if (gridColor != colors[j]) {
                    isColor = false;
                } else {
                    isColor = true;
                    j = colors.length;
                }
            }
            if (!isColor) {
                grid[rows - i][XY[1]].setBackground(currentColor);
                i = rows;
            }

            if (rows - i == 1) {
                gridColor = grid[0][XY[1]].getBackground();
                for (int j = 0; j < colors.length; j++) {
                    if (gridColor != colors[j]) {
                        isColor = false;
                    } else {
                        isColor = true;
                        j = colors.length;
                    }
                    if (!isColor) {
                        grid[0][XY[1]].setBackground(currentColor);
                        j = colors.length;
                    }
                }

            }
        }
    }
        //Detect loss
        public void checkLoss ( int[] XY){
            Color gridColor = grid[0][XY[1]].getBackground();
            for (int j = 0; j < colors.length; j++) {
                if (gridColor == colors[j]) {
                    checkLoss = true;
                }
            }

            if (checkLoss) {
                this.dispose();
                gameOverFrame();
            }

        }

        //Appears when game is lost
        public void gameOverFrame () {
            JOptionPane.showMessageDialog(null, "You lost!");
            System.exit(0);

        }

        //Locate complete sets of 3 or more
        public boolean findSets (){

            boolean hasSet = false;
            boolean[][] set = new boolean[grid.length][grid[0].length];

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {

                    boolean gridIsColor1 = true;

                    for (int j = 0; j < colors.length; j++) {
                        if (grid[r][c].getBackground() != colors[j]) {
                            gridIsColor1 = false;
                        } else {
                            gridIsColor1 = true;
                            j = colors.length;
                        }
                    }


                    if ((gridIsColor1 == true) && c + 2 < grid[r].length && grid[r][c].getBackground() == grid[r][c + 1].getBackground() && grid[r][c].getBackground() == grid[r][c+2].getBackground()) {
                        set[r][c] = set[r][c + 1] = set[r][c + 2] = true;
                    }
                    if ((gridIsColor1 == true) && r + 2 < grid.length && grid[r][c].getBackground() == grid[r+1][c].getBackground() && grid[r][c].getBackground() == grid[r+2][c].getBackground()) {
                        set[r][c] = set[r + 1][c] = set[r + 2][c] = true;
                    }
                }
            }

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    if (set[r][c]) {

                        grid[r][c].setBackground(null);
                        hasSet = true;
                        score++;
                        gameFrame.setTitle("Score: " + score);

                    }
                }
            }
            return hasSet;
        }

        public void removeSets (){
            boolean gridIsColor = true;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {

                    for (int j = 0; j < colors.length; j++) {
                        if (grid[r][c].getBackground() != colors[j]) {
                            gridIsColor = false;
                        } else {
                            gridIsColor = true;
                            j = colors.length;
                        }
                    }

                    if (gridIsColor == false) {
                        for (int i = r; i > 0; i--) {
                            grid[i][c].setBackground(grid[i - 1][c].getBackground());
                        }
                        grid[0][c].setBackground(null);
                    }
                }
            }
        }

        public void updateColorFrame(){

            Random random = new Random();
            int randomColor = random.nextInt(colors.length);
            currentColor = colors[randomColor];
            currentColorFrame = new CurrentColorFrame(types, currentColor);
        }

    }

