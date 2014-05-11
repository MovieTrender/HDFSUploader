
import java.io.File;

import org.apache.commons.io.FileUtils;


/*
 *		Class utils
 * 
 *		@desc utilities for HDFSUploader process
 *
 *		@author Vicente Ruben Del Pino Ruiz <<ruben.delpino@gmail.com>>
 *
 */
public class utils {
	
	
	/*
	 * 		public void moveFiles
	 * 
	 * 		@desc Move files from source to destination
	 * 
	 * 		@param String source. Source folder with all the files to move.
	 * 		@param String destination. Destination folder where move the files.
	 * 
	 */
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
					
					//Rename the file
					file.renameTo(fileDest);
					
					System.out.println("\t Copy file from "+file.getAbsolutePath()+"  to   "+ fileDest.getAbsolutePath());
					
					
				}catch(Exception e){
					System.err.println("Error moving files "+ e.getMessage());
				}
				
			
			}
		}
	
		
		
	}
	
	/*
	 * 		public void moveFolder
	 * 
	 * 		@desc Move folder from source to destination
	 * 
	 * 		@param String source. Source folder to move.
	 * 		@param String destination. Destination folder where move the source folder.
	 * 
	 */
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
				
				
					System.out.println("\t Move folder from "+file.getAbsolutePath()+"  to   "+destination+"/"+file.getName() );
					
					
					//Move the directory and all its content
					fileDest=new File(destination+"/"+file.getName()); 
					FileUtils.copyDirectory(file, fileDest);
					FileUtils.deleteDirectory(file);
			
				}catch (Exception e){
					System.err.println("Error moving the folder: "+e.getMessage());
				}
				
			}
		}
	
		
	}
	
	
}
