import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GetWeather
{
	private static HttpURLConnection connection; 			// creates a variaible connection with the type of HttPURLConnection
	
	GetWeather (String Location) throws IOException
	{
		
		
		URL url = new URL ("http://api.openweathermap.org/data/2.5/weather?q="+(Location)+"&APPID=26aa1d90a24c98fad4beaac70ddbf274"); 			// this is the url connection with the api key to hit the api and get its contents
		connection = (HttpURLConnection) url.openConnection(); 					// this will hit the connection but not establish it 
		
		connection.setRequestMethod("GET"); 	//connection is established here with GET
		connection.setConnectTimeout(5000); 	// interval of times to wait for connection (wait time for connection to the server) 
		connection.setReadTimeout(5000);		// wait time for data to be avaliable for reading 
		
		
		
		
	}
	
	 String weatherInfo() throws IOException		// this function will send back the temp of the desired location back to bot.java
	{
		
		
		
		
		int status = connection.getResponseCode(); 					// sets the status to the connection 
		BufferedReader reader; 										// declares reader as type BufferedReader
		String line; 												// declares line as a string 
		StringBuffer responseContent = new StringBuffer(); 			// this sets responseContent to string buffered which will make everything to a string in the below code
		
		
		
		
		if(status>299) 																						// checks to make sure the request was accepted then takes the contents and inputs it in a string context
		{
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream())); 				// if request fails while trying to read the input stream then we implememt getErrorStream
			while((line = reader.readLine())!=null)															// this will go line by line until there it no more content left 
			{
				responseContent.append(line); 																// this will get the full response of the requested information 
			}
			reader.close(); 							// this will disconnect the connection or close it 
		}
		else
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 				//when input stream works 
			while((line = reader.readLine())!=null)															// this will go line by line until there it no more content left 
			{
				responseContent.append(line); 																// this will get the full response of the requested information 
			}
			reader.close(); 							// this will disconnect the connection or close it 
		}
		
		
		// this will parse from the weather API documentation 

		JSONObject object = new JSONObject (responseContent.toString()); 		// creates new JSONobject and sets it equal to the contents of the documentation 
		
		double temp = object.getJSONObject("main").getDouble("temp"); 			// declares temp as a double and grabs "main" as a object the gets temp  in main 
		
		String varTemp = temp + ""; 				// makes a temp a string so it can't be returned as a string. 
		
		return varTemp; 
		
		
		
		
		
	}
	
	

}
