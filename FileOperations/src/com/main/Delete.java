package com.main;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Delete {
	
	public void deleteFolder() throws IOException {
		
		String file = "/user/flume/output";
		Configuration conf = new Configuration();
		conf.addResource(new Path("/home/hadoop/hadoop/conf/core-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/hdfs-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/mapred-site.xml"));
		 
		FileSystem fileSystem = FileSystem.get(conf);
		 
		fileSystem.delete(new Path(file), true);
		 
		fileSystem.close();
	}
}
