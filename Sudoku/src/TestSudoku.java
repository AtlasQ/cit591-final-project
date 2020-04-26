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
    
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    private void undoErrorMarkers() {
        Number toUndoErrorMarker = numberTable.getPuzzle()[rowIDGlobal][colIDGlobal];
        numberTable.undoIfMistake(toUndoErrorMarker);
        numberTable.undoIfValid(toUndoErrorMarker);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Locate the selected cell (toggled toggle button)
        String text = String.valueOf(e.getKeyChar());
        int keyCode = e.getKeyChar();
        ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
        if (button.isSelected() && ! this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal].getOrig()) {
            if (isInteger(text)) {
                if (Integer.parseInt(text) != 0) {
                    // Change button text
                    button.setText(text);
                    
                    Number number = new Number(Integer.parseInt(text), false, this.rowIDGlobal, this.colIDGlobal,
                            this.boxIDGlobal);
                    if (numberTable.getPuzzle()[rowIDGlobal][colIDGlobal].getValue() != 0) {
                        undoErrorMarkers();
                    }
                    numberTable.setPuzzle(number);
                    numberTable.ifMistake(number);
                    numberTable.ifValid(number);
                    // Change color
                    selectChangeColor();
                }
            } else if ((keyCode == 8) || (keyCode == 127)) {
                // Change button text
                button.setText("");
                button.setForeground(Color.BLACK);
                Number number = new Number(0, false, this.rowIDGlobal, this.colIDGlobal, this.boxIDGlobal);
                undoErrorMarkers();
                numberTable.setPuzzle(number);
                // Change color
                selectChangeColor();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // pass
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // pass
    }

    private void selectChangeColor() {
        Number number = numberTable.getPuzzle()[rowIDGlobal][colIDGlobal];
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                Number referenceNumber = numberTable.getPuzzle()[rowID][colID];
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
                button.setBackground(Color.WHITE);
                if (!referenceNumber.getOrig()) {
                    button.setForeground(Color.decode("#4a90e2"));
                }
                // Set row, column and box color
                if ((rowID == this.rowIDGlobal) || (colID == this.colIDGlobal) || (boxID == this.boxIDGlobal)){
                    button.setBackground(Color.decode("#e2e7ed"));
                }
                // Set color for cells with the same values as that of selected cell
                if (isInteger(button.getText())) {
                    if ((number.getValue() == Integer.parseInt(button.getText()))) {
                        button.setBackground(Color.decode("#cbdbed"));
                    }
                }
                // Set color for cells with error
                if ((referenceNumber.getRowComplience() != 0) || (referenceNumber.getColComplience() != 0) || (referenceNumber.getBoxComplience() != 0)  || (referenceNumber.getIfCorrect() != 0)) {
                    button.setBackground(Color.decode("#f7cfd6"));
                    if (!referenceNumber.getOrig()) {
                        button.setForeground(Color.decode("#fb3d40"));
                    }

                }

            }
        }
    }
    
    private void drawButtons(ButtonGroup buttonGroup, JPanel buttonPanel) {
        // Get puzzel from numberTable
        Number[][] puzzel = numberTable.getPuzzle();
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

                String text = "";
                int puzzelNumber = puzzel[rowID][colID].getValue();
                if (puzzelNumber != 0) {
                    text = Integer.toString(puzzelNumber);
                }
                button.setText(text);
                button.setBorder(BorderFactory.createMatteBorder(borderT, borderL, borderB, borderR, Color.DARK_GRAY));
                button.setBackground(Color.WHITE);
                button.addKeyListener(this);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        rowIDGlobal = button.getRowID();
                        colIDGlobal = button.getColID();
                        boxIDGlobal = button.getBoxID();
                        selectChangeColor();
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
        this.setSize(600, 600);
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
        // Create new game menu
        JMenu newGame = new JMenu("New Game");
        menu.add(newGame);
        JCheckBoxMenuItem easy = new JCheckBoxMenuItem("Easy");
        easy.setSelected(true);
        JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        JCheckBoxMenuItem hard = new JCheckBoxMenuItem("Hard");
        newGame.add(easy);
        newGame.add(intermediate);
        newGame.add(hard);

        // Create checkBoxGroup for new game menu
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(easy);
        checkBoxGroup.add(intermediate);
        checkBoxGroup.add(hard);

        // Create check options
        JMenu checks = new JMenu("Check Options");
        menu.add(checks);
        JCheckBoxMenuItem checkForComplience = new JCheckBoxMenuItem("Check for Complience");
        checkForComplience.setSelected(true);
        JCheckBoxMenuItem checkForMistake = new JCheckBoxMenuItem("Check for Mistake");
        checkForMistake.setSelected(true);
        checks.add(checkForComplience);
        checks.add(checkForMistake);


        // Create buttonGroup for Sudoku grid
        buttonPanel.setLayout(new GridLayout (9,9));
        drawButtons(buttonGroup, buttonPanel);

        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); 
        JButton restart = new JButton("Restart");
        JButton hint = new JButton("Hint");
        JButton erase = new JButton("Erase");
        panel.add(restart);
        panel.add(hint);
        panel.add(erase);

        // Adding Components to the frame.
        this.getContentPane().add(BorderLayout.SOUTH, panel);
        this.getContentPane().add(BorderLayout.NORTH, menu);
        this.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        this.setVisible(true);
        this.setResizable(false);
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