package ocp._01_java_class_design;

import java.util.Arrays;
import java.util.List;

import ocp._00_utils.Example;
import ocp._01_java_class_design.encapsulation.Chart;
import ocp._01_java_class_design.encapsulation.PointBadVersion;
import ocp._01_java_class_design.encapsulation.PointEncapsupated;
import ocp._01_java_class_design.encapsulation.PointEncapsupated2;
import ocp._01_java_class_design.immutable.Immutable;
import ocp._01_java_class_design.inheritance.InhCoffee;
import ocp._01_java_class_design.inheritance.InhDrink;
import ocp._01_java_class_design.overrides.OverCoffee;
import ocp._01_java_class_design.polymorphism.PolyBeer;
import ocp._01_java_class_design.polymorphism.PolyCoffee;
import ocp._01_java_class_design.polymorphism.PolyDrink;
import ocp._01_java_class_design.singleton.SimpleSingleton;
import ocp._01_java_class_design.statiq.StaticExample;

public class JavaClassDesignExample implements Example {

	@Override
	public void run() {

		encapsulation();
		inheritance();
		overrides();
		polymorphism();
		singleton();
		immutable();
		statiq();
	}

	/**
	 * Implement encapsulation
	 */
	private void encapsulation() {
		// Bad
		Chart chart = new Chart();
		PointBadVersion badPoint = new PointBadVersion();
		badPoint.x = 10;
		badPoint.y = 10;
		chart.place(badPoint.x, badPoint.y);

		// Good
		PointEncapsupated goodPoint = new PointEncapsupated(10, 10);
		chart.place(goodPoint.getX(), goodPoint.getY());

		// Better
		PointEncapsupated2 betterPoint = new PointEncapsupated2(10, 10);
		betterPoint.placeOnChart(chart);
	}

	/**
	 * Implement inheritance including visibility modifiers and composition
	 */
	private void inheritance() {
		InhCoffee coffee = new InhCoffee();
		System.out.println("Is coffee a drink :" + (coffee instanceof InhDrink));
		
		/**
		 * Composition example.
		 */
		class Cup {
			private InhDrink content = new InhCoffee();
		}
	}

	/**
	 * Implement polymorphism
	 */
	private void polymorphism() {
		List<PolyDrink> drinks = Arrays.asList(new PolyCoffee(), new PolyBeer());
		drinks.forEach(PolyDrink::opinion);
	}

	/**
	 * Override hashCode, equals, and toString methods from Object class
	 */
	private void overrides() {
		OverCoffee seeImplementation = null;
	}

	/**
	 * Create and use singleton classes
	 */
	private void singleton() {
		SimpleSingleton s1 = SimpleSingleton.getInstance();
		SimpleSingleton s2 = SimpleSingleton.getInstance();
		System.out.println(s1 == s2); // Reference comparison
	}

	/**
	 * Create and use immutable classes
	 */
	private void immutable() {
		Immutable uselessObject = new Immutable();
	}

	/**
	 * Develop code that uses static keyword on initialize blocks, variables,
	 * methods, and classes.
	 */
	private void statiq() {
		StaticExample.run();
	}
}