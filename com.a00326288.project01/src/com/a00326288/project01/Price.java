package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Price {

	private Integer priceId;
	private Integer eventPrice;
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Price(Integer priceId, Integer eventPrice) {

		this.priceId = priceId;
		this.eventPrice = eventPrice;

	}

	public Integer getPriceId() {
		return priceId;
	}

	private void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getEventPrice() {
		return eventPrice;
	}

	private void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
	}


	public static ArrayList<Price> getPrices() {
	
		ArrayList<Price> priceList = new ArrayList<Price>();
		
		String SQL = ("SELECT * from prices;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	priceList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  
  		  	Price price = new Price(rs.getInt("price_id"), rs.getInt("price"));
  	      
  		  	priceList.add(price);
  	
  
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
		return Objects.hash(eventPrice, priceId);
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
		return Objects.equals(eventPrice, other.eventPrice) && Objects.equals(priceId, other.priceId);
	}

	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", eventPrice=" + eventPrice + "]";
	}
	
	
	public static ArrayList<Price>  viewPrices() {
		
		ArrayList<Price> priceList = getPrices();
		
		System.out.println(String.format("%-10s %20s" , "Price ID","Price"));
		
		for(int i =0; i < priceList.size(); i++) {

			System.out.printf("%-10s %20s\n" , priceList.get(i).getPriceId(),priceList.get(i).getEventPrice());

		}
		UserAccessControl.returnMain();
		return priceList;
		
	}
	
	
	public static void addPrice() {
		
		Integer newPrice = null;
		
		while(true) {
			
			System.out.println("Please enter a new event price:");
			try {
				newPrice = sc.nextInt();
				dbAddPrice(newPrice);
				System.out.println("Price has been added.");
				UserAccessControl.returnMain();
				break;
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid number for a price.");
				sc.next();
				
			}
			
		}

		
	}
	
	public static void modifyPrice() {
		
		ArrayList<Price> priceList = viewPrices();
		
		List<Integer> priceLookup = priceList.stream()
		  			.map(Price::getPriceId)
		  			.collect(Collectors.toList());
		
		
		Integer selectedPrice = null;
		
		while(true) {
			
			try {
			System.out.println("Please specify the Price ID you wish to update.");
		
			selectedPrice = sc.nextInt();
				
			if(priceLookup.contains(selectedPrice)) {
				
				try {
				
				System.out.println("Please specify the updated price: ");
				int updatedPrice = sc.nextInt();
				
				if(updatedPrice <=0) {
					
					System.out.println("Price cannot be less than 0.");
				}else {
					dbUpdatePrice(updatedPrice,selectedPrice);
					System.out.println("Price has been updated");
					UserAccessControl.returnMain();
					break;
				}
				}catch(Exception e){
					System.out.println("Invalid Input. Please specify a correct ID");
					
				}
				
				
			}else {
				
				System.out.print("Price ID specified is not found. Please try again.");
			}
			
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Invalid Input. Please specify a correct ID.");
				sc.next();
			}
			
		}
		
		
	}
	

	public static void deletePrice() {
		// TODO Auto-generated method stub
		
		ArrayList<Price> priceList = viewPrices();
		
		List<Integer> priceLookup = priceList.stream()
				.map(Price::getPriceId)
				.collect(Collectors.toList());
		
		Integer priceSelection =null;
		
		while(true) {
			
			try {
				System.out.println("Please input the ID of the price you wish to delete:");
				
				priceSelection = sc.nextInt();
				
				if(priceLookup.contains(priceSelection)) {
					
					dbDeletePrice(priceSelection);
					System.out.println("Price has been deleted.");
					UserAccessControl.returnMain();
					break;
				}else {
					
					System.out.println("Invalid Price ID input. Please try again.");
				}
				
				
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Invalid Price ID input. Please try again.");
				sc.next();
			}
			
		}
		
		
	}

	private static void dbDeletePrice(Integer priceSelection) {
		// TODO Auto-generated method stub
		String SQL = ("DELETE FROM prices where price_id="+priceSelection+";");
		try {	
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate(SQL);
			 connection.close();
			}catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
			} 	
	}

	private static void dbUpdatePrice(int updatedPrice,int selectedPrice) {
		// TODO Auto-generated method stub
		String SQL = ("UPDATE prices set price="+updatedPrice+" where price_id="+selectedPrice+";");
		try {	
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate(SQL);
			 connection.close();
			}catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
			} 	
	}

	private static void dbAddPrice(Integer newPrice) {
		// TODO Auto-generated method stub
	
		String SQL = ("INSERT into prices (price) values("+newPrice+");");
	try {	
		Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQL);
		 connection.close();
		}catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		} 	
		
		
	}


}