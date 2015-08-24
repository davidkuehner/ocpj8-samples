package ocp._01_java_class_design.inheritance;

public class InhCoffee extends InhDrink{

	public void messingAround() {
		isLiquid = true; // Ok
		isHot = true; // Ok
		isImportantToLife = true; // Ok if in same package
		// isStartingWithD = false; // Not ok
	}

	public int quantity() {
		return 1;
	}; // Ok, equal or brighter visibility.
		// private int quantity() {return 1;};// Not ok, cannot reduce
		// the
		// visibility.
}