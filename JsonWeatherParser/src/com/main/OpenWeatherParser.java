package com.main;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.flume.Event;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import com.objects.Weather;

public class OpenWeatherParser extends AbstractSource implements EventDrivenSource  {

	private static String [] citys = {"Amsterdam", "Ankara", "Andorra la Vella", "Athen", "Belgrad", "Berlin", "Bern",
										"Bratislava", "Brüssel", "Budapest", "Chișinău", 
										"Dublin, irl", "Helsinki", "Kiew", "Kopenhagen ", "Lissabon", "Ljubljana", "London,uk",
										"Madrid", "Minsk", "Monaco ", "Moskau", "Oslo","Paris,fr",
										"Podgorica", "Prag", "Reykjavík", "Riga ", "Rom", "Sarajevo", "Skopje",
										"Sofia", "Stockholm", "Talinn", "Tirana", "Vaduz", "Valletta", "Vilnius",
										"Warschau", "Wien", "Zagreb"};
	
	private static String weatherJsonObject;
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		//wird stündlich ausgeführt
		while(true){
			
			final ChannelProcessor channel = getChannelProcessor();
			
			//Einheitlicher Zeitpunkt an dem die Daten abgerufen wurden
			Date aDate = new Date( );
		    SimpleDateFormat sdt = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
		    String dt =  sdt.format(aDate);
			
		    //ruft die Daten aller Städte ab
			for (String city: citys){
				
				String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city +"&mode=json&units=metric&lang=de";
				
				final Map<String, String> headers = new HashMap<String, String>();
				Event event = null;
				Weather aWeather = new Weather();
				
				//JSON holen und auswerten
				try {
			
				weatherJsonObject = IOUtils.toString(new URL(url));
				JSONObject obj1 = (JSONObject) JSONValue.parseWithException(weatherJsonObject);
				
					if (!obj1.isEmpty()){
						
						JSONObject obj2 = (JSONObject) obj1.get("coord");	
						JSONObject obj3 = (JSONObject) obj1.get("sys");
						JSONObject obj4 = (JSONObject) obj1.get("main");
						JSONObject obj5 = (JSONObject) obj1.get("wind");						
						
						aWeather.setId(obj1.get("id").toString());
						aWeather.setDt(obj1.get("dt").toString());
						aWeather.setName(obj1.get("name").toString());
						
						//coord
						if (!obj2.isEmpty()){
						
							aWeather.setLon(obj2.get("lon").toString());
							aWeather.setLat(obj2.get("lat").toString());
						}
						
						//sys
						if(!obj3.isEmpty()){
							aWeather.setCountry(obj3.get("country").toString());
							aWeather.setSunrise(obj3.get("sunrise").toString());
							aWeather.setSunset(obj3.get("sunset").toString());	
						}
						
						//main
						if (!obj4.isEmpty()){
							
							aWeather.setTemp(obj4.get("temp").toString());
							aWeather.setHumidity(obj4.get("humidity").toString());
							aWeather.setPressure(obj4.get("pressure").toString());
							aWeather.setTemp_min(obj4.get("temp_min").toString());
							aWeather.setTemp_max(obj4.get("temp_max").toString());
						}
			
						//wind
						if (!obj5.isEmpty()){
							
							aWeather.setSpeed(obj5.get("speed").toString());
							aWeather.setDeg(obj5.get("deg").toString());
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();		
				}	
				
				
				//body zusammenbauen: 	Zeit,Stadt,Land,Sonnaufgang, Sonnenuntergang, aktuelleTemperatur,
				//						maxTemperatur, mainTemperatur, Luftfeuchtigkeit, Luftdruck, Windgeschwindigkeit				
				String body = dt + "," + aWeather.getName() + ","+  aWeather.getCountry() + ","
							+ aWeather.getSunrise() + "," +  aWeather.getSunset() + "," 
							+ aWeather.getTemp() + "," + aWeather.getTemp_max() + ","  + aWeather.getTemp_min() + ","
							+ aWeather.getHumidity() + "," + aWeather.getPressure() + "," +  aWeather.getSpeed() + ";";
				
				//header zusammenbauen
				long now = System.currentTimeMillis();
				headers.put("timestamp", Long.toString(now));
				
				//event generieren und an den Channel übergeben
				event = EventBuilder.withBody(body.getBytes(), headers);
		        channel.processEvent(event);
				
			}
	        
	        try {
				Thread.sleep(3600000); // 1 Stunde schlafen: 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
