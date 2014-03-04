
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class Read {

	/**
	 * @param args
	 */
	public static void main(String [] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> words = new ArrayList<String>();
		WriteHTML aWriteHTML = new WriteHTML();
		
		Configuration conf = new Configuration();
	    conf.addResource(new Path("/hadoop/projects/hadoop-1.0.4/conf/core-site.xml"));
	    conf.addResource(new Path("/hadoop/projects/hadoop-1.0.4/conf/hdfs-site.xml"));
	    
	    FileSystem fs;
	    String filePath = "/user/flume/output/part-00000";
	
	    try {
	    	
		    Path path = new Path(filePath);
		    fs = path.getFileSystem(conf);
		    FSDataInputStream inputStream = fs.open(path);
		    
			Scanner input = new Scanner(inputStream);
			
			while(input.hasNext()) {
			
				String nextToken = input.next();
				StringTokenizer itr = new StringTokenizer(nextToken, ",");
				
				while (itr.hasMoreTokens()) {
					String word = itr.nextToken();
					
					words.add(word);
					System.out.println(word);
				}
			}
			
			fs.delete(new Path("/user/flume/output"), true);
			fs.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    aWriteHTML.writeHTML(words);    
	}
}
