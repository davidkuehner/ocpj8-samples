package ocp;

import java.util.Arrays;

import ocp._00_utils.Chapter;
import ocp._00_utils.ExampleCreator;

public class Main {

	public static void main(String[] args) {
//		runExampleOf(Chapter.JAVA_CLASS_DESIGN);
//		runExampleOf(Chapter.ADVANCED_JAVA_CLASS_DESIGN);
//		runExampleOf(Chapter.GENERICS_AND_COLLECTIONS);
//		runExampleOf(Chapter.LAMBDA_BUILTIN_FUNCTIONAL_INTERFACES);
//		runExampleOf(Chapter.JAVA_STREAM_API);
//		runExampleOf(Chapter.EXCEPTIONS_AND_ASSERTIONS);
//		runExampleOf(Chapter.USE_JAVA_SE_8_DATE_TIME_API);
//		runExampleOf(Chapter.JAVA_IO_FUNDAMENTALS);
//		runExampleOf(Chapter.JAVA_FILE_IO_NIO_2);
//		runExampleOf(Chapter.JAVA_CONCURRENCY);
//		runExampleOf(Chapter.BUILDING_DATABASE_APPLICATIONS_WITH_JDBC);
		runExampleOf(Chapter.LOCALIZATION);
//		runAll();
	}
	
	private static void runExampleOf(Chapter chapter) {
		ExampleCreator.getInstance().create(chapter).run();
	}
	
	private static void runAll(){
		Arrays.stream(Chapter.values()).forEach(Main::runExampleOf);
	}

}
