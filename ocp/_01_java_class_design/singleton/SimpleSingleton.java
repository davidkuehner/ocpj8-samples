package ocp._01_java_class_design.singleton;

/**
 * Singleton example
 */
public class SimpleSingleton {
	private static SimpleSingleton instance = new SimpleSingleton();

	private SimpleSingleton() {
	}

	public static SimpleSingleton getInstance() {
		return instance;
	}
}