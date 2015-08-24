package ocp._05_java_stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class JavaStreamAPIExample implements Example {

	@Override
	public void run() {
		peekMapExample();
		findMatchExample();
		optionalExample();
		dataCalculationExample();
		sortCollectionWithStream();
		collectExample();
		mergeFlatmapExample();
	}

	/**
	 * Develop code to extract data from an object using peek() and map()
	 * methods including primitive versions of the map() method
	 */
	private void peekMapExample() {
		// Peek
		stations.stream().peek(s -> s.name = s.name.toUpperCase()).forEach(s -> System.out.println(s.name));
		// Same with map
		stations.stream().map(s -> {
			s.name = s.name.toUpperCase();
			return s;
		}).forEach(s -> System.out.println(s.name));

		// Map primitive
		stations.stream().mapToInt(s -> s.name.length()).forEach(System.out::println);
		stations.stream().mapToDouble(s -> s.name.length() / 2.5).forEach(System.out::println);
		stations.stream().mapToLong(s -> s.name.length() / 2L).forEach(System.out::println);
	}

	/**
	 * Search for data by using search methods of the Stream classes including
	 * findFirst, findAny, anyMatch, allMatch, noneMatch
	 */
	private void findMatchExample() {
		OptionalInt first = IntStream.range(0, 10).findFirst();
		OptionalInt any = IntStream.range(0, 10).findAny();
		boolean anyMatch = IntStream.range(0, 10).anyMatch(i -> i % 2 == 0);
		boolean allMatch = IntStream.range(0, 10).allMatch(i -> i % 2 == 0);
		boolean noneMatch = IntStream.range(0, 10).noneMatch(i -> i % 2 == 0);
		System.out.println("First : " + first.orElse(-1) + " Any : " + any.getAsInt() + " anyMatch : " + anyMatch
				+ " allMatch : " + allMatch + " noneMatch : " + noneMatch);
	}

	/**
	 * Develop code that uses the Optional class
	 */
	private void optionalExample() {
		Optional<Integer> first = Stream.of(1, 2, 3, 4, 5).findFirst();
		Optional<Integer> of = Optional.of(1);
		Optional<Integer> ofNullable = Optional.ofNullable(null);
		Optional<Integer> empty = Optional.empty();

		System.out.println("isPresent: " + first.isPresent());
		System.out.println("get: " + first.get());
		System.out.print("ifPresent: ");
		first.ifPresent(System.out::println);
		System.out.print("filter: ");
		first.filter(i -> i == 1).ifPresent(System.out::println);
		System.out.print("map: ");
		first.map(i -> i / 2.1).ifPresent(System.out::println);

		System.out.println("orElse: " + empty.orElse(42));
		System.out.println("orElseGet: " + empty.orElseGet(() -> Integer.MAX_VALUE));
		System.out.println("orElseThrow: " + first.orElseThrow(() -> new NullPointerException()));
	}

	/**
	 * Develop code that uses Stream data methods and calculation methods
	 */
	private void dataCalculationExample() {
		// Data methods
		{
			Comparator<String> byLength = (s1, s2) -> Integer.compare(s1.length(), s2.length());
			Optional<String> max = Stream.of("a", "ab", "abc").max(byLength);
			Optional<String> min = Stream.of("a", "ab", "abc").min(byLength);
			long count = Stream.of("a", "ab", "abc").count();
			System.out.println("Min: " + min + " Max: " + max + " count: " + count);
		}

		// Calculation methods
		// Only on primitiv stream
		{
			int sum = IntStream.of(1, 2, 3, 4).sum();
			OptionalInt min = IntStream.of(1, 2, 3, 4).min();
			OptionalInt max = IntStream.of(1, 2, 3, 4).max();
			OptionalDouble average = IntStream.of(1, 2, 3, 4).average();
			System.out.println("sum: " + sum + " min: " + min + " max: " + max + " average: " + average);
		}
	}

	/**
	 * Sort a collection using Stream API
	 */
	private void sortCollectionWithStream() {
		Comparator<String> byLength = (s1, s2) -> Integer.compare(s1.length(), s2.length());
		Comparator<String> byAlpha = (s1, s2) -> s1.compareTo(s2);
		List<String> unsorted = Arrays.asList("cab", "abc", "a", "ab");
		List<String> sorted = unsorted.stream().sorted(byLength).collect(Collectors.toList());
		unsorted.forEach(System.out::println);
		sorted.forEach(System.out::println);

		List<String> doubleSorted = unsorted.stream().sorted(byLength.thenComparing(byAlpha))
				.collect(Collectors.toList());
		doubleSorted.forEach(System.out::println);
	}

	/**
	 * Save results to a collection using the collect method and group/partition
	 * data using the Collectors class
	 */
	private void collectExample() {
		// Simple example from existing Collector
		// join
		String all = Stream.of("So ", "say ", "we ", "all ", "!").collect(Collectors.joining());
		System.out.println(all);

		// group
		Map<Object, List<String>> map = Stream.of("a", "aa", "cda", "c", "aaa", "bb", "bfrw")
				.collect(Collectors.groupingBy(s -> s.charAt(0)));
		map.forEach((k, v) -> System.out.println(k + " : " + v));

		// Implementation
		class EvenOddCollector
				implements Collector<Integer, 
									Map<Boolean, List<Integer>>, 
									Map<Boolean, List<Integer>>> {

			@Override
			public Supplier<Map<Boolean, List<Integer>>> supplier() {
				return () -> new HashMap<Boolean, List<Integer>>() {{
					put(true, new ArrayList<Integer>());
					put(false, new ArrayList<Integer>());
				}};
			}

			@Override
			public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
				return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
					acc.get( candidate % 2 == 0).add(candidate);
				};
			}

			@Override
			public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
				return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
					map1.get(true).addAll(map2.get(true));
					map1.get(false).addAll(map2.get(false));
					return map1;
				};
			}

			@Override
			public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
				return Function.identity();
			}

			@Override
			public Set<java.util.stream.Collector.Characteristics> characteristics() {
				return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
			}
		}
		
		System.out.println(Stream.of(0,1,2,3,4,5,6,7,8,9).collect(new EvenOddCollector()));
	}
	
	/**
	 * Use of merge() and flatMap() methods of the Stream API
	 */
	private void mergeFlatmapExample() {
		List<List<Integer>> listOfList = new ArrayList<>();
		listOfList.add(Arrays.asList(1,2,3,4));
		listOfList.add(Arrays.asList(5,6,7,8));
		listOfList.add(Arrays.asList(9,10,11));
		
		// flatMap
		List<Integer> even = listOfList.stream().flatMap(l->l.stream()).filter(i->i%2==0).collect(Collectors.toList());
		System.out.println(even);
		
		// merge() doesn't exists.
	}

	class SpaceStation {
		public String name;

		public SpaceStation(String n) {
			name = n;
		}
	}

	SpaceStation salyut = new SpaceStation("Salyut");
	SpaceStation skylab = new SpaceStation("Skylab");
	SpaceStation mir = new SpaceStation("Mir");
	SpaceStation iss = new SpaceStation("ISS");

	List<SpaceStation> stations = Arrays.asList(salyut, skylab, mir, iss);
}
