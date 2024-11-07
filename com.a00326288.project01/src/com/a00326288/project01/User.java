package com.a00326288.project01;


import java.sql.Date;

public class User {

		private String UID;
		private String username;
		private String password;


	    // User class constructor
	    User(String username, String password)
	    {
	        this.UID = null;
	        this.username = username; 
	        this.password = password;
	       
	    }

	    // display() method to display
	    // the student data
	    
	    public String getUsername() {
			return username;
		}
	    
	    public void display()
	    {
	        System.out.printf(username);
	    }
	    
}
