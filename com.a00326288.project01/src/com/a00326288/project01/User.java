package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


public class User {
	
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
	    
	    public String encode(String username, String password) {
			

			String Input = username + password;
			String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
			return hashString;
		}
		
		public String encode(String password) {
			
			String hashString = Base64.getEncoder().encodeToString(password.getBytes());
			return hashString;
		}
	    
	    
	    // Check user exists in DB. Return bool (true, false).
	    
	 
	    
	    
	    public boolean dbCheckUser() {
	        
	        String SQL = ("SELECT * FROM uam where uid ='"+this.UID+"' and password='"+this.password+"';");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jmclaugh/git/a00326288.project01/com.a00326288.project01/db/a00326288.db");
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
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        if(getID()!=null) {
	        	return true;
	        }else 
	        {
	        	return false;
	        }
	       
	    }
	    
	    
	    public void show() 
	    { 
	    	System.out.println("id = " + getID()); 
	        System.out.println("UID = " + getUID()); 
	        System.out.println("username = " + getUsername()); 
	        System.out.println("password = " + getPassword()); 
	        System.out.println("role = " + getUserRole()); 
	        System.out.println("Admin Flag" + getAdminFlag()); 
	        System.out.println("Last Login = " + getLastLogin()); 
	        System.out.println("Account Locked = " + getAccountLockedInd()); 
	       
	        
	    } 
	    
	   
	   
	    @Override
	    public String toString() {
	          return "User [id=" + UID + ", Name=" + username + ", password=" + password+"]";
	      }


	    
	    
}
