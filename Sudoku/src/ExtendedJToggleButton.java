import javax.swing.JToggleButton;
public class ExtendedJToggleButton extends JToggleButton {
    /**
     * Extend the JToggleButton class with some of the private variables needed by Sudoku game.
     */
    private static final long serialVersionUID = 1L;
    
    private int rowID;
    private int colID;
    private int boxID;

    public int getRowID() {
        return this.rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public int getColID() {
        return this.colID;
    }

    public void setColID(int colID) {
        this.colID = colID;
    }

    public int getBoxID() {
        return this.boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }
}