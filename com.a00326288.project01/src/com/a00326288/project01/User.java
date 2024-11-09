package com.a00326288.project01;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


public class User {
		
		public String session;
		private Integer id;
		private String UID;
		private String username;
		private String password;
		private String usr_role;
		private byte admin_flg;
		private String last_login;
		private byte acc_lock_ind;
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			
		}
		

	    // User class constructor
	    public User(String username, String password )
	    {
	    	this.session = null;
	    	this.id = null;
	        this.UID = encode(username,password);
	        this.username = username; 
	        this.password = encode(password);
	        this.usr_role = null;
	        this.admin_flg = 0;
	        this.last_login = null;
	        this.acc_lock_ind = 0;
	        
	    }
	    
	    //getters and setters

	    public String getSession() {
	    	return session;
	    }
	    
	    public void setSession(String session) {
	    	this.session = session;
	    }

		public Integer getID() {
	    	return id;
	    	
	    }
	    
	    public void setID(Integer id) {
	    	this.id = id;
	    }
	    
	    public String getUID() {
	    	return UID;
	    	
	    }
	    
	    public void setUser(String UID) {
	    	this.UID = UID;
	    	
	    }
	    
	    public String getUsername() {
	    	return username;
	    }
	    
	    public void setUsername(String username) {
	    	this.username = username;
	    }
	    
 
	    public String getPassword() {
	    	return password;

	    }
	   
	    public void setPassword(String password) {
	    	this.password = password;
	    	
	    }
	    
	    public String getUserRole() {
	    	return usr_role;

	    }
	   
	    public void setUserRole(String usr_role) {
	    	this.usr_role = usr_role;
	    	
	    }
	    
	    public byte getAdminFlag() {
	    	return admin_flg;

	    }
	   
	    public void setAdminFlag(byte admin_flg) {
	    	this.admin_flg = admin_flg;
	    	
	    }
	    
	    public String getLastLogin() {
	    	return last_login;

	    }
	   
	    public void setLastLogin(String last_login) {
	    	this.last_login = last_login;
	    	
	    }
	    
	    public byte getAccountLockedInd() {
	    	return acc_lock_ind;

	    }
	   
	    public void setAccountLockedInd(byte acc_lock_ind) {
	    	this.acc_lock_ind = acc_lock_ind;
	    	
	    }
	    
	    
	    
	    // Encoding the Password and creating a Unique User ID from Username and Password
	    
	    private String encode(String username, String password) {
			

			String Input = username + password;
			String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
			return hashString;
		}
		
		private String encode(String password) {
			
			String hashString = Base64.getEncoder().encodeToString(password.getBytes());
			return hashString;
		}
		
		private String encode(Integer id, String UID, String username, String password, String role,Byte admin_flg, String last_login,Byte acc_lock_ind) {
			String Input = id.toString()+UID+username+password+role+admin_flg.toString()+last_login+acc_lock_ind.toString();
			String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
			return hashString;
		}
	    
	    
	    
	    // Check user exists in DB. Return bool (true, false).
	    
		public static boolean dbCheckUser(String username) {
	        
	        String SQL = ("SELECT * FROM uam where username ='"+username+"';");
	        String user_register = null;
			try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	        
	            while (rs.next()) 
	            
	            {
	            	user_register = rs.getString("username");
	                // print the results
	        
	            }
	            connection.close();
	            
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        if(user_register==null) {    	
	        
	        	return true;
	        	
	        }else 
	        {
	        	return false;
	        }
	       
	    }
	    
	    
	    
	    public boolean dbCheckUser() {
	        
	        String SQL = ("SELECT * FROM uam where uid ='"+this.UID+"' and password='"+this.password+"';");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	        
	            while (rs.next()) 
	            
	            {
	            	
	            	setID(rs.getInt("id"));
	            	setUserRole(rs.getString("usr_role"));	            	
	            	setAdminFlag(rs.getByte("admin_flg"));
	            	setLastLogin(rs.getString("last_login"));
	            	setAccountLockedInd(rs.getByte("acc_lock_ind"));
	            	
	            	/*
	                int id = rs.getInt("id");
	                String UID = rs.getString("uid");
	                String username = rs.getString("username");
	                String password = rs.getString("password");
	                String role = rs.getString("usr_role");
	                byte admin_flg = rs.getByte("admin_flg");
	                String last_login = rs.getString("last_login");
	                Byte acc_lock_ind = rs.getByte("acc_lock_ind");
	               */
	               
	            	
	                // print the results
	                //System.out.format("%s, %s, %s, %s, %s, %s, %s, %s\n", id, UID,username, password, role, admin_flg, last_login, acc_lock_ind);
	            }
	            connection.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        if(getID()!=null) {
	        	this.session=encode(this.id,this.UID,this.username,this.password,this.usr_role,this.admin_flg,this.last_login,this.acc_lock_ind);
	        	writeSession();
	        	return true;
	        	
	        }else 
	        {
	        	return false;
	        }
	       
	    }
	    
	    public void dbCreateUser(String username, String password) {
	    	
	    	

	        String SQL = ("INSERT INTO uam (uid,username,password,usr_role,admin_flg,last_login,acc_lock_ind) VALUES ('"+this.UID+"','"+this.username+"','"+this.password+"','"+this.usr_role+"',"+this.admin_flg+",'"+this.last_login+"',"+this.acc_lock_ind+");");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            statement.executeUpdate(SQL);
	            connection.close();
	        
	            {
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }   	
	    	
	    	
	    }
	    
	    
	    public static String readSession() {
	    	
	    	try {
	    		FileReader fileReader = new FileReader("session.txt");
	    		fileReader.close();
	    		return fileReader.toString();
	    		
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		return null;
	    	}
	    	
	    }
	    
	    private void writeSession() {
			// TODO Auto-generated method stub
	    	
	    	PrintWriter writer;
			try {
				writer = new PrintWriter("session.txt", "UTF-8");
				
				writer.println(this.session);
		    	writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    
		}
	    
	    
	    
	    public static void clearSession() {
	    	
	    	File sessionfile = new File("session.txt");
			sessionfile.delete();
	    }
	    	
	    
	    
	   

		private void show() 
	    { 
	    	System.out.println("sessionId = " + getSession()); 
	    	System.out.println("id = " + getID()); 
	        System.out.println("UID = " + getUID()); 
	        System.out.println("username = " + getUsername()); 
	        System.out.println("password = " + getPassword()); 
	        System.out.println("role = " + getUserRole()); 
	        System.out.println("Admin Flag" + getAdminFlag()); 
	        System.out.println("Last Login = " + getLastLogin()); 
	        System.out.println("Account Locked = " + getAccountLockedInd()); 
	       
	        
	    } 
	    
	   
	    
	    
}
