package ocp._02_advanced_java_class_design.abstrakt;

public abstract class AbsDrink {
	public void isLiquid() {
		System.out.println("Yes, it's liquid...");
	}
	/**
	 * Must be implemented by the first concrete class.
	 */
	public abstract void isHot();
}
