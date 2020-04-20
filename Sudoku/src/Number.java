
public class Number {

	private int value;
	private boolean orig;
	
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
	
	
	public static void main(String[] args) {
		// test
		Number nm = new Number(9, true);
		System.out.println(nm.getValue());
		System.out.println(nm.getOrig());
	}
}

