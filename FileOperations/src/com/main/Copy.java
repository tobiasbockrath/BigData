package com.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Copy {
    
	public static void fromHdfsToLocal () throws IOException {
		
		Date aDate = new Date( );
		SimpleDateFormat sdt = new SimpleDateFormat ("yyyyMMdd_HHmmss");
		String fileName =  sdt.format(aDate);
		
		Delete aDelete = new Delete();
		AllTempCityExportToHtml aExportToHtml = new AllTempCityExportToHtml();

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
			aExportToHtml.readFile(dest, fileName);
			
		}catch(Exception e){
			System.err.println("Exception caught! :" + e);
			System.exit(1);
		}finally{
			fileSystem.close();
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
