import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jibble.pircbot.PircBot;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;






	public class bot extends PircBot
		{
		
			public bot()	
			{
					this.setName("reraro32145"); //this is the name the bot will use to join the IRC server
			}

			public void onMessage(String channel, String sender, String login, String hostname, String message)	// this function will send messages to the users with the bot 
			{

				
				if(message.toLowerCase( ).startsWith("define ")) 						// this sets the message all to lower case and makes sures the phrase begins with "define"
					{

		            String searchString = message.substring(7).trim( );					// after the word define it will create a string and that string is the word to be defined 

		           
					try {
						MerWebDictionary obj = new MerWebDictionary(searchString);		// creates new objects so the word can be sent to the Web Dictionary class api 
						
						String getDefinition = obj.getDefinition();						// sets the object to the function get definition 
						
						
						sendMessage(channel, getDefinition); 							// prints the definition to the channel (or the bot it will print the definition retreived from the get defintion method 
																						// from the web dictionary class
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				
				
				
				 										
					
				
				
				if(message.toLowerCase( ).startsWith("weather ")) 						// this sets the message all to lower case and makes sure the phrase begins with "weather"
				{

	            String searchString = message.substring(8).trim( );						// after the word define it will create a string and that string is the word to be defined 
	           
	            
	            try {
					GetWeather classbOject = new GetWeather(searchString);					// creates new object to send location to the class getWeather 
					
					String weatherData = classbOject.weatherInfo(); 					// assigns object to the function WeatherInfo in class getWeather to retrieve the weather from that location 
					
					
					sendMessage(channel, "The temperature for "+ searchString+ " is "+weatherData); 	// this will print the temperature of the desired location 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	           
				
				
			}
			
				
				
					
	}
}
				
			
			
	
	
	
	
	
	
	
	
	

