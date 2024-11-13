package com.a00326288.project01;

import java.util.Objects;

public class Venue {

	public Venue() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

	public Venue(Integer venueId, String venueName, String venueAddress, String venueCity) {
		this.venueId = venueId;
		this.venueName = venueName;
		this.venueAddress = venueAddress;
		this.venueCity = venueCity;
	}



	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getVenueCity() {
		return venueCity;
	}

	public void setVenueCity(String venueCity) {
		this.venueCity = venueCity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(venueAddress, venueCity, venueId, venueName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		return Objects.equals(venueAddress, other.venueAddress) && Objects.equals(venueCity, other.venueCity)
				&& Objects.equals(venueId, other.venueId) && Objects.equals(venueName, other.venueName);
	}



	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", venueAddress=" + venueAddress
				+ ", venueCity=" + venueCity + "]";
	}	

	
}
