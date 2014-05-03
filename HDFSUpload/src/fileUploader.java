

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;



/*
 *		Class fileUploader
 * 
 *		@desc Reads all the files in <<InputFolder>> and uploads them to HDS in <<OutputFolder>>
 *		@author Vicente Ruben Del Pino Ruiz <<ruben.delpino@gmail.com>>
 *
 */
public class fileUploader {
	
	
	
	//Cluster configuration 
	Configuration conf;
	FileSystem fs;
	
	
	//Variables needed for reading the files in the folder
	String currentFile;
	FileReader fileReader;
	BufferedReader bufferedReader;
	String content;
	
	
	//Variables needed for writing files in HDFS
	Path path;
	FSDataOutputStream out;
	
	
	
	
	/*
	 * 		public fileUploader()
	 * 
	 * 		@desc Reads the configuration from the cluster and setup FileSystem
	 * 
	 */
	public fileUploader(){
		
		try{
			//Open configuration for cluster
			conf= new Configuration();
			fs = FileSystem.get(conf);
		}
		catch (Exception IOException){
			System.out.println("Error reading configuration from cluster and setting Filesystem: "+IOException.getMessage());
		}
		
	}
	
	
	/*
	 *		public void fileUploader
	 *
	 *		@param String inputFolder.   Input folder with all the files to upload to HDFS
	 *		@param String outputFolder.  Folder in HDFS where upload the files
	 *
	 *		@desc Read all the files in the folder and upload them to HDFS
	 * 	
	 */
	public fileUploader(String inputFolder, String outputFolder){
		
		//Reset content
		content="";
		
		
		//Open the folder and take all the files inside
		File folder = new File(inputFolder);
		File [] listofFiles= folder.listFiles();
		
		
		//Iterate for all the files of the input folder\
		for (File file: listofFiles){
			if (file.isFile()){
				
				try{
					
					//Read the file 
					currentFile=file.getName();
					fileReader =new FileReader(folder.getAbsolutePath()+"/"+currentFile);
					bufferedReader = new BufferedReader(fileReader);
					
					content="";
					
					while (bufferedReader.ready()){
						content+=bufferedReader.readLine();
					}
					
					
					//Write the content of the file to HDFS
			
					
					// Check if the file already exists
				    path = new Path(outputFolder+"/"+currentFile);
				    if (fs.exists(path)) {
				        System.out.println("File " + currentFile + " already exists");
				        return;
				    }
				
				    // Create a new file and write data to it.
				    out = fs.create(path);
				    out.writeUTF(content);
					
				    //Close the file in HDFS
				    out.close();
					
				    //Close the file in local folder
				    fileReader.close();
					
				}
				catch (Exception FileNotFoundException) {
					System.out.println("File not found: "+ FileNotFoundException.getMessage());
					
				}
			}
		}
	

	}
	
	

}
