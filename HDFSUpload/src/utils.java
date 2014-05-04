
import java.io.File;
import java.io.IOException;




public class utils {
	
	

	public void moveFiles(String source, String destination){
		
		//File to create
		File fileDest;
		
		//File to move
		String currentFile;
		
		//Open the folder and take all the files inside
		File folder = new File(source);
		File [] listofFiles= folder.listFiles();
		
		//Iterate for all the files of the input folder\
		for (File file: listofFiles){
			if (file.isFile() && file.getName().indexOf("ACK")>=0){
				
				//Take the file name
				currentFile=file.getName();
				
			
				try{
					//Change access in the file
					
					Process p =Runtime.getRuntime().exec("sudo chmod -R 777 "+file.getAbsolutePath());
					
					//Create the file destination
					fileDest = new File(destination+"/"+currentFile);
					
					file.renameTo(fileDest);
					
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				
			
			}
		}
	
		
		
	}
	
	
	public void moveFolder(String source, String destination){
		
		//Folder to create
		File fileDest;
		
		//Folder to move
		String currentDir;
		
		//Open the folder and take all the folders inside
		File folder = new File(source);
		File [] listofFiles= folder.listFiles();
		
		//Iterate for all the files of the input folder\
		for (File file: listofFiles){
			if (file.isDirectory() && file.getName().indexOf("ACK")>=0){
				
				//Take the file name
				currentDir=file.getName();
				
				try{
					//Change access in folder.
					Process p =Runtime.getRuntime().exec("sudo chmod -R 777 "+file.getAbsolutePath());
				
				
					//Create the file destination
					fileDest = new File(destination+"/"+currentDir);
				
					file.renameTo(fileDest);
			
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				
			}
		}
	
		
	}
	
	
}
