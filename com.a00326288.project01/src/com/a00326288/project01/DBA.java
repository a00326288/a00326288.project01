package com.a00326288.project01;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;


public class DBA  {

	public DBA() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	
	public static String dbCheckUser(String username) {
    	
        String SQL = ("SELECT * FROM uam where username='"+username+"';");
        String user = null;
        try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db"); 
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	user = rs.getString("username");
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
            	user = rs.getString("username");
            }
            statement.closeOnCompletion();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }
    
    static Integer dbCheckUserType(String username,String password) {
        String SQL = ("SELECT * FROM uam where username='"+username+"' and password='"+encode(password)+"';");
        Integer uac = 0;
        try {
  		  	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
        	//Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:com/a00326288/project01/db/a00326288.db");
        	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            {
            	uac = rs.getInt("acc_type");
            }
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uac;
        
    }
    
    static void dbCreateUser(String username, String password) {
    	

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
    
    
    /**** ADMIN BELOW THIS LINE */////
	
	

	static void dbUpdateUser(HashMap<Integer, String> usermapUpdate, int userSelection) {
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
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("name"),rs.getString("description"));
  		  		
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
		
        String SQL = ("SELECT DISTINCT a.event_id, a.name, a.description, b.event_date,c.venue_id, c.venue_name, d.price_id, d.price  FROM events a left join dates b on a.event_id = b.event_id left join venues c on b.venue_id = c.venue_id left join prices d on b.price_id = d.price_id where a.event_id="+selection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	eventDateList.clear();
  		  	
  		  	while(rs.next())
  		  	{	
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("name"),rs.getString("description"), rs.getString("event_date"),rs.getInt("venue_id"),rs.getString("venue_name"),rs.getInt("price_id"), rs.getInt("price"));			
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
		
		String SQL = ("INSERT INTO events (name, description) VALUES ('"+createEvent.getEventName()+"','"+createEvent.getEventDescription()+"');");
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
		
		
		String SQL = ("UPDATE events SET name ='"+eventmapUpdate.get(1)
				+ "', description='"+eventmapUpdate.get(2)+"' WHERE event_id="+eventSelection+";");
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

	
	
	
	public static ArrayList<Concerts> concertList(String SQL) {
		
		ArrayList<Concerts> list = new ArrayList<Concerts>();
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();
		  	
		  	while(rs.next())
		  	{	
		  		
		  		Concerts concert = new Concerts(rs.getInt("event_id"),rs.getString("name"),rs.getString("description"), rs.getInt("concert_id"),rs.getString("band_name"),rs.getString("band_members"),rs.getString("genre") );
		  		list.add(concert);
		  	}

		  	
          connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}
	
	public static ArrayList<Conferences> conferenceList(String SQL) {
		
		ArrayList<Conferences> list = new ArrayList<Conferences>();
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();
		  	
		  	while(rs.next())
		  	{	
		  		
		  		Conferences conference = new Conferences(rs.getInt("event_id"),rs.getString("name"),rs.getString("description"), rs.getInt("conference_id"),rs.getString("speakers"),rs.getString("sponsor"));
 
		  		list.add(conference);
		  	}

		  	
          connection.close();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}

	public static void create(String SQL1, String SQL2) {
		// TODO Auto-generated method stub
		try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL1);
            statement.executeUpdate(SQL2);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	}

	public static ArrayList<CustomType> dblistDates(String SQL) {
		// TODO Auto-generated method stub
		
		 ArrayList<CustomType> pairs = new ArrayList<>(); 
 
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
  		 	pairs.clear();
		  	
		  	while(rs.next())
		  	{	
		  		pairs.add(new CustomType(rs.getInt("event_id"), rs.getString("event_date"),rs.getString("name"))); 
		  		
		  	}
		  	
		  	connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return pairs;
	}
 
	public static void addDate(String SQL) {
		// TODO Auto-generated method stub
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

	public static void removeDate(String SQL) {
		// TODO Auto-generated method stub
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
	
	public static void delete(String SQL) {
		// TODO Auto-generated method stub
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
