package com.a00326288.project01;

import java.sql.Date;

public class User {

		private String UID;
		private String username;
		private String password;
		private String role;
		private int admin_flg;
		private Date date;
		private int acc_lock_ind;

	    // User class constructor
	    User(String UID, String password)
	    {
	        this.UID = UID;
	        this.username = password; 
	        this.password = password;
	        
	        
	        
	    }

	    // display() method to display
	    // the student data
	    public void display()
	    {
	        System.out.println("Username specified is: " + UID + " "
	                           + "and Password is: ******* ");
	        System.out.println();
	    }
	    
}
