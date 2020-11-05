import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class UI {
    private JFrame frame;
    private JPanel gridPanel;
    private JLabel[][] tiles = new JLabel[3][3];
    private String turn = "X";
    private JLabel turnText;
    private String winner = null;

    public UI() {
        prepareFrame();
        addWidgets();
    }

    private void prepareFrame() {
        frame = new JFrame("JavaSwingTest"); // Create window
        frame.setSize(600, 600); // Set size in px (x, y)
        frame.setLayout(new BorderLayout()); // Sets layout type, this one has side and upper/lower margins, and a center box, see
                                             // https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html for more

        frame.addWindowListener(new UIWindowListener()); // Listener for the 'x' button, it will stop the program (and
                                                         // close the window)
    }

    private void addWidgets() {
        JLabel title = new JLabel("This is a demo of a tic tac toe game", JLabel.CENTER);
        turnText = new JLabel("Turn: " + turn, JLabel.CENTER);
        gridPanel = new JPanel(new GridLayout(3, 3));

        title.setFont(new Font("sans serif", Font.PLAIN, 18));
        turnText.setFont(new Font("sans serif", Font.PLAIN, 18));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                createTile(i, j);
            }
        }
        Border compoundBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50),
                BorderFactory.createLineBorder(Color.BLACK, 4));
        gridPanel.setBorder(compoundBorder);

        frame.add(title, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(turnText, BorderLayout.SOUTH);
    }

    private void createTile(int x, int y) {
        tiles[x][y] = new JLabel("", JLabel.CENTER);
        tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tiles[x][y].setFont(new Font("sans serif", Font.BOLD, 72));
        tiles[x][y].addMouseListener(new TileClickListener(() -> clickTile(x, y)));

        gridPanel.add(tiles[x][y]);
        
    }

    private void clickTile(int x, int y) {
        if (tiles[x][y].getText().equals("") && winner == null) {
            tiles[x][y].setText(turn);
            turn = turn.equals("X")? "O" : "X";
            turnText.setText("Turn: " + turn);

            checkWinner();
        }
    }

    private void checkWinner() {
        int[][] combos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int[] combo : combos) {
            if (tiles[combo[0] % 3][combo[0] / 3].getText().equals(tiles[combo[1] % 3][combo[1] / 3].getText()) && tiles[combo[0] % 3][combo[0] / 3].getText().equals(tiles[combo[2] % 3][combo[2] / 3].getText()) && !tiles[combo[0] % 3][combo[0] / 3].getText().equals("")) {
                winner = tiles[combo[0] % 3][combo[0] / 3].getText();
                
                turnText.setText(winner + " Wins!!!");
            }
        }
    }

    public void start() {
        frame.setVisible(true); // Shows the window
    }
}