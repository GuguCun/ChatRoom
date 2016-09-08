package chat_server.server.backstage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chat.common.User;

/**
 * ��������̨�����ݿ⴦����
 * @author Administrator
 *
 */
public class DatabaseManage {
	//�����������ݿ�����Ҫ�Ķ���
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection ct = null;
	
	public void init(){
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//�õ�����
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/ChatRoomDao","root","c0223");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DatabaseManage(){
		this.init();
	}
	
	
	//ȡ���û���Ϣ
	public ResultSet GetUser(String Name){
		try {
			ps = ct.prepareStatement("select * from Users where Name=?");
			ps.setString(1, Name);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	//ע���û���Ϣ
	public boolean Register(User user){
		boolean b = true;
		try {
			ps = ct.prepareStatement("insert into Users(Name,PassWord) values(?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassWords());
			if(ps.executeUpdate()!=1)  // ִ��sql���
			{
				b=false;
			}
		} catch (SQLException e) {
			b = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	//���Ƿ��½�����޸�
	public boolean Update_IsLogin(User user,int isLogin){
		boolean b = true;
		try {
			ps = ct.prepareStatement("update Users set IsLogin=? where Name=?");
			ps.setInt(1, isLogin);
			ps.setString(2, user.getName());
			if(ps.executeUpdate()!=1)  // ִ��sql���
			{
				b=false;
			}
		} catch (SQLException e) {
			b = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	
	
	
	//�ر����ݿ���Դ
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
