
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/*
 *		Class sequenceUploader
 * 
 *		@desc Reads all the files in <<InputFolder>> and generates a sequence file in HDFS in <<OutputFile>>
 *		@author Vicente Ruben Del Pino Ruiz <<ruben.delpino@gmail.com>>
 *
 */
public class sequenceUploader {
	
	//Input Folder and output file to generate
	String inputFolder;
	String outputFile;
	
	//Cluster configuration 
	Configuration conf;
	FileSystem fs;
	
	//Variables needed for reading the files in the folder
	String currentFile;
	FileReader fileReader;
	BufferedReader bufferedReader;
	String content;
	
	/*
	 * 		public sequenceUploader
	 * 
	 * 		@desc Reads the configuration from the cluster and setup FileSystem
	 * 
	 */	
	public sequenceUploader(){
		
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
	 *		public void generateSequenceFile
	 *
	 *		@param String iFolder. Input folder with all the files to merge in a sequence file
	 *		@param String oFile.   Full path to the file to generate in HDFS
	 *
	 *		@desc Read all the files in the folder and generates a sequence file in HDFS
	 * 	
	 */
	public void generateSequenceFile(String iFolder, String oFile) throws IOException{
		
		try{
			
			//Setup the local variables
			inputFolder = iFolder;
			outputFile = oFile;
			
			
			//Reset content
			content="";
			
			
			//Open the folder and take all the files inside
			File folder = new File(inputFolder);
			File [] listofFiles= folder.listFiles();
			
		
			//Key and value to be written in the file
			Text key = new Text();
			Text value = new Text();
			
			//Open the sequence file
			Path outputPath = new Path(outputFile);
			@SuppressWarnings("deprecation")
			SequenceFile.Writer fileOutput = new SequenceFile.Writer(fs, conf, outputPath, org.apache.hadoop.io.Text.class, org.apache.hadoop.io.Text.class);
			
			
			//Iterate for all the files of the input folder\
			for (File file: listofFiles){
				if (file.isFile()){
					
					//Read the file 
					currentFile=file.getName();
					fileReader =new FileReader(folder.getAbsolutePath()+"/"+currentFile);
					bufferedReader = new BufferedReader(fileReader);
					
					content="";
					
					while (bufferedReader.ready()){
						content+=bufferedReader.readLine();
					}
					
					
					//Write the content of the file to the sequence file
					key =new Text(currentFile);
					value=new Text(content);
					//Append the key value to the file
					fileOutput.append(key,value);
				}
			}
		
		
			//Close the sequence file
			fileOutput.close();		
		}
		catch(Exception IOException){
			System.out.println("Error in the generation of the sequence file: "+ IOException.getMessage());
		}

	
		
		
	}
	
	
	
	

}
