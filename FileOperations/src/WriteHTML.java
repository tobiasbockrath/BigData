

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteHTML {

	public void writeHTML(ArrayList<String> words){
		
		
		Date aDate = new Date( );
		SimpleDateFormat sdt = new SimpleDateFormat ("yyyyMMdd_HHmmss");
		String fileName =  sdt.format(aDate);
		
		
		File outputFile = new File("/home/cloudera/Desktop/html/output/" + fileName +  "_AllTempAllCities.html");
		
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
