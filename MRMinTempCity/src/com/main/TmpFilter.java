package com.main;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class TmpFilter implements PathFilter {

	@Override
	public boolean accept(Path path) {
		// TODO Auto-generated method stub
		if (path.toString().contains(".tmp")){
			
			return false;
		}
		else{
			return true;
		}	
	}
}
