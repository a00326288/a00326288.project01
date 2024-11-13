package com.a00326288.project01;

import java.util.Objects;

public class Prices {

	private Integer priceId;
	private Integer eventPrice;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Prices(Integer priceId, Integer eventPrice) {

		this.priceId = priceId;
		this.eventPrice = eventPrice;
	}
	
	public Prices() {
		// TODO Auto-generated constructor stub
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
