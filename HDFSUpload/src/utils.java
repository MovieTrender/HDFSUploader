
import java.io.File;
import org.apache.commons.io.FileUtils;
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
		File fileDest;
		


		
		//Open the folder and take all the folders inside
		File folder = new File(source);
		File [] listofFiles= folder.listFiles();
		
	
		//Iterate for all the files of the input folder\
		for (File file: listofFiles){
			if (file.isDirectory() && file.getName().indexOf("ACK")>=0){
				
				
				try{
					//Change access in folder.
					Runtime.getRuntime().exec("sudo chmod -R 777 "+file.getAbsolutePath());
				
				
					System.out.println("Move folder from "+file.getAbsolutePath()+"  to   "+destination+"/"+file.getName() );
					
					fileDest=new File(destination+"/"+file.getName()); 
					
					FileUtils.copyDirectory(file, fileDest);
				
			
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
				
			}
		}
	
		
	}
	
	
}
