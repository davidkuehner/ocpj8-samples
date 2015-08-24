package ocp._04_lambda_builtin_functional_interfaces;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class LambdaBuiltinFunctionalInterface implements Example {

	@Override
	public void run() {
		builtinFunctionalInterfaces();
		builtinPrimitiveFunctionalInterfaces();
		builtinBinaryFunctionalInterfaces();
		builtinUnaryOperatorFunctionalInterface();
	}

	/**
	 * Use the built-in interfaces included in the java.util.function package
	 * such as Predicate, Consumer, Function, and Supplier
	 */
	private void builtinFunctionalInterfaces() {
		Predicate<String> p = s -> s.length() > 10;
		Consumer<String> c = s -> System.out.println(s);
		Function<String, Integer> f = s -> s.length();
		Supplier<String> s = () -> new String("hello");
	}

	/**
	 * Develop code that uses primitive versions of functional interfaces
	 */
	private void builtinPrimitiveFunctionalInterfaces() {
		// Predicate<T>
		{
			IntPredicate ip = i -> i > 1;
			LongPredicate lp = l -> l > 1L;
			DoublePredicate dp = d -> d > 1.2;
		}

		// Consumer<T>
		{
			IntConsumer ic = i -> calc(i);
			LongConsumer lc = l -> calc(l);
			DoubleConsumer dc = d -> calc(d);
		}

		// Function<T, R>
		{
			IntFunction<String> is = i -> String.valueOf(i);
			IntToDoubleFunction id = i -> i + 1.2;
			IntToLongFunction il = i -> i + 999L;
			// idem for long and double
		}

		// Supplier<T>
		{
			BooleanSupplier bs = () -> false;
			IntSupplier is = () -> 1;
			LongSupplier ls = () -> 1L;
			DoubleSupplier ds = () -> 1.2;
		}
	}

	/**
	 * Develop code that uses binary versions of functional interfaces
	 */
	private void builtinBinaryFunctionalInterfaces() {
		
		BiPredicate<Double, Float> bp = (d,f) -> d.isNaN() &&  f.isNaN();

		BiConsumer<Double, Float> bc = (d,f) -> System.out.println(d+f);
//		ObjIntConsumer<T>
//		ObjLongConsumer<T>
//		ObjDoubleConsumer<T>

		BiFunction<Double, Float, String> bf = (d,f) -> new Double(d+f).toString(); 
//		ToIntBiFunction<T, U>
//		ToLongBiFunction<T, U>
//		ToDoubleBiFunction<T, U>

		BinaryOperator<Double> bo = (d1,d2) -> d1/d2;
//		IntBinaryOperator
//		LongBinaryOperator
//		DoubleBinaryOperator
	}
	
	/**
	 * Develop code that uses the UnaryOperator interface
	 */
	private void builtinUnaryOperatorFunctionalInterface() {
		UnaryOperator<String> uo = s -> s.toUpperCase()+",";
		Function<String, String> same = uo;
		
		Stream.of("Salyut", "Skylab", "Mir").map(uo).forEach(System.out::print);
	}

	private void calc(int i) {
	}

	private void calc(long l) {
	}

	private void calc(double d) {
	}

}
