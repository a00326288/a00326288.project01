package com.a00326288.project01;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		System.out.println( Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEventName() + "\t" + Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEvent_date() + "\t" + Event.dbGetEventDates(getEvent_id()).get(indexSelection).getVenue_name() + "\t" + Event.dbGetEventDates(getEvent_id()).get(indexSelection).getPrice());
		
		System.out.println();
		
		System.out.println("Confirm your selection (Y/N):");
		
		sc.next();
		
		System.out.println("Available number of tickets for this date at this venue are : ");
		
		//calculate total number of tickets per booking for date at venue and then minus that from total count for the venue.
		
		System.out.println(numOfTickets(Event.dbGetEventDates(getEvent_id()).get(indexSelection).getEventId()));
		
		
		
		System.out.println("Please input the number of tickets you want : ");
		
		//total number cannot exceed the remaining number of tickets.
		
		//setNum_of_tickets(sc.nextInt());
		
		System.out.println("Not enough tickets please try at a later time or try another venue/date : ");
		
		System.out.println("The total cost of your booking is : ");
		
		
		
		
		
		System.out.println("Please input the card number for the booking guarntee : ");
		
		
		
		System.out.println("Thanks your booking ref is : ");
		
		System.out.println();
		
		
		
		
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
	
	private StringBuilder numOfTickets(Integer selection) {
		
		StringBuilder remainingTickets = new StringBuilder();
		
		String SQL = ("select b.event_date, a.event_id,a.venue_id, capacity, sum(a.num_of_tickets) as ticket_count  from bookings a inner join dates b on a.venue_id = b.venue_id and a.event_id = b.event_id inner join venues c on a.venue_id = c.venue_id where a.event_id="+selection+" group by 1,2,3,4;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
   		  	while(rs.next())
  		  	{	
  		  	
			remainingTickets.append("The total number of remaining tickets for ");
			remainingTickets.append(rs.getString("event_date"));
			remainingTickets.append(" is ");
  		  	remainingTickets.append(rs.getInt("capacity") - rs.getInt("ticket_count"));
  		  	remainingTickets.append(".");
	  		
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
