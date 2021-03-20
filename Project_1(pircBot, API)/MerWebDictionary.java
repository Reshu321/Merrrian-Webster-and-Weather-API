import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class MerWebDictionary 
{
	private static HttpURLConnection connection; 				// creates a variaible connection with the type of HttPURLConnection
	MerWebDictionary(String word) throws IOException
	{
		String APIKEY = "93a11541-aa97-4dc5-bd31-4b77c2c1d845";  // this is the key to have access to the dictionary API

		URL url = new URL ("https://www.dictionaryapi.com/api/v3/references/sd4/json/"+(word)+"?key="+(APIKEY)); 			// this is the url connection with the api key to hit the api and get its contents
		connection = (HttpURLConnection) url.openConnection(); 						// this will hit the connection but not establish it 
		
		connection.setRequestMethod("GET"); 	//connection is established here with GET
		connection.setConnectTimeout(5000); 	// interval of times to wait for connection (wait time for connection to the server) 
		connection.setReadTimeout(5000);		// wait time for data to be avaliable for reading 
	
		

		
	}
	String getDefinition() throws IOException				//this function will send back the definition of the requested word. 
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
	
		
		
		
		// this will parse from the Dictionary API documentation 
		
		JSONObject obj = new JSONArray(responseContent.toString()).getJSONObject(0); 		// creates new obkect and sets the contents to an array 
		
		
		//JSONObject arr1 = array.getJSONObject(0); 
		
		JSONArray Shortdef = obj.getJSONArray("shortdef"); 		// declares a variable shortdef of type JSONArray 
		
		String def = Shortdef.getString(0); 					// intialized the variable def with the definition of the word
		
		
		return def; 		// returns short def back to bot.java
		
		
		
		
		
		
		
		
		
	}
	


}
