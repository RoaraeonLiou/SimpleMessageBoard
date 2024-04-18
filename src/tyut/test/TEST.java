package tyut.test;

import javax.servlet.http.*;

import java.sql.*;

import javax.servlet.annotation.*;
import tyut.myBean.*;

@WebServlet("/0")
public class TEST extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String dbURL = "jdbc:mysql://localhost:3306/myappdata?serverTimezone=UTC";
//			String username = "root";
//			String password = "l2029017716";
//			Connection connection = DriverManager.getConnection(dbURL, username, password);
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from ticket_student");
//			while(resultSet.next()) {
//				System.out.println(resultSet.getString(1));
//			}
//			statement.close();
//			connection.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			;
//		}
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from ticket_student");
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			pool.freeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			;
		}
	}
}
