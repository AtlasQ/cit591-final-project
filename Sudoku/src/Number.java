
public class Number {

	private int value;
	private boolean init;
	
	/**
	 * Number constructor
	 * @param value
	 * value of the number
	 * 
	 * @param init
	 * boolean variable state whether this number is initial number or user input number
	 */
	public Number(int value, boolean init) {
		this.value = value;
		this.init = init;
	}
	
	/*
	 * Getter to get number value
	 */
	public int getValue() {
		return value;
	}
	
	/*
	 * Getter to get init
	 */
	public boolean getInit() {
		return init;
	}
	
	
	public static void main(String[] args) {
		// test
		Number nm = new Number(9, true);
		System.out.println(nm.getValue());
		System.out.println(nm.getInit());
	}
}

