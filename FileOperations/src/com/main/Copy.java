package com.main;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Copy {

	
	public static void fromHdfsToLocal () throws IOException {
		 
		String source = "/user/flume/output/part-00000";
		String dest = "/home/cloudera/Desktop/bde/jetty/filesfromHDFS";
		
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
