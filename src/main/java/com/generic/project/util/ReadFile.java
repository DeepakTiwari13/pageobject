package com.generic.project.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.opencsv.CSVReader;

public class ReadFile {

	static Logger logger = Logger.getLogger(ReadFile.class);
	CSVReader csvReader;
	FileReader filereader;
	String file;

	public Object[] readCsvLineByLine(String fName) {
		file = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "TestData" + File.separator + fName+".csv";
		try {
			filereader = new FileReader(file);
			csvReader = new CSVReader(filereader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Object [] nextRecord = null ;
		try {
			while ((nextRecord = csvReader.readNext()) != null) {
				for (Object cell : nextRecord) {
					logger.info(cell.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nextRecord;
	}
}
