package com.a00326288.project01;

import java.io.FileNotFoundException;
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


public class DBA {

	public DBA() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static ArrayList<User> UserSession = new ArrayList<User>();
	
	
	/*    USERS   */
	
	static String encode(String username, String password) {
		

		String Input = username + password;
		String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
		return hashString;
	}

	static String encode(String password) {
		
		String hashString = Base64.getEncoder().encodeToString(password.getBytes());
		return hashString;
	}
	
	static String encode(Integer id, String UID, String username, String password, String role,Boolean admin_flg, String last_login) {
		String Input = id.toString()+UID+username+password+role+admin_flg.toString()+last_login;
		String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
		return hashString;
	}
	
	
	static void writeSession(String session) {
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
    
	
	static boolean dbCheckUser(String username) {
		
		String SQL = ("SELECT * FROM uam where username='"+username+"';");
		int id = 0;
		try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
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
	
	static User dbCheckUser(Integer id) {
		
		String SQL = ("SELECT * FROM uam where user_id='"+id+"';");

		User user = new User(null, null);
		
		try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
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
	
    static String dbCheckUser(String username,String password) {
    			    	
        String SQL = ("SELECT * FROM uam where username='"+username+"' and password='"+encode(password)+"';");
        
        String user = null;
        
        
        
        try {
    
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db");
  		 
  		  
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	User userSession = new User();
            	
            	userSession.setId(rs.getInt("user_id"));
            	userSession.setUID(rs.getString("uid"));
            	userSession.setUsername(rs.getString("username"));
            	userSession.setPassword(rs.getString("password"));
            	userSession.setAdmin_flg(rs.getBoolean("admin_flg"));
            	userSession.setLast_login(rs.getString("last_login"));
            	
            	user = rs.getString("username");
 
            	UserSession.add(userSession);
            	
            }
            statement.closeOnCompletion();
            connection.close();
      
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           
        }
        return user;
        
    }
    
    static boolean dbCheckUserPermission(String username,String password) {
    		    	
        String SQL = ("SELECT * FROM uam where username='"+username+"' and password='"+encode(password)+"';");
        
        boolean adminPermission = false;
    
        try {
    
        	
        	
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db");
  		 
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
	            adminPermission = rs.getBoolean("admin_flg");
            }
            
            
            statement.closeOnCompletion();
            connection.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
           
        }
        return adminPermission;
        
    }
    
    static void dbCreateUser(String username, String password) {
    	
    	User user = new User(username,password);
    	
    	user.setUID(encode(username,password));
    	user.setPassword(encode(password));
    	user.setAdmin_flg(false);
    	user.setLast_login("01/01/2020");

        String SQL = ("INSERT INTO uam (uid,username,password,last_login,admin_flg) VALUES ('"+user.getUID()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getLast_login()+"',"+user.getAdmin_flg()+");");
        
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
    
    
    /**** ADMIN BELOW THIS LINE */////
	
	

	static void dbUpdateUser(HashMap<Integer, String> usermapUpdate, int userSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE uam SET admin_flg='"+ Integer.parseInt(usermapUpdate.get(1))+"' WHERE user_id="+userSelection+";");
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
	
	static void dbDeleteUser(int userSelection) {
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
	
	static ArrayList<User> dbGetUsers() {
		// TODO Auto-generated method stub
		
		ArrayList<User> allusers = new ArrayList<User>();
		
    	 String SQL = ("SELECT * FROM uam;");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
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
	
	
	/*   VENUES  */
	
	
	static void dbUpdateVenue(HashMap<Integer, String> venuesmapUpdate,Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE venues SET venue_name ='"+venuesmapUpdate.get(1)
				+ "', venue_address='"+venuesmapUpdate.get(2)+"', city='"+venuesmapUpdate.get(3)+"',capacity="+Integer.parseInt(venuesmapUpdate.get(4))+"  WHERE venue_id="+venueSelection+";");
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

	static void dbDeleteVenue(Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("DELETE FROM venues WHERE venue_id="+venueSelection+";");
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
	
	static void dbCreateVenue(String venueName,String venueAddress, String venueCity, Integer capacity) {
		
		String SQL = ("INSERT INTO venues (venue_name, venue_address, city, capacity) VALUES ('"+venueName+"','"+venueAddress+"','"+venueCity+"',"+capacity+");");
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
	
 
	
	static ArrayList<Venue> dbGetVenues() {
		ArrayList<Venue> venueList = new ArrayList<Venue>();
        String SQL = ("SELECT * from venues;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	venueList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  	Venue venue = new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getString("venue_address"), rs.getString("city"), rs.getInt("capacity"));
  		  	venueList.add(venue);
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return venueList;		
	}
	
	
	
	/**** EVENTS *****/
	
	static void dbDeleteEvent(Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("DELETE FROM events WHERE event_id="+eventSelection+";");
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
	
	static void dbDeleteEvent(Integer eventSelection, Integer venueOption, String eventDate) {
		// TODO Auto-generated method stub
		
		String SQL = ("DELETE FROM dates WHERE event_id="+eventSelection+" and venue_id="+venueOption+" and event_date='"+eventDate+"';");
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



	static ArrayList<Event> dbGetEvents() {
		// TODO Auto-generated method stub
		
		ArrayList<Event> eventList = new ArrayList<Event>();
		
        String SQL = ("SELECT * FROM events;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	eventList.clear();
  		  	
  		  	
  		  	while(rs.next())
  		  	{
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"));
  		  		
  		  		eventList.add(event);
  		  	}
 
			
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		
		return eventList;
		
	}
	
	
	
	static ArrayList<Event> dbGetEventDates(Integer selection) {
		// TODO Auto-generated method stub
		
		ArrayList<Event> eventDateList = new ArrayList<Event>();
		
        String SQL = ("SELECT DISTINCT a.event_id, a.event_name, a.event_description, b.event_date,c.venue_id, c.venue_name, d.price_id, d.price  FROM events a left join dates b on a.event_id = b.event_id left join venues c on b.venue_id = c.venue_id left join prices d on b.price_id = d.price_id where a.event_id="+selection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	eventDateList.clear();
  		  	
  		  	while(rs.next())
  		  	{	
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"), rs.getString("event_date"),rs.getInt("venue_id"),rs.getString("venue_name"),rs.getInt("price_id"), rs.getInt("price"));			
  		  		eventDateList.add(event);
  		  	}
 
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return eventDateList;
	}

	static void dbCreateEventDate(Integer eventId, String eventDate, Integer venueId, Integer priceId) {
		// TODO Auto-generated method stub
		String SQL = ("INSERT INTO dates (event_date, event_id, venue_id, price_id) VALUES ('"+eventDate+"',"+eventId+","+venueId+","+priceId+");");
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

	static void dbCreateEvent(Event createEvent) {
		
		String SQL = ("INSERT INTO events (event_name, event_description) VALUES ('"+createEvent.getEventName()+"','"+createEvent.getEventDescription()+"');");
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
	
	static void dbUpdateEvent(HashMap<Integer, String> eventmapUpdate,Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("UPDATE events SET event_name ='"+eventmapUpdate.get(1)
				+ "', event_description='"+eventmapUpdate.get(2)+"' WHERE event_id="+eventSelection+";");
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
	
	static void dbCancelBooking(int deleteOption) {
		// TODO Auto-generated method stub
		String SQL = ("DELETE FROM bookings WHERE booking_id="+deleteOption+";");
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
