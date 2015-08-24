package ocp._02_advanced_java_class_design.lambdaExample;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
	void accept(T t, U u, V v);
}
