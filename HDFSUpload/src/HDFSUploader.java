



public class HDFSUploader {
	
	//Will be used to upload the files and generate the sequence file
	sequenceUploader sUploader;
	fileUploader fUploader;
	

	public static void main(String[] args) {
		if (args.length!=1){
			System.out.println("Usage hdfsUploader Configuration/Configuration.json");
		}
		else
		{
			String configurationFile;
			configurationFile=args[0];
			
			configuration conf = new configuration(configurationFile);
	
			//Move Files to Process
			
			//sUploader = new sequenceUploader();
			//fUploader = new fileUploader();
		
			//Move Files to Loaded
			
			
			
		
			
			
			
			
			
			
			
		}
		

	}

}
