package ocp._12_localization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class LocalizationExample implements Example {

	@Override
	public void run() {
		advantages();
		readAndSet();
		propertiesFile();
		ressourceBundle();
	}

	/**
	 * Describe the advantages of localizing an application
	 */
	private void advantages() {
		// TODO Auto-generated method stub
	}

	/**
	 * Read and set the locale by using the Locale object
	 */
	private void readAndSet() {
		Locale l1 = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
		Locale l2 = new Locale("fr", "FR");
		Locale l3 = Locale.forLanguageTag("en-US");
		Locale l4 = Locale.CANADA_FRENCH;
		System.out.println(Locale.TAIWAN);
		Stream.of(NumberFormat.getAvailableLocales()).forEach(System.out::println);
	}

	/**
	 * Create and read a Properties file
	 */
	private void propertiesFile() {

		Properties prop = new Properties();
		prop.setProperty("Geometry", "20");
		prop.setProperty("Algebra", "20");
		prop.setProperty("Physics", "18");
		prop.setProperty("Chemistry", "17");
		prop.setProperty("Biology", "19");

		try (FileOutputStream out = new FileOutputStream("config.properties")) {
			prop.store(out, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try (FileInputStream in = new FileInputStream("config.properties");) {
			prop.load(in);
			System.out.println("Geo " + prop.getProperty("Geometry"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build a resource bundle for each locale and load a resource bundle in an
	 * application
	 */
	private void ressourceBundle() {
		ResourceBundle rb = ResourceBundle.getBundle("Bundle");
		System.out.println(rb.getString("my.hello"));
		System.out.println(rb.getString("my.goodbye"));
		Locale local = new Locale("fr", "FR");
		ResourceBundle rbfr = ResourceBundle.getBundle("Bundle",local);
		System.out.println(rbfr.getString("my.hello"));
		System.out.println(rbfr.getString("my.goodbye"));
	}

}
