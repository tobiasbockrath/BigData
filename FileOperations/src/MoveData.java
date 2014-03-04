

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class MoveData {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date aDate = new Date( );
		SimpleDateFormat sdt = new SimpleDateFormat ("yyyyMMdd_HHmmss");
		String fileName =  sdt.format(aDate);
		
		Configuration conf = new Configuration();
		conf.addResource(new Path("/home/hadoop/hadoop/conf/core-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/hdfs-site.xml"));
		conf.addResource(new Path("/home/hadoop/hadoop/conf/mapred-site.xml"));
		 
		
		String source1 = "/user/flume/output/part-00000";
		String dest = "/home/cloudera/Desktop/jetty/FilesFromHDFS/" + fileName;
	
		Path srcPath1 = new Path(source1);
		Path dstPath = new Path(dest);
		
		FileSystem fileSystem;
		
		try {
			
			fileSystem = FileSystem.get(conf);
			fileSystem.copyToLocalFile(srcPath1, dstPath);
			fileSystem.delete(new Path("/user/flume/output"), true);
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
	}
}
