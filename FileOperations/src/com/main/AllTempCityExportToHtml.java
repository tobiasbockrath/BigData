package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AllTempCityExportToHtml {

	
	private ArrayList<String> words = new ArrayList<String>();
	
	
	public void readFile(String dest, String fileName){
		
		Scanner input;
		File inputFile = new File(dest);
		
		try {
			
			input = new Scanner(inputFile);
			
			while(input.hasNext()) {
			
				String nextToken = input.next();
				StringTokenizer itr = new StringTokenizer(nextToken, ",");
				
				while (itr.hasMoreTokens()) {
					String word = itr.nextToken();
					
					words.add(word);
					System.out.println(word);
				}
		}
		
		input.close();
		generateHTML(fileName);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputFile.delete();
	}
	
	
	public void generateHTML(String fileName){
		
		
		File outputFile = new File("/home/cloudera/Desktop/jetty/webapps/output/AllTempCity" + fileName + ".html");
		
		try {
			
			outputFile.createNewFile();
		    FileWriter writer = new FileWriter(outputFile); 
			
			writer.write("<html>" + "\n");
			writer.write(System.getProperty("line.separator"));
			writer.write("<body>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<br><br>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<H1>Alle Temperaturen aller St&auml;dte</H1>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<br><br>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<table border= " + '"' + "1" + '"' + ">");
			writer.write(System.getProperty("line.separator"));
			
			//hier noch das Bild??
			
			writer.write("<tr>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Stadt</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Land</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Datum</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<th>Temperatur</th>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</tr>");
			writer.write(System.getProperty("line.separator"));
			
			for (int i = 0; i < words.size(); i++ ){
				
				if (i % 4 == 0) {
			
						writer.write("<tr>");
						writer.write(System.getProperty("line.separator"));
						writer.write("<td>" + words.get(i) + "</td>"); 
						writer.write(System.getProperty("line.separator"));
				   }
				  else {
	
						writer.write("<td>" + words.get(i) + "</td>"); 
						writer.write(System.getProperty("line.separator"));
				  }
			}
	
			writer.write("</table>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</html>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<br>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<br>");
			writer.write(System.getProperty("line.separator"));
			writer.write("<p><img src="+ '"' + "../bild.png" + '"' + " alt=" + '"' + "Landkarte"+ '"' + "></p>");
			writer.write(System.getProperty("line.separator"));
			writer.write("</body>");
		    writer.flush();
		    writer.close();
	    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
