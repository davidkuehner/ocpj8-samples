package ocp._02_advanced_java_class_design.finalExample;

public class FinalExample {
	public static void run() {
		// Variable
		final int i = 1;
		// i = 2; // Not ok

		// Method
		class Drink {
			public final boolean isLiquid() {
				return true;
			}
		}
		class Coffee extends Drink {
			// public final boolean isLiquid() { // Not ok
			// return true;
			// }
		}

		// Class
		final class Water extends Drink {
		}
		// class MagicWater extends Water{} // Not ok
	}
}
