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
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;




public class User {
	
	
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		}
	
		public static ArrayList<User> userlist = new ArrayList<User>();
		
		private static Scanner sc = new Scanner(System.in);
		
		private Integer menu_cursor;
		public static String session;
		public Integer id;
		public String UID;
		public String username;
		private String password;
		public String usr_role;
		public Integer admin_flg;
		public String last_login;
		public Integer acc_lock_ind;
		

	    // User class constructor
	    public User(String username, String password )
	    {
	    	this.session = null;
	    	this.id = null;
	        this.UID = encode(username,password);
	        this.username = username; 
	        this.password = encode(password);
	        this.usr_role = null;
	        this.admin_flg = null;
	        this.last_login = null;
	        this.acc_lock_ind = null;
	        
	    }
	    
	    
	    //getters and setters

	    
		
		public String getSession() {
			return session;
		}


		public void setSession(String session) {
			this.session = session;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getUID() {
			return UID;
		}


		public void setUID(String UID) {
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


		public String getUsr_role() {
			return usr_role;
		}


		public void setUsr_role(String usr_role) {
			this.usr_role = usr_role;
		}


		public Integer getAdmin_flg() {
			return admin_flg;
		}


		public void setAdmin_flg(Integer admin_flg) {
			this.admin_flg = admin_flg;
		}


		public String getLast_login() {
			return last_login;
		}


		public void setLast_login(String last_login) {
			this.last_login = last_login;
		}


		public Integer getAcc_lock_ind() {
			return acc_lock_ind;
		}


		public void setAcc_lock_ind(Integer acc_lock_ind) {
			this.acc_lock_ind = acc_lock_ind;
		}

	
	    
	    // Encoding the Password and creating a Unique User ID from Username and Password
	    
	    private static String encode(String username, String password) {
			

			String Input = username + password;
			String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
			return hashString;
		}

		private static String encode(String password) {
			
			String hashString = Base64.getEncoder().encodeToString(password.getBytes());
			return hashString;
		}
		
		private static String encode(Integer id, String UID, String username, String password, String role,Integer admin_flg, String last_login,Integer acc_lock_ind) {
			String Input = id.toString()+UID+username+password+role+admin_flg.toString()+last_login+acc_lock_ind.toString();
			String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
			return hashString;
		}
	    
	    
	    
	    // Check user exists in DB. Return bool (true, false).	    
	    
	    
		public static boolean dbCheckUser(String username) {
			
			String SQL = ("SELECT * FROM uam where username='"+username+"';");
			int id = 0;
			try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            statement.setQueryTimeout(30); 
	            while (rs.next()) 
	            
	            {
	            	id=rs.getInt("user_id");
	            }
	            
	            statement.closeOnCompletion();
	            connection.close();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			if(id==1) {
				return true;
			}else {
				return false;
			}
			
		}
		
	    public static String dbCheckUser(String username,String password) {
	
	    	User user = new User(username, password);
	    		    	
	        String SQL = ("SELECT * FROM uam where username='"+username+"' and password='"+encode(password)+"';");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            statement.setQueryTimeout(30); 
	            while (rs.next()) 
	            
	            {

	            	userlist.clear();
	            	
	            	user.setId(rs.getInt("user_id"));
	            	user.setUID(rs.getString("uid"));
		            user.setUsername(rs.getString("username"));
		            user.setPassword(rs.getString("password"));
		            user.setUsr_role(rs.getString("usr_role"));	
		            user.setAdmin_flg(rs.getInt("admin_flg"));
		            user.setLast_login(rs.getString("last_login"));
		            user.setAcc_lock_ind(rs.getInt("acc_lock_ind"));
		            
		            
		            
		            
		            userlist.add(user);
		            
		          
		         
	            }
	            
	            statement.closeOnCompletion();
	            connection.close();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        
	        
	        if(user.getId()!=null) {
	       	
	        	user.setSession(encode(user.getId(),user.getUID(),user.getUsername(),user.getPassword(),user.getUsername(),user.getAdmin_flg(),user.getLast_login(),user.getAcc_lock_ind()));
	        	UserAccessControl.writeSession(user.getSession(),user.getAdmin_flg());
	        	return username;
	        	
	        }else 
	        {
	        	return null;
	        }
	       
	    }
	    
	    public static void dbCreateUser(String username, String password) {
	    	
	    	
	    	User user = new User(username,password);
	    	
	    	user.setUID(encode(username,password));
	    	user.setPassword(encode(password));
	    	user.setUsr_role("user");
	    	user.setAdmin_flg(0);
	    	user.setLast_login("01/01/2020");
	    	user.setAcc_lock_ind(0);
	    	 
	    	
	        String SQL = ("INSERT INTO uam (uid,username,password,usr_role,admin_flg,last_login,acc_lock_ind) VALUES ('"+user.getUID()+"','"+username+"','"+user.getPassword()+"','"+user.getUsr_role()+"',"+user.getAdmin_flg()+",'"+user.getLast_login()+"',"+user.getAcc_lock_ind()+");");
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
	    
	    
	    
	    	
	    
	    public static Boolean checkUserRole(User user) {
	    	
	    	try {
	    	if(user.getAdmin_flg()==1) {
	    		return true;
	    	}else {
	    		return false;
	    	}}catch(Exception e){
	    		return false;
	    	}
			
	    	
	    	
	    }

 		
		public void Menu() {
		 
	        
	        while(true) {
	        	
	            try {
	        
				System.out.println("---------------------------");
		        System.out.println("-Choose from the following options -");
		        System.out.println("---------------------------\n");
		        System.out.println("1 - Book Event");
		        System.out.println("2 - View Events"); 
		        System.out.println("3 - My Bookings"); 
		        System.out.println("4 - Log Out");
			
		        setMenu_cursor(sc.nextInt());
		        
		       switch(getMenu_cursor()) {
				  case 1:
					  Booking.createBooking();
				    break;
				  case 2:
					  Event.viewEvents();
				    break;
				  case 3:
					  Booking.viewBooking();
					break;
				  case 4:
					  UserAccessControl.Logout();
					break;
				  default:
					  System.out.println("Please select a valid option");
				}
			
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please select a valid option");
			sc.next();
		}
	        }
		}


		public Integer getMenu_cursor() {
			return menu_cursor;
		}


		public void setMenu_cursor(Integer menu_cursor) {
			this.menu_cursor = menu_cursor;
		}


		public static void createUserProfile() {
			// TODO Auto-generated method stub
			
		}


		public static void viewUserProfile() {
			// TODO Auto-generated method stub
			
		}


		public static void modifyUserProfile() {
			// TODO Auto-generated method stub
			
		}


		public static void deleteUserProfile() {
			// TODO Auto-generated method stub
			
		}


	
		
	    
}

class Admin extends User {
	Admin(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
			
	
	private Scanner sc = new Scanner(System.in);
	
	private void ModifyUser() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void Menu() {
		

		System.out.println("---------------------------");
 	    System.out.println("-Choose from the following options -");
 	    System.out.println("---------------------------\n");
 	    System.out.println("1 - Manage Events");
 	    System.out.println("2 - Manage Bookings");
 	    System.out.println("3 - Manage Venues"); 
 	    System.out.println("4 - Manage Users"); 
 	    System.out.println("5 - Log Out");
		
		while(true) {
			try {     
	        	
	     	    setMenu_cursor(sc.nextInt());
	     	    switch(getMenu_cursor()) {  
	     	    	case 1:
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add Event");
	     	    		System.out.println("2 - View Events");
	     	    		System.out.println("3 - Modify Event");
	     	    		System.out.println("4 - Delete Event");
	     	    		System.out.println("5 - Main Menu");
	     	    		System.out.println("6 - Log Out");
				  
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				Event.createEvent();
	     	    			case 2:
	     	    				Event.viewEvents();
	     	    				break;
	     	    			case 3:
	     	    				Event.modifyEvent();
	     	    				break;
	     	    			case 4:
	     	    				Event.deleteEvent();
	     	    				break;
	     	    			case 5:
	     	    				Menu();
	     	    				break;
	     	    			case 6:
	     	    				UserAccessControl.Logout();
	     	    				break;
	     	    			}
	     	    		
	     	    		break;
	     	    	case 2:
		
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Make a Booking");
	     	    		System.out.println("2 - View a Booking");
	     	    		System.out.println("3 - Modify a Booking");
	     	    		System.out.println("4 - Cancel a Booking");
	     	    		System.out.println("5 - Main Menu");
	     	    		System.out.println("6 - Log Out");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				System.out.println("Make a Booking");
	     	    				Booking.createBooking();
	     	    				break;
	     	    			case 2:
	     	    				System.out.println("View a Booking");
	     	    				Booking.createBooking();
	     	    				break;
	     	    			case 3:
	     	    				System.out.println("Modify a Booking");
	     	    				Booking.modifyBooking();
	     	    				break;
	     	    			case 4:
	     	    				System.out.println("Cancel a Booking");
	     	    				Booking.cancelBooking();
	     	    				break;
	     	    			case 5:
	     	    				Menu();
	     	    				break;
	     	    			case 6:
	     	    				UserAccessControl.Logout();
	     	    				break;
	     	    			}
				  
 
	     	    		break;
	     	    	case 3:
				
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add a Venue");
	     	    		System.out.println("2 - View Venues");
	     	    		System.out.println("3 - Modify a Venue");
	     	    		System.out.println("4 - Remove a Venue");
	     	    		System.out.println("5 - Main Menu");
	     	    		System.out.println("6 - Log Out");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				Venue.createVenue();
	     	    				break;
	     	    			case 2:
	     	    				Venue.viewVenues();
	     	    				break;
	     	    			case 3:
	     	    				Venue.modifyVenue();
	     	    				break;
	     	    			case 4:
	     	    				Venue.deleteVenue();
	     	    				break;
	     	    			case 5:
	     	    				Menu();
	     	    				break;
	     	    			case 6:
	     	    				UserAccessControl.Logout();
	     	    				break;
	     	    			}
	     	       
	     	    		break;
	     	    	case 4:
				  
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Create a User Profile");
	     	    		System.out.println("2 - View a User Profile");
	     	    		System.out.println("3 - Modify a User Profile");
	     	    		System.out.println("4 - Delete a User Profile");
	     	    		System.out.println("5 - Main Menu");
	     	    		System.out.println("6 - Log Out");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				UserAccessControl.Register();
	     	    				break;
	     	    			case 2:
	     	    				User.viewUserProfile();
	     	    				break;
	     	    			case 3:
	     	    				User.modifyUserProfile();
	     	    				break;
	     	    			case 4:
	     	    				User.deleteUserProfile();
	     	    				break;
	     	    			case 5:
	     	    				Menu();
	     	    				break;
	     	    			case 6:
	     	    				UserAccessControl.Logout();
	     	    				break;
	     	    			}
	     	    		break;
	     	    	case 5:
	     	    		UserAccessControl.clearSession();
	     	    		launchpad.menu();
	     	    		break;
	     	    	default:
	     	    		System.out.println("Please select a valid option");
	     	    		break;
			}
	        
	       }catch(InputMismatchException e) {
	    	   e.printStackTrace();
	    	   System.out.println("Please select a valid option");
	    	   sc.next();
	       }
		}
	}
		
}

	
	


