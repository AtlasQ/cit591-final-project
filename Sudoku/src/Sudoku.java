import javax.swing.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.awt.*;

public class Sudoku extends JFrame implements KeyListener {

    
    private static final long serialVersionUID = 3517500680629946998L;
    private ExtendedJToggleButton[][] buttonTable = new ExtendedJToggleButton[9][9];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel buttonPanel = new JPanel();
    private NumberTable numberTable;
    private int rowIDGlobal = -1;
    private int colIDGlobal = -1;
    private int boxIDGlobal = -1;
    private String currentDifficulty = "Easy";
    private HashMap<String, Integer> hintChance = new HashMap<String, Integer>();
    private int hintChanceLeft;
    private boolean checkComplience;
    private boolean checkMistake;

    public Sudoku(NumberTable numberTable) {
        super("Sudoku - Team 99");
        this.numberTable = numberTable;
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
        JCheckBoxMenuItem intermediate = new JCheckBoxMenuItem("Intermediate");
        JCheckBoxMenuItem hard = new JCheckBoxMenuItem("Hard");
        newGame.add(easy);
        newGame.add(intermediate);
        newGame.add(hard);
        
     
       
            ActionListener aListener = new ActionListener() {
            public void actionPerformed(ActionEvent He) {
                // TODO Auto-generated method stub
                JCheckBoxMenuItem option =   (JCheckBoxMenuItem) He.getSource();
               
                if (option.isSelected()) {
                   
                    currentDifficulty = option.getText();
                    System.out.println(option.getText());

                    hintChanceLeft = hintChance.get(currentDifficulty);
                    System.out.println(hintChanceLeft);
                    

                }

            }}
        ;
        hard.addActionListener(aListener);
        easy.addActionListener(aListener);
        intermediate.addActionListener(aListener);

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
        checkComplience = checkForComplience.isSelected();
        checkForComplience.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                checkComplience = checkForComplience.isSelected();
                cellFormatting();
            }
        });
        JCheckBoxMenuItem checkForMistake = new JCheckBoxMenuItem("Check for Mistake");
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
        JButton cleanAll = new JButton("Clean All");
        cleanAll.addKeyListener(this);
        cleanAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cleanAllFunction();
            }
        });
        JButton hint = new JButton("Hint");
        hint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                hintFunction();
                if (hintChanceLeft <= 0) {
                    hint.setEnabled(false);
                } else {
                    hint.setEnabled(true);
                }
            }
        });
        JButton erase = new JButton("Erase");
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

    private void cellFormatting() {
        Number number = this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal];
        for (int rowID = 0; rowID < 9; rowID++) {
            for (int colID = 0; colID < 9; colID++) {
                Number referenceNumber = this.numberTable.getPuzzle()[rowID][colID];
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
                if (checkComplience) {
                    if ((referenceNumber.getRowComplience() != 0) || (referenceNumber.getColComplience() != 0) || (referenceNumber.getBoxComplience() != 0)) {
                        button.setBackground(Color.decode("#f7cfd6"));
                        if (!referenceNumber.getOrig()) {
                            button.setForeground(Color.decode("#fb3d40"));
                        }
                    }
                }

                if (checkMistake) {
                    if (referenceNumber.getIfCorrect() != 0) {
                        button.setBackground(Color.decode("#f7cfd6"));
                        if (!referenceNumber.getOrig()) {
                            button.setForeground(Color.decode("#fb3d40"));
                        }
                    }
                }


            }
        }
    }
    
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

    private void hintFunction() {
        Number[][] puzzel = this.numberTable.getPuzzle();
        if ((-1 != this.rowIDGlobal) && (-1 != this.colIDGlobal)) {
            if ((!puzzel[this.rowIDGlobal][this.colIDGlobal].getOrig()) && (this.hintChanceLeft > 0)) {
                this.hintChanceLeft --;
                undoErrorMarkers();
                Number[][] answer = this.numberTable.getAnswer();
                ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
                int numberValue = answer[this.rowIDGlobal][this.colIDGlobal].getValue();
                Number number = new Number(numberValue, true, this.rowIDGlobal, this.colIDGlobal,this.boxIDGlobal);
                numberTable.setPuzzle(number);
                button.setText(numberValue+"");
                cellFormatting();
            }
        }
    }

    private void eraseFunction() {
        if ((-1 != this.rowIDGlobal) && (-1 != this.colIDGlobal)) {
            if (!this.numberTable.getPuzzle()[this.rowIDGlobal][this.colIDGlobal].getOrig()) {
                ExtendedJToggleButton button = buttonTable[this.rowIDGlobal][this.colIDGlobal];
                // Change button text
                button.setText("");
                button.setForeground(Color.BLACK);
                Number number = new Number(0, false, this.rowIDGlobal, this.colIDGlobal, this.boxIDGlobal);
                undoErrorMarkers();
                numberTable.setPuzzle(number);
                // Change color
                cellFormatting();
            }
        }
    }


    private void drawButtons(ButtonGroup buttonGroup, JPanel buttonPanel) {
        // Get puzzel from numberTable
        Number[][] puzzel = this.numberTable.getPuzzle();
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




    public static void main(String args[]) {
        // Launch
        NewGameCreator ngc = new NewGameCreator();
        ngc.setPuzzleS();
        ngc.setAnswerS();
        String puzzleS = ngc.getPuzzleS();
        String answerS = ngc.getAnswerS();
        
        NumberTable numberTable = new NumberTable(puzzleS, answerS);
        Sudoku a = new Sudoku(numberTable);
    }


}