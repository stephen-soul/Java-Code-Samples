package database;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static final String DRIVER = "org.apache.driver.jdbc.EmbeddedDriver";
	private static final String JDBC_URL = "jdbc:derby:AdventureTM;create=true"; // Name of the database that's being saved
	private static final String errorPath = "error.txt";
	private static final String errorMessage = "Failed to connect to database storing user information";
	
	Connection conn;
	
	ResultSet rs = null;
	Statement statement = null;
	
	public DBConnection() {
		try {
			this.conn = DriverManager.getConnection(JDBC_URL);
			if (this.conn != null) {
				System.out.println("Connection successful");	
			}
		} catch (SQLException e) {
			System.out.println("Failed to connect");
			// Log the error in a text file
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(errorPath, "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			writer.println(errorMessage);
			writer.close();
		}
	} // End of DBConnection
	
	public boolean saveToDb() {
		return true;
	}
	
	public boolean loadFromDb() throws SQLException {
		statement = conn.createStatement();
		//statement.execute("");
		return true;
	}
	
	public boolean doesDbExist() {
		return true;
	}
}
