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
	private String display;
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
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
		this.display="1";
	}

	public OlympicDataPojo() {
	}
	public String toString()
	{
		return this.year+","+this.city+","+this.sport+","+this.discipline+","+this.athlete+","+this.country+","+this.gender+","+this.event+","+this.medal;
	}

}
