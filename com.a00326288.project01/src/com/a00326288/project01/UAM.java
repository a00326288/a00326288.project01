package com.a00326288.project01;

import java.io.Console;
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
import java.util.UUID;
import java.util.stream.Collectors;

public class UAM  {

	static Scanner sc = new Scanner(System.in);
	static Console cnsl = System.console();
	
	// Make a Session ID
	private static UUID getRandomUUID() {
		return UUID.randomUUID();
	}


	public static void Login() {
		
		//Console used for password echoing.
		
		if (cnsl == null) {
	        System.out.println("No console available");
	        System.exit(0);
	    }

			
			String username = cnsl.readLine( 
					"Enter username : ");
			
			char[] consolePass = cnsl.readPassword( 
					"Enter password : ");
			
			String password = String.valueOf(consolePass);
			
			
			//Check if Username and Password are Correct
			Integer userId = dbCheckUser(username,password); 
			
			//If user returned then print users username and create dashboard, else print Invalid Details and return to main menu.
			if(userId!=0) {
 	            
				System.out.println("---------------------------");
		        System.out.println("-Welcome " +username + "        -");
		        System.out.println("---------------------------\n");
			
		        Boolean uac = dbCheckUserType(userId)   ;
		        UserMenuInterface umi;
		        if(uac==false) {
		        		final String userType = "User";
		        		umi = new UserDashboard(userId, username, userType, getRandomUUID());		
		        }else {
		        		final String userType = "Admin";
		        		umi = new AdminDashboard(userId, username, userType, getRandomUUID());
		        }	
				umi.Menu();
				
			}else {
					System.out.println("Invalid details or User does not Exists");
					username = null;
					password = null;
 			}
	}


	public static void Register() {
		
		System.out.println("Register");
				
		String username ="";
		
		while(true) {
			  try {
				  System.out.println("Enter a username:");
				  username = sc.next();
				  
				  // check if username is already in use, if false then its not and we break the loop and continue.
				  if(validateUsername(username)==false) {;	
				  break;
				  }
			  }catch(Exception e) {
				  e.printStackTrace();  
			  }			  
		}
		
		while(true) {
		
				
				System.out.println("Enter a password:");
				String password = sc.next();
				
				//check the password is valid and conforms to a given set of standards - see InputValidation.java. 
				int flag = InputValidation.validateInput(password);
				
				//if password valid then create the user and break the loop. If input -1 then allow user to abort the register process.
				if(flag==1)
				{
					final String userType = "User";
					
					dbCreateUser(username,password);	
					System.out.println("User registered. Please login.");
					username = null;
					password = null;
					break;
					
				}else
				if(flag==-1){
					break;
				}else
				{
					System.out.println("Please enter between 10 - 15 characters, comprising of 1 special char, 1 digit and no spaces.");
					}
				}
		
	}
	
	private static Boolean validateUsername(String username) {

			if(!dbCheckUser(username).isEmpty() ) 
			{
			System.out.println("Username " + username + " is already taken. Please try again.");
			return true;
			}
			else{
			System.out.println("Username "+ username + " is accepted.");
			return false;
			}
		
	}
	
	static String encode(String username, String password) {
		String Input = username + password;
		String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
		return hashString;
	}

	static String encode(String password) {
		String hashString = Base64.getEncoder().encodeToString(password.getBytes());
		return hashString;
	}
	
