package com.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Copy {
	
	private static Date aDate = new Date( );
	private static SimpleDateFormat sdt = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	private static String fileName =  sdt.format(aDate);
    
	public static void fromHdfsToLocal () throws IOException {
		
		Delete aDelete = new Delete();

		String source = "/user/flume/output/part-00000";
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
			generateHtml(dest);
			
		}catch(Exception e){
			System.err.println("Exception caught! :" + e);
			System.exit(1);
		}finally{
			fileSystem.close();
		}
	}
		
	
	public static void generateHtml(String dest){
		
		Scanner input;
		File inputFile = new File(dest);
		File outputFile = new File("/home/cloudera/Desktop/jetty/webapps/output/" + fileName + ".html");
	
		ArrayList<String> Words = new ArrayList<String>();
		try {
			input = new Scanner(inputFile);
			
			while(input.hasNext()) {
				String nextToken = input.next();
				Words.add(nextToken);
				//einlsen, Html inkl Bild erezeugen und ausgeben
				System.out.println(nextToken);
		}
			
			FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i=0; i<Words.size(); i++){
				bw.write(Words.get(i));
			}
			
			bw.close();
			
			input.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputFile.delete();
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
