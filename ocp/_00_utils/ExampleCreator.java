package ocp._00_utils;

import ocp._01_java_class_design.JavaClassDesignExample;
import ocp._02_advanced_java_class_design.AdvancedJavaClassDesignExample;
import ocp._03_generics_and_collections.GenericsAndCollectionExample;
import ocp._04_lambda_builtin_functional_interfaces.LambdaBuiltinFunctionalInterface;
import ocp._05_java_stream_api.JavaStreamAPIExample;
import ocp._06_exceptions_and_assertions.ExceptionsAndAssertionsExample;
import ocp._07_use_java_se_8_date_time_api.DateTimeExample;
import ocp._08_java_io_fundamentals.IoFundamentalsExample;
import ocp._09_java_file_io_nio_2.JavaFileNIO2Example;
import ocp._10_java_concurrency.JavaConcurrencyExample;
import ocp._11_building_database_applications_with_jdbc.JdbcExample;
import ocp._12_localization.LocalizationExample;

public class ExampleCreator implements Creator {
	
	private static ExampleCreator instance = new ExampleCreator();
	
	private ExampleCreator() {
		//Empty
	}

	@Override
	public Example create(Chapter chapter) {
		System.out.println(chapter);
		switch (chapter) {
		case JAVA_CLASS_DESIGN:
			return new JavaClassDesignExample();
		case ADVANCED_JAVA_CLASS_DESIGN:
			return new AdvancedJavaClassDesignExample();
		case GENERICS_AND_COLLECTIONS:
			return new GenericsAndCollectionExample();
		case LAMBDA_BUILTIN_FUNCTIONAL_INTERFACES:
			return new LambdaBuiltinFunctionalInterface();
		case JAVA_STREAM_API:
			return new JavaStreamAPIExample();
		case EXCEPTIONS_AND_ASSERTIONS:
			return new ExceptionsAndAssertionsExample();
		case USE_JAVA_SE_8_DATE_TIME_API:
			return new DateTimeExample();
		case JAVA_IO_FUNDAMENTALS:
			return new IoFundamentalsExample();
		case JAVA_FILE_IO_NIO_2:
			return new JavaFileNIO2Example();
		case JAVA_CONCURRENCY:
			return new JavaConcurrencyExample();
		case BUILDING_DATABASE_APPLICATIONS_WITH_JDBC:
			return new JdbcExample();
		case LOCALIZATION:
			return new LocalizationExample();
		default:
			return new DefaultExample();
		}
	}

	public static ExampleCreator getInstance() {
		return instance;
	}

}
