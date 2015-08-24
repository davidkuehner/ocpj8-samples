package ocp._06_exceptions_and_assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import ocp._00_utils.Example;

public class ExceptionsAndAssertionsExample implements Example {

	@Override
	public void run() {
		trayCatchThrowExample();
		catchMulticatchFinally();
		autoclosableRessource();
		customExceptionAutoclosable();
		invariantExample();
	}

	/**
	 * Use try-catch and throw statements
	 */
	private void trayCatchThrowExample() {
		try {
			throw new Exception("Rocket");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Use catch, multi-catch, and finally clauses
	 */
	private void catchMulticatchFinally() {
		// Standard
		try {
			thrower();
		} catch (IOException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			System.out.println("Finally");
		}

		// multicatch
		try {
			thrower();
		} catch (IOException | SQLException e) {
			System.out.println(e);
		}

		// generic
		try {
			thrower();
		} catch (Exception e) { // This is not polymorphisme but more like
								// shorthand.
			System.out.println(e);
		}
	}

	/**
	 * Use Autoclose resources with a try-with-resources statement
	 */
	private void autoclosableRessource() {
		try (BufferedReader br = new BufferedReader(new FileReader(""))) {
			System.out.println(br.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}

		try (BufferedReader br1 = new BufferedReader(new FileReader(""));
				BufferedReader br2 = new BufferedReader(new FileReader(""))) {
			System.out.println(br1.readLine());
			System.out.println(br2.readLine());
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Finally");
		}
	}
	
	/**
	 * Create custom exceptions and Auto-closeable resources
	 */
	private void customExceptionAutoclosable() {
		
		// Exception
		class RocketException extends Exception {
			public RocketException(String m) {
				super(m);
			}
		}
		
		try {
			throw new RocketException("Rocket explode");
		} catch (RocketException e) {
			System.out.println(e);
		}
		
		// Ressource
		class SpaceShuttle implements AutoCloseable {

			public void land() {
				System.out.println("Landing");
			}
			
			@Override
			public void close() throws Exception {
				System.out.println("Closing Shuttle");
			}
		}
		
		try(SpaceShuttle shuttle = new SpaceShuttle()) {
			shuttle.land();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Test invariants by using assertions
	 */
	private void invariantExample() {
		try {
			assert 1>2: "No way";
		} catch (AssertionError e) {
			System.out.println(e);
		}
	}

	private void thrower() throws IOException, SQLException {
		throw new IOException("IOE");
	}
}
