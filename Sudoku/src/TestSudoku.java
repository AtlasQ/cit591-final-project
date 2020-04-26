import javax.swing.*;
import java.awt.*;

public class TestSudoku {

    public TestSudoku() {
        // Creat the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        // Creat the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu difficulty = new JMenu("Difficulty");
        JMenu check = new JMenu("Check for Mistakes");
        mb.add(difficulty);
        mb.add(check);

        JCheckBoxMenuItem difficultyEasy = new JCheckBoxMenuItem("Easy");
        JCheckBoxMenuItem difficultyIntermediate = new JCheckBoxMenuItem("Intermediate");
        JCheckBoxMenuItem difficultyHard = new JCheckBoxMenuItem("Hard");
        difficulty.add(difficultyEasy);
        difficulty.add(difficultyIntermediate);
        difficulty.add(difficultyHard);
        // Create checkBoxGroup for difficulty menu
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(difficultyEasy);
        checkBoxGroup.add(difficultyIntermediate);
        checkBoxGroup.add(difficultyHard);

        // Create buttonGroup for Sudoku grid
        ButtonGroup buttonGroup = new ButtonGroup();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout (9,9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                JToggleButton b = new JToggleButton(Integer.toString(i + 1) + "," + Integer.toString(j + 1));
                int borderT = 1;
                int borderL = 1;
                int borderB = 1;
                int borderR = 1;
                if (i % 3 == 0) {
                    borderT = 2;
                } 
                if ((i + 1) % 3 == 0) {
                    borderB = 2;
                }

                if (j % 3 == 0) {
                    borderL = 2;
                }
                if ((j + 1) % 3 == 0) {
                    borderR = 2;
                }
                b.setBorder(BorderFactory.createMatteBorder(borderT, borderL, borderB, borderR, Color.DARK_GRAY));
                b.setBackground(Color.WHITE);
                buttonGroup.add(b);
                buttonPanel.add(b);
            }
            System.out.println(-1%3);
        }

        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton newGame = new JButton("New Game");
        JButton reset = new JButton("Reset");
        JButton hint = new JButton("Hint");
        JButton erase = new JButton("Erase");
        panel.add(newGame); // Components Added using Flow Layout
        panel.add(reset);
        panel.add(hint);
        panel.add(erase);

        // Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        frame.setVisible(true);
    }


    public static void main(String args[]) {
        TestSudoku s = new TestSudoku();
    }
}