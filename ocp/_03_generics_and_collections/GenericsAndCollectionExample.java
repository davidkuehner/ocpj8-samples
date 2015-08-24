package ocp._03_generics_and_collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class GenericsAndCollectionExample implements Example {

	@Override
	public void run() {
		generic();
		listSetMapQue();
		comparatorExample();
		collectionsStreamsFiltersExample();
		forEach();
		filterCollection();
		methodReferences();
	}

	private void generic() {

		// Generic
		class Passer<T> {
			T pass(T t) {
				return t;
			}
		}
		Passer<Double> d = new Passer<>();
		Double passedDouble = d.pass(1.14);

		// Wildcard ?
		{
			List<Double> listOfDouble = new ArrayList<>();
			List<?> listOfUnkown = listOfDouble; // listOfUnkown is readOnly
			listOfUnkown.forEach(System.out::println); // read only items are
														// Object
		}

		// Wildcard ? extends
		{
			List<Integer> listOfInteger = new ArrayList<>();
			List<Double> listOfDouble = new ArrayList<>();
			List<? extends Number> listOfNumber = null;
			listOfNumber = listOfInteger;
			listOfNumber = listOfDouble;
		}

		// Wildcard ? super
		{
			List<Number> listOfNumber = new ArrayList<>();
			List<Object> listOfObject = new ArrayList<>();
			List<? super Integer> listOfSuperInteger = null;
			listOfSuperInteger = listOfNumber;
			listOfSuperInteger = listOfObject; // Need to be casted as object
		}
	}

	/**
	 * Create and use ArrayList, TreeSet, TreeMap, and ArrayDeque objects
	 */
	private void listSetMapQue() {
		// ArrayList
		{
			List<Integer> l = new ArrayList<>();
			l.add(1);
			l.add(1);
			l.add(1);
			l.add(4);

			l.size();
			l.remove(3);
			l.isEmpty();
			l.get(0);
		}

		// TreeSet
		{
			Set<Integer> s = new TreeSet<>();
			Integer i = 1;
			s.add(9);
			s.add(i);
			s.add(i);
			s.add(2);
			System.out.println(s.toString()); // there is only one 1
		}

		// TreeMap
		{
			Map<String, Integer> m = new TreeMap<>();
			m.put("Nine", 9);
			m.put("One", 1);
			m.put("Two", 2);

			for (Entry<String, Integer> e : m.entrySet()) {
				System.out.println(e);
			}

			m.get("Nine");
		}

		// ArrayDeque
		// peek return null if empty otherwise it an Exception
		{
			Deque<Integer> d = new ArrayDeque<>();
			d.add(1); // add at tail
			d.addFirst(0); // add at head
			d.addLast(99); // add at tail

			d.forEach(System.out::println);
			d.element();
			d.forEach(System.out::println);
		}
	}

	/**
	 * Use java.util.Comparator and java.lang.Comparable interfaces
	 */
	private void comparatorExample() {

		// Comparable
		class Spaceship implements Comparable<Spaceship> {
			public int power;

			@Override
			public int compareTo(Spaceship s) {
				return Integer.compare(power, s.power);
			}
		}

		// Comparator
		class SpaceshipComparator implements Comparator<Spaceship> {
			@Override
			public int compare(Spaceship o1, Spaceship o2) {
				return Integer.compare(o1.power, o2.power);
			}
		}
	}

	/**
	 * Collections Streams and Filters
	 */
	private void collectionsStreamsFiltersExample() {
		// Some stream declarations
		Stream.of("Appolo2", "Soyuse", "Serenity").forEach(System.out::println);
		IntStream.range(1, 4).forEach(System.out::println);
		DoubleStream.of(1.0, 1.2, 1.3).forEach(System.out::println);

		// From collection
		Arrays.asList("engin", "booster", "pod").stream().forEach(System.out::println);

		// Filter
		Stream.of("Appolo2", "Soyuse", "Serenity").filter(a -> a.startsWith("S")).forEach(System.out::println);
	}

	/**
	 * Iterate using forEach methods of Streams and List
	 */

	private void forEach() {
		// forEach stream
		Stream.of("Appolo2", "Soyuz", "Serenity").forEach(System.out::println);
		// forEach list
		Arrays.asList("engin", "booster", "pod").forEach(System.out::println);
	}

	/**
	 * Describe Stream interface and Stream pipeline
	 * 
	 * INTERFACE
	 * A sequence of elements supporting sequential and parallel aggregate
	 * operations.
	 * 
	 * PIPLINE
	 * To perform a computation, stream operations are composed into a stream
	 * pipeline. A stream pipeline consists of a source (which might be an
	 * array, a collection, a generator function, an I/O channel, etc), zero or
	 * more intermediate operations (which transform a stream into another
	 * stream, such as Stream.filter(Predicate)), and a terminal operation
	 * (which produces a result or side-effect, such as Stream.count() or
	 * Stream.forEach(Consumer)). Streams are lazy; computation on the source
	 * data is only performed when the terminal operation is initiated, and
	 * source elements are consumed only as needed.
	 * 
	 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
	 */
	
	/**
	 * Filter a collection by using lambda expressions
	 */
	private void filterCollection() {
		List<String> spaceship = Arrays.asList("US-Mercury-1961", "US-Gemini-1965", "RU-Vostok-1961");
		Predicate<String> russianShipFilter = a -> a.startsWith("RU");
		Predicate<String> firstOrbitals = a -> a.endsWith("1961");
		List<String> russianFleet = spaceship.stream().filter(russianShipFilter).collect(Collectors.toList());
		russianFleet.forEach(System.out::print);
		System.out.println("");
		List<String> pionneer = spaceship.stream().filter(firstOrbitals).collect(Collectors.toList());
		pionneer.forEach(System.out::print);
	}
	
	/**
	 * Use method references with Streams
	 */
	private void methodReferences() {
		System.out.println("");
		Stream.of("Galactica", "Pegasus").forEach(System.out::println);
	}
}
