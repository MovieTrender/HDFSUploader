
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;




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
					
					Runtime.getRuntime().exec("sudo chmod -R 777 "+file.getAbsolutePath());
					
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
		Path fileDest;
		
		//Source folder to move
		Path fileSource;

		
		//Open the folder and take all the folders inside
		File folder = new File(source);
		File [] listofFiles= folder.listFiles();
		
	
		//Iterate for all the files of the input folder\
		for (File file: listofFiles){
			if (file.isDirectory() && file.getName().indexOf("ACK")>=0){
				
				
				try{
					//Change access in folder.
					Runtime.getRuntime().exec("sudo chmod -R 777 "+file.getAbsolutePath());
				
					fileSource = FileSystems.getDefault().getPath(file.getAbsolutePath());
					fileDest=FileSystems.getDefault().getPath(destination+"/"+file.getName()); 
					
					Files.move(fileSource, fileDest,StandardCopyOption.REPLACE_EXISTING);
				
			
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				
			}
		}
	
		
	}
	
	
}
