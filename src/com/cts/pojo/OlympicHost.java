package com.cts.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="host")
public class OlympicHost {
	@Id
	private int year;
	private String city;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="hostObject")
	private List<OlympicAthlete>athleteList=new ArrayList<OlympicAthlete>();
	public List<OlympicAthlete> getAthleteList() {
		return athleteList;
	}
	public void setAthleteList(List<OlympicAthlete> athleteList) {
		this.athleteList = athleteList;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OlympicHost other = (OlympicHost) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return year + ", " + city;
	}


	
}
