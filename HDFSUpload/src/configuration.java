import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class configuration {
	
	//Configuration variables
	String inputFolder;
	String processFolder;
	String loadedFolder;
	String HDFScommonFolder;
	String HDFSclassifyFolder;
	

	

	public configuration(String configurationFile){
		
		//Create the parser to be used
		JSONParser parser = new JSONParser();
		 
		try {
			
			//Open the file and read all the configuration values
			Object obj = parser.parse(new FileReader(configurationFile));
	 
			JSONObject jsonObject = (JSONObject) obj;
	 
			
			
			inputFolder = (String) jsonObject.get("Input Folder");
			processFolder = (String) jsonObject.get("Process Folder");
			loadedFolder = (String) jsonObject.get("Loaded Folder");
			HDFScommonFolder = (String) jsonObject.get("Output Common Folder");
			HDFSclassifyFolder = (String) jsonObject.get("Output Classify Folder"); 
			
			
			System.out.println("Configuration readed: ");
			System.out.println("\t Input Folder: "+inputFolder);
			System.out.println("\t Process Folder: "+processFolder);
			System.out.println("\t Loaded Folder: "+ loadedFolder);
			System.out.println("\t HDFS Common Folder: "+HDFScommonFolder);
			System.out.println("\t HDFS Classify Folder: "+HDFSclassifyFolder);
	 
			
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
	}
	
	
	
	public String getInputFolder(){
		return inputFolder;
		
	}
	
	public String getProcessFolder(){
		return processFolder;
	}
	
	public String getLoadedFolder(){
		return loadedFolder;
	}
	
	public String getHDFSCommonFolder(){
		return HDFScommonFolder;
	}
	
	public String getHDFSclassifyFolder(){
		return HDFSclassifyFolder;
	}
	
	

}
