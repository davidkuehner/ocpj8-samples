package ocp._09_java_file_io_nio_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import ocp._00_utils.Example;

public class JavaFileNIO2Example implements Example {

	@Override
	public void run() {
		pathInterface();
		filesExample();
		streamWithNIO2();
	}

	/**
	 * Use Path interface to operate on file and directory paths
	 */
	private void pathInterface() {
		Path p = Paths.get("/tmp/path");
		System.out.println(p);
		System.out.println(p.getFileName());
		System.out.println(p.getNameCount());
		System.out.println(p.getName(1));
		System.out.println(p.getRoot());
		System.out.println(String.valueOf(p));
		File f = p.toFile();
		System.out.println(p.toAbsolutePath());
	}

	/**
	 * Use Files class to check, read, delete, copy, move, manage metadata of a
	 * file or directory
	 */
	private void filesExample() {
		// Check
		Path p = Paths.get("/tmp/not/there");
		System.out.println(Files.exists(p, new LinkOption[] { LinkOption.NOFOLLOW_LINKS }));

		// Read
		try {
			byte[] b = Files.readAllBytes(p);
			List<String> l = Files.readAllLines(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Delete
		try {
			Files.delete(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Copy
		Path s = Paths.get("/tmp/s");
		Path t = Paths.get("/tmps/d");
		try {
			Files.copy(s, t, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Move
		try {
			Files.move(s, t, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Metadata
		try {
			Files.isDirectory(p);
			Files.isExecutable(p);
			Files.isHidden(p);
			Files.isReadable(p);
			Files.isSameFile(p, p);
			Files.isSymbolicLink(p);
			Files.isWritable(p);
			Files.size(p); // In bytes
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attributes
		try {
			int size = (Integer) Files.getAttribute(p, "basic:size");
			Files.setAttribute(p, "dos:hidden", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Use Stream API with NIO.2
	 */
	private void streamWithNIO2() {
		// List
		try (Stream<Path> stream = Files.list(Paths.get("/tmp/"))) {
			stream.map(String::valueOf).filter(path -> path.endsWith(".txt")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Walk
				try (Stream<Path> stream = Files.walk(Paths.get("/tmp/"), 1)) {
					stream.map(path->path.toString()).filter(path -> path.endsWith(".txt")).forEach(System.out::println);
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		// Find is like walk with a filter operation more
		try (Stream<Path> stream = Files.find(Paths.get("/tmp/"), 1, (path, attr)->path.toString().endsWith(".txt"))) {
			System.out.println("Find");
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Lines
		try( Stream<String> stream = Files.lines(Paths.get("/tmp/l"))) {
			stream.map(String::toLowerCase).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//BuffededReader
		try(BufferedReader br = Files.newBufferedReader(Paths.get("/tmp/br"))) {
			br.lines().map(String::toLowerCase).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
