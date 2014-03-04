package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {
	
	public void readFile(File inputFile){
		WriteHTML aWriteHTML = new WriteHTML();
		ArrayList<String> words = new ArrayList<String>();
		Scanner input;
		String fileName = inputFile.getName();
		
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
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputFile.delete();
	
		aWriteHTML.writeHTML(fileName, words);
	}

}
