package com.a00326288.project01;


import java.sql.Date;

public class User extends UserAccessControl {

		String UID;
		static String username;
		static String password;


	    // User class constructor
	    public User(String username, String password)
	    {
	        this.UID = null;
	        User.username = username; 
	        User.password = password;
	       
	    }

	    // display() method to display
	    // the student data
	    
	    public String getUID() {
	    	return UID;
	    	
	    }
 
	    
	    
	    public void display()
	    {
	        System.out.printf(User.username);
	        
	    }
	    
}
