
import java.io.File;




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
				
				//Change access in folder.
				file.setReadable(true);
				file.setExecutable(true);
				file.setWritable(true);
				
				//Create the file destination
				fileDest = new File(destination+"/"+currentFile);
				
				file.renameTo(fileDest);
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
				
				//Change access in folder.
				file.setReadable(true);
				file.setExecutable(true);
				file.setWritable(true);
				
				//Create the file destination
				fileDest = new File(destination+"/"+currentDir);
				
				file.renameTo(fileDest);
			}
		}
	
		
	}
	
	
}
