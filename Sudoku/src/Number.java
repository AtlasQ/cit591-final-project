
public class Number {

	private int value;
	private boolean orig;
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
	public Number(int value, boolean orig) {
		this.value = value;
		this.orig = orig;
		this.rowComplience = true;
		this.colComplience = true;
		this.boxComplience = true;
	}
	
	/*
	 * Getter to get number value
	 */
	public int getValue() {
		return value;
	}
	
	/*
	 * Getter to get orig
	 */
	public boolean getOrig() {
		return orig;
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
		Number nm = new Number(9, true);
		System.out.println(nm.getValue());
		System.out.println(nm.getOrig());
		System.out.println(nm.getRowComplience());
		System.out.println(nm.getColComplience());
		System.out.println(nm.getBoxComplience());
	}
}

