import javax.swing.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.awt.*;

public class Sudoku extends JFrame implements KeyListener {
    /**
     * Sudoku class which stores the game logic and game GUI for the game Sudoku
     */

    // the following are a series of getters for testing purpose
    public JCheckBoxMenuItem getCheckForMistake() {
        return checkForMistake;
    }

    public JCheckBoxMenuItem getCheckForComplience() {
        return checkForComplience;
    }

    public boolean isCheckComplience() {
        return checkComplience;
    }

    public boolean isCheckMistake() {
        return checkMistake;
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public HashMap<String, Integer> getHintChance() {
        return hintChance;
    }

    public int getHintChanceLeft() {
        return hintChanceLeft;
    }

    public void setCurrentDifficulty(String currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }


    private static final long serialVersionUID = 3517500680629946998L;
    private ExtendedJToggleButton[][] buttonTable = new ExtendedJToggleButton[9][9];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel buttonPanel = new JPanel();
    private NumberTable numberTable;
    private int rowIDGlobal = -1; // this helps track the cell that user clicks
    private int colIDGlobal = -1; // this helps track the cell that user clicks
    private int boxIDGlobal = -1; // this helps track the cell that user clicks
    private String currentDifficulty = "Easy";
    private HashMap<String, Integer> hintChance = new HashMap<String, Integer>();
    private int hintChanceLeft; // this helps track the hints left
    private boolean checkComplience;
    private boolean checkMistake;
    private JCheckBoxMenuItem checkForMistake;
    private JCheckBoxMenuItem checkForComplience;
    private JButton cleanAll;
    private JButton hint;
    private JButton erase;

    /**
     * Sudoku class constructor, which holds the major game logic of the Sudoku game
     */
    public Sudoku() {
        super("Sudoku - Team 99"); //window title
        this.hintChance.put("Easy", 10);
        this.hintChance.put("Intermediate", 5);
        this.hintChance.put("Hard", 2);
        this.hintChanceLeft = hintChance.get(currentDifficulty);
        // Creat the Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        // try {
        // // Set System L&F
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (UnsupportedLookAndFeelException e) {
        // // handle exception
        // } catch (ClassNotFoundException e) {
        // // handle exception
        // } catch (InstantiationException e) {
        // // handle exception
        // } catch (IllegalAccessException e) {
        // // handle exception
        // }
        UIManager.put("ToggleButton.select", Color.decode("#bbdefb"));
        // Creat the MenuBar and adding components
        JMenuBar menu = new JMenuBar();
        // Create new game menu
        JMenu newGame = new JMenu("New Game");
        menu.add(newGame);
        JCheckBoxMenuItem easy = new JCheckBoxMenuItem("Easy");
        easy.setSelected(true);
        this.currentDifficulty = "Easy";
        newGameFunction();
        JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        JCheckBoxMenuItem hard = new JCheckBoxMenuItem("Hard");
        newGame.add(easy);
        newGame.add(intermediate);
        newGame.add(hard);
        /**
         * Action listner for newGame menu
         */
        ActionListener aListener = new ActionListener() {
            public void actionPerformed(ActionEvent He) {
                JCheckBoxMenuItem option = (JCheckBoxMenuItem) He.getSource();

                if (option.isSelected()) {

                    currentDifficulty = option.getText();

                    hintChanceLeft = hintChance.get(currentDifficulty);

                    newGameFunction();
                    cleanAllFunction();

                }

            }
        };
        hard.addActionListener(aListener); // adding action listeners
        easy.addActionListener(aListener); // adding action listeners
        intermediate.addActionListener(aListener); // adding action listeners

        // Create checkBoxGroup for new game menu
        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(easy);
        checkBoxGroup.add(intermediate);
        checkBoxGroup.add(hard);

        // Create check options
        JMenu checks = new JMenu("Check Options");
        menu.add(checks);
        checkForComplience = new JCheckBoxMenuItem("Check for Complience");
        checkForComplience.setSelected(true);
        checkComplience = checkForComplience.isSelected();
        checkForComplience.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                checkComplience = checkForComplience.isSelected();
                cellFormatting();
            }
        });
        checkForMistake = new JCheckBoxMenuItem("Check for Mistake");
        checkForMistake.setSelected(true);
        checkMistake = checkForMistake.isSelected();
        checkForMistake.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                checkMistake = checkForMistake.isSelected();
                cellFormatting();
            }
        });
        checks.add(checkForComplience);
        checks.add(checkForMistake);

        // Create buttonGroup for Sudoku grid
        buttonPanel.setLayout(new GridLayout(9, 9));
        drawButtons(buttonGroup, buttonPanel);

        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        this.cleanAll = new JButton("Clean All");
        cleanAll.addKeyListener(this);
        cleanAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cleanAllFunction();
            }
        });
        this.hint = new JButton("Hint (" + hintChanceLeft + " left)");
        hint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                hintFunction();
                if (hintChanceLeft <= 0) {
                    hint.setEnabled(false);
                } else {
                    hint.setEnabled(true);
                }
                hint.setText("Hint (" + hintChanceLeft + " left)");
            }
        });
        this.erase = new JButton("Erase");
        erase.addKeyListener(this);
        erase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                eraseFunction();
            }
        });
        panel.add(cleanAll);
        panel.add(hint);
        panel.add(erase);

        // Adding Components to the frame.
        this.getContentPane().add(BorderLayout.SOUTH, panel);
        this.getContentPane().add(BorderLayout.NORTH, menu);
        this.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        this.setVisible(true);
        this.setResizable(false);
    }
    /**
     * Private helper function isInteger checks if a String object is made of pure Integer
     * @param s String
     * @return boolean
     */
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

    /**
     * Private method undoErrorMarkers will undo all the formatting caused by invalid number input when compared with Sudoku answer
     */
    private void undoErrorMarkers() {
        Number toUndoErrorMarker = numberTable.getPuzzle()[rowIDGlobal][colIDGlobal];
        numberTable.undoIfMistake(toUndoErrorMarker);
        numberTable.undoIfValid(toUndoErrorMarker);
    }

    /**
     * The following public methods are implementations of interface KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Locate the selected cell (toggled toggle button)
        String text = String.valueOf(e.getKeyChar());
        int keyCode = e.getKeyChar();
        ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
        if (button.isSelected() && !this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal].getOrig()) {
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
                    cellFormatting();
                }
            } else if ((keyCode == 8) || (keyCode == 127)) {
                eraseFunction();
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

    /**
     * Private cellFormatting method helps format the cells in NumberTable of the Sudoku game based on the validity of user input nubmers
     */
    private void cellFormatting() {
        Number number = this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal];
        int correctCells = 0;
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                Number referenceNumber = this.numberTable.getPuzzle()[rowID][colID];
                Number answeNumber = this.numberTable.getAnswer()[rowID][colID];
                int boxID = HelperFunction.calculateBoxID(rowID, colID);
                ExtendedJToggleButton button = this.buttonTable[rowID][colID];
                button.setBackground(Color.WHITE);
                // Set special font color for user input
                if (!referenceNumber.getOrig()) {
                    button.setForeground(Color.decode("#4a90e2"));
                } else {
                    button.setForeground(Color.BLACK);
                }
                // Set row, column and box color
                if ((rowID == this.rowIDGlobal) || (colID == this.colIDGlobal) || (boxID == this.boxIDGlobal)) {
                    button.setBackground(Color.decode("#e2e7ed"));
                }
                // Set color for cells with the same values as that of selected cell
                if (isInteger(button.getText())) {
                    if ((number.getValue() == Integer.parseInt(button.getText()))) {
                        button.setBackground(Color.decode("#cbdbed"));
                    }
                }
                // Set color for cells with error
                // Checks for complience (duplicated numbers in rows, cols, and/or boxes)
                if (checkComplience) {
                    if ((referenceNumber.getRowComplience() != 0) || (referenceNumber.getColComplience() != 0)
                            || (referenceNumber.getBoxComplience() != 0)) {
                        button.setBackground(Color.decode("#f7cfd6"));
                        if (!referenceNumber.getOrig()) {
                            button.setForeground(Color.decode("#fb3d40"));
                        }
                    }
                }
                // Checks for mistakes
                if (checkMistake) {
                    if (referenceNumber.getIfCorrect() != 0) {
                        button.setBackground(Color.decode("#f7cfd6"));
                        if (!referenceNumber.getOrig()) {
                            button.setForeground(Color.decode("#fb3d40"));
                        }
                    }
                }
                if (referenceNumber.equals(answeNumber)) {
                    correctCells++;
                }

            }
        }

        if (correctCells == 81) {
            congratulationFormatting();
            cleanAllFunction();
        }
    }
    /**
     * cleanAllFunction that cleans all hints and user inputs from the Sudoku NumberTable
     */
    private void cleanAllFunction() {
        this.numberTable.copyOriginalPuzzle();
        Number[][] puzzel = this.numberTable.getPuzzle();
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                String text = "";
                int puzzelNumber = puzzel[rowID][colID].getValue();
                if (puzzelNumber != 0) {
                    text = Integer.toString(puzzelNumber);
                }
                ExtendedJToggleButton referenceButton = this.buttonTable[rowID][colID];
                referenceButton.setText(text);
                referenceButton.setBackground(Color.WHITE);
            }
        }
        if ((-1 != this.rowIDGlobal) && (-1 != this.colIDGlobal)) {
            cellFormatting();
        }
    }

    /**
     * hintFunction helps generate hints and put the value into user designated cell of the Sudoku NumberTable
     */
    private void hintFunction() {
        Number[][] puzzel = this.numberTable.getPuzzle();
        // if not inital state
        if ((-1 != this.rowIDGlobal) && (-1 != this.colIDGlobal)) {
            if ((!puzzel[this.rowIDGlobal][this.colIDGlobal].getOrig()) && (this.hintChanceLeft > 0)) {
                this.hintChanceLeft--;
                // undo error markers
                undoErrorMarkers();
                Number[][] answer = this.numberTable.getAnswer();
                ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
                int numberValue = answer[this.rowIDGlobal][this.colIDGlobal].getValue();
                Number number = new Number(numberValue, true, this.rowIDGlobal, this.colIDGlobal, this.boxIDGlobal);
                numberTable.setPuzzle(number);
                button.setText(numberValue + "");
                cellFormatting();
            }
        }
    }
    /**
     * eraseFunction helps erase the number of a specificed cell designated by the user
     */
    private void eraseFunction() {
        if ((-1 != this.rowIDGlobal) && (-1 != this.colIDGlobal)) {
            if (!this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal].getOrig()) {
                ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
                // Change button text
                button.setText("");
                button.setForeground(Color.BLACK);
                Number number = new Number(0, false, this.rowIDGlobal, this.colIDGlobal, this.boxIDGlobal);
                // undo error markers
                undoErrorMarkers();
                numberTable.setPuzzle(number);
                // Change color
                cellFormatting();
            }
        }
    }

    /**
     * newGameFunction helps create new game and format the NumberTalbe
     */
    private void newGameFunction() {
        if (this.hint != null) {
            this.hint.setEnabled(true);
            this.hintChanceLeft = hintChance.get(currentDifficulty);
            this.hint.setText("Hint (" + hintChanceLeft + " left)");
        }
        NewGameCreator ngc = new NewGameCreator();
        this.hintChanceLeft = hintChance.get(currentDifficulty);
        ngc.setDifficulty(this.currentDifficulty);
        ngc.setRandomGameSeed();
        ngc.setPuzzleS();
        ngc.setAnswerS();
        
        numberTable = new NumberTable(ngc.getPuzzleS(), ngc.getAnswerS());

    }
    
    /**
     * drawButtons function helps draw the buttons on the GUI
     * 
     * @param buttonGroup ButtonGroup
     * @param buttonPanel JPanel
     */
    private void drawButtons(ButtonGroup buttonGroup, JPanel buttonPanel) {
        // Get puzzel from numberTable
        Number[][] puzzel = this.numberTable.getPuzzle();
        // Draw buttons
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                // create button object using ExtendedJToggleButton
                ExtendedJToggleButton button = new ExtendedJToggleButton();
                // define boarder thickness
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
                        cellFormatting();
                    }
                });
                button.setRowID(rowID);
                button.setColID(colID);
                button.setBoxID(HelperFunction.calculateBoxID(rowID, colID));
                this.buttonTable[rowID][colID] = button;
                buttonGroup.add(button);
                buttonPanel.add(button);
            }
        }
    }
    // Congratulation pop-up box, this will also call newGameFunction to generate another new game with the current difficulty setting
    private void congratulationFormatting() {
        JOptionPane.showMessageDialog(null, "Congratulations! You have solved the Sudoku puzzle.");
        newGameFunction();
    }

    public static void main(String args[]) {
        // Launch
        Sudoku a = new Sudoku();
    }

}