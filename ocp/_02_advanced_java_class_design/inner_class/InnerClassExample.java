package ocp._02_advanced_java_class_design.inner_class;

import ocp._02_advanced_java_class_design.inner_class.InnerClassExample.NestedClass;

public class InnerClassExample {

	public static void run() {
		// Local class
		class LocalClass {}
		
		// Anonymous intern class
		Drink cappuccino = new Drink() {
			@Override
			public void printName() {
				System.out.println("Cappuccino");
			}
		};
	}
	
	// Static inner class
	private static class InnerClass {}
	
	// Nested class
	public class NestedClass {}

}

interface Drink {
	public void printName();
}
