package ocp._02_advanced_java_class_design;

import javax.swing.plaf.BorderUIResource.BevelBorderUIResource;

import ocp._00_utils.Example;
import ocp._02_advanced_java_class_design.abstrakt.AbsDrink;
import ocp._02_advanced_java_class_design.abstrakt.ConCoffee;
import ocp._02_advanced_java_class_design.enum_example.Coffees;
import ocp._02_advanced_java_class_design.finalExample.FinalExample;
import ocp._02_advanced_java_class_design.inner_class.InnerClassExample;
import ocp._02_advanced_java_class_design.interface_example.InterfaceExample;
import ocp._02_advanced_java_class_design.lambdaExample.TriConsumer;

public class AdvancedJavaClassDesignExample implements Example {

	@Override
	public void run() {
		abstrakt();
		finals();
		innerClass();
		enumExample();
		interfaceExample();
		lambdaExample();
	}
	
	/**
	 * Develop code that uses abstract classes and methods
	 */
	private void abstrakt() {
		AbsDrink coffee = new ConCoffee();
		coffee.isLiquid();
		coffee.isHot();
	}
	
	/**
	 * Develop code that uses final keyword
	 */
	private void finals() {
		FinalExample.run();
	}
	
	/**
	 * Create inner classes including static inner class, local class, nested class, and anonymous inner class.
	 */
	private void innerClass() {
		InnerClassExample.run();
		InnerClassExample ice = new InnerClassExample();
		InnerClassExample.NestedClass nc = ice.new NestedClass();
	}
	
	/**
	 * Use enumerated types including methods, and constructors in an enum type
	 */
	private void enumExample() {
		System.out.println(Coffees.RISTRETTO + " : Caffeine = " + Coffees.RISTRETTO.getCaffeineMg() + " mg");
	}
	
	/**
	 * Develop code that declares, implements and/or extends interfaces and use the atOverride annotation
	 */
	private void interfaceExample() {
		InterfaceExample.run();
	}
	
	/**
	 * Create and use Lambda expressions
	 */
	private void lambdaExample() {
		// Dummy example
		TriConsumer<Integer, Double, Long> triType = (t ,u ,v )-> System.out.println(t.toString() + u.toString() + v.toString());
		triType.accept(1, 1.1, 2L);
	}
}
