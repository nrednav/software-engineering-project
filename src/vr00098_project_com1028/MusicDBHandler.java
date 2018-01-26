package vr00098_project_com1028;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class was created to handle connections to the embedded h2 database
 * @author Vandern Rodrigues
 *
 */
public class MusicDBHandler {
	
	private final String url = "jdbc:h2:~/musicdb"; // Contains path to the database
	private final String username = "vr00098"; // Username to access database
	private final String password = ""; // Password to access database
	
	private Connection connection = null; // This variable will store the connection obtained

	public MusicDBHandler() {
		super();
	}
	
	/**
	 * This method will obtain a connection to the database
	 * 
	 * @return connection
	 */
	public Connection connectToDB() {
		
		try {
			connection = DriverManager.getConnection(url, username, password); // Use JDBC driver to connect to the database
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection; // Return the connection obtained
	}
	
	/**
	 * This method returns the URL of the database connection
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * This method returns the username used to access the database
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * This method returns the password used to access the database
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
}
