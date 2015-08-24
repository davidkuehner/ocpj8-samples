package ocp._08_java_io_fundamentals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;

import ocp._00_utils.Example;

public class IoFundamentalsExample implements Example {

	@Override
	public void run() {
		// readWriteConsole(); // Run in eclipse doesn't work -> jar
		streamsExamples();
	}

	/**
	 * Read and write data from the console
	 */
	private void readWriteConsole() {
		// Console
		Console c = System.console();

		c.format("Your name: ");
		String name = c.readLine();
		c.format(name);
		c.format("\n");

		c.format("Your password: ");
		char[] pw = c.readPassword();
		for (char ch : pw) {
			c.format("%c", ch);
		}
		c.format("\n");

		// Stream simple
		System.out.println("sysout print");

		// Stream
		System.out.print("Ship: ");
		String message = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			message = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			bw.write(message);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Use BufferedReader, BufferedWriter, File, FileReader, FileWriter,
	 * FileInputStream, FileOutputStream, ObjectOutputStream, ObjectInputStream,
	 * and PrintWriter in the java.io package.
	 */
	private void streamsExamples() {
		// File
		File file = new File("/tmp/text");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		file.exists();
		file.isDirectory();
		file.canRead();

		// FileWriter
		try (FileWriter fw = new FileWriter(file)) {
			fw.write("File content");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FileReader
		try (FileReader fr = new FileReader(file)) {
			char[] cbuf = new char[256];
			fr.read(cbuf);
			for (char c : cbuf) {
				System.out.print(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// PrintWriter
		try (PrintWriter pw = new PrintWriter(file)) {
			pw.write("write");
			pw.println(true);
			pw.println(1);
			pw.println(2.4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// FileOutputStream
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// FileInputStream
		try (FileInputStream fis = new FileInputStream(file)) {
			fis.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// BufferedWriter
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("easy way to write strings");
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// BufferReader
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			// br.lines();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ObjectOutputStream
		File file2 = new File("/tmp/obj");
		try {
			file2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file2))) {
			oos.writeObject(new Galaxy());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ObjectInputStream
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2))) {
			Galaxy g = (Galaxy) ois.readObject();
			System.out.println("asdf");
			System.out.println(g.hi());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Galaxy implements Serializable {
	private static final long serialVersionUID = 1L;

	public String hi() {
		return "Hi!";
	}
}
