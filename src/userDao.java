import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;


public class userDao {
	
	public static boolean insertData(String name,String email,String userName,String password) throws ClassNotFoundException
	{ 
		String query="INSERT INTO USER"
				+ "(name, email, userName, password) VALUES"
				+ "(?,?,?,?)";
		Connection c=null;
		try {
			System.out.println("in dao ...");
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3308/assignment2","root","root")
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			System.out.println("first line");
			ds.setServerName(System.getenv("ICSI518_SERVER"));
			System.out.println("servername");
			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
			System.out.println("port number ...");
			ds.setDatabaseName(System.getenv("ICSI518_DB"));
			System.out.println("in db ...");
			ds.setUser(System.getenv("ICSI518_USER"));
			System.out.println("username");
			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
			System.out.println("password");
			

			// Get a connection object
			
			c = ds.getConnection();
			java.sql.PreparedStatement ps;
			ps = c.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, userName);
			ps.setString(4, password);
			ps.executeUpdate();
			ps.close();
			c.close();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("in catch of dao...");
			e.printStackTrace();
			return false;
		}	
	}
    public static boolean checkData(String userName,String password) throws Exception
    {   //for validation of uname and password...
    	System.out.println("dao method called...");
    	String query="SELECT USERNAME,PASSWORD FROM USER WHERE USERNAME=? and PASSWORD=?";
    	Connection c=null;
    	try{
    		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
    		ds.setServerName(System.getenv("ICSI518_SERVER"));
			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("ICSI518_DB"));
			ds.setUser(System.getenv("ICSI518_USER"));
			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
			c = ds.getConnection();
			java.sql.PreparedStatement ps;
		    ps = c.prepareStatement(query);
		
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			//if there is data...then true..
			return true;
		}
    	}
    	catch(Exception e)
    	{   
    		System.out.println("error in login...");
    		e.printStackTrace();
    		return false;
    	}
    	finally{
    		//close the db connection..no matter what!!
    		
    		c.close();
    		
    	}
    	return false;
    }
}
