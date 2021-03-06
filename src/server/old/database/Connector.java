package server.old.database;

import java.sql.*;

import server.old.User;
import server.old.utils.DecryptUtils;


public class Connector {
	
	private Connection connection;
	private DecryptUtils decryptUtils;
	
	public Connector() {
		this.decryptUtils = new DecryptUtils();
		
		connect();
	}
	
	private void connect() {
		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
		String username = "matrix_connector";
		String pw = "matrix";
		try {
			connection = DriverManager.getConnection(url,username,pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Legt einen neuen User in der Datenbank an, 
	 * @RETURN 1 = angelegt
	 */
	public int addNewUser(String userName, String decryptedPassword) {
		try {
			Statement st = connection.createStatement();
			String statement = "INSERT INTO user (name,password) VALUES ('"+userName+"','"+decryptedPassword+"')";
//			rs = st.execute(statement);
			return st.executeUpdate(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Prüfe ob user schon gibt 
	
	public boolean checkForUserName(String username) {
		try {
			Statement st = connection.createStatement();
			String statement = "SELECT * FROM user";
			ResultSet rs =  st.executeQuery(statement);
			while(rs.next()) {
				if(rs.getString(1).contains(username)) {
					return true;
				}
				System.out.println(rs.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkLoginData(String username, String password) {
		boolean isDataCorrect = false;
		
		try {
			Statement st = connection.createStatement();
			String statement = "SELECT * FROM user WHERE name ='" + decryptUtils.decryptUsername(username) + "' AND password ='" + password + "'";
			ResultSet rs =  st.executeQuery(statement);
			if(rs.wasNull()) {
				isDataCorrect = false;
			} else {
				isDataCorrect = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDataCorrect;
	}
	
	//login 
	public User login(String userName, String decryptedPassword) {
		try {
			Statement st = connection.createStatement();
			String statement = "SELECT * FROM user WHERE name ='" + userName + "' AND Password ='" + decryptedPassword + "'";
					//"SELECT * FROM user WHERE name = "' + userName + '"";
			ResultSet rs =  st.executeQuery(statement);
			while(rs.next()) {
				return new User(rs.getString(1),rs.getInt(3), rs.getInt(4), rs.getShort(5), true);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