	static String encode(Integer id, String UID, String username, String password, String role, String last_login, Integer acc_type) {
		String Input = id.toString()+UID+username+password+role+acc_type.toString()+last_login;
		String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
		return hashString;
	}

	
	
    
    public static void viewProfile(Integer userId, String Usertype) {
    	
    	ArrayList<Person> userlist = getUsers(userId,Usertype);
    	
    	for(Person person : userlist) {
    		
    		System.out.println("User ID : " + person.userId());
    		System.out.println("Username : " + person.username());
    		System.out.println("User Type : " + person.userType());
    		System.out.println("Address : " + person.Address());
    		System.out.println("Email : " + person.Email());
    		System.out.println("Date Of Birth : " + person.DOB());
    		System.out.println("Gender : " + person.Gender());
    		System.out.println("Last Login : " + person.LastLogin());
    		System.out.println();
    	 

    		
    	}
    	
    }
     
   
    public static void deleteUser(Integer userID, String userType) {
		// TODO Auto-generated method stub
    	 
    	 viewProfile(userID,userType);
    	 
    	 ArrayList<Person> personList = getUsers(userID,userType);
		  
		  final List<Integer> lookupUserList = personList.stream()
		  			.map(Person->Person.userId())
		  			.collect(Collectors.toList());
		  
		  System.out.println();
		  System.out.println("Please enter user ID to delete.");
		  
		  Integer Selection;
		  
		  while(true) {
		  
		  try {
		  
		  Selection = sc.nextInt();
		  
		  if(lookupUserList.contains(Selection)) {;
		  	dbDeleteUser(Selection);
	  		System.out.println("User deleted.");
	  		break;
		  }else {
			  System.out.println("Cannot find that user ID.");
		  }
		  
		  }catch(InputMismatchException e) {
			  e.printStackTrace();
			  System.out.println("Invalid user ID. Please try again");
			  sc.next();
		  }
		  
		  }
		
	}
	

	public static void modifyUser(Integer userID, String userType) {
		// TODO Auto-generated method stub
		 
		  viewProfile(userID,userType);
		
		  ArrayList<Person> personList = getUsers(userID,userType);
		  
		  final List<Integer> lookupUserList = personList.stream()
		  			.map(Person->Person.userId())
		  			.collect(Collectors.toList());
		  
		  System.out.println();
		  System.out.println("Please enter user ID to update.");
		  
		  Integer Selection;
		  
		  while(true) {
		  
		  try {
		  
		  Selection = sc.nextInt();
		  
		  Integer PersonIdx;
		  
		  if(lookupUserList.contains(Selection)) {;
		  	PersonIdx=lookupUserList.indexOf(Selection);
		  	System.out.println("User ID : " + personList.get(PersonIdx).userId());
	  		System.out.println("Username : " + personList.get(PersonIdx).username());
	  		System.out.println("User Type : " + personList.get(PersonIdx).userType());
	  		System.out.println("Address : " + personList.get(PersonIdx).Address());
	  		System.out.println("Email : " + personList.get(PersonIdx).Email());
	  		System.out.println("Date Of Birth : " + personList.get(PersonIdx).DOB());
	  		System.out.println("Gender : " + personList.get(PersonIdx).Gender());
	  		System.out.println("Last Login : " + personList.get(PersonIdx).LastLogin());

	  		System.out.println();
	  		break;
		  }else {
			  System.out.println("Cannot find that user ID.");
		  }
		  
		  }catch(InputMismatchException e) {
			  e.printStackTrace();
			  System.out.println("Invalid user ID. Please try again");
			  sc.next();
		  }
		  
		  }
		 
		  System.out.println("Please enter the Option you wish to modify");
		  
		  System.out.println("1. Update User Type");
		  System.out.println("2. Update Address");
		  System.out.println("3. Update Email");
		  System.out.println("4. Update Date of Birth");
		  System.out.println("5. Update Gender");
 
		  System.out.println();
		  System.out.println("Option:");
		  
		  Integer option; 
		  String update = new String(); 
		   
		  
		  while(true) {
		  try {
		  option = sc.nextInt();
		  if(option >=1 || option <=5)
		  break;
		  }catch(InputMismatchException e) {
			  e.printStackTrace();
			  System.out.println("Invalid entry. Please specify an option by Number.");
			  sc.next();
		  }
		  }
 
 	  
		  switch(option) {
		  	case 1:
		  		while(true) {
		  		System.out.println("Enter either 'Admin' or 'User' : ");
		  		update = sc.next();
		  		if(update.equals("User")|| update.equals("Admin")) {
		  			break;
		  		}else {
		  			System.out.println("Invalid input try again");
		  			System.out.println(update);
		  		}
		  		}
		  		break;
		  	case 2:
		  		System.out.println("Please enter a new Address: ");
		  		update = sc.next();
		  		 
		  		break;
		  	case 3:
		  		System.out.println("Please enter a new Email Address: ");
		  		update = sc.next();
		  		 
		  		break;
		  	case 4:
		  		System.out.println("Please enter a new Date of Birth: ");
		  		update = sc.next();
		  		 
		  		break;
		  	case 5:
		  		System.out.println("Please enter Gender: ");
		  		update = sc.next();
		  		 
		  		break;
		  	default:
		  		System.out.println("Please enter a valid option: ");
		
		  }
		 
		  updateUser( Selection,  option,  update );
 
		  System.out.println("Update completed.");
		  
	}
	
	


