package com.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Weather {

	private String id;
	private String dt;
	private String name;
	private String lon;
	private String lat;
	private String temp;
	private String humidity;
	private String pressure;
	private String temp_min;
	private String temp_max;
	private String speed;
	private String deg;
	private String country;
	private String sunrise;
	private String sunset;

	
	public Weather(){
		
	}
	
	public String convertUnixTimeStamp(String unixTimeStamp){
		
		TimeZone.setDefault(TimeZone.getTimeZone("CET"));
		Date aDate = new Date ();
		aDate.setTime((Long.parseLong(unixTimeStamp))*1000);
		
	    SimpleDateFormat sdt = new SimpleDateFormat ("yyyy.MM.dd_HH:mm:ss");
	    String convertedDate =  sdt.format(aDate);
		
		return convertedDate;
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDt() {
		return dt;
	}


	public void setDt(String dt) {
		this.dt = dt;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getTemp() {
		return temp;
	}


	public void setTemp(String temp) {
		this.temp = temp;
	}


	public String getHumidity() {
		return humidity;
	}


	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}


	public String getPressure() {
		return pressure;
	}


	public void setPressure(String pressure) {
		this.pressure = pressure;
	}


	public String getTemp_min() {
		return temp_min;
	}


	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}


	public String getTemp_max() {
		return temp_max;
	}


	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}


	public String getSpeed() {
		return speed;
	}


	public void setSpeed(String speed) {
		this.speed = speed;
	}


	public String getDeg() {
		return deg;
	}


	public void setDeg(String deg) {
		this.deg = deg;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getSunrise() {
		return sunrise;
	}


	public void setSunrise(String sunrise) {
		this.sunrise = convertUnixTimeStamp(sunrise);
	}


	public String getSunset() {
		return sunset;
	}


	public void setSunset(String sunset) {
		this.sunset = convertUnixTimeStamp(sunset);
	}

}
