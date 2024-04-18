package tyut.test;
import java.io.*;
import java.sql.*;

public class databaseTest {
	public static void main() throws SQLException {
		try {
			String dbURL = "jdbc:mysql://localhost:3306";
			String username = "root";
			String password = "root";
			Connection connection = DriverManager.getConnection(dbURL, username, password);
			Statement statement = connection.createStatement();
			statement.close();
			connection.close();
		}finally {
			;
		}
	}
	
}
