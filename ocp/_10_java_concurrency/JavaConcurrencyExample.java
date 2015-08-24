package ocp._10_java_concurrency;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class JavaConcurrencyExample implements Example {

	@Override
	public void run() {
		runnableCallableExecutorService();
		deadlock();
		starvation();
		livelock();
		raceCondition();
		synchronizedExample();
		concurrentCollectionsCyclicBarrier();
		forkJoin();
		parallelStream();
	}

	/**
	 * Create worker threads using Runnable, Callable and use an ExecutorService
	 * to concurrently execute tasks
	 */
	private void runnableCallableExecutorService() {
		// Runnable
		class TaskRunnable implements Runnable {
			@Override
			public void run() {
				System.out.println("From runnable class");
			}
		}
		new Thread(new TaskRunnable()).run();

		// Runnable intern anonymous
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("From runnable anonymous");
			}
		}).run();

		// Runnable lambda
		new Thread(() -> System.out.println("From runnable lambda")).run();

		// Callable
		class TaskCallable implements Callable<Integer> {
			@Override
			public Integer call() throws Exception {
				return Integer.MAX_VALUE;
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(new TaskCallable());

		try {
			System.out.println("From callable class " + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Callable intern anonymous
		future = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Integer.MAX_VALUE;
			}
		});

		try {
			System.out.println("From callable anonymous " + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Callable lambda
		future = executor.submit(() -> Integer.MAX_VALUE);

		try {
			System.out.println("From callable lambda " + future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ExecutorService
		ExecutorService esSingle = Executors.newSingleThreadExecutor();
		ExecutorService esPool = Executors.newFixedThreadPool(10);
		ExecutorService esScheduled = Executors.newScheduledThreadPool(10);

		// execute
		esSingle.execute(() -> System.out.println("submit"));

		// submit(Runnable)
		Future f = esSingle.submit(() -> System.out.println("submit"));
		try {
			System.out.println(f.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// submit (Callable)
		Future<Integer> fi = esScheduled.submit(() -> Integer.MIN_VALUE);

		// invokeAny
		List<Callable<String>> callables = Stream.of("one", "two", "three").map(s -> (Callable<String>) () -> s)
				.collect(Collectors.toList());
		try {
			System.out.println(esSingle.invokeAny(callables));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// invokeAll
		try {
			List<Future<String>> futures = esSingle.invokeAll(callables);
			futures.forEach(e -> {
				try {
					System.out.println(e.get());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Identify potential threading problems among deadlock, starvation,
	 * livelock, and race conditions
	 */
	private void deadlock() {
		/**
		 * A strict rule of courtesy is that when you bow to a friend, you must
		 * remain bowed until your friend has a chance to return the bow.
		 * Unfortunately, this rule does not account for the possibility that
		 * two friends might bow to each other at the same time
		 */
		Object lockA = new Object();
		Object lockB = new Object();

		ExecutorService exec = Executors.newFixedThreadPool(2);
		exec.execute(() -> {
			synchronized (lockA) {
				System.out.println("Task A locked lockA");
				System.out.println(IntStream.range(0, Integer.MAX_VALUE / 100).sum());
				System.out.println("Task A waiting on lockB");
				synchronized (lockB) {
					System.out.println("Continue working...");
				}
			}
		});
		exec.execute(() -> {
			synchronized (lockB) {
				System.out.println("Task B locked lockB");
				System.out.println(IntStream.range(0, Integer.MAX_VALUE / 100).sum());
				System.out.println("Task B waiting on lockA");
				synchronized (lockA) {
					System.out.println("Continue working...");
				}
			}
		});
	}

	/**
	 * Identify potential threading problems among deadlock, starvation,
	 * livelock, and race conditions
	 */
	private void starvation() {
		/**
		 * This is comparable to two people attempting to get the coffee
		 * machine. Alphonse get his break everyday at 10:00 and his coffee
		 * takes 10min to be brew. Gaston can take his break everyday only at
		 * 10:05. As Gaston can ask only once per day the access to the coffee
		 * machine he is now starving.
		 */
		ReentrantLock lock = new ReentrantLock();
		lock.lock(); // Consider that the main thread is aquaring the lock each
						// time

		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			exec.execute(() -> {
				try {
					if (lock.tryLock(200, TimeUnit.MILLISECONDS))
						System.out.println("Got it"); // Never reach this point
					else
						System.out.println("GiveUp");
				} catch (Exception e) {

				}
			});
		}
	}

	/**
	 * Identify potential threading problems among deadlock, starvation,
	 * livelock, and race conditions
	 */
	private void livelock() {
		/**
		 * This is comparable to two people attempting to pass each other in a
		 * corridor: Alphonse moves to his left to let Gaston pass, while Gaston
		 * moves to his right to let Alphonse pass
		 */
	}

	public int count = 0;

	/**
	 * Identify potential threading problems among deadlock, starvation,
	 * livelock, and race conditions
	 */
	private void raceCondition() {

		ExecutorService exec = Executors.newFixedThreadPool(3);
		exec.execute(() -> {
			for (int i = 0; i < 10; i++) {
				count++;
			}
		});
		exec.execute(() -> {
			for (int i = 0; i < 10; i++) {
				count++;
			}
		});

		System.out.println("Count should be 20 but is : " + count);
	}

	public int number = 1;

	/**
	 * Use synchronized keyword and java.util.concurrent.atomic package to
	 * control the order of thread execution
	 */
	synchronized private void synchronizedExample() {
		number = number + number;

		// Atomic
		AtomicInteger i = new AtomicInteger();
		i.incrementAndGet();
		i.getAndIncrement();

		i.decrementAndGet();
		i.getAndDecrement();

		i.getAndAdd(10);
		i.addAndGet(10);

		i.updateAndGet(v -> v * 2);

		i = new AtomicInteger(10);
		i.accumulateAndGet(10, (a, b) -> a * b);
		System.out.print("AtomicInteger : ");
		System.out.println(i);

		AtomicBoolean ab;
		AtomicInteger ai;
		DoubleAdder da;
		LongAdder la;
		DoubleAccumulator dac;
		LongAccumulator lac;
	}

	/**
	 * Use java.util.concurrent collections and classes including CyclicBarrier
	 * and CopyOnWriteArrayList.
	 */
	private void concurrentCollectionsCyclicBarrier() {

		// concurrent collections
		ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(10);
		DelayQueue<Delayed> dq = new DelayQueue<>();

		LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();
		LinkedTransferQueue<Integer> ltq = new LinkedTransferQueue<>();

		PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>();
		SynchronousQueue<Integer> sq = new SynchronousQueue<>();

		ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();
		ConcurrentNavigableMap<String, Integer> cnm = new ConcurrentSkipListMap<>();
		cnm.headMap("2");
		cnm.tailMap("8");
		cnm.subMap("2", "8");

		// CycliyBarrier
		int nbThreadToWait = 3;
		CyclicBarrier cb = new CyclicBarrier(nbThreadToWait);
		try {
			cb.await(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("CyclicBarrier timeout.");
		}

		// CopyOnWriteArrayList
		CopyOnWriteArrayList<Integer> conwal = new CopyOnWriteArrayList<>();
	}

	/**
	 * Use parallel Fork/Join Framework
	 */
	private void forkJoin() {
		// ForkJoinPool
		ForkJoinPool pool = new ForkJoinPool(10);
		ForkJoinTask<Integer> task = new RecursiveTask<Integer>() {
			@Override
			protected Integer compute() {
				return 1 + 1;
			}
		};
		System.out.print("forkJoin : ");
		System.out.println(pool.invoke(task));
	}

	/**
	 * Use parallel Streams including reduction, decomposition, merging
	 * processes, pipelines and performance.
	 */
	private void parallelStream() {
		// Parallel streams
		Stream.of(1,2,3,4,4).parallel().forEach(System.out::println);
	}
}
