package ocp._11_building_database_applications_with_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ocp._00_utils.Example;

public class JdbcExample implements Example {

	@Override
	public void run() {
		driverConnectionStatementResultSet();
		dbConnection();
		submitQuery();
	}

	/**
	 * Describe the interfaces that make up the core of the JDBC API including
	 * the Driver, Connection, Statement, and ResultSet interfaces and their
	 * relationship to provider implementations
	 */
	private void driverConnectionStatementResultSet() {
		/**
		 * Driver: Interface that every driver must implement. DriverManager:
		 * Grant access to the Drivers Connection : Session with a specific
		 * database. Statement : Used to execute SQL query (string) ResultQuery
		 * : Result of statement.executeQuerry("SELECT * ...")
		 * 
		 * Before JDBC 4 : JWDriver driver =
		 * Class.forName("com.jw.client.JWDriver)
		 */
	}

	/**
	 * Identify the components required to connect to a database using the
	 * DriverManager class including the JDBC URL
	 */
	private void dbConnection() {
		String url = "jdbc:mysql://localhost:3306/data";
		String user = "user";
		String pwd = "password";
		try {
			Connection conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("No database as expected");
		}
	}

	/**
	 * Submit queries and read results from the database including creating
	 * statements, returning result sets, iterating through the results, and
	 * properly closing result sets, statements, and connections
	 */
	private void submitQuery() {
		/**
		 * 
		 * 1. Establishing a connection. 
		 * 2. Create a statement. 
		 * 3. Execute the query.
		 * 4. Process the ResultSet object. 
		 * 5. Close the connection.
		 * 
		 */
		
		String url = "jdbc:mysql://localhost:3306/data";
		String user = "user";
		String pwd = "password";
		String query = "SELECT * FROM books WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(url, user, pwd)) {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, 42);
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					System.out.println(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			System.out.println("No database as expected");
		}
	}
}
