import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TestSudoku extends JFrame implements KeyListener {

    
    private static final long serialVersionUID = 3517500680629946998L;
    private ExtendedJToggleButton[][] buttonTable = new ExtendedJToggleButton[9][9];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel buttonPanel = new JPanel();
    private NumberTable numberTable;
    private int rowIDGlobal;
    private int colIDGlobal;
    private int boxIDGlobal;
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Locate the selected cell (toggled toggle button)
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                ExtendedJToggleButton button = buttonTable[rowID][colID];
                if (button.isSelected()) {
                    // Change button text
                    button.setText(String.valueOf(e.getKeyChar()));

                }
            }
        }
        System.out.println(this.rowIDGlobal);
        System.out.println(this.colIDGlobal);
        System.out.println(this.boxIDGlobal);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // pass
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // pass
    }

    private void changeColor() {
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                int rowCompensation = 0;
                switch (rowID / 3) {
                    case 0:
                        rowCompensation = 0;
                        break;
                    case 1:
                        rowCompensation = 2;
                        break;
                    case 2:
                        rowCompensation = 4;
                        break;
                }
                // Get box id
                int boxID= (rowID / 3) + (colID / 3) + rowCompensation;
                ExtendedJToggleButton button = buttonTable[rowID][colID];
                if ((rowID == this.rowIDGlobal) || (colID == this.colIDGlobal) || (boxID == this.boxIDGlobal)){
                    button.setBackground(Color.decode("#e2e7ed"));
                } else {
                    button.setBackground(Color.WHITE);
                }

            }
        }
    }
    
    private void drawButtons(ButtonGroup buttonGroup, JPanel buttonPanel, String text) {
        // 
        // Draw buttons
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                ExtendedJToggleButton button = new ExtendedJToggleButton();
                int borderT = 1;
                int borderL = 1;
                int borderB = 1;
                int borderR = 1;
                if (rowID % 3 == 0) {
                    borderT = 2;
                }
                if ((rowID + 1) % 3 == 0) {
                    borderB = 2;
                }

                if (colID % 3 == 0) {
                    borderL = 2;
                }
                if ((colID + 1) % 3 == 0) {
                    borderR = 2;
                }

                
                button.setText(Integer.toString(rowID) + Integer.toString(colID) + text);
                button.setBorder(BorderFactory.createMatteBorder(borderT, borderL, borderB, borderR, Color.DARK_GRAY));
                button.setBackground(Color.WHITE);
                button.addKeyListener(this);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        rowIDGlobal = button.getRowID();
                        colIDGlobal = button.getColID();
                        boxIDGlobal = button.getBoxID();
                        changeColor();
                    }
                });
                button.setRowID(rowID);
                button.setColID(colID);

                int rowCompensation = 0;
                switch (rowID / 3) {
                    case 0:
                        rowCompensation = 0;
                        break;
                    case 1:
                        rowCompensation = 2;
                        break;
                    case 2:
                        rowCompensation = 4;
                        break;
                }
                // Get box id
                this.boxIDGlobal= (rowID / 3) + (colID / 3) + rowCompensation;
                button.setBoxID(this.boxIDGlobal);
                buttonTable[rowID][colID] = button;
                buttonGroup.add(button);
                buttonPanel.add(button);
            }
        }
    }

    public TestSudoku(NumberTable numberTable) {
        super("Sudoku - Team 99");

        this.numberTable = numberTable;
        // Creat the Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 900);
        // try {
        //     // Set System L&F
        //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (UnsupportedLookAndFeelException e) {
        //     // handle exception
        // } catch (ClassNotFoundException e) {
        //     // handle exception
        // } catch (InstantiationException e) {
        //     // handle exception
        // } catch (IllegalAccessException e) {
        //     // handle exception
        // }
        UIManager.put("ToggleButton.select", Color.decode("#bbdefb"));
        // Creat the MenuBar and adding components
        JMenuBar menu = new JMenuBar();
        JMenu difficulty = new JMenu("Difficulty");
        JMenu check = new JMenu("Check for Mistakes");
        menu.add(difficulty);
        menu.add(check);

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
        buttonPanel.setLayout(new GridLayout (9,9));
        drawButtons(buttonGroup, buttonPanel, "default");

        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); 
        JButton newGame = new JButton("New Game");
        JButton restart = new JButton("Restart");
        JButton hint = new JButton("Hint");
        JButton erase = new JButton("Erase");
        panel.add(newGame); // Components Added using Flow Layout
        panel.add(restart);
        panel.add(hint);
        panel.add(erase);

        // Adding Components to the frame.
        this.getContentPane().add(BorderLayout.SOUTH, panel);
        this.getContentPane().add(BorderLayout.NORTH, menu);
        this.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        this.setVisible(true);
    }


    public static void main(String args[]) {
        // Launch
        NewGameCreator ngc = new NewGameCreator();
        ngc.setPuzzleS();
        ngc.setAnswerS();
        String puzzleS = ngc.getPuzzleS();
        String answerS = ngc.getAnswerS();
        
        NumberTable numberTable = new NumberTable(puzzleS, answerS);
        TestSudoku a = new TestSudoku(numberTable);
    }


}