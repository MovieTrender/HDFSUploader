import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class configuration {
	
	JSONParser parser;
	JSONObject jsonObject;
	Object obj;
	
	public configuration(String configurationFile){
		parser = new JSONParser();
		try {
					
			//Open the file and read all the configuration values
			obj = parser.parse(new FileReader(configurationFile));
			 
			jsonObject = (JSONObject) obj;
			 	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
		
		
	}


	public String get(String configurationValue){
		
		String value;
		try{

			value= (String) jsonObject.get(configurationValue);
		
		}catch (Exception e){

			value=null;
			
		}
		
		
		return value;
	 
	}
	
	
	public void printConfigurationValue(String configurationValue){
		String value;
		value = get(configurationValue);
		
		System.out.println(configurationValue+" :  "+value);
	}
	
	
	
	

	

}
