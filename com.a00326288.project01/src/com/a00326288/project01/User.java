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


public class User {
		
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
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			
		}
		

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


		public void setUID(String uID) {
			UID = uID;
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
	   
	            	myuser.setId(rs.getInt("user_id"));
	            	myuser.setUID(rs.getString("uid"));
		            myuser.setUsername(rs.getString("username"));
		            myuser.setPassword(rs.getString("password"));
		            myuser.setUsr_role(rs.getString("usr_role"));	
		            myuser.setAdmin_flg(rs.getByte("admin_flg"));
		            myuser.setLast_login(rs.getString("last_login"));
		            myuser.setAcc_lock_ind(rs.getByte("acc_lock_ind"));
		            
		            /*
		            System.out.println(myuser.getId());
		            System.out.println(myuser.getUID());
		            System.out.println(myuser.getUsername());
		            System.out.println(myuser.getPassword());
		            System.out.println(myuser.getUsr_role());
		            System.out.println(myuser.getAdmin_flg());
		            System.out.println(myuser.getLast_login());
		            System.out.println(myuser.getAcc_lock_ind());
		            */
		          
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
	    
	    public void dbCreateUser(String username, String password) {
	    	
	    	

	        String SQL = ("INSERT INTO uam (uid,username,password,usr_role,admin_flg,last_login,acc_lock_ind) VALUES ('"+this.UID+"','"+this.username+"','"+this.password+"','"+this.usr_role+"',"+this.admin_flg+",'"+this.last_login+"',"+this.acc_lock_ind+");");
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
	    	

		private void show() 
	    { 
	    	System.out.println("sessionId = " + getSession()); 
	    	System.out.println("id = " + getId()); 
	        System.out.println("UID = " + getUID()); 
	        System.out.println("username = " + getUsername()); 
	        System.out.println("password = " + getPassword()); 
	        System.out.println("role = " + getUsername()); 
	        System.out.println("Admin Flag" + getAdmin_flg()); 
	        System.out.println("Last Login = " + getLast_login()); 
	        System.out.println("Account Locked = " + getAcc_lock_ind()); 
	       
	        
	    }




	
	    
	   
	    
	    
}
