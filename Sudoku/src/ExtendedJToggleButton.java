import javax.swing.JToggleButton;

public class ExtendedJToggleButton extends JToggleButton  {

    /**
     *
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
    
    public static void main(String[] args) {
        ExtendedJToggleButton etb = new ExtendedJToggleButton();
        etb.setRowID(1);
        System.out.println(etb.getRowID());
    }
}