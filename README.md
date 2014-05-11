## Read [Project Documentation](https://github.com/MovieTrender/Documentation "Project Documentation")

## HDFSUploader

Data flow manager for files generated by [TwitterLayer](https://github.com/MovieTrender/TwitterLayer "TwitterLayer")

###Use
Execution:
	
    hadoop jar HDSFUploader.jar Configuration/Configuration.json

###Configuration

Reads configuration parameters from a json file specified in command line (Configuration.json).

Contents of Configuration.json:

{

	"Input Folder": "/Twitter Data",
    
	"Process Folder":"/HDFSUploader/InProcess",
    
	"Loaded Folder":"/HDFSUploader/Processed",
    
	"Output Classify Folder":"/Incoming/TweetsToClassify",
    
	"Output Common Folder":"/Incoming/Tweets"
    
}
	
###Folder Structure

_Local Drive:_

/Root/Twitter Data/Data:

	Folder with common files grouping tweets.

/Root/Twitter Data/Classify:

	Folder with files (file per tweet) to calculate the sentiment.

/Root/InProcess:

	Folder with all the files to be processed by HDFSUploader.

/Root/Processed:

	Historic folder with all the files processed by HDFSUploader.
    
_HDFS:_

/Incoming/TweetsToClassify:
	
    Folder with the sequence files generated by HDFSUploader.

/Incoming/Tweets:

	Folder with common files grouping tweets to be analyzed.
    

###What it does?

1. Moves common files with ACK flag from source folder to In Progress folder.
   
   Moves singles tweet files in ACK folders from source to In progress folder.
   
2. Upload all the common files to HDFS.
   
   Merge single tweet files and generate a sequence file in HDFS.
   
3. Move common files to Loaded folder
   
   Move single tweet files to Loaded folder.
   
   
   
   
   
   





