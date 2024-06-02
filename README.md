# ConnectThreeBlockGame_JavaQCSpring2024
The third project is a GUI implementation of the first project I have done in my Java CS class. "You may expand upon your own code from project one to complete project three; however, professor's code from project one will also be available for you to use."

The project should do the following:
1. Ask the user to input the number of rows, columns, and types using JOptionPane.
2. Display a set of randomly chosen colors equal to the number of types and ask the user whether
those colors are okay.
3. Repeat step 2 until the user accepts the colors.
4. For the game, display two windows. One should contain a grid of buttons with the given number
of rows and columns. The other should simply display the color of the next tile (chosen
randomly from the set of colors accepted by the user).
5. Whenever the user clicks on a button in the grid, add a tile of the displayed color to the lowest
open space in the column of the button clicked.
6. Display a message when a set of three or more in a row is made and remove the set after the
user clicks OK.
7. Handle chain reactions.
8. Display an endgame message when the user adds a tile to a full column.
You do not have to validate user input. That means you may assume the user gives proper input to all
prompts (for example, you may assume the numbers at the beginning of the game are all positive
integers less than 25). All user input and messages to the user must be through JOptionPane. Your
project should not use the console.
Hint: To reset a button to its original color, set its background to null.
You will not be graded on efficiency. However, you will be graded on coding style, including good
variable and method names, proper indentation, sufficient comments, and following the DRY (don’t
repeat yourself) principle.
This is an individual project. Working in a group will be considered academic dishonesty.
Submit your code (the .java file(s)) on Gradescope. Your code for this project may consist of one or more
classes. Late submissions will lose one point per day late.
