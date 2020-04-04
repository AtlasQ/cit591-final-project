
public class Number {

	private int value;
	private boolean init;
	
	public Number(int value, boolean init) {
		this.value = value;
		this.init = init;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean getInit() {
		return init;
	}
	
	public static void main(String[] args) {
		Number nm = new Number(9, true);
		System.out.println(nm.getValue());
		System.out.println(nm.getInit());
	}
}

