package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class Price {

	private Integer priceId;
	private Integer eventPrice;
	private Integer eventId;
	private Integer venueId;
	private String eventDate;

	
	private static ArrayList<Price> priceList = new ArrayList<Price>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
 
		

	}
	
	public Price(Integer priceId, Integer eventPrice, Integer eventId, Integer venueId, String eventDate) {

		this.priceId = priceId;
		this.eventPrice = eventPrice;
		this.venueId = venueId;
		this.eventId = eventId;
		this.eventDate = eventDate;

	}

	private Integer getPriceId() {
		return priceId;
	}

	private void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	private Integer getEventPrice() {
		return eventPrice;
	}

	private void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
	}

	private Integer getEventId() {
		return eventId;
	}

	private void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	private Integer getVenueId() {
		return venueId;
	}

	private void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	private String getEventDate() {
		return eventDate;
	}

	private void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public static ArrayList<Price> getPrices() {
		
		String SQL = ("SELECT * from prices;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	priceList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  
  		  	Price venue = new Price(rs.getInt("price_id"), rs.getInt("price"), rs.getInt("venue_id"), rs.getInt("event_id"), rs.getString("event_date"));
  	      
  		  	priceList.add(venue);
  	
  
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }	
		return priceList;	
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventDate, eventId, eventPrice, priceId, venueId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(eventDate, other.eventDate) && Objects.equals(eventId, other.eventId)
				&& Objects.equals(eventPrice, other.eventPrice) && Objects.equals(priceId, other.priceId)
				&& Objects.equals(venueId, other.venueId);
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", eventPrice=" + eventPrice + ", eventId=" + eventId + ", venueId="
				+ venueId + ", eventDate=" + eventDate + "]";
	}
	
	
	


	
	

}

/*
class Currency extends Price{
	public Currency(int i, int j) {
		super(priceId,eventPrice,eventId,venueId,eventDate);
		// TODO Auto-generated constructor stub
		this.euro();

	}

	void euro() {
		System.out.println("In euro ");
	}
	
	void gbp() {
		System.out.println("The amount is $" );
		
	}
	
}

*/

