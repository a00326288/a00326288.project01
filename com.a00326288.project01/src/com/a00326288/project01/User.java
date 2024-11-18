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

 




	
		
	    
}

class Admin extends User {
	Admin(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	
	
			
	private static ArrayList<User> allusers = new ArrayList<User>();
	private static Scanner sc = new Scanner(System.in);
	

	private static String isAdmin(Boolean AdminFlg) {
		if(AdminFlg==true) {
			return "Admin";
		}else{
			return "User";
		}	
	}
	
	private static void viewUserProfile() {
		// TODO Auto-generated method stub
		
		displayUsers();
		
		UserAccessControl.returnMain();
	}
	
	public static void displayUsers() {
		// TODO Auto-generated method stub
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 0;i < getUsers().size();i++) {
			
			str.setLength(0);
			
			str.append(isAdmin(getUsers().get(i).getAdmin_flg()));
			
			System.out.println("User ID: "+getUsers().get(i).getId());
			System.out.println("User Name: "+getUsers().get(i).getUsername());
			System.out.println("User Role: " + str);
			System.out.println("User Last Login: "+getUsers().get(i).getLast_login());
			
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
	            
	            statement.closeOnCompletion();
	            connection.close();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return allusers;
	        
	}
	
	private static void modifyUserProfile() {
		// TODO Auto-generated method stub
		
		displayUsers();
		
		sc.useDelimiter("\r?\n");
		
		
		System.out.println("Enter the User ID to modify: ");
		
		int userSelection = sc.nextInt();
		 
		User user= dbCheckUser(userSelection);
		
		
		System.out.println("Username: "+ user.getUsername());
		System.out.println("User Role: "+ isAdmin(user.getAdmin_flg()).toString());
		
		System.out.println();
		
		HashMap<Integer, String> usermap = new HashMap<>();
		usermap.put(1, "Username");
		usermap.put(2, "User Role");
 
		HashMap<Integer, String> usermapUpdate = new HashMap<>();
		usermapUpdate.put(1, user.getUsername());
		usermapUpdate.put(2, isAdmin(user.getAdmin_flg()).toString());
 
		System.out.println("Which property do you want to modify?");
		
		
		for(int i=1;i<=usermap.size();i++) {
			System.out.println(i+". " + usermap.get(i));
			
		}
		
		int cursor = sc.nextInt();
		
		System.out.println("Please enter new value for "+usermap.get(cursor)+":");
		
		
		String newval = null;
		Boolean mybool;
		switch(cursor) {
			case 1:
				while(true) {
					  try {
						  if(UserAccessControl.validateUsername(newval)==false) {
							  usermapUpdate.put(cursor, newval);
						  break;
						  }
					  }catch(Exception e) {
						  e.printStackTrace();
						  
					  }
					  
				  
					}
				break;
			case 2:
				mybool=sc.nextBoolean();
				usermapUpdate.put(cursor, mybool.toString());
				break;
			default:
				break;
		}


		dbUpdateUser(usermapUpdate,userSelection);
		
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		
		UserAccessControl.returnMain();
		
	}


	private static void dbUpdateUser(HashMap<Integer, String> usermapUpdate, int userSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE uam SET username ='"+usermapUpdate.get(1)
		+ "', admin_flg='"+ Boolean.parseBoolean(usermapUpdate.get(2))+"' WHERE user_id="+userSelection+";");
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

	private static void deleteUserProfile() {
		// TODO Auto-generated method stub
		
		displayUsers();

		System.out.println("Enter the User ID to delete: ");
		
		int userSelection = sc.nextInt();
		
		dbDeleteUser(userSelection);
		
		
		System.out.println("Delete Complete! Press enter to return to Main Menu.");
		UserAccessControl.returnMain();
		
		
		
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
	     	    		System.out.println("1 - Create a User");
	     	    		System.out.println("2 - View Users");
	     	    		System.out.println("3 - Modify a User");
	     	    		System.out.println("4 - Delete a User");
	     	    		System.out.println("5 - Main Menu");
	     	    		System.out.println("6 - Log Out");
	     	      
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
	     	    				Menu();
	     	    				break;
	     	    			case 6:
	     	    				UserAccessControl.Logout();
	     	    				break;
	     	    			}
	     	    		break;
	     	    	case 5:
	     	    		UserAccessControl.Logout();
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

	
	


