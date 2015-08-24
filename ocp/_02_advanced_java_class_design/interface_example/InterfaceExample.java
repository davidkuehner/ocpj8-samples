package ocp._02_advanced_java_class_design.interface_example;

public class InterfaceExample {
	public static void run() {
		Beer beer = new Beer();
		beer.sayHello();
		beer.flowOverthere();
		beer.sample();
	}
}
interface Liquid {
	public void flowOverthere();
}

interface Drink extends Liquid{
	public void sayHello();

	default public void sample() {
		System.out.println("Default");
	}
}

class Beer implements Drink {

	@Override
	public void sayHello() {
		System.out.println("Cheers");
	}

	@Override
	public void flowOverthere() {
		System.out.println("GlouGlouGlou");
		
	}
}