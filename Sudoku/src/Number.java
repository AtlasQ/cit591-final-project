
public class Number {

	private int value;
	private boolean orig;
	private int colID;
	private int rowID;
	private int boxID;
	private boolean rowComplience;
	private boolean colComplience;
	private boolean boxComplience;
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
		this.colID = colID;
		this.rowID = rowID;
		this.boxID = boxID;
		this.rowComplience = true;
		this.colComplience = true;
		this.boxComplience = true;
	}
	
	/*
	 * Getter to get number value
	 */
	public int getValue() {
		return this.value;
	}
	
	/*
	 * Getter to get orig
	 */
	public boolean getOrig() {
		return this.orig;
	}
	

	/*
	 * Getter to get number value
	 */
	public int getColID() {
		return this.colID;
	}

	/*
	 * Getter to get orig
	 */
	public int getRowID() {
		return this.rowID;
	}

	/*
	 * Getter to get orig
	 */
	public int getBoxID() {
		return this.boxID;
	}


	/*
	 * Getter to get rowComplience
	 */
	public boolean getRowComplience() {
		return rowComplience;
	}

	/*
	 * Getter to get colComplience
	 */
	public boolean getColComplience() {
		return colComplience;
	}

	/*
	 * Getter to get boxComplience
	 */
	public boolean getBoxComplience() {
		return boxComplience;
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

