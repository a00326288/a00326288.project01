package com.a00326288.project01;
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
import java.util.stream.Collectors;




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
		public Boolean admin_flg;
		public String last_login;
 
		

	    // User class constructor
	    public User(String username, String password )
	    {
	    	User.session = null;
	    	this.id = null;
	        this.UID = encode(username,password);
	        this.username = username; 
	        this.password = null;
	        this.admin_flg = null;
	        this.last_login = null;
 
	        
	    }
	    
	    
	    //getters and setters

	    
		
		public String getSession() {
			return session;
		}


		public void setSession(String session) {
			User.session = session;
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
 

		public Boolean getAdmin_flg() {
			return admin_flg;
		}


		public void setAdmin_flg(Boolean admin_flg) {
			this.admin_flg = admin_flg;
		}


		public String getLast_login() {
			return last_login;
		}


		public void setLast_login(String last_login) {
			this.last_login = last_login;
		}

 
		private Integer getMenu_cursor() {
			return menu_cursor;
		}

		private void setMenu_cursor(Integer menu_cursor) {
			this.menu_cursor = menu_cursor;
		}
 		
	    
		public void Menu() {
			
			
			setMenu_cursor(0);
			
	        while(getMenu_cursor()!=-1) {
	        	
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
					  Booking newBooking = new Booking();
					  newBooking.createBooking();
					  break;
				  case 2:
					  Event.viewEvents();
					  break;
				  case 3:
					  Booking.viewMyBookings();
					  break;
				  case 4:
					  setMenu_cursor(-1);
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
		
		private static String encode(Integer id, String UID, String username, String password, String role,Boolean admin_flg, String last_login) {
			String Input = id.toString()+UID+username+password+role+admin_flg.toString()+last_login;
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
		
		public static User dbCheckUser(Integer id) {
			
			String SQL = ("SELECT * FROM uam where user_id='"+id+"';");

			User user = new User(null, null);
			
			try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            statement.setQueryTimeout(30); 
	            while (rs.next()) 
	            {
	            	user.setId(rs.getInt("user_id"));
	            	user.setUID(rs.getString("uid"));
	            	user.setUsername(rs.getString("username"));
	            	user.setPassword(rs.getString("password"));
	            	user.setAdmin_flg(rs.getBoolean("admin_flg"));
	            	user.setLast_login(rs.getString("last_login"));
	            }
	            statement.closeOnCompletion();
	            connection.close(); 
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return user;
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
		            user.setAdmin_flg(rs.getBoolean("admin_flg"));
		            user.setLast_login(rs.getString("last_login"));
		            
		            userlist.add(user);
	            }
	            statement.closeOnCompletion();
	            connection.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        if(user.getId()!=null) {
	       	
	        	user.setSession(encode(user.getId(),user.getUID(),user.getUsername(),user.getPassword(),user.getUsername(),user.getAdmin_flg(),user.getLast_login()));
	        	UserAccessControl.writeSession(user.getSession());
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
	    	user.setAdmin_flg(false);
	    	user.setLast_login("01/01/2020");

	        String SQL = ("INSERT INTO uam (uid,username,password,last_login,admin_flg) VALUES ('"+user.getUID()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getLast_login()+"',"+user.getAdmin_flg()+");");
	        
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

}

class Admin extends User {

	private Integer menu_cursor;
	private static ArrayList<User> allusers = new ArrayList<User>();
	private static Scanner sc = new Scanner(System.in);
	
	Admin(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	private Integer getMenu_cursor() {
		return menu_cursor;
	}

	
	private void setMenu_cursor(Integer menu_cursor) {
		this.menu_cursor = menu_cursor;
	}

	private static String isAdmin(Boolean AdminFlg) {
		if(AdminFlg==true) {
			return "Admin";
		}else{
			return "User";
		}	
	}
	
		
	@Override
	public void Menu() {
		
		setMenu_cursor(0);
	
		while(getMenu_cursor()!=-1) {
			
			System.out.println("---------------------------");
	 	    System.out.println("-Choose from the following options -");
	 	    System.out.println("---------------------------\n");
	 	    System.out.println("1 - Manage Events");
	 	    System.out.println("2 - Manage Bookings");
	 	    System.out.println("3 - Manage Venues"); 
	 	    System.out.println("4 - Manage Users"); 
	 	    System.out.println("5 - Log Out");
			
			try {     
	     	    setMenu_cursor(sc.nextInt());
	     	    
	     	    switch(getMenu_cursor()) {  
	     	    	case 1:
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add an Event");
	     	    		System.out.println("2 - Add Event Dates");
	     	    		System.out.println("3 - View Event and Dates");
	     	    		System.out.println("4 - Modify an Event");
	     	    		System.out.println("5 - Delete an Event");
	     	    		System.out.println("6 - Delete Event Dates");
	     	    		System.out.println("7 - Main Menu");
				  
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				Event.createEvent();
	     	    				break;
	     	    			case 2:
	     	    				Event.createEventDate();
	     	    				break;
	     	    			case 3:
	     	    				Event.viewEvents();
	     	    				break;
	     	    			case 4:
	     	    				Event event = new Event();
	     	    				event.modifyEvent();
	     	    				break;
	     	    			case 5:
	     	    				Event.deleteEvent();
	     	    				break;
	     	    			case 6:
	     	    				Event.deleteEventDate();
	     	    				break;
	     	    			case 7:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    			}
	     	    		break;
	     	    	case 2:
		
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Make a Booking");
	     	    		System.out.println("2 - View Bookings");
	     	    		System.out.println("3 - Cancel a Booking");
	     	    		System.out.println("4 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				Booking createBooking = new Booking();
	     	    				createBooking.createBooking();
	     	    				break;
	     	    			case 2:
	     	    				Booking viewBooking = new Booking();
	     	    				viewBooking.viewBookings();
	     	    				break;
	     	    			case 3:
	     	    				System.out.println("Cancel a Booking");
	     	    				Booking CancelBooking = new Booking();
	     	    				CancelBooking.cancelBooking();
	     	    				break;
	     	    			case 4:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
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
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    		}
	     	    		break;
	     	    	case 4:
				  
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Create a User");
	     	    		System.out.println("2 - View Users");
	     	    		System.out.println("3 - Modify a User");
	     	    		System.out.println("4 - Delete a User");
	     	    		System.out.println("5 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				UserAccessControl.Register();
	     	    				break;
	     	    			case 2:
	     	    				Admin.viewUserProfile();
	     	    				break;
	     	    			case 3:
	     	    				Admin.modifyUserProfile();
	     	    				break;
	     	    			case 4:
	     	    				Admin.deleteUserProfile();
	     	    				break;
	     	    			case 5:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    			}
	     	    		break;
	     	    	case 5:
	     	    		setMenu_cursor(-1);
	     	    		System.out.println("You have logged out.");
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
	
	private static void viewUserProfile() {
		// TODO Auto-generated method stub
		
		displayUsers();
		UserAccessControl.returnMain();
	}
	
	public static ArrayList<User> displayUsers() {
		// TODO Auto-generated method stub
		
		
		ArrayList<User> Users = getUsers();
 
		System.out.println(String.format("%-10s %-40s %-15s %-25s" , "User ID", "Username ", "User Role", "User Last Login"));
		
		for(int i = 0;i < Users.size();i++) {
	
			System.out.format("%-10s %-40s %-15s %-25s\n",+Users.get(i).getId(),Users.get(i).getUsername(),isAdmin(Users.get(i).getAdmin_flg()),Users.get(i).getLast_login());
		 
		}
		return Users; 
	}
	
	private static void modifyUserProfile() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		ArrayList<User> Users = displayUsers();
		
		
		List<Integer> lookupUserList = Users.stream()
					.map(User::getId)
		  			.collect(Collectors.toList());
 
		Integer userSelection = null;
		
		while(true) {
			
			try {
		
		System.out.println("Enter the User ID to modify: ");
		userSelection = sc.nextInt();
		 
		if(lookupUserList.contains(userSelection)) {
			break;
		}else {
			System.out.println("Please input a valid user ID.");
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please input a valid user ID.");
			sc.next();
		}
		}
		
		User user= dbCheckUser(userSelection);
		System.out.println("Username: "+ user.getUsername());
		System.out.println("User Role: "+ isAdmin(user.getAdmin_flg()).toString());
		
		
		System.out.println();
		
		HashMap<Integer, String> usermap = new HashMap<>();
		usermap.put(1, "User Role");
 
		HashMap<Integer, String> usermapUpdate = new HashMap<>();
		usermapUpdate.put(1, isAdmin(user.getAdmin_flg()).toString());
 
		System.out.println("Which property do you want to modify?");
		
		for(int i=1;i<=usermap.size();i++) {
			System.out.println(i+". " + usermap.get(i));			
		}
		
		Integer cursor = null;
		
		while(true) {
			try {
				cursor = sc.nextInt();
				if(usermap.containsKey(cursor)) {
					break;
				}else {
					
					System.out.println("Please input a valid option.");
				}
				
				
			}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please input a valid option.");
			sc.next();
			
			}
		}
		
		System.out.println("Please enter new value for "+usermap.get(cursor)+":");
		
		
		String usr_role =null;
		
		if(cursor==1) {
			System.out.println("Please enter 'Admin' to assign Administrator Role or 'User' to assign User role.");
			
		}
		
		while(true) {
		
		try {
			usr_role = sc.next();
			if(usr_role.contentEquals("Admin")) {
				usermapUpdate.put(cursor, "1");
				break;
			}else if(usr_role.contentEquals("User")) {
				usermapUpdate.put(cursor, "0");
				break;
			}
			{
				System.out.println("Please enter 'Admin' to assign Administrator Role or 'User' to assign User role.");
			}

		}catch(InputMismatchException e){
			e.printStackTrace();
			System.out.println("Please enter 'Admin' to assign Administrator Role or 'User' to assign User role.");
			sc.next();
		}
		
		}
		
		
		dbUpdateUser(usermapUpdate,userSelection);
		
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		
		UserAccessControl.returnMain();
		
	}

	private static void deleteUserProfile() {
		// TODO Auto-generated method stub
		
		ArrayList<User> Users = displayUsers();
		
		
		List<Integer> lookupUserList = Users.stream()
					.map(User::getId)
		  			.collect(Collectors.toList());
 
		Integer userSelection = null;

		while(true) {
		
		try {
		System.out.println("Enter the User ID to delete: ");
		userSelection = sc.nextInt();
		
		if(lookupUserList.contains(userSelection)) {
			dbDeleteUser(userSelection);
			System.out.println("Delete Complete! Press enter to return to Main Menu.");
			break;
		}
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please input a valid User ID to delete.");
			sc.next();
		}
		
		}
		
		UserAccessControl.returnMain();
	}

	private static void dbUpdateUser(HashMap<Integer, String> usermapUpdate, int userSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE uam SET admin_flg='"+ Integer.parseInt(usermapUpdate.get(1))+"' WHERE user_id="+userSelection+";");
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate(SQL);
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	private static void dbDeleteUser(int userSelection) {
		// TODO Auto-generated method stub

		String SQL = ("DELETE FROM uam WHERE user_id="+userSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 		
	}
	
	private static ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
    	 String SQL = ("SELECT * FROM uam;");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            statement.setQueryTimeout(30); 
	            
	            allusers.clear();
	            
	            while (rs.next()) 
	            {
	            	User user = new User(rs.getString("username"), rs.getString("password"));
	            	
	            	user.setId(rs.getInt("user_id"));
	            	user.setUID(rs.getString("uid"));
	            	user.setUsername(rs.getString("username"));
	            	user.setPassword(rs.getString("password"));
	            	user.setAdmin_flg(rs.getBoolean("admin_flg"));
	            	user.setLast_login(rs.getString("last_login"));
            
	            	allusers.add(user);		         
	            }
	            connection.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return allusers;
	}
	
}