package com.a00326288.project01;

import java.util.Objects;

public class Prices {

	public static Integer priceId;
	public static Integer eventPrice;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
 
		Currency myc = new Currency(0, 0);
		

	}
	
	public Prices(Integer priceId, Integer eventPrice) {

		this.priceId = priceId;
		this.eventPrice = eventPrice;
	}

	
	
	
	public Integer getPriceId() {
		return priceId;
	}



	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}



	public Integer getEventPrice() {
		return eventPrice;
	}



	public void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
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
		Prices other = (Prices) obj;
		return Objects.equals(eventPrice, other.eventPrice) && Objects.equals(priceId, other.priceId);
	}

	@Override
	public String toString() {
		return "Prices [priceId=" + priceId + ", eventPrice=" + eventPrice + "]";
		
	}

	
	

}


class Currency extends Prices{
	public Currency(int i, int j) {
		super(priceId, eventPrice);
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



