package com.main;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;



public class AllTempCity  extends MapReduceBase implements
Mapper<LongWritable, Text, Text, IntWritable>,
Reducer<Text, IntWritable, Text, IntWritable> {


	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
			
		String line = value.toString();
		StringTokenizer itr = new StringTokenizer(line, ";");
	
		while (itr.hasMoreTokens()) {
			
			word.set(itr.nextToken());
			
			String splitWord[] = word.toString().split(",");
			String datum = splitWord[0];
			String city = splitWord[1];
			String country = splitWord[2]; 
			float temp = Float.parseFloat(splitWord[5]);
					
			output.collect(new Text(city + "," + country + "," + datum + "," ), new IntWritable((int)temp));

		}
		
	}
	
	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> output, Reporter reporter3)
			throws IOException {
		// TODO Auto-generated method stub
		int maxTemp = Integer.MIN_VALUE; 
		
		while (values.hasNext()) {
	
			maxTemp = Math.max(maxTemp, values.next().get());
		}
		
		output.collect(key, new IntWritable(maxTemp));
	}


	
	public void run(String inputPath, String outputPath) throws Exception {
		JobConf conf = new JobConf(AllTempCity.class);
		conf.setJobName("maxtemp");

		// the keys are words (strings)
		conf.setOutputKeyClass(Text.class);
		// the values are counts (ints)
		conf.setOutputValueClass(IntWritable.class);

		conf.setMapperClass(AllTempCity.class);
		conf.setReducerClass(AllTempCity.class);
		
		//temporäre Dateien sollen nicht berücksichtigt werden
		FileInputFormat.setInputPathFilter(conf, TmpFilter.class);
		
		FileInputFormat.addInputPath(conf, new Path(inputPath));
		FileOutputFormat.setOutputPath(conf, new Path(outputPath));

		JobClient.runJob(conf);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new AllTempCity().run(args[0], args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
