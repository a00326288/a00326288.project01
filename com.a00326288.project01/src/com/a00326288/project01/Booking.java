package com.a00326288.project01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Booking {

	private static Scanner sc = new Scanner(System.in);
	
	private int booking_id;
	private String booking_ref;
	private String booking_dte;
	private int num_of_tickets;
	private int event_id;
	private String name;
	private int venue_id;
	private String venue_name;
	private int user_id;
	private String username;
	private String event_date;
	private String cardNumber;
	private Integer price_id;
	private Integer price;
	
	
	Booking() {
		// TODO Auto-generated constructor stub

	}
	
	public Booking(int booking_id, String booking_ref, String booking_dte, int num_of_seats, int num_of_tickets, int event_id, String name, int venue_id, String venue_name, int user_id, String username, String event_date, String cardNumber, Integer price_id, Integer price) {
		this.booking_id = booking_id;
		this.booking_ref = booking_ref;
		this.booking_dte = booking_dte;
		this.num_of_tickets = num_of_tickets;
		this.event_id = event_id;
		this.name = name;
		this.venue_id = venue_id;
		this.venue_name = venue_name;
		this.user_id = user_id;
		this.username = username;
		this.event_date = event_date;
		this.price_id = price_id;
		this.price = price;
		this.cardNumber = cardNumber;
 
		
	}
	

	private int getBooking_id() {
		return booking_id;
	}

	private void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	private String getBooking_ref() {
		return booking_ref;
	}

	private void setBooking_ref(String booking_ref) {
		this.booking_ref = booking_ref;
	}

	private String getBooking_dte() {
		return booking_dte;
	}

	private void setBooking_dte(String booking_dte) {
		this.booking_dte = booking_dte;
	}

	private int getNum_of_tickets() {
		return num_of_tickets;
	}

	private void setNum_of_tickets(int num_of_tickets) {
		this.num_of_tickets = num_of_tickets;
	}

	private int getEvent_id() {
		return event_id;
	}

	private void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	private int getVenue_id() {
		return venue_id;
	}

	private void setVenue_id(int venue_id) {
		this.venue_id = venue_id;
	}
	
	private int getPrice_id() {
		return price_id;
	}

	private void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	
	private int getPrice() {
		return price;
	}

	private void setPrice(int price) {
		this.price = price;
	}
	
	
	private String getname() {
		return name;
	}
	
	private void setname(String name) {
		this.name = name;
	}

	
	private String getVenue_name() {
		return venue_name;
	}

	private void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}

	private int getUser_id() {
		return user_id;
	}

	private void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	private String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private String getEvent_date() {
		return event_date;
	}

	private void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	private String getCardNumber() {
		return cardNumber;
	}

	private void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");
	}


	public void createBooking(Integer user_id, String eventType) {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");		
		
		
		System.out.println();
		
		ArrayList<CustomType> eventList = DBA.dbGetEvents(eventType);
		
		
		System.out.println(String.format("%-20s %-70s %40s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-20s %-70s %40s\n",eventList.get(i).getEvent_id(),eventList.get(i).getEvent_name(),eventList.get(i).getEvent_description());
			
		}
		
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
		
        Integer eventSelection = null;
        
		while(true) {
			
			try {
				System.out.println("Please input the ID of the event you wish to book:");
				eventSelection = sc.nextInt();
				
				List<Integer> lookupEventList = DBA.dbGetEvents(eventType).stream()
	  		  			.map(CustomType::getEvent_id)
	  		  			.collect(Collectors.toList());
				
				if(lookupEventList.contains(eventSelection)) {
					
					int idx = lookupEventList.indexOf(eventSelection);
					
					
					setEvent_id(eventList.get(idx).getEvent_id());
					setname(eventList.get(idx).getEvent_name());
					
				
					List<CustomType> list = DBA.dbGetEventDates(eventSelection);
				
					if(list.get(0).getEvent_name().isBlank()) {
						System.out.println("Sorry no available dates currently for this event. Please try again.");
					}else {
						System.out.println();
						for(int i = 0; i < list.size(); i++) {
			
							System.out.printf("%-25s %-25s %-15s %-30s %10s","Option "+i+ " : " , list.get(i).getEvent_name() , list.get(i).getEvent_date() , list.get(i).getVenue_name() , list.get(i).getPrice());
						}
						break;
					}
					}else {
					System.out.println("Cannot find that event ID. Please enter another.");
				}
				}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Invalid input. Please enter a valid Event ID.");
				sc.nextLine();
				}
			}
			
	
			System.out.println();
			
			System.out.println("Please input the option number from the list above for the date and venue to book:");
			

 
			
			Integer indexSelection = null;
			
			while(true) {
				
				try {
					
					indexSelection = sc.nextInt();
					
					List<CustomType> list = DBA.dbGetEventDates(eventSelection);
					
					
					
					try {
					if(list.get(indexSelection)!=null) {
						
 
						
						setEvent_date(list.get(indexSelection).getEvent_date());
						setVenue_id(list.get(indexSelection).getVenue_id());
						setVenue_name(list.get(indexSelection).getVenue_name());
						setPrice_id(list.get(indexSelection).getPrice_id());
						setPrice(list.get(indexSelection).getPrice());
						setUser_id(user_id);
						
						
 
						
						break;	
						
					}else {
						System.out.println("Option entered is not valid. Please input a valid option from the list.");	
					}
					
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("Invalid input. Please enter the ID of the option you wish to book.");
				}
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Invalid input. Please enter the ID of the option you wish to book.");
					sc.next();
				}
			}
			
			System.out.println();
			
			System.out.println("You have selected:");
			
			System.out.println();
					
			System.out.println(String.format("%-25s %-15s %-30s %10s" , "Event Name", "Event Date", "Venue", "Price" ));
			
			System.out.printf("%-25s %-15s %-30s %10s",getname(), getEvent_date(), getVenue_name(), getPrice());

			System.out.println();
			
			//calculate total number of tickets per booking for date at venue and then minus that from total count for the venue.
			
			int remainingTickets = numOfTickets(getEvent_id(),getVenue_id(),getEvent_date());
			
			System.out.println();
			
			System.out.println("Total remaining number of tickets: " + remainingTickets);
			
			System.out.println();
			
			System.out.println("Please input the number of tickets you want : ");
			
			boolean checkRemainder=false;
			
			while(checkRemainder==false) {
			
				while(true) 
				{
					try {
						setNum_of_tickets(sc.nextInt());
						
						if(getNum_of_tickets()<=0) {
							System.out.println("Must purchase more than one ticket.");
						}else {
							break;
						}
					}catch(InputMismatchException e) {
						e.printStackTrace();
						System.out.println("Please input a valid number of tickets.");
						sc.next();
					}
				}
				
				if(getNum_of_tickets()>remainingTickets){
					System.out.println("Sorry not enough tickets. Please choose less or another date.");
					checkRemainder=false;
				}else{
					System.out.println("The total cost of your booking is : " + (getNum_of_tickets() *  getPrice()));	
					checkRemainder=true;
			}
			};

			
			System.out.println("Please input the card number for the booking Guarantee : ");
			
			while(true) {
				try {
					setCardNumber(sc.next());
					if(InputValidation.cardNumberVerification(getCardNumber())==true) {
						setCardNumber(getCardNumber());
						break;
					}else {
						System.out.println("Invalid Input. Please ensure card number is at least 12 digits.");
					}
				}catch(Exception e) {
					System.out.println("Invalid input.");
				}
			}
			
			
			StringBuilder bookref = new StringBuilder();
			
			bookref.append(LocalDateTime.now()+"-"+user_id+"-"+getEvent_id()+getVenue_id());
			
			
			System.out.println("Thanks. Your booking reference is : " + bookref);
			
			dbCreateEventBooking(LocalDate.now(),getEvent_id(),getVenue_id(),getUser_id(),getNum_of_tickets(),getCardNumber(),bookref,getEvent_date(),getPrice_id());
			
		}
	
		
	
	private static void dbCreateEventBooking(LocalDate booking_dte, Integer event_id, Integer venue_id, Integer user_id,Integer num_of_tickets, String cardnumber, StringBuilder booking_ref, String event_date, Integer price_id   ) {
		// TODO Auto-generated method stub

		
		String SQL = ("INSERT INTO bookings (booking_date,venue_id, event_id, user_id, num_of_tickets, cardNumber, booking_ref,event_date) VALUES ('"+booking_dte+"',"+venue_id+","+event_id+","+user_id+","+num_of_tickets+",'"+cardnumber+"','"+booking_ref+"','"+event_date+"');");
		
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
	

	public void cancelBooking(Integer user_id, String eventType) {
		// TODO Auto-generated method stub

		ArrayList<Booking> bookingList = viewBookings(eventType);

		List<Integer> lookupDeleteList = bookingList.stream()
		  			.map(Booking::getBooking_id)
		  			.collect(Collectors.toList());
		
		
		System.out.println("Please enter the ID of the booking to delete");
		while(true) {
		try {
		int deleteOption = sc.nextInt();
		
		if(lookupDeleteList.contains(deleteOption)) {
			DBA.dbCancelBooking(deleteOption);
			System.out.println("Booking has been cancelled.");
			break;
		}else {
			System.out.println("Cannot find that Booking ID");
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Invalid input. Please enter a correct ID");
			sc.next();
		}
		}

		UAM.returnMain();
	}

 

	public ArrayList<Booking> viewBookings(String EventType) {
		// TODO Auto-generated method stub
		
	
		sc.useDelimiter("\r?\n");		
		
		
		System.out.println();
		
		List<CustomType> eventList = DBA.dbGetEvents(EventType);
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEvent_id(),eventList.get(i).getEvent_name(),eventList.get(i).getEvent_description());
			
		}
		
	
		System.out.println("Please input the ID for the event you wish to view bookings:");
		
		while(true) {
		
			try {
				
				setEvent_id(sc.nextInt());
				
				try {
				
				List<CustomType> list = DBA.dbGetEventDates(getEvent_id());
				
				
				List<Integer> lookupEventList = DBA.dbGetEvents(EventType).stream()
	  		  			.map(CustomType::getEvent_id)
	  		  			.collect(Collectors.toList());
		
				if(list.get(0).getEvent_date()==null) {
					System.out.println("Sorry no available booking dates currently for this event. Please try again.");
				}else {
				if(lookupEventList.contains(getEvent_id())){			
					System.out.println();
					for(int i = 0; i < list.size(); i++) {
						System.out.println("Option "+i+ " : " + list.get(i).getEvent_name() + "\t" + list.get(i).getEvent_date() + "\t" + list.get(i).getVenue_name() + "\t" + list.get(i).getPrice());	
					}
					break;
				}else{
					System.out.println("Invalid Event ID specified");
					}
				}
				}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Invalid input valid Event ID.");
				sc.next();
			}
			}catch(Exception e){
				System.out.println("Please input a valid Event ID.");
				sc.nextLine();
			}
		}
			
	
			System.out.println();
			
			System.out.println("Please input the option number from the list above for the date and venue to book:");
			
			
			Integer eventId = null;
			String eventName = null;
			String eventDate = null;
			Integer venue_id = null;
			String venueName = null;
			Integer price_id = null;
			Integer price = null;
			Integer userId = null;
			

			while(true) {
				
				try {
					int indexSelection = sc.nextInt();
					
					try {
			
					if(DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_id() != 0) {
						
						eventId = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_id();
						eventName = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_name();
						eventDate = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_date();
						venue_id = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getVenue_id();
						venueName = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getVenue_name();
						price_id = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getPrice_id();
						price = DBA.dbGetEventDates(getEvent_id()).get(indexSelection).getPrice();
						userId = user_id;
						break;	
						
					}else {
						System.out.println("ID is not found");	
					}
					}catch(Exception e) {
						System.out.println("ID is not found");
						
					}
					
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Invalid selection");
					sc.next();
				}

			}
			System.out.println();
			
			System.out.println("Option selected:");
			
			System.out.println(eventName + "\t" + eventDate + "\t" + venueName + "\t" + price);
	
			System.out.println("Bookings:");
			
			System.out.println();
			
		
			ArrayList<Booking> bookingList = dbGetBookingList(eventId,venue_id,eventDate);

			
			if(bookingList.size()==0) {
				System.out.println("No bookings yet!");
				
			}else {
			
			System.out.println(String.format("%-15s %-15s %-70s %-20s %-45s %-20s %-20s %10s", "Booking ID", "Booking Date", "Booking Reference", "Username", "Event Name", "Event Date", "Venue", "No. of Tickets"));
			
 
			for(int i = 0; i < bookingList.size(); i++) {
				System.out.format("%-15s %-15s %-70s %-20s %-45s %-20s %-20s %10s\n", bookingList.get(i).getBooking_id(), bookingList.get(i).getBooking_dte(), bookingList.get(i).getBooking_ref(), bookingList.get(i).getUsername(), bookingList.get(i).getname(), bookingList.get(i).getEvent_date(), bookingList.get(i).getVenue_name(), bookingList.get(i).getNum_of_tickets());
  				
			}
			
			}
		 	
			UAM.returnMain();
			
			return bookingList;
			
	}
	
	private static ArrayList<Booking> dbGetBookingList(Integer eventId, Integer venue_id, String eventDate ){

		ArrayList<Booking> bookingList = new ArrayList<>();
		
		String SQL = ("SELECT a.booking_id, a.booking_date,  a.booking_ref, a.num_of_tickets, a.venue_id, b.venue_name, a.event_date, a.event_id, d.name,  a.user_id, c.username  from bookings a left join venues b on a.venue_id = b.venue_id left join uam c on a.user_id  = c.user_id left join events d on a.event_id = d.event_id where a.event_id="+eventId+" AND a.venue_id="+venue_id+" AND a.event_date='"+eventDate+"';");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	bookingList.clear();
  		  	
  		  	
  		  	
   		  	while(rs.next())
  		  	{
   		  		
   		  		Booking booking = new Booking();
   		  		
   		  		booking.setBooking_id(rs.getInt("booking_id"));
   		  		booking.setBooking_ref(rs.getString("booking_ref"));
   		  		booking.setBooking_dte(rs.getString("booking_date"));
   		  		booking.setUsername(rs.getString("username"));
   		  		booking.setname(rs.getString("name"));
   		  		booking.setEvent_date(rs.getString("event_date"));
   		  		booking.setVenue_name(rs.getString("venue_name"));
   		  		booking.setNum_of_tickets(rs.getInt("num_of_tickets"));
   		  		
   		  		bookingList.add(booking);
   		  		
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }	
		return bookingList;

	}
	
	

	private static Integer numOfTickets(Integer eventId, Integer venue_id, String eventDate) {
		
		Integer remainingTickets = null;
		
		String SQL = ("select b.event_date, b.event_id,b.venue_id, c.capacity, sum(b.num_of_tickets) as ticket_count  from dates a left join bookings b on a.venue_id = b.venue_id and a.event_id = b.event_id and a.event_date = b.event_date left join venues c on a.venue_id = c.venue_id where a.event_id="+eventId+" AND a.venue_id="+venue_id+" AND a.event_date='"+eventDate+"' group by 1,2,3,4;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
   		  	while(rs.next())
  		  	{
   		  		//Incase their has been no bookings then exit.
   		  		try {
   		  			remainingTickets=(rs.getInt("capacity") - rs.getInt("ticket_count"));  		  
   		  		}catch(Exception e) {
   		  			remainingTickets=rs.getInt("capacity");
   		  		}
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return remainingTickets;
		
	}

	private static ArrayList<Booking> dbGetBookingList(Integer user_id){

		ArrayList<Booking> bookingList = new ArrayList<>();
		
		String SQL = ("SELECT a.booking_id, a.booking_date,  a.booking_ref, a.num_of_tickets, a.venue_id, b.venue_name, a.event_date, a.event_id, d.name,  a.user_id, c.username  from bookings a left join venues b on a.venue_id = b.venue_id left join uam c on a.user_id  = c.user_id left join events d on a.event_id = d.event_id where a.user_id='"+user_id+"';");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	bookingList.clear();
  		  	
  		  	
  		  	
   		  	while(rs.next())
  		  	{
   		  		
   		  		Booking booking = new Booking();
   		  		
   		  		booking.setBooking_id(rs.getInt("booking_id"));
   		  		booking.setBooking_ref(rs.getString("booking_ref"));
   		  		booking.setBooking_dte(rs.getString("booking_date"));
   		  		booking.setUsername(rs.getString("username"));
   		  		booking.setname(rs.getString("name"));
   		  		booking.setEvent_date(rs.getString("event_date"));
   		  		booking.setVenue_name(rs.getString("venue_name"));
   		  		booking.setNum_of_tickets(rs.getInt("num_of_tickets"));
   		  		
   		  		bookingList.add(booking);
   		  		
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }	
		return bookingList;

	}
	
	public void viewBookings(Integer user_id, String eventType) {
		
		ArrayList<Booking> mybookings = dbGetBookingList(user_id);

		System.out.println(String.format("%-15s %-15s %-70s %-20s %-35s %-20s %-35s %10s", "Booking ID", "Booking Date", "Booking Reference", "Username", "Event Name", "Event Date", "Venue", "No. of Tickets"));
		 
		for(int i = 0; i < mybookings.size(); i++) {
			System.out.format("%-15s %-15s %-70s %-20s %-35s %-20s %-35s %10s\n", mybookings.get(i).getBooking_id(), mybookings.get(i).getBooking_dte(), mybookings.get(i).getBooking_ref(), mybookings.get(i).getUsername(), mybookings.get(i).getname(), mybookings.get(i).getEvent_date(), mybookings.get(i).getVenue_name(), mybookings.get(i).getNum_of_tickets());
				
		}
		
	}
 	
	
}
