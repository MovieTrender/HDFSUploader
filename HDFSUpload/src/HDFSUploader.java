
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;





public class HDFSUploader {
	
	//Will be used to upload the files and generate the sequence file
	static sequenceUploader sUploader;
	fileUploader fUploader;
	

	public static void main(String[] args) {
		if (args.length!=1){
			System.out.println("Usage hdfsUploader Configuration/Configuration.json");
		}
		else
		{
			String configurationFile;
			configurationFile=args[0];
			
			
			//Read configuration
			configuration conf = new configuration(configurationFile);
			
			
			String inputCommonFolder=conf.get("Input Common Folder");
			String inputClassifyFolder=conf.get("Input Classify Folder");
			String processCommonFolder=conf.get("Process Common Folder");
			String processClassifyFolder=conf.get("Process Classify Folder");
			String loadedCommonFolder=conf.get("Loaded Common Folder");
			String loadedClassifyFolder= conf.get("Loaded Classify Folder");
			String outputCommonFolder=conf.get("Output Common Folder");
			String outputClassifyFolder=conf.get("Output Classify Folder");
			
			//Move Files to Process
			utils ut = new utils();
			
			ut.moveFiles(inputCommonFolder, processCommonFolder);
			ut.moveFolder(inputClassifyFolder, processClassifyFolder);
			
			//Upload files to HDS
			//Generate the sequence files
			DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
			Date today = Calendar.getInstance().getTime();
			String tmstampFile = df.format(today);
			String sequenceFile = outputClassifyFolder+"/"+tmstampFile;
			
			
			System.out.println("Generating Sequence File");
			//Generate sequence file
			sUploader = new sequenceUploader();
			sUploader.generateSequeceFileRecursive(inputClassifyFolder,sequenceFile);
			
			System.out.println("Uploading files to HDFS");
			//Upload the files to HDFS
			fileUploader fu = new fileUploader();
			fu.fileUploader(inputCommonFolder,outputCommonFolder);
		
			//Move Files to Loaded
			ut.moveFiles(processCommonFolder,loadedCommonFolder);
			ut.moveFolder(processClassifyFolder,loadedClassifyFolder);
			
			
			
		}
		

	}

}
