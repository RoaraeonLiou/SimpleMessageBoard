package tyut.myBean;
import java.sql.*;
import java.util.ArrayList;

import tyut.myBean.*;

public class UserDao {
	ConnectionPool pool;
	Connection connection;
	int num = 10;
	String exist_sql="select count(*) from user where email=?";
	
	
	String getinfo_sql="select * from user where email=?";
	
	public UserDao() {
		pool = ConnectionPool.getInstance();
	}
	
	public boolean insertUser(User user) {
		String insert_sql = "insert into user(email,password,type) values (?, ?,?)";
		boolean flag = false;
		if(!exist(user)) {
			try{
				connection = pool.getConnection();
				PreparedStatement ps = connection.prepareStatement(insert_sql);
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPassword());
				ps.setString(3, "user");
				int result = ps.executeUpdate();
				
				// 检查插入是否成功
				if(result>0) {
					flag = true;
				}else {
					flag = false;
				}
				
				// 关闭连接
				ps.close();
				pool.freeConnection(connection);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean exist(User user){
		boolean flag = false;
		try{
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(exist_sql);
			ps.setString(1, user.getEmail());
			ResultSet resultSet = ps.executeQuery();
			
			// 检查数据库中是否已存在用户
			if(resultSet.next()) {
				if(resultSet.getInt(1)>0) {
					flag = true;
				}else {
					flag = false;
				}
			}
			
			// 关闭连接
			resultSet.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean check(User user){
		String check_sql="select * from user where email=? and password=?";
		boolean flag = false;
		try{
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(check_sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ResultSet resultSet = ps.executeQuery();
			
			// 对用户进行检查
			if(resultSet.next()) {
				flag = true;
				Integer id = resultSet.getInt("id");
				user.setId(id.toString());
				String name = resultSet.getString("username");
				if(name==null) {
					name="";
				}
				user.setName(name);				
			}
			
			// 关闭连接
			resultSet.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void getOtherInfo(User user) {
		try{
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getinfo_sql);
			ps.setString(1, user.getEmail());
			ResultSet resultSet = ps.executeQuery();
			
			// 检查数据库中是否已存在用户
			if(resultSet.next()) {
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("username"));
				user.setType(resultSet.getString("type"));
			}
			
			// 关闭连接
			resultSet.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> getUsers(int i) {
		String getUsers_sql="select * from user limit ?,?";
		ArrayList<User> user_array = new ArrayList<User>();
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getUsers_sql);
			ps.setInt(1, i*10);
			ps.setInt(2, num);
			ResultSet rs = ps.executeQuery();
			
			// 获取消息数组
			while(rs.next()) {
				User u = new User();
				Integer id = rs.getInt("id");
				u.setId(id.toString());
				u.setEmail(rs.getNString("email"));
				u.setType(rs.getNString("type"));
				u.setName(rs.getNString("username"));
				user_array.add(u);
			}
			
			// 关闭连接
			rs.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user_array;
	}
	
	public int getLastPageId() {
		String sql = "select count(*) from user";
		int page_num = 0;
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			// 获取消息数组
			if(rs.next()) {
				page_num = rs.getInt(1)/num;
			}
			
			// 关闭连接
			rs.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page_num;
	}
	
	public boolean deletetUser(int id) {
		String delete_sql = "delete from user where id=?";
		boolean flag = false;
		try{
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			
			// 检查插入是否成功
			if(result>0) {
				flag = true;
			}else {
				flag = false;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateUserType(int id, String type) {
		String update_sql = "update user set type=? where id=?";
		boolean flag = false;
		try{
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(update_sql);
			ps.setString(1, type);
			ps.setInt(2, id);
			int result = ps.executeUpdate();
			
			// 检查插入是否成功
			if(result>0) {
				flag = true;
			}else {
				flag = false;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
