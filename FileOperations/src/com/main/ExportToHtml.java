package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ExportToHtml {

	public void generateHtml(String dest, String fileName){
		
		Scanner input;
		File inputFile = new File(dest);
		File outputFile = new File("/home/cloudera/Desktop/jetty/webapps/output/" + fileName + ".html");
		
		try {
			
			outputFile.createNewFile();
		    FileWriter writer = new FileWriter(outputFile); 
			input = new Scanner(inputFile);
			
			writer.write("<html>" + "\n");
			writer.write(System.getProperty("line.separator"));
			writer.write("<body>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<table border= " + "'0'" + ">");
			writer.write(System.getProperty("line.separator"));
			
			//hier noch das Bild??
			
			writer.write("<tr>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Stadt</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Land</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Temperatur</th>");
			writer.write(System.getProperty("line.separator"));
			
			while(input.hasNext()) {
			
				String nextToken = input.next();
				StringTokenizer itr = new StringTokenizer(nextToken, ",");
				

				while (itr.hasMoreTokens()) {
					
					writer.write("<td>" + itr.nextToken() + "</td>"); 
					writer.write(System.getProperty("line.separator"));
				}

		}
			
			writer.write("</tr>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</table>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</html>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</body>");
		    writer.flush();
		    writer.close();
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
}
