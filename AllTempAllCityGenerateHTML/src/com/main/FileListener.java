package com.main;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;


public class FileListener {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ReadFile aReadFile = new ReadFile();
		String FOLDER = "/home/cloudera/Desktop/jetty/FilesFromHDFS";
		final long pollingInterval = 5 * 1000;
		File folder = new File(FOLDER);
		
		 FileAlterationObserver observer = new FileAlterationObserver(folder);
	     FileAlterationMonitor monitor = new FileAlterationMonitor(pollingInterval);
	     FileAlterationListener listener = new FileAlterationListenerAdaptor() {
	          
	    	 @Override
	            public void onFileCreate(File file) {
	                try {
	                	aReadFile.readFile(file);
	                    System.out.println("File created: "+ file.getCanonicalPath());
	                    
	                } catch (IOException e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	     };
	     
	     observer.addListener(listener);
	     monitor.addObserver(observer);
	     try {
			monitor.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
