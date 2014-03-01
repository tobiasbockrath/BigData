package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Copy {

	private static String dest;
	
    
	public static void fromHdfsToLocal () throws IOException {
		
		Delete aDelete = new Delete();
		Date aDate = new Date( );
		SimpleDateFormat sdt = new SimpleDateFormat ("yyyyMMdd_HHmmss");

		String source = "/user/flume/output/part-00000";
		String fileName =  sdt.format(aDate);
		
		String dest = "/home/cloudera/Desktop/jetty/FilesFromHDFS/" + fileName;
		
		
		Configuration conf = new Configuration();
		conf.addResource(new Path("/home/hadoop/hadoop/conf/core-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/hdfs-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/mapred-site.xml"));
		 
		FileSystem fileSystem = FileSystem.get(conf);
		
		Path srcPath = new Path(source);
		Path dstPath = new Path(dest);
		
		String filename = source.substring(source.lastIndexOf('/') + 1, source.length());
		 
		try{
			fileSystem.copyToLocalFile(srcPath, dstPath);
			System.out.println("File " + filename + "copied to " + dest);
			aDelete.deleteFolder();
		//	generateHtml();
		}catch(Exception e){
			System.err.println("Exception caught! :" + e);
			System.exit(1);
		}finally{
			fileSystem.close();
		}
	}
		
	
	public static void generateHtml(){
		
		File file = new File(dest);
		Scanner input;
		try {
			input = new Scanner(file);
			while(input.hasNext()) {
				String nextToken = input.next();
			    
				//einlsen, Html inkl Bild erzeugen und ausgeben
				System.out.println(nextToken);
		}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			fromHdfsToLocal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
