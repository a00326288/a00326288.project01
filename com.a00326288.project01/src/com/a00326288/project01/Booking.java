package com.a00326288.project01;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Booking {

	private static Scanner sc = new Scanner(System.in);
	private int booking_id;
	private Date booking_dte;
	private int num_of_tickets;
	private int event_id;
	private int venue_id;
	private int user_id;
	private String event_date;
	private String cardNumber;
	
	
	public Booking() {
		// TODO Auto-generated constructor stub

	}
	
	public Booking(int booking_id, Date booking_dte, int num_of_seats) {
		super();
		this.booking_id = booking_id;
		this.booking_dte = booking_dte;
		this.num_of_tickets = num_of_tickets;
		this.event_id = event_id;
		this.venue_id = venue_id;
		this.user_id = user_id;
		this.event_date = event_date;
		this.cardNumber = cardNumber;
			
	}
	
	
	private int getBooking_id() {
		return booking_id;
	}

	private void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	private Date getBooking_dte() {
		return booking_dte;
	}

	private void setBooking_dte(Date booking_dte) {
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

	private int getUser_id() {
		return user_id;
	}

	private void setUser_id(int user_id) {
		this.user_id = user_id;
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


	public static void modifyBooking() {
		// TODO Auto-generated method stub
		
	}

	public void createBooking() {
		// TODO Auto-generated method stub
				
		System.out.println();
		
		Event.dbGetEvents();
		
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input the ID of the event you wish to book:");
		
		setEvent_id(sc.nextInt());
		
		System.out.println();
				
		
		for(int i = 0; i < Event.dbGetEventDates(getEvent_id()).size(); i++) {
			System.out.println("Option "+i+ " : " + Event.dbGetEventDates(getEvent_id()).get(i).getEventName() + "\t" + Event.dbGetEventDates(getEvent_id()).get(i).getEvent_date() + "\t" + Event.dbGetEventDates(getEvent_id()).get(i).getVenue_name() + "\t" + Event.dbGetEventDates(getEvent_id()).get(i).getPrice());	
		}

		System.out.println();
				
		System.out.println("Please input the option number from the list above for the date and venue to book:");
		
		int indexSelection = sc.nextInt();
		
		System.out.println("You have selected:");
		
		System.out.println();
		
		Integer eventId = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEventId();
		String eventName = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEventName();
		String eventDate = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_date();
		Integer venue_id = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getVenue_id();
		String venueName = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getVenue_name();
		Integer price_id = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getPrice_id();
		Integer price = Event.dbGetEventDates(getEvent_id()).get(indexSelection).getPrice();
		Integer userId = User.userlist.get(0).getId();
		
		System.out.println(eventName + "\t" + eventDate + "\t" + venueName + "\t" + price);

		System.out.println();
		
		System.out.println("Confirm your selection (Y/N):");
		
		sc.next();
		
		//calculate total number of tickets per booking for date at venue and then minus that from total count for the venue.
		
		int remainingTickets = numOfTickets(eventId,venue_id,eventDate);
		
		System.out.println("Total remaining number of tickets: " + remainingTickets);
		
		System.out.println("Please input the number of tickets you want : ");
		
		boolean checkRemainder=false;
		
		while(checkRemainder==false) {
		
		setNum_of_tickets(sc.nextInt());		

		if(getNum_of_tickets()>remainingTickets){
			System.out.println("Sorry not enough tickets. Please choose less or another date");
			checkRemainder=false;
		}else{
			System.out.println("The total cost of your booking is : " + (getNum_of_tickets() *  price));	
			checkRemainder=true;
		}
		};

		
		System.out.println("Please input the card number for the booking guarntee : ");
		
		String cardnumber = sc.next();
		
		StringBuilder bookref = new StringBuilder();
		
		bookref.append(LocalDate.now()+"-"+User.userlist.get(0).getUID()+"-"+event_id);
		
		
		System.out.println("Thanks your booking ref is : " + bookref);
		
			
		/*
		System.out.println("Booking Date: " +LocalDate.now());
		System.out.println("Event ID: " + eventId);
		System.out.println("Event Date: " + eventDate);
		System.out.println("Venue ID: " + venue_id);
		System.out.println("Venue Name: " + venueName);
		System.out.println("Price ID: " + price_id);
		System.out.println("Price: " + price);
		System.out.println("User ID: " + userId);
		System.out.println("Number of Tickets: " + getNum_of_tickets());
		System.out.println("Card Number: " + cardnumber);
		
		*/
			
		dbCreateEventBooking(LocalDate.now(),eventId,venue_id,userId,getNum_of_tickets(),cardnumber,bookref,eventDate,price_id);
		
	}
	
	private static void dbCreateEventBooking(LocalDate booking_dte, Integer event_id, Integer venue_id, Integer user_id,Integer num_of_tickets, String cardnumber, StringBuilder booking_ref, String event_date, Integer price_id   ) {
		// TODO Auto-generated method stub
		//booking_date,venue_id, event_id, user_id, num_of_tickets, cardNumber
		
		String SQL = ("INSERT INTO bookings (booking_date,venue_id, event_id, user_id, num_of_tickets, cardNumber, booking_ref,event_date) VALUES ('"+booking_dte+"',"+venue_id+","+event_id+","+event_id+","+num_of_tickets+",'"+cardnumber+"','"+booking_ref+"','"+event_date+"');");
		
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
	

	private void setEventDate(String next) {
		// TODO Auto-generated method stub
		
	}

	public static void cancelBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void viewBooking() {
		// TODO Auto-generated method stub
		
	}
	
	private Integer numOfTickets(Integer eventId, Integer venue_id, String eventDate) {
		
		Integer remainingTickets = null;
		
		String SQL = ("select b.event_date, b.event_id,b.venue_id, c.capacity, sum(b.num_of_tickets) as ticket_count  from dates a left join bookings b on a.venue_id = b.venue_id and a.event_id = b.event_id and a.event_date = b.event_date left join venues c on a.venue_id = c.venue_id where a.event_id="+eventId+" AND a.venue_id="+venue_id+" AND a.event_date='"+eventDate+"' group by 1,2,3,4;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
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

	
	
}
