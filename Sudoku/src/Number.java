
public class Number {
	/**
	 * Number class helps Sudoku game keep tracking of each number in each cell of Sudoku NumberTable
	 */
	private int value;
	private boolean orig;
	private int rowID;
	private int colID;
	private int boxID;
	private int rowComplience;
	private int colComplience;
	private int boxComplience;
	private int ifCorrect;

	/**
	 * Number class constructor
	 * @param value int, the number value
	 * @param orig boolean, indicates if the number is original or user input
	 * @param rowID	int rowID
	 * @param colID int colID
	 * @param boxID int boxID
	 */
	public Number(int value, boolean orig, int rowID, int colID, int boxID) {
		this.value = value;
		this.orig = orig;
		this.rowID = rowID;
		this.colID = colID;
		this.boxID = boxID;
		this.rowComplience = 0;
		this.colComplience = 0;
		this.boxComplience = 0;
		this.ifCorrect = 0;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean getOrig() {
		return this.orig;
	}

	public void setOrig(boolean orig) {
		this.orig = orig;
	}

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

	public int getRowComplience() {
		return this.rowComplience;
	}

	public void setRowComplience(int rowComplience) {
		this.rowComplience = rowComplience;
	}

	public int getColComplience() {
		return this.colComplience;
	}

	public void setColComplience(int colComplience) {
		this.colComplience = colComplience;
	}

	public int getBoxComplience() {
		return this.boxComplience;
	}

	public void setBoxComplience(int boxComplience) {
		this.boxComplience = boxComplience;
	}

	public int getIfCorrect() {
		return this.ifCorrect;
	}

	public void setIfCorrect(int ifCorrect) {
		this.ifCorrect = ifCorrect;
	}

	/**
	 * public method that checks if two numbers are at the same location
	 * @param number Number class object
	 * @return boolean
	 */
	public boolean sameLocation(Number number) {
		if ((this.rowID == number.getRowID()) && (this.colID == number.getColID())
				&& (this.boxID == number.getBoxID())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * public method that checks if two numbers are equal
	 * @param number Number class object
	 * @return boolean
	 */
	public boolean equals(Number number) {
		if ((this.rowID == number.getRowID()) && (this.colID == number.getColID()) && (this.boxID == number.getBoxID())
				&& this.value == number.getValue()) {
			return true;
		} else {
			return false;
		}
	}
}
