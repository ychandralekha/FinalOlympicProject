package com.cts.pojo;


public class OlympicDataPojo {
	private int year;
	private String city;
	private String sport;
	private String discipline;
	private String athlete;
	private String country;
	private String gender;
	private String event;
	private String medal;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getAthlete() {
		return athlete;
	}
	public void setAthlete(String athlete) {
		this.athlete = athlete;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getMedal() {
		return medal;
	}
	public void setMedal(String medal) {
		this.medal = medal;
	}
	public OlympicDataPojo(int year, String city, String sport,
			String discipline, String athlete, String country, String gender,
			String event, String medal) {
		this.year = year;
		this.city = city;
		this.sport = sport;
		this.discipline = discipline;
		this.athlete = athlete;
		this.country = country;
		this.gender = gender;
		this.event = event;
		this.medal = medal;
	}

	public String toString()
	{
		return this.year+","+this.city+","+this.sport+","+this.discipline+","+this.athlete+","+this.country+","+this.gender+","+this.event+","+this.medal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OlympicDataPojo other = (OlympicDataPojo) obj;
		if (athlete == null) {
			if (other.athlete != null)
				return false;
		} else if (!athlete.equals(other.athlete))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (discipline == null) {
			if (other.discipline != null)
				return false;
		} else if (!discipline.equals(other.discipline))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (medal == null) {
			if (other.medal != null)
				return false;
		} else if (!medal.equals(other.medal))
			return false;
		if (sport == null) {
			if (other.sport != null)
				return false;
		} else if (!sport.equals(other.sport))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
