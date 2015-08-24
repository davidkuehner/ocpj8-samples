package ocp._01_java_class_design.statiq;

public class StaticExample {
	public static void run() {
		// Block
		StaticBlock sb = new StaticBlock();

		// Variable
		StaticVariable sv1 = new StaticVariable();
		StaticVariable sv2 = new StaticVariable();
		System.out.println(sv1.variable.equals(sv2.variable));
		
		// Method
		StaticMethod seeImplementation = null;
		
		// Class
		OuterStaticClass sc = new OuterStaticClass();
	}

}

class StaticBlock {
	static {
		System.out.println("Static initialization");
	}

	public StaticBlock() {
		System.out.println("Constructor");
	}
}

class StaticVariable {
	public static String variable = "Coffee";
}

class StaticMethod {
	private static int iStatic;
	private int i;

	public static void nothing() {
		// this.hashCode(); // Not ok
		// i; // Not ok
		// super.hashCode(); // Not ok
		int local = iStatic; // ok
	}
}

// static class StaticClass { // Not ok, it's a nonsens.
class OuterStaticClass {
	private static int iStatic = 42;
	private int i;
	static {
		StaticInnerClass.hello();
	}
	public static class StaticInnerClass {
		public static void hello() {
			System.out.println(iStatic);
//			System.out.println(i); // Not ok
		}
	}
}
