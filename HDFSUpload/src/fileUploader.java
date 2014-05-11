
import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;




/*
 *		Class fileUploader
 * 
 *		@desc Reads all the files in <<InputFolder>> and uploads them to HDS in <<OutputFolder>>
 *
 *		@author Vicente Ruben Del Pino Ruiz <<ruben.delpino@gmail.com>>
 *
 */
public class fileUploader {
	
	
	
	//Cluster configuration 
	Configuration conf;
	FileSystem fs;
	
	
	
	//Variables needed for writing files in HDFS
	Path path;
	FSDataOutputStream out;
	
	
	
	
	/*
	 * 		public fileUploader
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
			System.err.println("Error reading configuration from cluster and setting Filesystem: "+IOException.getMessage());
		}
		
	}
	
	/*
	 *		public void fileClose
	 *
	 *		@desc Free the resources allocates for the process
	 * 	
	 */
	public void close(){
	
		try{
			//close the file system
			fs.close();
		}catch (Exception e){
			System.err.println("Exception closing the file system: "+ e.getMessage());
		}
	}
	
	
	/*
	 *		public void fileUpload
	 *
	 *	    @desc Read all the files in the folder and upload them to HDFS
	 *
	 *		@param String inputFolder.   Input folder with all the files to upload to HDFS
	 *		@param String outputFolder.  Folder in HDFS where upload the files
	 *
	 */
	
	public void fileUpload(String inputFolder, String outputFolder){
		
		//Open the folder and take all the files inside
		File folder = new File(inputFolder);
		File [] listofFiles= folder.listFiles();
		
		
		//Iterate for all the files of the input folder
		for (File file: listofFiles){
			if (file.isFile()){
				

				try{
					
					Path srcPath = new Path(file.getAbsolutePath());
					Path dstPath = new Path(outputFolder);
					
					// Check if the file already exists
					if (!(fs.exists(dstPath))) {
						System.out.println("No such destination " + dstPath);
						return;
					}
	 
					
			
					fs.copyFromLocalFile(srcPath, dstPath);
					System.out.println("\t File " + file.getName() + " copied to " + outputFolder);
					
					
				}catch(Exception e){
					System.err.println("Error in fileUpload :" + e);
				}

			}
			
		}
		
		
	

	}
	
	

}
