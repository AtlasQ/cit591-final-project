
public class Number {

	private int value;
	private boolean orig;
	private int rowID;
	private int colID;
	private int boxID;
	private boolean rowComplience;
	private boolean colComplience;
	private boolean boxComplience;
	private boolean ifCorrect;
	/**
	 * Number constructor
	 * @param value
	 * value of the number
	 * 
	 * @param init
	 * boolean variable state whether this number is original number or user input number
	 */
	public Number(int value, boolean orig, int colID, int rowID, int boxID) {
		this.value = value;
		this.orig = orig;
		this.rowID = rowID;
		this.colID = colID;
		this.boxID = boxID;
		this.rowComplience = true;
		this.colComplience = true;
		this.boxComplience = true;
		this.ifCorrect = true;
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


	public boolean getRowComplience() {
		return this.rowComplience;
	}

	public void setRowComplience(boolean rowComplience) {
		this.rowComplience = rowComplience;
	}


	public boolean getColComplience() {
		return this.colComplience;
	}

	public void setColComplience(boolean colComplience) {
		this.colComplience = colComplience;
	}


	public boolean getBoxComplience() {
		return this.boxComplience;
	}

	public void setBoxComplience(boolean boxComplience) {
		this.boxComplience = boxComplience;
	}


	public boolean getIfCorrect() {
		return this.ifCorrect;
	}

	public void setIfCorrect(boolean ifCorrect) {
		this.ifCorrect = ifCorrect;
	}

	public boolean sameLocation(Number number) {
		if ((this.rowID == number.getRowID()) && (this.colID == number.getColID()) && (this.boxID == number.getBoxID())) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// test
		Number nm = new Number(9, true, 1, 1, 1);
		System.out.println(nm.getValue());
		System.out.println(nm.getOrig());
		System.out.println(nm.getRowComplience());
		System.out.println(nm.getColComplience());
		System.out.println(nm.getBoxComplience());
	}
}

