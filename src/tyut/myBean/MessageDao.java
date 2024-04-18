package tyut.myBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

import tyut.myBean.*;

public class MessageDao {
	ConnectionPool pool;
	Connection connection;
	int num = 10;
	
	public MessageDao() {
		pool = ConnectionPool.getInstance();
	}
	
	public int getLastPageId() {
		String sql = "select count(*) from message";
		int page_num = 0;
		try{
			// 链接并查询
			connection = pool.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// 获取消息数组
			if(rs.next()) {
				page_num = rs.getInt(1)/num;
			}
			
			// 关闭连接
			rs.close();
			st.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return page_num;
	}
	
	public int getUserLastPageId(User user) {
		String sql = "select count(*) from message where email=?";
		int page_num = 0;
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
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
	
	public ArrayList<Message> getMessage(int i) {
		String getMessage_sql="select * from message limit ?,?";
		ArrayList<Message> message_array = new ArrayList<Message>();
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getMessage_sql);
			ps.setInt(1, i*10);
			ps.setInt(2, num);
			ResultSet rs = ps.executeQuery();
			
			// 获取消息数组
			while(rs.next()) {
				Message m = new Message();
				Integer id = rs.getInt("id");
				m.setId(id.toString());
				m.setEmail(rs.getNString("email"));
				m.setTitle(rs.getNString("title"));
				m.setContent(rs.getNString("content"));
				message_array.add(m);
			}
			
			// 关闭连接
			rs.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return message_array;
	}
	
	public Message getMessageById(int i) {
		String sql="select * from message where id=?";
		Message message = new Message();
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			
			// 获取消息数组
			if(rs.next()) {
				Integer id = rs.getInt("id");
				message.setId(id.toString());
				message.setEmail(rs.getNString("email"));
				message.setTitle(rs.getNString("title"));
				message.setContent(rs.getNString("content"));
			}

			// 关闭连接
			rs.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}
	
	public ArrayList<Message> getUserMessage(int i, User user) {
		String getUserMessage_sql="select * from message where email=? limit ?,?";
		ArrayList<Message> message_array = new ArrayList<Message>();
		try{
			// 链接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getUserMessage_sql);
			ps.setString(1, user.getEmail());
			ps.setInt(2, i*10);
			ps.setInt(3, num);
			ResultSet rs = ps.executeQuery();
			
			// 获取消息数组
			while(rs.next()) {
				Message m = new Message();
				Integer id = rs.getInt("id");
				m.setId(id.toString());
				m.setEmail(rs.getNString("email"));
				m.setTitle(rs.getNString("title"));
				m.setContent(rs.getNString("content"));
				message_array.add(m);
			}
			
			// 关闭连接
			rs.close();
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return message_array;
	}
	
	public boolean insert(Message m) {
		boolean flag=false;
		String insert_sql="insert into message(email, title, content) values(?,?,?)";
		try{
			// 连接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(insert_sql);
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getContent());
			int rs = ps.executeUpdate();
			
			// 查看是否插入成功
			if(rs>0) {
				flag=true;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	public boolean delete(Message m) {
		boolean flag=false;
		String delete_sql="delete from message where id=?";
		try{
			// 连接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setString(1, m.getId());
			int rs = ps.executeUpdate();
			
			// 查看是否插入成功
			if(rs>0) {
				flag=true;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	public boolean deleteById(int id) {
		boolean flag=false;
		String delete_sql="delete from message where id=?";
		try{
			// 连接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			
			// 查看是否插入成功
			if(rs>0) {
				flag=true;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	
	public boolean updateById(int id, String title, String content) {
		boolean flag=false;
		String delete_sql="update message set title=?,content=? where id=?";
		try{
			// 连接并查询
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setString(1, title);
			ps.setString(2,content);
			ps.setInt(3, id);
			int rs = ps.executeUpdate();
			
			// 查看是否插入成功
			if(rs>0) {
				flag=true;
			}
			
			// 关闭连接
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
}
