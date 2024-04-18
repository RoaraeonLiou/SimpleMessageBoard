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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			// ��ȡ��Ϣ����
			if(rs.next()) {
				page_num = rs.getInt(1)/num;
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			
			// ��ȡ��Ϣ����
			if(rs.next()) {
				page_num = rs.getInt(1)/num;
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getMessage_sql);
			ps.setInt(1, i*10);
			ps.setInt(2, num);
			ResultSet rs = ps.executeQuery();
			
			// ��ȡ��Ϣ����
			while(rs.next()) {
				Message m = new Message();
				Integer id = rs.getInt("id");
				m.setId(id.toString());
				m.setEmail(rs.getNString("email"));
				m.setTitle(rs.getNString("title"));
				m.setContent(rs.getNString("content"));
				message_array.add(m);
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			
			// ��ȡ��Ϣ����
			if(rs.next()) {
				Integer id = rs.getInt("id");
				message.setId(id.toString());
				message.setEmail(rs.getNString("email"));
				message.setTitle(rs.getNString("title"));
				message.setContent(rs.getNString("content"));
			}

			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(getUserMessage_sql);
			ps.setString(1, user.getEmail());
			ps.setInt(2, i*10);
			ps.setInt(3, num);
			ResultSet rs = ps.executeQuery();
			
			// ��ȡ��Ϣ����
			while(rs.next()) {
				Message m = new Message();
				Integer id = rs.getInt("id");
				m.setId(id.toString());
				m.setEmail(rs.getNString("email"));
				m.setTitle(rs.getNString("title"));
				m.setContent(rs.getNString("content"));
				message_array.add(m);
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(insert_sql);
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getContent());
			int rs = ps.executeUpdate();
			
			// �鿴�Ƿ����ɹ�
			if(rs>0) {
				flag=true;
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setString(1, m.getId());
			int rs = ps.executeUpdate();
			
			// �鿴�Ƿ����ɹ�
			if(rs>0) {
				flag=true;
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			
			// �鿴�Ƿ����ɹ�
			if(rs>0) {
				flag=true;
			}
			
			// �ر�����
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
			// ���Ӳ���ѯ
			connection = pool.getConnection();
			PreparedStatement ps = connection.prepareStatement(delete_sql);
			ps.setString(1, title);
			ps.setString(2,content);
			ps.setInt(3, id);
			int rs = ps.executeUpdate();
			
			// �鿴�Ƿ����ɹ�
			if(rs>0) {
				flag=true;
			}
			
			// �ر�����
			ps.close();
			pool.freeConnection(connection);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
}
