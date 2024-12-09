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
import java.util.Scanner;
import java.util.UUID;

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
					Person newPerson = new Person(0, username, password, userType);
					dbCreateUser(newPerson.username(),newPerson.password());	
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
    
    
    public static void viewProfile(Integer userId, String Usertype) {
    	
    	ArrayList<Person> userlist = getUsers(userId,Usertype);
    	
    	for(Person person : userlist) {
    		person.toString();

    		
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
	
	private void dbDeleteUser(int userSelection) {
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


	public static void modifyUser(Integer userID, String userType) {
		// TODO Auto-generated method stub
		
	}


	public static void deleteUser(Integer userID, String userType) {
		// TODO Auto-generated method stub
		 
		
		
	}
	
	private static ArrayList<Person> getUsers(Integer userId, String userType) {
		// TODO Auto-generated method stub

		String SQL = new String();
		
		if(userType=="Admin") {
		SQL = ("SELECT * FROM uam;");
		}else {
		SQL = ("SELECT * FROM uam WHERE user_id="+userId+";");
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
	
	
 
}
