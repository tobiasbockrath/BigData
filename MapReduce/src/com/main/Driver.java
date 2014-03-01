package com.main;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String argv[]) {
		// TODO Auto-generated method stub

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		
		
		try {
			pgd.addClass("maxtempcity", MaxTempCity.class, "MapReduce Programm um höchste Temperatur einer Stadt zu ermitteln");
			pgd.addClass("maxtempdate", MaxTempDate.class, "MapReduce Programm um höchste Temperatur insgesamt zu ermitteln");
			pgd.addClass("mintempcity", MinTempCity.class, "MapReduce Programm um niedrigste Temperatur einer Stadt zu ermitteln");
			pgd.addClass("mintempdate", MinTempDate.class, "MapReduce Programm um niedrigste Temperatur insgesamt zu ermitteln");
			pgd.addClass("alltempcity", AllTempCity.class, "MapReduce Programm um alle Temperaturen aller Städte zu ermitteln");
			pgd.driver(argv);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(exitCode);
	}

}
