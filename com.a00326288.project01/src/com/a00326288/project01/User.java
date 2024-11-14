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
import java.util.InputMismatchException;
import java.util.Scanner;




public class User {
	
	
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
		public String usr_role;
		public byte admin_flg;
		public String last_login;
		public byte acc_lock_ind;


		public static ArrayList<User> myuserlist = new ArrayList<User>();
		

	    // User class constructor
	    public User(String username, String password )
	    {
	    	this.session = null;
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


		public byte getAdmin_flg() {
			return admin_flg;
		}


		public void setAdmin_flg(byte admin_flg) {
			this.admin_flg = admin_flg;
		}


		public String getLast_login() {
			return last_login;
		}


		public void setLast_login(String last_login) {
			this.last_login = last_login;
		}


		public byte getAcc_lock_ind() {
			return acc_lock_ind;
		}


		public void setAcc_lock_ind(byte acc_lock_ind) {
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
		
		private static String encode(Integer id, String UID, String username, String password, String role,Byte admin_flg, String last_login,Byte acc_lock_ind) {
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
		
	    public static boolean dbCheckUser(String username,String password) {
	
	    	User myuser = new User(username, password);
	    	
	        String SQL = ("SELECT * FROM uam where username='"+username+"' and password='"+encode(password)+"';");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(SQL);
	            statement.setQueryTimeout(30); 
	            while (rs.next()) 
	            
	            {

	            	//this is important because for subsequent logins need to clear out the user and replace with new user.
	            	try {
	            	myuserlist.removeFirst();
	            	}catch(Exception e)
	            	{}
	            	
	            	myuser.setId(rs.getInt("user_id"));
	            	myuser.setUID(rs.getString("uid"));
		            myuser.setUsername(rs.getString("username"));
		            myuser.setPassword(rs.getString("password"));
		            myuser.setUsr_role(rs.getString("usr_role"));	
		            myuser.setAdmin_flg(rs.getByte("admin_flg"));
		            myuser.setLast_login(rs.getString("last_login"));
		            myuser.setAcc_lock_ind(rs.getByte("acc_lock_ind"));
		            
		          
		          
		            myuserlist.add(myuser);
	            }
	            
	            statement.closeOnCompletion();
	            connection.close();
	        	
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        if(myuser.getId()!=null) {
	       	
	        	myuser.setSession(encode(myuser.getId(),myuser.getUID(),myuser.getUsername(),myuser.getPassword(),myuser.getUsername(),myuser.getAdmin_flg(),myuser.getLast_login(),myuser.getAcc_lock_ind()));
	        	writeSession();
	        	return true;
	        	
	        }else 
	        {
	        	return false;
	        }
	       
	    }
	    
	    public static void dbCreateUser(String username, String password) {
	    	
	    	
	    	User newUser = new User(username,password);
	    	newUser.setUID(encode(username,password));
	    	newUser.setPassword(encode(password));
	    	newUser.setUsr_role("user");
	    	newUser.setAdmin_flg((byte) 0);
	    	newUser.setLast_login("01/01/2020");
	    	newUser.setAcc_lock_ind((byte) 0);
	    	 
	    	
	        String SQL = ("INSERT INTO uam (uid,username,password,usr_role,admin_flg,last_login,acc_lock_ind) VALUES ('"+newUser.getUID()+"','"+username+"','"+newUser.getPassword()+"','"+newUser.getUsr_role()+"',"+newUser.getAdmin_flg()+",'"+newUser.getLast_login()+"',"+newUser.getAcc_lock_ind()+");");
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
	    
	    
	    public static String readSession() {
	    	
	    	try {
	    		FileReader fileReader = new FileReader("session.txt");
	    		fileReader.close();
	    		return fileReader.toString();
	    		
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		return null;
	    	}
	    	
	    }
	    
	    private static void writeSession() {
			// TODO Auto-generated method stub
	    	
	    	PrintWriter writer;
			try {
				writer = new PrintWriter("session.txt", "UTF-8");
				
				writer.println(session);
		    	writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	

	    
		}
	    	    

	    public static void clearSession() {
	    	
	    	File sessionfile = new File("session.txt");
			sessionfile.delete();
	    }
	    	
	    
	    public static Boolean checkUserRole() {
	    	
	    	try {
	    	if(myuserlist.get(0).admin_flg==1) {
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
		        System.out.println("2 - View Event"); 
		        System.out.println("3 - My Bookings"); 
		        System.out.println("4 - Log Out");
			
		        setMenu_cursor(sc.nextInt());
		        
		       switch(getMenu_cursor()) {
				  case 1:
					  Bookings.bookEvent();
				    break;
				  case 2:
					  Event.viewEvent();
				    break;
				  case 3:
					  
					  Event.myBooking();
					break;
				  case 4:
					  clearSession();
					  launchpad.menu();
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
	
	private Scanner sc = new Scanner(System.in);
	
	private void ModifyUser() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Menu() {
				
	        
	        while(true) {
	        	
	        	 try {
	        		 
	        		 System.out.println("---------------------------");
	     	        System.out.println("-Choose from the following options -");
	     	        System.out.println("---------------------------\n");
	     	        System.out.println("1 - Book Event");
	     	        System.out.println("2 - View Event"); 
	     	        System.out.println("3 - Create Event");
	     	        System.out.println("4 - Modify Event");
	     	        System.out.println("5 - Modify User");
	     	        System.out.println("6 - Modify Booking");
	     	        System.out.println("7 - Log Out");
	      
	        	
	        setMenu_cursor(sc.nextInt());
	        
	        switch(getMenu_cursor()) {
			  case 1:
				  Bookings.bookEvent();
			    break;
			  case 2:
				  Event.viewEvent();
			    break;
			  case 3:
				  Event.createEvent();
				break;
			  case 4:
				  Event.modifyEvent();
				  break;
			  case 5:
				  ModifyUser();
				  break;
			  case 6:
				  Bookings.modifyBooking();
				  break;
			  case 7:
				  clearSession();
				  launchpad.menu();
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
		
}

	
	


