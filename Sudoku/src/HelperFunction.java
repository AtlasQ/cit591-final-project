
public class HelperFunction {
    /**
     * Helper function to get boxID based on rowID and colID of the cell on Sudoku NubmerTable
     * @param rowID int rowID
     * @param colID int colID
     * @return int boxID
     */
    public static int calculateBoxID(int rowID, int colID) {
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
        int boxID = (rowID / 3) + (colID / 3) + rowCompensation;
        return boxID;
    }
}