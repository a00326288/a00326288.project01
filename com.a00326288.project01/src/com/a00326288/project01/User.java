package com.a00326288.project01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class User  {
	
	
		public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	 
		}
	 
		
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
	        this.UID = DBA.encode(username,password);
	        this.username = username; 
	        this.password = null;
	        this.admin_flg = null;
	        this.last_login = null;
 
	        
	    }
	    
	    
	    //getters and setters

	    
		
		public User() {
			
		}


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
		
		
		

		private static void menuSelector(Integer selector, String type) {	
			
			if(type=="Events"){
				Event event = new Event();
				if(selector ==2) {
				event.viewEvents();
				}
				
			}else if(type =="Bookings") {
				Booking booking = new Booking();
				if(selector ==1) {
				booking.createBooking();
				}if(selector==3) {
				booking.viewMyBookings();
					
				}
				
			}
			

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
					  menuSelector(getMenu_cursor(),"Bookings");
					  break;
				  case 2:
					  menuSelector(getMenu_cursor(),"Events");
					  break;
				  case 3:
					  menuSelector(getMenu_cursor(),"Bookings");
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
		
		private static String isAdmin(Boolean AdminFlg) {
			if(AdminFlg==true) {
				return "Admin";
			}else{
				return "User";
			}	
		}
		
		
		public void viewUserProfile() {
			// TODO Auto-generated method stub
			
			displayUsers();
			UAM.returnMain();
		}
		
		public static ArrayList<User> displayUsers() {
			// TODO Auto-generated method stub
			
			
			ArrayList<User> Users = DBA.dbGetUsers();
	 
			System.out.println(String.format("%-10s %-40s %-15s %-25s" , "User ID", "Username ", "User Role", "User Last Login"));
			
			for(int i = 0;i < Users.size();i++) {
		
				System.out.format("%-10s %-40s %-15s %-25s\n",+Users.get(i).getId(),Users.get(i).getUsername(),isAdmin(Users.get(i).getAdmin_flg()),Users.get(i).getLast_login());
			 
			}
			return Users; 
		}
		
		public void modifyUserProfile() {
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
			
			User user= DBA.dbCheckUser(userSelection);
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
			
			
			DBA.dbUpdateUser(usermapUpdate,userSelection);
			
			System.out.println("Update Complete! Press enter to return to Main Menu.");
			
			UAM.returnMain();
			
		}

		
		public void deleteUserProfile() {
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
				DBA.dbDeleteUser(userSelection);
				System.out.println("Delete Complete! Press enter to return to Main Menu.");
				break;
			}
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid User ID to delete.");
				sc.next();
			}
			
			}
			
			UAM.returnMain();
		}


		 
 
		

}