	private static void updateUser(Integer selection, Integer option, String update) {
		// TODO Auto-generated method stub
		
		String SQL = new String();
		
		Integer acc_type;
		if(option==1 && update.equals("Admin")) {
			acc_type=1;
		}else {
			acc_type=0;
		}
		
	 
		
		switch(option) {
		  	case 1:
		  		SQL = "UPDATE uam SET acc_type="+acc_type+",userType='"+update+"' where user_id="+selection+";"; 
		  		break;
		  	case 2:
		  		SQL = "UPDATE uam SET Address='"+update+"' where user_id="+selection+";"; 
		  		break;
		  	case 3:
		  		SQL = "UPDATE uam SET Email='"+update+"' where user_id="+selection+";";  		  		
		  		break;
		  	case 4:
		  		SQL = "UPDATE uam SET DOB='"+update+"' where user_id="+selection+";"; 
		  		break;
		  	case 5:
		  		SQL = "UPDATE uam SET gender='"+update+"' where user_id="+selection+";";  
		  		break;
		  	default:
		  		System.out.println("Please enter a valid option: ");
		  		break;
		  }
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate(SQL);
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
			
	}
	


	
	
	private static ArrayList<Person> getUsers(Integer userID, String userType) {
		// TODO Auto-generated method stub

		String SQL = new String();
		
		if(userType=="Admin") {
		SQL = ("SELECT * FROM uam;");
		}else {
		SQL = ("SELECT * FROM uam WHERE user_id="+userID+";");
		}
		
		final ArrayList<Person> personList = new ArrayList<>();
		
		try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db"); 
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	 Person newPerson = new Person(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("userType"), rs.getString("Address"), rs.getString("Email"), rs.getString("DOB"), rs.getString("gender"),rs.getString("last_login"));
            	 personList.add(newPerson);
            }
            statement.closeOnCompletion();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return personList;
	}
	
	public static StringBuilder dbCheckUser(String username) {
    	
        String SQL = ("SELECT * FROM uam where username='"+username+"';");
        
        StringBuilder user = new StringBuilder();
        try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db"); 
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	user.append(rs.getString("username"));
            }
            statement.closeOnCompletion();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }
	
    private static Integer dbCheckUser(String username,String password) {
    			    	
        String SQL = ("SELECT user_id FROM uam where username='"+username+"' and password='"+encode(password)+"';");
        int user =0;
        try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db"); 
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	user = rs.getInt("user_id");
            }
            statement.closeOnCompletion();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }
    
    private static Boolean dbCheckUserType(Integer user_id) {
        String SQL = ("SELECT * FROM uam where user_id="+user_id+";");
        boolean uac = false;
        try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db");
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	uac = rs.getBoolean("acc_type");
            }
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uac;
        
    }
    
    private static void dbCreateUser(String username, String password) {
    	

        String SQL = ("INSERT INTO uam (uid,username,password,last_login,acc_type) VALUES ('"+encode(username,password)+"','"+username+"','"+encode(password)+"','"+null+"',"+0+");");
        
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
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
    
    private static void dbUpdateUser(HashMap<Integer, String> usermapUpdate, int userSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE uam SET acc_type='"+ Integer.parseInt(usermapUpdate.get(1))+"' WHERE user_id="+userSelection+";");
		try {
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
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
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 		
	}

	
 
}
