package ocp._02_advanced_java_class_design.enum_example;

public enum Coffees {
	CAPPUCCINO(425), 
	RISTRETTO(999);
	
	private int caffeineMg;
	
	private Coffees(int caffeineMg) {
		this.caffeineMg = caffeineMg;
	}
	
	public int getCaffeineMg() {
		return caffeineMg;
	}
}
